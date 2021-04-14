package com.example.catchi_nichi;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Pattern;

public class JoinActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

    }

    public void onClick(View view) {
        switch(view.getId()){

            case R.id.join  :

                Pattern pattern = Patterns.EMAIL_ADDRESS;
                EditText email = findViewById(R.id.email);
                CheckBox check = findViewById(R.id.certify);
                EditText pwd1 = findViewById(R.id.pwd1);
                EditText pwd2 = findViewById(R.id.pwd2);

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
