package com.example.catchi_nichi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NicknameActivity extends AppCompatActivity {

    //Retrofit
    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(RetrofitAPI.API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static final RetrofitAPI apiService = retrofit.create(RetrofitAPI.class);

    //수신데이터
    String email, phone, password, gender, snsId,join;
    Integer age;
    EditText shText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nickname);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();


        Intent intent = getIntent();
        join = intent.getStringExtra("join");
        email = intent.getStringExtra("email");
        try{
            phone = intent.getStringExtra("phone");}
        catch(Exception ignored){}
        try{
            password = intent.getStringExtra("password");}
        catch(Exception ignored){}
        try{
            snsId = intent.getStringExtra("snsId");}
        catch(Exception ignored){}
        try{
        gender = intent.getStringExtra("gender");}
        catch(Exception ignored){}
        try{
        age = intent.getIntExtra("age",0);}
        catch(Exception ignored){}
    }

    //buttonListener 구현
    public void onClick(View v) {

        EditText nick = findViewById(R.id.nickName);
        switch (v.getId()){

            case R.id.nicknameconfirm:

                if(join.equals("plain")){
                    HashMap<String, Object> nickName = new HashMap<>();
                    nickName.put("nick",nick.getText().toString());
                    nickName.put("email",email);
                    nickName.put("age",age);
                    nickName.put("gender",gender);
                    nickName.put("phone",phone);
                    nickName.put("password",password);

                    HashMap<String,Object> ckNick = new HashMap<>();
                    ckNick.put("nick",nick.getText().toString());
                    Call<Post> check_nick = apiService.checkNickAPI(ckNick);
                    Call<Post> nick_Name = apiService.signupAPI(nickName);

                    check_nick.enqueue(new Callback<Post>() {
                        @Override
                        public void onResponse(Call<Post> call, Response<Post> response) {
                            if (response.body().getSuccess()){
                                Log.i("닉네임중복", "ok");
                                nick_Name.enqueue(new Callback<Post>() {
                                    @Override
                                    public void onResponse(Call<Post> call, Response<Post> response) {
                                        Log.i("회원가입", "success");
                                        Log.i("회원가입", response.toString());
                                        Toast.makeText(getApplicationContext(), "회원가입이 완료되었습니다 :)", Toast.LENGTH_SHORT).show();
                                        Intent usedPerfume = new Intent(getApplicationContext(), UsedPerfumeActivity.class);
                                        usedPerfume.putExtra("nickname",nick.getText().toString());
                                        startActivity(usedPerfume);
                                        finish();
                                    }

                                    @Override
                                    public void onFailure(Call<Post> call, Throwable t) {

                                    }
                                });

                            }
                            else{
                                Log.i("닉네임중복", "fail");
                                Toast.makeText(getApplicationContext(), "동일한 닉네임이 존재합니다.", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Post> call, Throwable t) {

                        }
                    });

                }

                else if(join.equals("kakao")){
                    HashMap<String, Object> nickName = new HashMap<>();
                    nickName.put("nick",nick.getText().toString());
                    nickName.put("email",email);
                    nickName.put("age",age);
                    nickName.put("gender",gender);
                    nickName.put("snsId",snsId);

                    HashMap<String,Object> ckNick = new HashMap<>();
                    ckNick.put("nick",nick.getText().toString());
                    Call<Post> check_nick = apiService.checkNickAPI(ckNick);
                    Call<Post> kakaosignUp = apiService.kakaosingupAPI(nickName);

                    check_nick.enqueue(new Callback<Post>() {
                        @Override
                        public void onResponse(Call<Post> call, Response<Post> response) {
                            if (response.body().getSuccess()){
                                Log.i("닉네임중복", "ok");

                                kakaosignUp.enqueue(new Callback<Post>() {
                                    @Override
                                    public void onResponse(Call<Post> call, Response<Post> response) {
                                        Log.i("kakaosignUp", response.toString());
                                        if(response.body().getSuccess()){
                                            Log.i("회원가입", "success");
                                            Log.i("회원가입", response.toString());
                                            Toast.makeText(getApplicationContext(), "회원가입이 완료되었습니다 :)", Toast.LENGTH_SHORT).show();
                                            Intent usedPerfume = new Intent(getApplicationContext(), UsedPerfumeActivity.class);
                                            usedPerfume.putExtra("nickname",nick.getText().toString());
                                            startActivity(usedPerfume);
                                            finish();
                                        }
                                        else{
                                            Log.i("회원가입", "fail");
                                            Log.i("회원가입", response.toString());
                                            Toast.makeText(getApplicationContext(), "회원가입에 오류가 발생했습니다.", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                            startActivity(intent);
                                            finish();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<Post> call, Throwable t) {
                                        Log.i("kakaosignUp", "fail");
                                        t.printStackTrace();
                                    }
                                });

                            }
                            else{
                                Log.i("닉네임중복", "fail");
                                Toast.makeText(getApplicationContext(), "동일한 닉네임이 존재합니다.", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Post> call, Throwable t) {

                        }
                    });

                }
                break;

    }
}
    }