package com.example.catchi_nichi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SmellNoteMainActivity extends AppCompatActivity {
    //Retrofit
    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(RetrofitAPI.API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    public static final RetrofitAPI apiService = retrofit.create(RetrofitAPI.class);

    String nick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smellnotemain);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        //수신데이터
        Intent intent = getIntent();
        nick = intent.getStringExtra("nick");

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

            case R.id.smellNoteRegister:
                Intent intent6 = new Intent(getApplicationContext(), SmellNoteChooseActivity.class);
                intent6.putExtra("nick",nick);
                startActivity(intent6);
                finish();
                break;



        }
    }

}
