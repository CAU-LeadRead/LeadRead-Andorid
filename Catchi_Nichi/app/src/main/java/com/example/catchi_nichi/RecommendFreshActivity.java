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

public class RecommendFreshActivity extends AppCompatActivity {
    //Retrofit
    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(RetrofitAPI.API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    public static final RetrofitAPI apiService = retrofit.create(RetrofitAPI.class);

    String nick;
    String firstSelected;
    String category1="null";
    TextView selectedView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend_fresh_1);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        //수신데이터
        Intent intent = getIntent();
        nick = intent.getStringExtra("nick");
        firstSelected = intent.getStringExtra("firstSelected");

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

            case R.id.citrusBtn:
                selectedView.setText("싱그럽고 생기 있는 과일이 선택되었습니다.");
                category1 = "citrus";
                break;

            case R.id.fruityBtn:
                selectedView.setText("달콤하고 향긋한 과일이 선택되었습니다.");
                category1 = "fruity";
                break;

            case R.id.next_Btn:
                if(!category1.equals("null")){
                    Intent intent2 = new Intent(getApplicationContext(), RecommendTodaySecondActivity.class);
                    intent2.putExtra("firstSelected", firstSelected);
                    intent2.putExtra("category1",category1);
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
