package com.example.catchi_nichi;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RecommendSimilarResultActivity extends AppCompatActivity {
    //Retrofit
    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(RetrofitAPI.API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    public static final RetrofitAPI apiService = retrofit.create(RetrofitAPI.class);


    ArrayList<HashMap<String, String>> searchList ;

    String nick;
    LinearLayout resultView;
    Bitmap bitmap;
    int temp;
    ImageButton perfumeImageBtn;
    LinearLayout group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend_similar_result);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        //수신데이터
        Intent intent = getIntent();
        nick = intent.getStringExtra("nick");
        searchList = (ArrayList<HashMap<String, String>>) intent.getSerializableExtra("searchList");

        resultView = findViewById(R.id.Info);
        resultView.setOrientation(LinearLayout.VERTICAL);

        TextView resultText = findViewById(R.id.resultView);
        resultText.setTypeface(Typeface.DEFAULT_BOLD);
        resultText.setText(searchList.size()+" 개의 향수가 검색되었습니다.");

        for(temp=0;temp<searchList.size();temp++){

            perfumeImageBtn = new ImageButton(this);
            perfumeImageBtn.setLayoutParams(new LinearLayout.LayoutParams(600,500));
            perfumeImageBtn.setId(temp);
            perfumeImageBtn.setBackgroundColor(Color.parseColor("#FFFFFF"));


            final int x = temp;
            perfumeImageBtn.setOnClickListener(v -> {
                Intent intent2 = new Intent(getApplicationContext(), PerfumeDataActivity.class);
                intent2.putExtra("img",searchList.get(x).get("img"));
                intent2.putExtra("kr_brand",searchList.get(x).get("kr_brand"));
                intent2.putExtra("kr_name",searchList.get(x).get("kr_name"));
                intent2.putExtra("en_name",searchList.get(x).get("en_name"));
                intent2.putExtra("brand",searchList.get(x).get("brand"));
                intent2.putExtra("likes",searchList.get(x).get("likes"));
                intent2.putExtra("countingReview",searchList.get(x).get("countingReview"));
                intent2.putExtra("avgStars",searchList.get(x).get("avgStars"));
                intent2.putExtra("nick",nick);
                startActivity(intent2);
                finish();
            });

            group = new LinearLayout(this);
            group.setOrientation(LinearLayout.HORIZONTAL);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(5,15,5,15);
            group.setLayoutParams(params);

            Thread mThread = new Thread(){
                public void run(){
                    try{
                        URL url = new URL(searchList.get(temp).get("img"));
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
                perfumeImageBtn.setImageBitmap(bitmap);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            TextView perfumeInfo = new TextView(this);
            perfumeInfo.setGravity(Gravity.CENTER);
            perfumeInfo.setLayoutParams(new LinearLayout.LayoutParams(600,500));
            perfumeInfo.setTextColor(Color.WHITE);
            perfumeInfo.setText("\n  "+ searchList.get(temp).get("en_name") + "\n  " + searchList.get(temp).get("brand") + "\n  " );
            //perfumeInfo.setTypeface(Typeface.DEFAULT_BOLD);
            group.setGravity(Gravity.CENTER);


            group.addView(perfumeImageBtn);
            group.addView(perfumeInfo);
            resultView.addView(group);
        }

        Log.i("searchResult", String.valueOf(searchList));

        }





    public void onClick(View v) {

        switch (v.getId()){

            case R.id.search_Btn:
                //발신 데이터
                Intent intent = new Intent(getApplicationContext(), RecommendSimilarActivity.class);
                intent.putExtra("nick",nick);
                startActivity(intent);
                finish();
                break;

            case R.id.ok_Btn:
                //발신 데이터
                Intent intent2 = new Intent(getApplicationContext(), RecommendMainActivity.class);
                intent2.putExtra("nick",nick);
                startActivity(intent2);
                finish();
                break;

        }
    }



}

