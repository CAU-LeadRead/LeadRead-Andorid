package com.example.catchi_nichi;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

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
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class MainActivity extends AppCompatActivity {

    //Retrofit
    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(RetrofitAPI.API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static final RetrofitAPI apiService = retrofit.create(RetrofitAPI.class);

    String nickName;
    TextView memberNotice;

    ImageButton imgBtn1,imgBtn2,imgBtn3,imgBtn4,imgBtn5;
    ArrayList<HashMap<String, String>> searchList ;
    Bitmap bitmap1,bitmap2,bitmap3,bitmap4,bitmap5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        //수신데이터
        Intent intent = getIntent();
        nickName = intent.getStringExtra("nick");
        memberNotice = findViewById(R.id.memNotice);
        memberNotice.setText(nickName+" 님께 추천드리는 향수입니다.");

        imgBtn1 = findViewById(R.id.imgBtn1);
        imgBtn2 = findViewById(R.id.imgBtn2);
        imgBtn3 = findViewById(R.id.imgBtn3);
        imgBtn4 = findViewById(R.id.imgBtn4);
        imgBtn5 = findViewById(R.id.imgBtn5);

        searchList = (ArrayList<HashMap<String, String>>) intent.getSerializableExtra("searchList");

        Call<Post> recommendPersonal = apiService.recommendPersonalAPI(nickName);
        recommendPersonal.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                Log.i("recommendPersonal","success");
                searchList = response.body().getSearchList();

                Load();
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Log.i("recommendPersonal","fail");
                t.printStackTrace();
            }
        });

    }

    void Load(){

        Thread mThread = new Thread(){
            public void run(){
                try{

                    URL url1 = new URL(searchList.get(0).get("img"));
                    URL url2 = new URL(searchList.get(1).get("img"));
                    URL url3 = new URL(searchList.get(2).get("img"));
                    URL url4 = new URL(searchList.get(3).get("img"));
                    URL url5 = new URL(searchList.get(4).get("img"));

                    HttpURLConnection conn1 = (HttpURLConnection) url1.openConnection();
                    HttpURLConnection conn2 = (HttpURLConnection) url2.openConnection();
                    HttpURLConnection conn3 = (HttpURLConnection) url3.openConnection();
                    HttpURLConnection conn4 = (HttpURLConnection) url4.openConnection();
                    HttpURLConnection conn5 = (HttpURLConnection) url5.openConnection();
                    conn1.setDoInput(true);
                    conn1.connect();
                    conn2.setDoInput(true);
                    conn2.connect();
                    conn3.setDoInput(true);
                    conn3.connect();
                    conn4.setDoInput(true);
                    conn4.connect();
                    conn5.setDoInput(true);
                    conn5.connect();

                    InputStream is1 = conn1.getInputStream();
                    bitmap1 = BitmapFactory.decodeStream(is1);
                    InputStream is2 = conn2.getInputStream();
                    bitmap2 = BitmapFactory.decodeStream(is2);
                    InputStream is3 = conn3.getInputStream();
                    bitmap3 = BitmapFactory.decodeStream(is3);
                    InputStream is4 = conn4.getInputStream();
                    bitmap4 = BitmapFactory.decodeStream(is4);
                    InputStream is5 = conn5.getInputStream();
                    bitmap5 = BitmapFactory.decodeStream(is5);



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
            imgBtn1.setImageBitmap(bitmap1);
            imgBtn2.setImageBitmap(bitmap2);
            imgBtn3.setImageBitmap(bitmap3);
            imgBtn4.setImageBitmap(bitmap4);
            imgBtn5.setImageBitmap(bitmap5);

            imgBtn1.setOnClickListener(v -> {
                Intent intent2 = new Intent(getApplicationContext(), PerfumeDataActivity.class);
                intent2.putExtra("img",searchList.get(0).get("img"));
                intent2.putExtra("kr_name",searchList.get(0).get("kr_name"));
                intent2.putExtra("en_name",searchList.get(0).get("en_name"));
                intent2.putExtra("brand",searchList.get(0).get("brand"));
                intent2.putExtra("kr_brand",searchList.get(0).get("kr_brand"));
                intent2.putExtra("likes",searchList.get(0).get("likes"));
                intent2.putExtra("countingReview",searchList.get(0).get("countingReview"));
                intent2.putExtra("avgStars",searchList.get(0).get("avgStars"));
                intent2.putExtra("nick",nickName);

                //화면전환
                intent2.putExtra("Activity","main");
                intent2.putExtra("searchList",searchList);
                startActivity(intent2);
                finish();
            });

            imgBtn2.setOnClickListener(v -> {
                Intent intent2 = new Intent(getApplicationContext(), PerfumeDataActivity.class);
                intent2.putExtra("img",searchList.get(1).get("img"));
                intent2.putExtra("kr_name",searchList.get(1).get("kr_name"));
                intent2.putExtra("en_name",searchList.get(1).get("en_name"));
                intent2.putExtra("brand",searchList.get(1).get("brand"));
                intent2.putExtra("kr_brand",searchList.get(1).get("kr_brand"));
                intent2.putExtra("likes",searchList.get(1).get("likes"));
                intent2.putExtra("countingReview",searchList.get(1).get("countingReview"));
                intent2.putExtra("avgStars",searchList.get(1).get("avgStars"));
                intent2.putExtra("nick",nickName);

                //화면전환
                intent2.putExtra("Activity","main");
                intent2.putExtra("searchList",searchList);
                startActivity(intent2);
                finish();
            });

            imgBtn3.setOnClickListener(v -> {
                Intent intent2 = new Intent(getApplicationContext(), PerfumeDataActivity.class);
                intent2.putExtra("img",searchList.get(2).get("img"));
               intent2.putExtra("kr_name",searchList.get(2).get("kr_name"));
                intent2.putExtra("en_name",searchList.get(2).get("en_name"));
               intent2.putExtra("brand",searchList.get(2).get("brand"));
               intent2.putExtra("kr_brand",searchList.get(2).get("kr_brand"));
               intent2.putExtra("likes",searchList.get(2).get("likes"));
               intent2.putExtra("countingReview",searchList.get(2).get("countingReview"));
               intent2.putExtra("avgStars",searchList.get(2).get("avgStars"));
               intent2.putExtra("nick",nickName);

                //화면전환
                intent2.putExtra("Activity","main");
                intent2.putExtra("searchList",searchList);
               startActivity(intent2);
               finish();
            });

            imgBtn4.setOnClickListener(v -> {
                Intent intent2 = new Intent(getApplicationContext(), PerfumeDataActivity.class);
                intent2.putExtra("img",searchList.get(3).get("img"));
                intent2.putExtra("kr_name",searchList.get(3).get("kr_name"));
                intent2.putExtra("en_name",searchList.get(3).get("en_name"));
                intent2.putExtra("brand",searchList.get(3).get("brand"));
                intent2.putExtra("kr_brand",searchList.get(3).get("kr_brand"));
                intent2.putExtra("likes",searchList.get(3).get("likes"));
                intent2.putExtra("countingReview",searchList.get(3).get("countingReview"));
                intent2.putExtra("avgStars",searchList.get(3).get("avgStars"));
                intent2.putExtra("nick",nickName);

                //화면전환
                intent2.putExtra("Activity","main");
                intent2.putExtra("searchList",searchList);
                startActivity(intent2);
                finish();
            });

            imgBtn5.setOnClickListener(v -> {
                Intent intent2 = new Intent(getApplicationContext(), PerfumeDataActivity.class);
                intent2.putExtra("img",searchList.get(4).get("img"));
                intent2.putExtra("kr_name",searchList.get(4).get("kr_name"));
                intent2.putExtra("en_name",searchList.get(4).get("en_name"));
                intent2.putExtra("brand",searchList.get(4).get("brand"));
                intent2.putExtra("kr_brand",searchList.get(4).get("kr_brand"));
                intent2.putExtra("likes",searchList.get(4).get("likes"));
                intent2.putExtra("countingReview",searchList.get(4).get("countingReview"));
                intent2.putExtra("avgStars",searchList.get(4).get("avgStars"));
                intent2.putExtra("nick",nickName);

                //화면전환
                intent2.putExtra("Activity","main");
                intent2.putExtra("searchList",searchList);
                startActivity(intent2);
                finish();
            });

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    //buttonListener 구현
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.searchButton:
                Intent intent = new Intent(getApplicationContext(), mainSearchActivity.class);
                intent.putExtra("nick",nickName);
                startActivity(intent);
                finish();
                break;

            case R.id.camera_search:
                Intent intent2 = new Intent(getApplicationContext(), CameraSearchActivity.class);
                intent2.putExtra("nick",nickName);
                startActivity(intent2);
                finish();
                break;

            case R.id.smellnote_btn:
                Intent intent3 = new Intent(getApplicationContext(), SmellNoteMainActivity.class);
                intent3.putExtra("nick",nickName);
                startActivity(intent3);
                finish();
                break;

            case R.id. home_btn:
                Intent intent4 = new Intent(getApplicationContext(), MainActivity.class);
                intent4.putExtra("nick",nickName);
                startActivity(intent4);
                finish();
                break;

            case R.id. recommend_btn:
                Intent intent5 = new Intent(getApplicationContext(), RecommendMainActivity.class);
                intent5.putExtra("nick",nickName);
                startActivity(intent5);
                finish();
                break;

            case R.id.todayBtn:
                Intent intent6 = new Intent(getApplicationContext(), RecommendTodayActivity.class);
                intent6.putExtra("nick",nickName);
                startActivity(intent6);
                finish();
                break;

            case R.id.similarBtn:
                Intent intent7 = new Intent(getApplicationContext(), RecommendSimilarActivity.class);
                intent7.putExtra("nick",nickName);
                startActivity(intent7);
                finish();
                break;

            case R.id.mypage_btn:
                Intent intent8 = new Intent(getApplicationContext(), MyPageActivity.class);
                intent8.putExtra("nick", nickName);
                startActivity(intent8);
                finish();
                break;


        }
    }
}