package com.example.catchi_nichi;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SmellNoteRegisterActivity extends AppCompatActivity {
    //Retrofit
    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(RetrofitAPI.API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    public static final RetrofitAPI apiService = retrofit.create(RetrofitAPI.class);

    String nick;
    ImageView perfumePic;
    TextView perfumeInfo;
    TextView userWrite;
    Bitmap bitmap;
    ArrayList<HashMap<String, String>> searchList ;
    int checkedImageId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smellnote_register);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        //키보드 숨기기
        userWrite = findViewById(R.id.userWrite);
        ConstraintLayout layout = findViewById(R.id.layout);
        layout.setOnClickListener(v -> {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(userWrite.getWindowToken(), 0);
        });

        //수신데이터
        Intent intent = getIntent();
        nick = intent.getStringExtra("nick");
        searchList = (ArrayList<HashMap<String, String>>) intent.getSerializableExtra("searchList");
        checkedImageId = intent.getIntExtra("pickPurfume",999);

        Log.i("selected perfume: ", searchList.get(checkedImageId).get("kr_name"));

        //날짜 설정
        long now = System.currentTimeMillis();
        Date mDate = new Date(now);
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        String getTime = simpleDate.format(mDate);
        TextView dateTime = findViewById(R.id.editTextDate);
        dateTime.setText(getTime);

        //선택 향수로 설정
        perfumePic = findViewById(R.id.imageView);
        perfumeInfo = findViewById(R.id.imageText);

        Thread mThread = new Thread(){
            public void run(){
                try{
                    URL url = new URL(searchList.get(checkedImageId).get("img"));
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setDoInput(true);
                    conn.connect();

                    InputStream is = conn.getInputStream();
                    bitmap = BitmapFactory.decodeStream(is);

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        mThread.start();

        try{
            mThread.join();
            perfumePic.setImageBitmap(bitmap);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        perfumeInfo.setText("\n  " +searchList.get(checkedImageId).get("kr_name") + "\n  " + searchList.get(checkedImageId).get("brand"));
    }


    public void onClick(View v) {

        switch (v.getId()){

            case R.id.cancel_btn:
                Intent intent = new Intent(getApplicationContext(), SmellNoteMainActivity.class);
                intent.putExtra("nick", nick);
                startActivity(intent);
                finish();
                break;

        }

    }

}