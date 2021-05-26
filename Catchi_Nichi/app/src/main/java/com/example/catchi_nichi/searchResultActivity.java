package com.example.catchi_nichi;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.io.BufferedInputStream;
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
import retrofit2.http.Url;

public class searchResultActivity extends AppCompatActivity {
    //Retrofit
    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(RetrofitAPI.API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    public static final RetrofitAPI apiService = retrofit.create(RetrofitAPI.class);

    Bitmap bitmap;
    ImageButton perfumeImageBtn;
    int temp;
    ArrayList<HashMap<String, String>> searchList ;
    String enterSearch;
    int getCount;
    EditText shText;
    String nick;
    LinearLayout resultView;
    LinearLayout group;
    String[] items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchresult);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();


        //수신데이터
        Intent intent = getIntent();
        searchList = (ArrayList<HashMap<String, String>>) intent.getSerializableExtra("searchList");
        enterSearch = intent.getStringExtra("enterSearch");
        getCount = intent.getIntExtra("getCount",0);
        items = intent.getStringArrayExtra("autoSearchItem");
        nick = intent.getStringExtra("nick"); //수신데이터

        shText = findViewById(R.id.search);
        shText.setText(enterSearch);

        //키보드 숨기기
        ConstraintLayout layout = findViewById(R.id.layout);
        layout.setOnClickListener(v -> {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(shText.getWindowToken(), 0);
        });

        Adapter();
        TextView resultText = findViewById(R.id.resultView);
        resultText.setTypeface(Typeface.DEFAULT_BOLD);
        resultText.setText(getCount+" 개의 향수가 검색되었습니다.");

        resultView = findViewById(R.id.Info);
        resultView.setOrientation(LinearLayout.VERTICAL);

        for(temp=0;temp<searchList.size();temp++){

            perfumeImageBtn = new ImageButton(this);
            perfumeImageBtn.setLayoutParams(new LinearLayout.LayoutParams(600,500));
            perfumeImageBtn.setId(temp);
            perfumeImageBtn.setBackgroundColor(Color.parseColor("#FFFFFF"));

            final int x = temp;

            perfumeImageBtn.setOnClickListener(v -> {
                Intent intent2 = new Intent(getApplicationContext(), PerfumeDataActivity.class);
                intent2.putExtra("img",searchList.get(x).get("img"));
                intent2.putExtra("kr_name",searchList.get(x).get("kr_name"));
                intent2.putExtra("en_name",searchList.get(x).get("en_name"));
                intent2.putExtra("brand",searchList.get(x).get("brand"));
                intent2.putExtra("kr_brand",searchList.get(x).get("kr_brand"));
                intent2.putExtra("likes",searchList.get(x).get("likes"));
                intent2.putExtra("countingReview",searchList.get(x).get("countingReview"));
                intent2.putExtra("avgStars",searchList.get(x).get("avgStars"));
                intent2.putExtra("nick",nick);
                startActivity(intent2);
                finish();
            });

            group = new LinearLayout(this);
            group.setOrientation(LinearLayout.HORIZONTAL);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(5,15,5,15);
            group.setLayoutParams(params);

            Thread mThread = new Thread(){
                public void run(){
                    try{
                        URL url = new URL(searchList.get(temp).get("img"));
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

            try{
                mThread.join();
                perfumeImageBtn.setImageBitmap(bitmap);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            TextView perfumeInfo = new TextView(this);
            perfumeInfo.setGravity(Gravity.CENTER);
            perfumeInfo.setLayoutParams(new LinearLayout.LayoutParams(600,500));
            perfumeInfo.setText("\n  "+ searchList.get(temp).get("kr_name") + "\n  " + searchList.get(temp).get("brand") + "\n  " + "Likes : " + searchList.get(temp).get("likes")+ "\n  "+ "리뷰수 : " + searchList.get(temp).get("countingReview")+ "\n  "+ "평균별점 : " + searchList.get(temp).get("avgStars")+ "\n");
            //perfumeInfo.setTypeface(Typeface.DEFAULT_BOLD);
            group.setGravity(Gravity.CENTER);


            group.addView(perfumeImageBtn);
            group.addView(perfumeInfo);
            resultView.addView(group);
        }

        Log.i("searchResult", String.valueOf(searchList));
    }

    public void Adapter(){
        ArrayAdapter<String> adWord = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, items);
        AutoCompleteTextView edit = (AutoCompleteTextView) findViewById(R.id.search);
        edit.setAdapter(adWord);
    }


    public void onClick(View v) {

        String searchText = shText.getText().toString();

        switch (v.getId()){

            case R.id.searchButton:

                Call<Post> search = apiService.searchAPI(searchText,"likes",999,0,1);
                search.enqueue(new Callback<Post>() {
                    @Override
                    public void onResponse(Call<Post> call, Response<Post> response) {
                        Log.i("searchInfo","success");
                        Log.i("searchInfo",response.toString());

                        //송신 데이터
                        Intent intent = new Intent(getApplicationContext(), searchResultActivity.class);
                        intent.putExtra("searchList",response.body().getSearchList());
                        intent.putExtra("getCount",response.body().getCount());
                        intent.putExtra("enterSearch",searchText);
                        intent.putExtra("nick",nick);
                        intent.putExtra("autoSearchItem",items);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onFailure(Call<Post> call, Throwable t) {
                        Log.i("searchInfo","fail");
                        t.printStackTrace();
                    }

                });

                break;

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

            case R.id.mypage_btn:
                Intent intent7 = new Intent(getApplicationContext(), MyPageActivity.class);
                intent7.putExtra("nick", nick);
                startActivity(intent7);
                finish();
                break;
        }
    }

}