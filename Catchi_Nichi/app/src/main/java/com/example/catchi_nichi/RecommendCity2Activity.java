package com.example.catchi_nichi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RecommendCity2Activity extends AppCompatActivity {
    //Retrofit
    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(RetrofitAPI.API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    public static final RetrofitAPI apiService = retrofit.create(RetrofitAPI.class);

    String nick;
    String firstSelected;
    String secondSelected;
    String category2="null";
    String category1;
    TextView selectedView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend_city_2);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        //수신데이터
        Intent intent = getIntent();
        nick = intent.getStringExtra("nick");
        firstSelected = intent.getStringExtra("firstSelected");
        secondSelected = intent.getStringExtra("secondSelected");
        category1 =intent.getStringExtra("category1");
        selectedView = findViewById(R.id.selectedView);

    }
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.cancel_Btn:
                Intent intent = new Intent(getApplicationContext(), RecommendMainActivity.class);
                intent.putExtra("nick", nick);
                startActivity(intent);
                finish();
                break;

            case R.id.sweetsBtn:
                selectedView.setText("달콤한 베이커리가 선택되었습니다.");
                category2 = "sweets";
                break;

            case R.id.beverageBtn:
                selectedView.setText("화려한 칵테일 바가 선택되었습니다.");
                category2 = "beverage";
                break;

            case R.id.etcBtn:
                selectedView.setText("가보지 못한 새로운 곳이 선택되었습니다.");
                category2 = "etc";
                break;

            case R.id.next_Btn:
                if(!category2.equals("null")){
                    Intent intent2 = new Intent(getApplicationContext(), RecommendTodayResultActivity.class);
                    intent2.putExtra("category1",category1);
                    intent2.putExtra("category2",category2);
                    intent2.putExtra("nick", nick);
                    startActivity(intent2);
                    finish();
                }
                else{
                    Toast.makeText(getApplicationContext(), "질문에 알맞은 버튼을 선택해주세요.", Toast.LENGTH_SHORT).show();
                }
                break;


        }
    }
}
