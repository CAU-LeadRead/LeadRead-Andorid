package com.example.catchi_nichi;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SmellNoteModifyActivity extends AppCompatActivity {
    //Retrofit
    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(RetrofitAPI.API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    public static final RetrofitAPI apiService = retrofit.create(RetrofitAPI.class);

    String nick;
    String img;
    String brand;
    String name;
    String date;
    String comment;
    Integer id;

    ImageView perfumePic;
    TextView perfumeInfo;
    EditText userWrite;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smellnote_modify);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        //키보드 숨기기
        ConstraintLayout layout = findViewById(R.id.layout);
        layout.setOnClickListener(v -> {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(userWrite.getWindowToken(), 0);
        });

        //수신데이터
        Intent intent = getIntent();
        nick = intent.getStringExtra("nick");
        brand = intent.getStringExtra("brand");
        img = intent.getStringExtra("img");
        name = intent.getStringExtra("name");
        date = intent.getStringExtra("date");
        comment = intent.getStringExtra("comment");
        id = intent.getIntExtra("id",0);

        perfumePic = findViewById(R.id.imageView);
        perfumeInfo = findViewById(R.id.imageText);
        userWrite = findViewById(R.id.userWrite);
        userWrite.setText(comment);
        TextView dateTime = findViewById(R.id.editTextDate);
        dateTime.setText(date);

        Thread mThread = new Thread(){
            public void run(){
                try{
                    URL url = new URL(img);
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
        perfumeInfo.setText("\n  " +name + "\n  " + brand);
    }

    public void onClick(View v) {
        switch (v.getId()){

            case R.id.delete_Btn:
                Call<Post> search = apiService.deleteMemoAPI(id);
                search.enqueue(new Callback<Post>() {
                    @Override
                    public void onResponse(Call<Post> call, Response<Post> response) {
                        Log.i("deleteSmellNote","success");
                        Intent intent = new Intent(getApplicationContext(), SmellNoteMainActivity.class);
                        intent.putExtra("nick", nick);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onFailure(Call<Post> call, Throwable t) {
                        Log.i("deleteSmellNote","fail");
                        t.printStackTrace();
                    }
                });
                break;

            case R.id.ok_btn:

                HashMap<String,Object> updateComment = new HashMap<>();
                updateComment.put("comment",userWrite.getText());
                Call<Post> update = apiService.updateMemoAPI(id,updateComment);
                update.enqueue(new Callback<Post>() {
                    @Override
                    public void onResponse(Call<Post> call, Response<Post> response) {
                        Log.i("updatedSmellNote","success");
                        Log.i("updatedSmellNote",response.body().getMessage());
                        Intent intent2 = new Intent(getApplicationContext(), SmellNoteConfineActivity.class);
                        intent2.putExtra("nick", nick);
                        intent2.putExtra("brand",brand);
                        intent2.putExtra("img",img);
                        intent2.putExtra("name",name);
                        intent2.putExtra("date",date);
                        intent2.putExtra("comment",userWrite.getText()+"");
                        intent2.putExtra("id",id);

                        startActivity(intent2);
                        finish();
                    }

                    @Override
                    public void onFailure(Call<Post> call, Throwable t) {
                        Log.i("updateSmellNote","fail");
                        t.printStackTrace();
                    }
                });
                break;

            case R.id.cancel_Btn:
                Intent intent3 = new Intent(getApplicationContext(), SmellNoteMainActivity.class);
                intent3.putExtra("nick", nick);
                startActivity(intent3);
                finish();
                break;






        }
    }
}
