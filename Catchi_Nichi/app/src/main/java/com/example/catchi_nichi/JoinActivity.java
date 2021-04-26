package com.example.catchi_nichi;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class JoinActivity extends AppCompatActivity {

    //Retrofit
    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(RetrofitAPI.API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    public static final RetrofitAPI apiService = retrofit.create(RetrofitAPI.class);

    private int temp;
    private boolean result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        //인증번호
        temp = 0;
        //동일한 이메일 확인
        result = false;
    }

    public void onClick(View v) {

        Pattern pattern = Patterns.EMAIL_ADDRESS;
        EditText email = findViewById(R.id.email);
        EditText phone1 = findViewById(R.id.num1);
        EditText phone2 = findViewById(R.id.num2);
        EditText phone3 = findViewById(R.id.num3);
        //라디오버튼
        RadioGroup genderGroup = findViewById(R.id.radiogroup);

        CheckBox check = findViewById(R.id.certify);
        EditText pwd1 = findViewById(R.id.pwd1);
        EditText pwd2 = findViewById(R.id.pwd2);
        EditText age = findViewById(R.id.age);
        EditText certifyNum = findViewById(R.id.certify_num);

        switch (v.getId()){

            case R.id.certifynum_btn:
                HashMap <String, Object> certify_num = new HashMap<>();
                certify_num.put("phone", phone1.getText().toString() + phone2.getText().toString() + phone3.getText().toString());
                Log.i("certifyNum",phone1.getText().toString() + phone2.getText().toString() + phone3.getText().toString());

                Call<Post> Certify = apiService.certifyNumAPI(certify_num);
                Certify.enqueue(new Callback<Post>() {
                    @Override
                    public void onResponse(Call<Post> call, Response<Post> response) {
                        try{
                            temp = response.body().getRandomNumber();
                            Log.i("certify","success");
                            Log.i("certify",response.toString());
                            Log.i("certify", String.valueOf(temp));

                        } catch(Exception e){
                            Log.i("certify","fail");
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<Post> call, Throwable t) {
                        Log.e("certifyErr",t.getMessage());
                    }
                } );
                 break;


            case R.id.confirm_btn:
                try{
                    if (temp==0) {
                        Toast.makeText(getApplicationContext(), "인증번호를 먼저 발급해주세요.", Toast.LENGTH_SHORT).show();
                    } else if (Integer.parseInt(certifyNum.getText().toString())==temp) {
                        check.setChecked(true);
                        Toast.makeText(getApplicationContext(), "핸드폰이 인증되었습니다.", Toast.LENGTH_SHORT).show();
                    } else {
                        check.setChecked(false);
                        Toast.makeText(getApplicationContext(), "인증번호가 알맞지 않습니다.", Toast.LENGTH_SHORT).show();
                    }
                }catch(Exception e){
                    check.setChecked(false);
                    Toast.makeText(getApplicationContext(), "인증번호가 알맞지 않습니다.", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.join:
                if (!check.isChecked()) {
                    Toast.makeText(getApplicationContext(), "핸드폰 인증을 해주세요.", Toast.LENGTH_SHORT).show();
                } else if (!pattern.matcher(email.getText().toString()).matches()) {
                    Toast.makeText(getApplicationContext(), "이메일 정보를 정확히 입력해주세요.", Toast.LENGTH_SHORT).show();
                } else if (!equalEmail(email.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "이미 사용중인 이메일 입니다.", Toast.LENGTH_SHORT).show();
                } else if (pwd1.getText().toString().equals("") || pwd1.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show();
                } else if (!pwd1.getText().toString().equals(pwd2.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                } else {

                    //서버에 회원가입 정보 등록
                    HashMap<String, Object> userJoin = new HashMap<>();
                    userJoin.put("email",email.getText().toString());
                    userJoin.put("password",pwd1.getText().toString());
                    userJoin.put("phone",phone1.getText().toString() + phone2.getText().toString() + phone3.getText().toString());

                    try{
                        int genderCheck = genderGroup.getCheckedRadioButtonId();
                        RadioButton gender = findViewById(genderCheck);
                        userJoin.put("gender",gender.getText().toString());
                    }catch (Exception ignored){ }

                    try{
                        userJoin.put("age",age.getText().toString());
                    }catch (Exception ignored){ }

                    Call<Post> Join = apiService.signupAPI(userJoin);
                    Join.enqueue(new Callback<Post>() {
                        @Override
                        public void onResponse(Call<Post> call, Response<Post> response) {
                            Log.i("Join","success");
                            try{
                                Log.i("Join",response.toString());
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onFailure(Call<Post> call, Throwable t) {
                            Log.i("Join","fail");
                        }
                    });

                    Toast.makeText(getApplicationContext(), "회원가입이 완료되었습니다 :)", Toast.LENGTH_SHORT).show();
                    Intent login1 = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(login1);
                    finish();

                }
                break;


            case R.id.cancel:
                Intent login1 = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(login1);
                finish();
                break;
        }
    }


    protected boolean equalEmail(String email){

        Log.i("Email",email);

        HashMap<String, Object> equalEmail = new HashMap<>();
        equalEmail.put("email",email);

        Call<Post> EEqual = apiService.checkEmailAPI(equalEmail);
        EEqual.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {

                Log.i("equalEmail",response.toString());
                try{
                if(response.body().getSuccess()){
                    result = true;
                }
                else{
                    result = false; //이메일중복
                }}catch(Exception e) {
                    e.printStackTrace();
                    result = false;
                }
            }
            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Log.i("equalEmail","fail");
            }
        });

        if(result==true)
            return true;
        else
            return false;
    };

}

