package com.example.catchi_nichi;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UsedPerfumeActivity extends AppCompatActivity {

    //Retrofit
    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(RetrofitAPI.API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static final RetrofitAPI apiService = retrofit.create(RetrofitAPI.class);

    String nickname;
    String[] itemsPerfume;
    String[] itemsBrand;
    String[] items;
    float stars=0;
    RatingBar ratingbar;
    int selectedItem=-1;

    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_used_perfume);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        Intent intent = getIntent();
        nickname = intent.getStringExtra("nickname");

        Call<Post> search = apiService.searchAPI("","category", 999,0,0);
        search.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                itemsPerfume = new String[response.body().getCount()];
                itemsBrand  = new String[response.body().getCount()];
                items = new String[response.body().getCount()];
                for(int i=0; i<response.body().getCount();i++){
                    itemsPerfume[i]=(response.body().getSearchList().get(i).get("en_name"));
                    itemsBrand[i]=(response.body().getSearchList().get(i).get("brand"));
                    items[i] = "["+itemsBrand[i]+"]  "+itemsPerfume[i];
                }
                Spinner();
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                t.printStackTrace();
            }
        });

        ratingbar = findViewById(R.id.ratingBar);
        ratingbar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                stars = rating;
            }
        });

    }

    public void Spinner(){
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

    public void onClick(View v) {

        switch (v.getId()){

            case R.id.plusBtn:

                Log.i("nick",nickname);
                Log.i("en_name",itemsPerfume[selectedItem]);
                Log.i("brand",itemsBrand[selectedItem]);
                Log.i("stars", String.valueOf(stars));

                HashMap<String, Object> review = new HashMap<>();
                review.put("nick",nickname);
                review.put("en_name",itemsPerfume[selectedItem]);
                review.put("brand",itemsBrand[selectedItem]);
                review.put("stars",stars);
                review.put("category",0);

                Call<Post> addReview = apiService.addReviewAPI(review);
                addReview.enqueue(new Callback<Post>() {
                    @Override
                    public void onResponse(Call<Post> call, Response<Post> response) {
                        Log.i("usedPerfume","success");
                        response.body().getMessage();
                    }

                    @Override
                    public void onFailure(Call<Post> call, Throwable t) {
                        Log.i("usedPerfume","error");
                        t.printStackTrace();
                    }
                });

                Intent login2 = new Intent(getApplicationContext(), UsedPerfumeActivity.class);
                login2.putExtra("nickname",nickname);
                startActivity(login2);
                finish();

                break;

            case R.id.skipBtn:
                Intent login = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(login);
                finish();
                break;

            case R.id.confirmBtn:
                HashMap<String, Object> review2 = new HashMap<>();
                review2.put("nick",nickname);
                review2.put("en_name",itemsPerfume[selectedItem]);
                review2.put("brand",itemsBrand[selectedItem]);
                review2.put("stars",stars);
                review2.put("category",0);

                Call<Post> addReview2 = apiService.addReviewAPI(review2);
                addReview2.enqueue(new Callback<Post>() {
                    @Override
                    public void onResponse(Call<Post> call, Response<Post> response) {
                        Log.i("usedPerfume","success");
                        response.body().getMessage();

                    }

                    @Override
                    public void onFailure(Call<Post> call, Throwable t) {
                        Log.i("usedPerfume","error");
                        t.printStackTrace();
                    }
                });
                Intent login3 = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(login3);
                finish();
                break;

        }
    }
}
