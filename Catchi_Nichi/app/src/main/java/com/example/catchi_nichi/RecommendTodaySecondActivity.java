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

public class RecommendTodaySecondActivity extends AppCompatActivity {

    //Retrofit
    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(RetrofitAPI.API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    public static final RetrofitAPI apiService = retrofit.create(RetrofitAPI.class);

    String nick;
    String firstSelected;
    String secondSelected="";
    String category1;
    TextView selectedView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend_today_second);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        //수신데이터
        Intent intent = getIntent();
        nick = intent.getStringExtra("nick");
        firstSelected = intent.getStringExtra("firstSelected");
        category1 = intent.getStringExtra("category1");

        selectedView = findViewById(R.id.selectedView);

    }

    public void onClick(View v) {

        switch (v.getId()){

            case R.id.floralBtn:
                secondSelected = "floral";
                selectedView.setText("넓게 펼쳐진 꽃 밭이 선택되었습니다.");
                break;

            case R.id.woodyBtn:
                secondSelected = "woody";
                selectedView.setText("마음이 편안해지는 숲 속이 선택되었습니다.");
                break;

            case R.id.freshBtn:
                secondSelected = "fresh";
                selectedView.setText("상상만해도 상쾌한 바람이 선택되었습니다.");
                break;

            case R.id.cityBtn:
                secondSelected = "city";
                selectedView.setText("현대적이고 생동감 있는 도시가 선택되었습니다.");
                break;

            case R.id.orientalBtn:
                secondSelected = "oriental";
                selectedView.setText("신비롭고 고급스러운 미지의 장소가 선택되었습니다.");
                break;

            case R.id.cancel_Btn:
                Intent intent = new Intent(getApplicationContext(), RecommendMainActivity.class);
                intent.putExtra("nick", nick);
                startActivity(intent);
                finish();
                break;

            case R.id.next_Btn:
                if(secondSelected.equals(firstSelected)){
                    Toast.makeText(getApplicationContext(), "첫번째 선택과 다른 선택을 해주세요.", Toast.LENGTH_SHORT).show();
                }
                else if(secondSelected=="floral"){
                    Intent intent2 = new Intent(getApplicationContext(), RecommendFloral2Activity.class);
                    intent2.putExtra("firstSelected",firstSelected);
                    intent2.putExtra("category1",category1);
                    intent2.putExtra("secondSelected", "floral");
                    intent2.putExtra("nick", nick);
                    startActivity(intent2);
                    finish();

                }
                else if(secondSelected=="woody"){
                    Intent intent2 = new Intent(getApplicationContext(), RecommendWoody2Activity.class);
                    intent2.putExtra("secondSelected", "woody");
                    intent2.putExtra("nick", nick);
                    intent2.putExtra("firstSelected",firstSelected);
                    intent2.putExtra("category1",category1);
                    startActivity(intent2);
                    finish();

                }
                else if(secondSelected=="city"){
                    Intent intent2 = new Intent(getApplicationContext(), RecommendCity2Activity.class);
                    intent2.putExtra("secondSelected", "city");
                    intent2.putExtra("nick", nick);
                    intent2.putExtra("firstSelected",firstSelected);
                    intent2.putExtra("category1",category1);
                    startActivity(intent2);
                    finish();
                }
                else if(secondSelected=="oriental"){
                    Intent intent2 = new Intent(getApplicationContext(), RecommendOriental2Activity.class);
                    intent2.putExtra("secondSelected", "oriental");
                    intent2.putExtra("nick", nick);
                    intent2.putExtra("firstSelected",firstSelected);
                    intent2.putExtra("category1",category1);
                    startActivity(intent2);
                    finish();

                }
                else if(secondSelected=="fresh"){
                    Intent intent2 = new Intent(getApplicationContext(), RecommendFresh2Activity.class);
                    intent2.putExtra("secondSelected", "fresh");
                    intent2.putExtra("nick", nick);
                    intent2.putExtra("firstSelected",firstSelected);
                    intent2.putExtra("category1",category1);
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
