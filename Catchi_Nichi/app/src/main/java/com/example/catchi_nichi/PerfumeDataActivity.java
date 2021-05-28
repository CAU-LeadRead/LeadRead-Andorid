package com.example.catchi_nichi;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

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

public class PerfumeDataActivity extends AppCompatActivity {
    //Retrofit
    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(RetrofitAPI.API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    public static final RetrofitAPI apiService = retrofit.create(RetrofitAPI.class);


    String nick;
    String img;
    String kr_name;
    String en_name;
    String likes;
    String countingReview;
    String avgStars;
    String brand;
    String kr_brand;
    Bitmap bitmap;
    ImageView perfumeImg;
    TextView perfumeText;
    TextView perfumeInfo;
    LinearLayout resultView;
    LinearLayout group;
    ImageButton perfumeImageBtn;
    int temp;

    RatingBar avgStar;

    ArrayList topNote;
    ArrayList middleNote;
    ArrayList bottleNote;

    ArrayList<HashMap<String, Object>> reviewList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfumedetail);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        //수신데이터
        Intent intent = getIntent();
        nick = intent.getStringExtra("nick");
        img = intent.getStringExtra("img");
        kr_name = intent.getStringExtra("kr_name");
        en_name = intent.getStringExtra("en_name");
        brand = intent.getStringExtra("brand");
        kr_brand = intent.getStringExtra("kr_brand");
        likes = intent.getStringExtra("likes");
        countingReview = intent.getStringExtra("countingReview");
        avgStars = intent.getStringExtra("avgStars");

        perfumeImg = findViewById(R.id.perfumeImg);
        perfumeText = findViewById(R.id.perfumeText);
        perfumeInfo = findViewById(R.id.perfumeInfo);
        avgStar = findViewById(R.id.avgStar);

        avgStar.setRating(Float.parseFloat(avgStars));
        avgStar.setIsIndicator(true);

        reviewList = new ArrayList<>();

        Call<Post> perfumeNote = apiService.perfumeNoteAPI(brand, en_name);
        perfumeNote.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                Log.i("perfumeNote", response.body().getMessage());
                Log.i("perfumeNote", "success");
                topNote = response.body().getTop();
                middleNote = response.body().getMid();
                bottleNote = response.body().getBot();
                Load();
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Log.i("perfumeNote", "fail");
                t.printStackTrace();
            }
        });}

        void Load() {

            //Info
            Thread mThread = new Thread() {
                public void run() {
                    try {
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

            try {
                mThread.join();
                perfumeImg.setImageBitmap(bitmap);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            perfumeText.setText("\n  " + kr_name + "\n  " + brand);

            perfumeInfo.setText("Top : ");
            for (int i = 0; i < topNote.size(); i++) {
                perfumeInfo.setText(perfumeInfo.getText() + topNote.get(i).toString() +"\n");
            }
            perfumeInfo.setText(perfumeInfo.getText() + "\nMiddle : ");
            for (int i = 0; i < middleNote.size(); i++) {
                perfumeInfo.setText(perfumeInfo.getText() + middleNote.get(i).toString()+"\n");
            }
            perfumeInfo.setText(perfumeInfo.getText() + "\nBottle : ");
            for (int i = 0; i < bottleNote.size(); i++) {
                perfumeInfo.setText(perfumeInfo.getText() + bottleNote.get(i).toString()+"\n");
            }


            //Review
            Call<Post> perfumeReview = apiService.perfumeReviewAPI(kr_brand, kr_name);
            perfumeReview.enqueue(new Callback<Post>() {
                @Override
                public void onResponse(Call<Post> call, Response<Post> response) {
                    Log.i("perfumeReview", "success");
                    reviewList = response.body().getReview();
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

        for(temp=0;temp<reviewList.size();temp++){

            perfumeImageBtn = new ImageButton(this);
            perfumeImageBtn.setLayoutParams(new LinearLayout.LayoutParams(300,150));
            perfumeImageBtn.setBackgroundColor(Color.parseColor("#FFFFFF"));
            perfumeImageBtn.setImageResource(R.drawable.user_image);
            perfumeImageBtn.setScaleType(ImageView.ScaleType.FIT_CENTER);

            final int x = temp;

            perfumeImageBtn.setOnClickListener(v -> {

                Intent intent2 = new Intent(getApplicationContext(), ConfirmReviewActivity.class);
                intent2.putExtra("nick",nick);

                //perfume 정보
                intent2.putExtra("img",img);
                intent2.putExtra("kr_name",kr_name);
                intent2.putExtra("en_name",en_name);
                intent2.putExtra("brand",brand);
                intent2.putExtra("kr_brand",kr_brand);
                intent2.putExtra("likes",likes);
                intent2.putExtra("countingReview",countingReview);
                intent2.putExtra("avgStars",avgStars);

                //review 정보
                intent2.putExtra("review_writer",(String) reviewList.get(x).get("UserNick"));
                intent2.putExtra("review_longevity", ((Double) reviewList.get(x).get("longevity")).floatValue());
                intent2.putExtra("review_star", ((Double) reviewList.get(x).get("stars")).floatValue());
                intent2.putExtra("review_mood",(String) reviewList.get(x).get("mood"));
                intent2.putExtra("review_comment",(String)reviewList.get(x).get("comment"));
                intent2.putExtra("review_id",((Double) reviewList.get(x).get("id")).floatValue());

                startActivity(intent2);
                finish();
            });

            group = new LinearLayout(this);
            group.setOrientation(LinearLayout.HORIZONTAL);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(5,15,5,15);
            group.setLayoutParams(params);


            TextView perfumeInfo = new TextView(this);
            perfumeInfo.setGravity(Gravity.CENTER);
            perfumeInfo.setLayoutParams(new LinearLayout.LayoutParams(300,150));
            perfumeInfo.setText("닉네임: "+reviewList.get(temp).get("UserNick") +"\n 별점: "+reviewList.get(temp).get("stars") +"\n 지속성: "+reviewList.get(temp).get("longevity") +"\n 분위기: "+reviewList.get(temp).get("mood") );
            //perfumeInfo.setTypeface(Typeface.DEFAULT_BOLD);
            group.setGravity(Gravity.CENTER);


            group.addView(perfumeImageBtn);
            group.addView(perfumeInfo);
            resultView.addView(group);
    }
        Log.i("reviewResult", String.valueOf(reviewList));
    }


    public void onClick(View v) {

        switch (v.getId()) {

            case R.id. addReviewBtn:
                Intent intent6 = new Intent(getApplicationContext(), AddReviewActivity.class);
                intent6.putExtra("nick",nick);
                intent6.putExtra("brand",brand);
                intent6.putExtra("kr_brand",kr_brand);
                intent6.putExtra("en_name",en_name);
                intent6.putExtra("kr_name",kr_name);
                intent6.putExtra("img",img);
                intent6.putExtra("likes",likes);
                intent6.putExtra("countingReview",countingReview);
                intent6.putExtra("avgStars",avgStars);

                startActivity(intent6);
                finish();
                break;

            case R.id.perfumeSearch:
                Intent intent7 = new Intent(getApplicationContext(), mainSearchActivity.class);
                intent7.putExtra("nick",nick);
                startActivity(intent7);
                finish();
                break;


        }
    }

}
