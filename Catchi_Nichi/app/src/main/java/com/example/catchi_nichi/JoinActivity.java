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
    Retrofit retrofit;
    RetrofitAPI apiService;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

    }

    public void onClick(View view) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        EditText email = findViewById(R.id.email);
        EditText phone1 = findViewById(R.id.num1);
        EditText phone2 = findViewById(R.id.num2);
        EditText phone3 = findViewById(R.id.num3);
        RadioGroup gender = findViewById(R.id.radiogroup);
        CheckBox check = findViewById(R.id.certify);
        EditText pwd1 = findViewById(R.id.pwd1);
        EditText pwd2 = findViewById(R.id.pwd2);
        EditText age = findViewById(R.id .age);
        EditText certifyNum = findViewById(R.id.certify_num);

        final String[] temp = new String[1];
        temp[0]=" ";

        switch(view.getId()){

            case R.id.certifynum_btn :
                //Retrofit
                retrofit = new Retrofit.Builder()
                        .baseUrl(RetrofitAPI.API_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                apiService = retrofit.create(RetrofitAPI.class);

                HashMap<String, Object> certify_num = new HashMap<>();
                certify_num.put("phone",phone1.getText().toString()+phone2.getText().toString()+phone3.getText().toString());

                Call<Post> Certify = apiService.certifyNumAPI(certify_num);
                Certify.enqueue(new Callback<Post>() {
                    @Override
                    public void onResponse(Call<Post> call, Response<Post> response) {
                        Log.i("certifyNum","success");
                        temp[0] = response.message();
                    }

                    @Override
                    public void onFailure(Call<Post> call, Throwable t) {
                        Log.i("certifyNum","failure");
                    }
                });
                break;

            case R.id.confirm_btn:
                if(certifyNum.equals(temp)){
                    check.setChecked(true);
                }
                else{
                    Toast.makeText(getApplicationContext(), "인증번호가 알맞지 않습니다.", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.join  :

                if(!check.isChecked()){
                    Toast.makeText(getApplicationContext(), "핸드폰 인증을 해주세요.", Toast.LENGTH_SHORT).show();
                }
                else if(!pattern.matcher(email.getText().toString()).matches()){
                    Toast.makeText(getApplicationContext(), "이메일 정보를 정확히 입력해주세요.", Toast.LENGTH_SHORT).show();
                }
                else if(pwd1.getText().toString().equals("") || pwd1.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show();
                }
                else if(!pwd1.getText().toString().equals(pwd2.getText().toString())){
                    Toast.makeText(getApplicationContext(), "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                }
                else{
                    //Retrofit
                    retrofit = new Retrofit.Builder()
                            .baseUrl(RetrofitAPI.API_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    apiService = retrofit.create(RetrofitAPI.class);

                    HashMap<String, Object> userJoin = new HashMap<>();
                    userJoin.put("email",email.getText().toString());
                    userJoin.put("password",pwd1.getText().toString());
                    userJoin.put("phone",phone1.getText().toString()+phone2.getText().toString()+phone3.getText().toString());
                    userJoin.put("gender",gender.getCheckedRadioButtonId());
                    userJoin.put("age",age.getText().toString());

                    Call<Post> Join = apiService.signupAPI(userJoin);
                    Join.enqueue(new Callback<Post>() {
                        @Override
                        public void onResponse(Call<Post> call, Response<Post> response) {
                            Log.i("Join","success");
                        }

                        @Override
                        public void onFailure(Call<Post> call, Throwable t) {
                            Log.i("Join","failure");
                        }
                    });

                    //서버에 회원가입 정보 등록
                    Toast.makeText(getApplicationContext(), "회원가입이 완료되었습니다 :)", Toast.LENGTH_SHORT).show();
                    Intent login1 = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(login1);
                    finish();
                }
                break;

            case R.id.cancel :
                Intent login1 = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(login1);
                finish();
                break;

        }
    }
}
