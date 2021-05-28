package com.example.catchi_nichi;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
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

public class MyPageActivity extends AppCompatActivity {
    //Retrofit
    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(RetrofitAPI.API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    public static final RetrofitAPI apiService = retrofit.create(RetrofitAPI.class);

    String nick;
    TextView myPageText;
    TextView reviewNotice;
    TextView memberNotice;

    ImageButton imgBtn1,imgBtn2,imgBtn3,imgBtn4,imgBtn5;
    ArrayList<HashMap<String, String>> searchList ;
    Bitmap bitmap1,bitmap2,bitmap3,bitmap4,bitmap5;

    LinearLayout resultView;
    LinearLayout group;
    ImageButton perfumeImageBtn;
    int temp;
    Bitmap bitmap;

    String[] myReviewImgList ;
    String[] myReviewKrNameList ;
    String[] myReviewBrandList ;
    Integer[] myReviewIdList ;
    String[] myReviewEnNameList;
    String[] myReviewKrBrandList;
    Integer[] myReviewLikesList ;
    Integer[] myReviewCountingReviewList ;
    String[] myReviewAvgStarsList;

    Float[] myReviewLongevity;
    Float[] myReviewStar;
    String[] myReviewMood;
    String[] myReviewComment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();


        //수신데이터
        Intent intent = getIntent();
        nick = intent.getStringExtra("nick");

        myPageText = findViewById(R.id.myPageText);
        myPageText.setText(nick+"님의 페이지");

        memberNotice = findViewById(R.id.memNotice);
        reviewNotice = findViewById(R.id.reviewNotice);
        memberNotice.setText(nick+" 님께 추천드리는 향수입니다.");

        //personal recommend
        Call<Post> recommendPersonal = apiService.recommendPersonalAPI(nick);
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


        //Review
        Call<Post> myReview = apiService.myReviewAPI(nick);
        myReview.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                Log.i("perfumeReview", "success");

                myReviewImgList = new String [response.body().getReviewList().size()];
                myReviewKrNameList = new String [response.body().getReviewList().size()];
                myReviewBrandList = new String [response.body().getReviewList().size()];
                myReviewIdList = new Integer [response.body().getReviewList().size()];
                myReviewEnNameList= new String [response.body().getReviewList().size()];
                myReviewKrBrandList= new String [response.body().getReviewList().size()];
                myReviewLikesList = new Integer [response.body().getReviewList().size()];
                myReviewCountingReviewList  = new Integer [response.body().getReviewList().size()];
                myReviewAvgStarsList  = new String [response.body().getReviewList().size()];

                myReviewLongevity = new Float [response.body().getReviewList().size()];
                myReviewStar = new Float [response.body().getReviewList().size()];
                myReviewMood = new String [response.body().getReviewList().size()];
                myReviewComment = new String [response.body().getReviewList().size()];

                reviewNotice.setText(nick+" 님이 작성하신 향수 리뷰는 "+response.body().getReviewList().size()+"개 입니다.");

                for(int i=0;i<response.body().getReviewList().size();i++) {
                    myReviewImgList[i] = response.body().getReviewList().get(i).fragrance.img;
                    myReviewEnNameList[i] = response.body().getReviewList().get(i).fragrance.en_name;
                    myReviewKrBrandList[i]= response.body().getReviewList().get(i).fragrance.kr_brand;
                    myReviewLikesList[i]= response.body().getReviewList().get(i).fragrance.likes;
                    myReviewCountingReviewList[i]= response.body().getReviewList().get(i).fragrance.countingReview;
                    myReviewAvgStarsList[i]= response.body().getReviewList().get(i).fragrance.avgStars;
                    myReviewKrNameList[i] = response.body().getReviewList().get(i).fragrance.kr_name;
                    myReviewBrandList[i] = response.body().getReviewList().get(i).fragrance.brand;
                    myReviewIdList[i] = response.body().getReviewList().get(i).id;

                    myReviewLongevity[i] = response.body().getReviewList().get(i).longevity;
                    myReviewStar[i] = response.body().getReviewList().get(i).stars;
                    myReviewMood[i] = response.body().getReviewList().get(i).mood;
                    myReviewComment[i] = response.body().getReviewList().get(i).comment;
                }

                Log.i("perfumeReview",response.body().getMessage());
                drawReview();
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Log.i("perfumeReview", "fail");
                t.printStackTrace();
            }
        });

    }

    void drawReview(){

        resultView = findViewById(R.id.Info);
        resultView.setOrientation(LinearLayout.VERTICAL);

            for(temp=0;temp<myReviewImgList.length;temp++){

                perfumeImageBtn = new ImageButton(this);
                perfumeImageBtn.setLayoutParams(new LinearLayout.LayoutParams(350,300));
                perfumeImageBtn.setBackgroundColor(Color.parseColor("#FFFFFF"));
                perfumeImageBtn.setScaleType(ImageView.ScaleType.CENTER_INSIDE);

                final int x = temp;

                perfumeImageBtn.setOnClickListener(v -> {

                    Intent intent2 = new Intent(getApplicationContext(), ConfirmReviewActivity.class);
                    intent2.putExtra("nick",nick);

                    //perfume 정보
                    intent2.putExtra("img",myReviewImgList[x]);
                    intent2.putExtra("kr_name",myReviewKrNameList[x]);
                    intent2.putExtra("en_name",myReviewEnNameList[x]);
                    intent2.putExtra("brand",myReviewBrandList[x]);
                    intent2.putExtra("kr_brand",myReviewKrBrandList[x]);
                    intent2.putExtra("likes",myReviewLikesList[x]);
                    intent2.putExtra("countingReview",myReviewCountingReviewList[x]);
                    intent2.putExtra("avgStars",myReviewAvgStarsList[x]);

                    //review 정보
                    intent2.putExtra("review_writer",nick);
                    intent2.putExtra("review_longevity", myReviewLongevity[x]);
                    intent2.putExtra("review_star", myReviewStar[x]);
                    intent2.putExtra("review_mood",myReviewMood[x]);
                    intent2.putExtra("review_comment",myReviewComment[x]);
                    intent2.putExtra("review_id", myReviewIdList[x]);

                    startActivity(intent2);
                    finish();
                });



            Thread mThread = new Thread(){
                public void run(){
                    try{
                        URL url = new URL(myReviewImgList[temp]);
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

            group = new LinearLayout(this);
            group.setOrientation(LinearLayout.HORIZONTAL);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(5,15,5,15);
            group.setLayoutParams(params);


            TextView perfumeInfo = new TextView(this);
            perfumeInfo.setGravity(Gravity.CENTER);
            perfumeInfo.setLayoutParams(new LinearLayout.LayoutParams(350,300));
            perfumeInfo.setText("\n  " + myReviewKrNameList[temp] + "\n  " + myReviewBrandList[temp] +"\n"+"\n"+ "별점: "+myReviewStar[temp] );
            perfumeInfo.setTypeface(Typeface.DEFAULT_BOLD);
            group.setGravity(Gravity.CENTER);


            group.addView(perfumeImageBtn);
            group.addView(perfumeInfo);
            resultView.addView(group);
        }
    }

    void Load(){

        imgBtn1 = findViewById(R.id.imgBtn1);
        imgBtn2 = findViewById(R.id.imgBtn2);
        imgBtn3 = findViewById(R.id.imgBtn3);
        imgBtn4 = findViewById(R.id.imgBtn4);
        imgBtn5 = findViewById(R.id.imgBtn5);

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
                intent2.putExtra("nick",nick);
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
                intent2.putExtra("nick",nick);
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
                intent2.putExtra("nick",nick);
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
                intent2.putExtra("nick",nick);
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
                intent2.putExtra("nick",nick);
                startActivity(intent2);
                finish();
            });

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void onClick(View v) {

        switch (v.getId()){

            case R.id.camera_search:
                Intent intent2 = new Intent(getApplicationContext(), CameraSearchActivity.class);
                intent2.putExtra("nick",nick);
                startActivity(intent2);
                finish();
                break;

            case R.id.smellnote_btn:
                Intent intent3 = new Intent(getApplicationContext(), SmellNoteMainActivity.class);
                intent3.putExtra("nick",nick);
                startActivity(intent3);
                finish();
                break;

            case R.id. home_btn:
                Intent intent4 = new Intent(getApplicationContext(), MainActivity.class);
                intent4.putExtra("nick",nick);
                startActivity(intent4);
                finish();
                break;

            case R.id. recommend_btn:
                Intent intent5 = new Intent(getApplicationContext(), RecommendMainActivity.class);
                intent5.putExtra("nick",nick);
                startActivity(intent5);
                finish();
                break;

            case R.id.mypage_btn:
                Intent intent7 = new Intent(getApplicationContext(), MyPageActivity.class);
                intent7.putExtra("nick", nick);
                startActivity(intent7);
                finish();
                break;

            case R.id.logout:
                Intent intent8 = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent8);
                finish();
                break;

        }
    }
}
