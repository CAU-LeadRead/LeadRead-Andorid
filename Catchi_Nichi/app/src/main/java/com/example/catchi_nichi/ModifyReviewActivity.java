package com.example.catchi_nichi;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Spinner;
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

public class ModifyReviewActivity extends AppCompatActivity {
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
    String brand;
    String kr_brand;
    String likes;
    String countingReview;
    String avgStars;
    String review_writer;
    String review_mood;
    String review_comment;
    Float review_star;
    Float review_longevity;
    Integer review_id;

    ImageView perfumePic;
    TextView perfumeInfo;
    EditText userWrite;
    Bitmap bitmap;
    TextView writerName;
    TextView moodText;

    RatingBar star;
    RatingBar longevity;

    Spinner spinner;
    String[] item={"세련된","관능적","청순한","우아한","명량한","사랑스러운","지적인","포근한"};
    int selectedItem=-1;

    float stars=0;
    float longevitys=0;

    String activity;
    String enterSearch;
    int getCount;
    String[] items;
    ArrayList<HashMap<String, String>> searchList;
    String category1;
    String category2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_review);
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

        //perfume 정보
        img = intent.getStringExtra("img");
        kr_name = intent.getStringExtra("kr_name");
        en_name = intent.getStringExtra("en_name");
        brand = intent.getStringExtra("brand");
        kr_brand = intent.getStringExtra("kr_brand");
        likes = intent.getStringExtra("likes");
        countingReview = intent.getStringExtra("countingReview");
        avgStars = intent.getStringExtra("avgStars");

        //review 정보
        review_writer = intent.getStringExtra("review_writer");
        review_star = intent.getFloatExtra("review_star", 0);
        review_longevity = intent.getFloatExtra("review_longevity", 0);
        review_mood = intent.getStringExtra("review_mood");
        review_comment = intent.getStringExtra("review_comment");
        review_id = intent.getIntExtra("review_id",0);

        //화면전환
        activity = intent.getStringExtra("Activity");
        searchList = (ArrayList<HashMap<String, String>>) intent.getSerializableExtra("searchList");
        try{
            enterSearch = intent.getStringExtra("enterSearch");
            getCount = intent.getIntExtra("getCount", 0);
            items = intent.getStringArrayExtra("autoSearchItem");
            category1 =intent.getStringExtra("category1");
            category2 =intent.getStringExtra("category2");}
        catch (Exception e){
            e.printStackTrace();
        }

        perfumePic = findViewById(R.id.imageView);
        perfumeInfo = findViewById(R.id.imageText);
        userWrite = findViewById(R.id.userWrite);
        userWrite.setText(review_comment);

        writerName = findViewById(R.id.writerName);
        moodText = findViewById(R.id.moodText);
        star = findViewById(R.id.starRating);
        longevity = findViewById(R.id.longevityRating);

        star.setRating((float)review_star);
        stars = (float)review_star;
        star.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                stars = rating;
            }
        });

        longevity.setRating((float)review_longevity);
        longevitys = (float)review_longevity;
        longevity.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                longevitys = rating;
            }
        });



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
            perfumePic.setImageBitmap(bitmap);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        perfumeInfo.setText("\n  " + kr_name + "\n  " + brand);
        writerName.setText(review_writer);

        Spinner();



    }

    public void Spinner() {
        spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedItem = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void onClick(View v) {


        switch (v.getId()) {

            case R.id.cancel_Btn:
                Intent intent2 = new Intent(getApplicationContext(), ConfirmReviewActivity.class);
                intent2.putExtra("nick", nick);

                //perfume 정보
                intent2.putExtra("img", img);
                intent2.putExtra("kr_name", kr_name);
                intent2.putExtra("en_name", en_name);
                intent2.putExtra("brand", brand);
                intent2.putExtra("kr_brand", kr_brand);
                intent2.putExtra("likes", likes);
                intent2.putExtra("countingReview", countingReview);
                intent2.putExtra("avgStars", avgStars);

                //review 정보
                intent2.putExtra("review_writer", review_writer);
                intent2.putExtra("review_longevity", review_longevity);
                intent2.putExtra("review_star", review_star);
                intent2.putExtra("review_mood",review_mood);
                intent2.putExtra("review_comment", review_comment);
                intent2.putExtra("review_id",review_id);

                //화면전환
                //activity 정보
                intent2.putExtra("Activity",activity);
                intent2.putExtra("searchList",searchList);

                try{
                    intent2.putExtra("autoSearchItem",items);
                    intent2.putExtra("getCount",getCount);
                    intent2.putExtra("enterSearch",enterSearch);
                    intent2.putExtra("category1",category1);
                    intent2.putExtra("category2",category2);}
                catch (Exception e){
                    e.printStackTrace();
                }

                startActivity(intent2);
                finish();
                break;

            case R.id.ok_btn:

                //ReviewUpdate
                HashMap<String,Object> review = new HashMap<>();
                review.put("mood",item[selectedItem]);
                review.put("stars",stars);
                review.put("longevity",longevitys);
                review.put("comment",userWrite.getText()+"");
                review.put("brand",brand);
                review.put("en_name",en_name);

                Call<Post> updateReview = apiService.updateReviewAPI(review_id, review);
                updateReview.enqueue(new Callback<Post>() {
                    @Override
                    public void onResponse(Call<Post> call, Response<Post> response) {
                        Log.i("updateReview","success");
                        Toast.makeText(getApplicationContext(), "리뷰가 수정되었습니다.", Toast.LENGTH_SHORT).show();

                        Intent intent2 = new Intent(getApplicationContext(), ConfirmReviewActivity.class);
                        intent2.putExtra("nick", nick);

                        //perfume 정보
                        intent2.putExtra("img", img);
                        intent2.putExtra("kr_name", kr_name);
                        intent2.putExtra("en_name", en_name);
                        intent2.putExtra("brand", brand);
                        intent2.putExtra("kr_brand", kr_brand);
                        intent2.putExtra("likes", likes);
                        intent2.putExtra("countingReview", countingReview);
                        intent2.putExtra("avgStars", avgStars);

                        //review 정보
                        intent2.putExtra("review_writer", review_writer);
                        intent2.putExtra("review_longevity", longevitys);
                        intent2.putExtra("review_star", stars);
                        intent2.putExtra("review_mood", item[selectedItem]);
                        intent2.putExtra("review_comment", userWrite.getText()+"");
                        intent2.putExtra("review_id",review_id);

                        //화면전환
                        //activity 정보
                        intent2.putExtra("Activity",activity);
                        intent2.putExtra("searchList",searchList);

                        try{
                            intent2.putExtra("autoSearchItem",items);
                            intent2.putExtra("getCount",getCount);
                            intent2.putExtra("enterSearch",enterSearch);
                            intent2.putExtra("category1",category1);
                            intent2.putExtra("category2",category2);
                        }
                        catch (Exception e){
                            e.printStackTrace();
                        }

                        startActivity(intent2);
                        finish();
                    }

                    @Override
                    public void onFailure(Call<Post> call, Throwable t) {
                        Log.i("updateReview","fail");
                        t.printStackTrace();

                    }
                });
                break;

            case R.id.delete_Btn:

                Call<Post> deleteReview = apiService.deleteReviewAPI(review_id);
                deleteReview.enqueue(new Callback<Post>() {
                    @Override
                    public void onResponse(Call<Post> call, Response<Post> response) {
                        Log.i("deleteReview","success");
                        Toast.makeText(getApplicationContext(), "리뷰가 삭제되었습니다.", Toast.LENGTH_SHORT).show();

                        if(activity.equals("myPage")){
                            Intent intent9 = new Intent(getApplicationContext(), MyPageActivity.class);
                            intent9.putExtra("nick",nick);
                            intent9.putExtra("searchList",searchList);
                            startActivity(intent9);
                            finish();
                        }
                        else{
                            Intent intent2 = new Intent(getApplicationContext(), PerfumeDataActivity.class);
                            intent2.putExtra("nick", nick);

                            //perfume 정보
                            intent2.putExtra("img", img);
                            intent2.putExtra("kr_name", kr_name);
                            intent2.putExtra("en_name", en_name);
                            intent2.putExtra("brand", brand);
                            intent2.putExtra("kr_brand", kr_brand);
                            intent2.putExtra("likes", likes);
                            intent2.putExtra("countingReview", countingReview);
                            intent2.putExtra("avgStars", avgStars);

                            //화면전환
                            //activity 정보
                            intent2.putExtra("Activity",activity);
                            intent2.putExtra("searchList",searchList);

                            try{
                                intent2.putExtra("autoSearchItem",items);
                                intent2.putExtra("getCount",getCount);
                                intent2.putExtra("enterSearch",enterSearch);
                                intent2.putExtra("category1",category1);
                                intent2.putExtra("category2",category2);}
                            catch (Exception e){
                                e.printStackTrace();
                            }
                            startActivity(intent2);
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<Post> call, Throwable t) {
                        Log.i("deleteReview","fail");
                        t.printStackTrace();
                    }
                });
                break;

        }

    }
}
