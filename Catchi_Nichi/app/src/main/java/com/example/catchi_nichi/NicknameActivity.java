package com.example.catchi_nichi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NicknameActivity extends Activity {

    //Retrofit
    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(RetrofitAPI.API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static final RetrofitAPI apiService = retrofit.create(RetrofitAPI.class);

    //수신데이터
    String email, phone, password, gender, age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nickname);
        Intent intent = getIntent();
        email = intent.getStringExtra("email");
        phone = intent.getStringExtra("phone");
        password = intent.getStringExtra("password");
        try{
        gender = intent.getStringExtra("gender");}
        catch(Exception ignored){}
        try{
        age = intent.getStringExtra("age");}
        catch(Exception ignored){}

        Log.i("Nickname(email)", email);
        Log.i("Nickname(phone)", phone);
        Log.i("Nickname(pwd)", password);
        Log.i("Nickname(gender)", gender);
        Log.i("Nickname(age)", age);
    }

    //buttonListener 구현
    public void onClick(View v) {

        EditText nick = findViewById(R.id.nickName);
        switch (v.getId()){

            case R.id.nicknameconfirm:

                HashMap<String, Object> nickName = new HashMap<>();
                nickName.put("nick",nick.getText().toString());
                nickName.put("email",email);
                nickName.put("age",age);
                nickName.put("gender",gender);
                nickName.put("phone",phone);
                nickName.put("password",password);
                Call<Post> nick_Name = apiService.signupAPI(nickName);

                nick_Name.enqueue(new Callback<Post>() {
                    @Override
                    public void onResponse(Call<Post> call, Response<Post> response) {
                        try {
                            if (response.body().getSuccess()) {
                                Log.i("Nickname", "success");
                                Log.i("Nickname", response.toString());
                                Toast.makeText(getApplicationContext(), "회원가입이 완료되었습니다 :)", Toast.LENGTH_SHORT).show();
                                Intent login = new Intent(getApplicationContext(), LoginActivity.class);
                                startActivity(login);
                                finish(); }
                            else {
                                Log.i("Nickname", "fail");
                                Toast.makeText(getApplicationContext(), "동일한 닉네임이 존재합니다.", Toast.LENGTH_SHORT).show(); }
                        }catch (Exception e) {
                            Log.i("Nickname", "fail");
                            e.printStackTrace();
                        }}
                        @Override
                        public void onFailure (Call < Post > call, Throwable t){
                                Log.i("searchInfo", "fail");
                                t.printStackTrace();
                    }

                });

    }
}
    }