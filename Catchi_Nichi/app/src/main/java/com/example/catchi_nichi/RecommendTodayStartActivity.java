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

public class RecommendTodayStartActivity extends AppCompatActivity {
    //Retrofit
    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(RetrofitAPI.API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    public static final RetrofitAPI apiService = retrofit.create(RetrofitAPI.class);

    String nick;
    String firstSelected ="";
    TextView selectedView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend_today_start);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        //수신데이터
        Intent intent = getIntent();
        nick = intent.getStringExtra("nick");

        selectedView = findViewById(R.id.selectedView);

    }

    public void onClick(View v) {

        switch (v.getId()){

            case R.id.floralBtn:
                firstSelected = "floral";
                selectedView.setText("넓게 펼쳐진 꽃 밭이 선택되었습니다.");
                break;

            case R.id.woodyBtn:
                firstSelected = "woody";
                selectedView.setText("마음이 편안해지는 숲 속이 선택되었습니다.");
                break;

            case R.id.freshBtn:
                firstSelected = "fresh";
                selectedView.setText("상상만해도 상쾌한 바람이 선택되었습니다.");
                break;

            case R.id.cityBtn:
                firstSelected = "city";
                selectedView.setText("현대적이고 생동감 있는 도시가 선택되었습니다.");
                break;

            case R.id.orientalBtn:
                firstSelected = "oriental";
                selectedView.setText("신비롭고 고급스러운 미지의 장소가 선택되었습니다.");
                break;

            case R.id.cancel_Btn:
                Intent intent = new Intent(getApplicationContext(), RecommendMainActivity.class);
                intent.putExtra("nick", nick);
                startActivity(intent);
                finish();
                break;

            case R.id.next_Btn:
                if(firstSelected=="floral"){
                    Intent intent2 = new Intent(getApplicationContext(), RecommendFloralActivity.class);
                    intent2.putExtra("firstSelected", "floral");
                    intent2.putExtra("nick", nick);
                    startActivity(intent2);
                    finish();

                }
                else if(firstSelected=="woody"){
                    Intent intent2 = new Intent(getApplicationContext(), RecommendWoodyActivity.class);
                    intent2.putExtra("firstSelected", "woody");
                    intent2.putExtra("nick", nick);
                    startActivity(intent2);
                    finish();

                }
                else if(firstSelected=="city"){
                    Intent intent2 = new Intent(getApplicationContext(), RecommendCityActivity.class);
                    intent2.putExtra("firstSelected", "city");
                    intent2.putExtra("nick", nick);
                    startActivity(intent2);
                    finish();
                }
                else if(firstSelected=="oriental"){
                    Intent intent2 = new Intent(getApplicationContext(), RecommendOrientalActivity.class);
                    intent2.putExtra("firstSelected", "oriental");
                    intent2.putExtra("nick", nick);
                    startActivity(intent2);
                    finish();

                }
                else if(firstSelected=="fresh"){
                    Intent intent2 = new Intent(getApplicationContext(), RecommendFreshActivity.class);
                    intent2.putExtra("firstSelected", "fresh");
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
