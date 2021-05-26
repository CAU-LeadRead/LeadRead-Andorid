package com.example.catchi_nichi;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SmellNoteConfineActivity extends AppCompatActivity {
    //Retrofit
    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(RetrofitAPI.API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    public static final RetrofitAPI apiService = retrofit.create(RetrofitAPI.class);

    String nick;
    String img;
    String brand;
    String name;
    String date;
    String comment;
    Integer id;

    ImageView perfumePic;
    TextView perfumeInfo;
    TextView userWrite;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smellnoteconfine);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        //수신데이터
        Intent intent = getIntent();
        nick = intent.getStringExtra("nick");
        brand = intent.getStringExtra("brand");
        img = intent.getStringExtra("img");
        name = intent.getStringExtra("name");
        date = intent.getStringExtra("date");
        comment = intent.getStringExtra("comment");
        id = intent.getIntExtra("id",0);

        perfumePic = findViewById(R.id.imageView);
        perfumeInfo = findViewById(R.id.imageText);
        userWrite = findViewById(R.id.userWrite);
        userWrite.setText(comment);
        TextView dateTime = findViewById(R.id.editTextDate);
        dateTime.setText(date);

        Thread mThread = new Thread(){
            public void run(){
                try{
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

        try{
            mThread.join();
            perfumePic.setImageBitmap(bitmap);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        perfumeInfo.setText("\n  " + name + "\n  " + brand);
    }

    public void onClick(View v) {
        switch (v.getId()){

            case R.id.ok_btn:
                Intent intent = new Intent(getApplicationContext(), SmellNoteMainActivity.class);
                intent.putExtra("nick", nick);
                startActivity(intent);
                finish();
                break;

            case R.id.modify_Btn:
                Intent intent2 = new Intent(getApplicationContext(), SmellNoteModifyActivity.class);
                intent2.putExtra("img", img);
                intent2.putExtra("brand",brand);
                intent2.putExtra("date",date);
                intent2.putExtra("id",id);
                intent2.putExtra("name",name);
                intent2.putExtra("nick", nick);
                intent2.putExtra("comment",comment);
                startActivity(intent2);
                finish();
                break;




        }
    }
}
