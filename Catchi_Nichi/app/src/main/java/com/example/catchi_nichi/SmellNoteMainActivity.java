package com.example.catchi_nichi;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

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

public class SmellNoteMainActivity extends AppCompatActivity {
    //Retrofit
    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(RetrofitAPI.API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    public static final RetrofitAPI apiService = retrofit.create(RetrofitAPI.class);

    String nick;
    String[] smellNoteImgList ;
    String[] smellNoteBrandList ;
    String[] smellNoteNameList ;
    String[] smellNoteDateList ;
    String[] smellNoteCommentList;
    Integer[] smellNoteIdList;
    LinearLayout resultView;
    LinearLayout group;
    ImageButton myNoteBtn;

    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smellnotemain);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        //수신데이터
        Intent intent = getIntent();
        nick = intent.getStringExtra("nick");
        resultView = findViewById(R.id.smellNote);

        Call<Post> myNote = apiService.smellNoteAllAPI(nick);
        myNote.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {

                Log.i("smellNoteList",response.body().getMessage());
                Log.i("smellNoteList","success");

                smellNoteImgList = new String [response.body().getMemoList().size()];
                smellNoteBrandList = new String [response.body().getMemoList().size()];
                smellNoteNameList = new String [response.body().getMemoList().size()];
                smellNoteDateList = new String [response.body().getMemoList().size()];
                smellNoteCommentList = new String [response.body().getMemoList().size()];
                smellNoteIdList = new Integer[response.body().getMemoList().size()];


                for(int i=0;i<response.body().getMemoList().size();i++) {
                    smellNoteImgList[i]= response.body().getMemoList().get(i).fragrance.img;
                    smellNoteBrandList[i] = response.body().getMemoList().get(i).kr_brand;
                    smellNoteNameList[i] =response.body().getMemoList().get(i).kr_name;
                    smellNoteDateList[i] = response.body().getMemoList().get(i).updatedAt;
                    smellNoteCommentList[i] = response.body().getMemoList().get(i).comment;
                    smellNoteIdList[i] = response.body().getMemoList().get(i).id;
                }

                MySmellNote();
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Log.i("smellNoteList","fail");
                t.printStackTrace();
            }
        });
    }

    int temp;
    public void MySmellNote(){
        for(temp=0;temp<smellNoteImgList.length;temp++){

            myNoteBtn = new ImageButton(this);
            myNoteBtn.setLayoutParams(new LinearLayout.LayoutParams(600,500));
            myNoteBtn.setTag(temp);
            myNoteBtn.setBackgroundColor(Color.parseColor("#FFFFFF"));

            final int x = temp;

            myNoteBtn.setOnClickListener(v -> {
                Intent intent = new Intent(getApplicationContext(), SmellNoteConfineActivity.class);
                intent.putExtra("img",smellNoteImgList[x]);
                intent.putExtra("brand",smellNoteBrandList[x]);
                intent.putExtra("name",smellNoteNameList[x]);
                intent.putExtra("date",smellNoteDateList[x]);
                intent.putExtra("comment",smellNoteCommentList[x]);
                intent.putExtra("id",smellNoteIdList[x]);
                intent.putExtra("nick",nick);
                startActivity(intent);
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
                        URL url = new URL(smellNoteImgList[temp]);
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
                myNoteBtn.setImageBitmap(bitmap);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            TextView perfumeInfo = new TextView(this);
            perfumeInfo.setGravity(Gravity.CENTER);
            perfumeInfo.setLayoutParams(new LinearLayout.LayoutParams(600,500));
            perfumeInfo.setText("\n  "+ smellNoteNameList[temp] + "\n  " + smellNoteBrandList[temp] + "\n  " + "\n  " + smellNoteDateList[temp] + "\n  ");
            perfumeInfo.setTypeface(Typeface.DEFAULT_BOLD);
            group.setGravity(Gravity.CENTER);


            group.addView(myNoteBtn);
            group.addView(perfumeInfo);
            resultView.addView(group);
        }
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

            case R.id.mypage_btn:
                Intent intent7 = new Intent(getApplicationContext(), MyPageActivity.class);
                intent7.putExtra("nick", nick);
                startActivity(intent7);
                finish();
                break;


        }
    }

}
