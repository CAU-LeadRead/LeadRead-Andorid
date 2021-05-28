package com.example.catchi_nichi;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

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

public class RecommendTodayResultActivity extends AppCompatActivity {

    //Retrofit
    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(RetrofitAPI.API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    public static final RetrofitAPI apiService = retrofit.create(RetrofitAPI.class);

    String nick;
    String category2;
    String category1;

    ArrayList<HashMap<String, String>> recommendList;

    LinearLayout resultView;
    Bitmap bitmap;
    int temp;
    ImageButton perfumeImageBtn;
    LinearLayout group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend_today_result);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        //수신데이터
        Intent intent = getIntent();
        nick = intent.getStringExtra("nick");
        category1 =intent.getStringExtra("category1");
        category2 =intent.getStringExtra("category2");

        resultView = findViewById(R.id.Info);
        resultView.setOrientation(LinearLayout.VERTICAL);

        Search();

    }

    void Search(){


        Call<Post> moodPerfume = apiService.recommendMoodAPI(category1,category2);

        moodPerfume.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                Log.i("mood Search","success");
                recommendList = response.body().getRecommendList();
                Load();

            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Log.i("mood Search","fail");
                t.printStackTrace();
            }
        });

    }

    void Load(){

        for(temp=0;temp<recommendList.size();temp++){

            perfumeImageBtn = new ImageButton(this);
            perfumeImageBtn.setLayoutParams(new LinearLayout.LayoutParams(350,300));
            perfumeImageBtn.setId(temp);
            perfumeImageBtn.setBackgroundColor(Color.parseColor("#FFFFFF"));
            perfumeImageBtn.setScaleType(ImageView.ScaleType.CENTER_INSIDE);

            group = new LinearLayout(this);
            group.setOrientation(LinearLayout.HORIZONTAL);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(5,15,5,15);
            group.setLayoutParams(params);

            Thread mThread = new Thread(){
                public void run(){
                    try{
                        URL url = new URL(recommendList.get(temp).get("img"));
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
            perfumeInfo.setLayoutParams(new LinearLayout.LayoutParams(350,300));
            //perfumeInfo.setTextColor(Color.WHITE);
            perfumeInfo.setText("\n  "+ recommendList.get(temp).get("kr_name") + "\n  " + recommendList.get(temp).get("brand") + "\n  " +"\n  " +  "Likes : " + recommendList.get(temp).get("likes")+ "\n  "+ "리뷰수 : " + recommendList.get(temp).get("countingReview")+ "\n  "+ "평균별점 : " + recommendList.get(temp).get("avgStars")+ "\n");
            //perfumeInfo.setTypeface(Typeface.DEFAULT_BOLD);
            group.setGravity(Gravity.CENTER);


            group.addView(perfumeImageBtn);
            group.addView(perfumeInfo);
            resultView.addView(group);
        }
    }

    public void onClick(View v) {

        switch (v.getId()){

            case R.id.ok_Btn:
                //발신 데이터
                Intent intent = new Intent(getApplicationContext(), RecommendMainActivity.class);
                intent.putExtra("nick",nick);
                startActivity(intent);
                finish();
                break;

            case R.id.again_Btn:
                //발신 데이터
                Intent intent2 = new Intent(getApplicationContext(), RecommendTodayActivity.class);
                intent2.putExtra("nick",nick);
                startActivity(intent2);
                finish();
                break;

        }


    }
}
