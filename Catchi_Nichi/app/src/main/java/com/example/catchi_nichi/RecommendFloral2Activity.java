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

public class RecommendFloral2Activity extends AppCompatActivity {
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
        setContentView(R.layout.activity_recommend_floral_2);
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

            case R.id.floralBtn:
                selectedView.setText("다채롭고 달콤한 향이 선택되었습니다.");
                category2 = "floral";
                break;

            case R.id.whitefloralBtn:
                selectedView.setText("고혹하면서 청초, 우아한 향이 선택되었습니다.");
                category2 = "whitefloral";
                break;

            case R.id.roseBtn:
                selectedView.setText("부드럽고 차분한 향이 선택되었습니다.");
                category2 = "rose";
                break;

            case R.id.next_Btn:
                if(!category1.equals("null")){
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
