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

public class AddReviewActivity extends AppCompatActivity {
    //Retrofit
    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(RetrofitAPI.API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    public static final RetrofitAPI apiService = retrofit.create(RetrofitAPI.class);

    String nick;
    String brand;
    String kr_brand;
    String en_name;
    String kr_name;
    String img;
    String likes;
    String countingReview;
    String avgStars;

    Bitmap bitmap;

    Spinner spinner;
    String[] items={"세련된","관능적","청순한","우아한","명량한","사랑스러운","지적인","포근한"};
    int selectedItem=-1;

    RatingBar starRatingbar;
    RatingBar longevityRatingbar;

    float stars=0;
    float longevity=0;

    ImageView perfumeImg;
    TextView perfumeText;
    EditText userWrite;
    TextView writerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_review);
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
        kr_brand = intent.getStringExtra("kr_brand");
        en_name = intent.getStringExtra("en_name");;
        kr_name = intent.getStringExtra("kr_name");
        img = intent.getStringExtra("img");
        likes = intent.getStringExtra("likes");
        countingReview = intent.getStringExtra("countingReview");
        avgStars = intent.getStringExtra("avgStars");

        perfumeImg = findViewById(R.id.perfumeImg);
        perfumeText = findViewById(R.id.perfumeText);
        userWrite = findViewById(R.id.userWrite);
        writerName = findViewById(R.id.writerName);
        writerName.setText(nick);

        Spinner();

        starRatingbar = findViewById(R.id.starRating);
        starRatingbar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                stars = rating;
            }
        });

        longevityRatingbar = findViewById(R.id.longevityRating);
        longevityRatingbar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                longevity = rating;
            }
        });

        Load();
    }

    public void Spinner() {
        spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items);
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

    }

    public void onClick(View v) {
        switch (v.getId()){

            case R.id.cancel_btn:
                Intent intent2 = new Intent(getApplicationContext(), PerfumeDataActivity.class);
                intent2.putExtra("img",img);
                intent2.putExtra("kr_name",kr_name);
                intent2.putExtra("en_name",en_name);
                intent2.putExtra("brand",brand);
                intent2.putExtra("kr_brand",kr_brand);
                intent2.putExtra("likes",likes);
                intent2.putExtra("countingReview",countingReview);
                intent2.putExtra("avgStars",avgStars);
                intent2.putExtra("nick",nick);
                startActivity(intent2);
                finish();
                break;

            case R.id.submit_Btn:

                HashMap<String,Object> review = new HashMap<>();
                review.put("nick",nick);
                review.put("brand",brand);
                review.put("en_name",en_name);
                review.put("kr_brand",kr_brand);
                review.put("kr_name",kr_name);
                review.put("mood",items[selectedItem]);
                review.put("stars",stars);
                review.put("longevity",longevity);
                review.put("comment",userWrite.getText());
                review.put("category",1);

                Call<Post> search = apiService.addReviewAPI(review);
                search.enqueue(new Callback<Post>() {
                                   @Override
                                   public void onResponse(Call<Post> call, Response<Post> response) {
                                       Log.i("addReview","success");

                                       Intent intent3 = new Intent(getApplicationContext(), PerfumeDataActivity.class);
                                       intent3.putExtra("img",img);
                                       intent3.putExtra("kr_name",kr_name);
                                       intent3.putExtra("en_name",en_name);
                                       intent3.putExtra("brand",brand);
                                       intent3.putExtra("kr_brand",kr_brand);
                                       intent3.putExtra("likes",likes);
                                       intent3.putExtra("countingReview",countingReview);
                                       intent3.putExtra("avgStars",avgStars);
                                       intent3.putExtra("nick",nick);
                                       startActivity(intent3);
                                       finish();

                                   }

                                   @Override
                                   public void onFailure(Call<Post> call, Throwable t) {
                                       Log.i("addReview","fail");
                                       t.printStackTrace();
                                   }
                               } );
                break;


        }
    }
}
