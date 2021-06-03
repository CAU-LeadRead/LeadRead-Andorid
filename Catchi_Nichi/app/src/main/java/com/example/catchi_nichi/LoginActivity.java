package com.example.catchi_nichi;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
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

import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.ApiErrorCode;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeV2ResponseCallback;
import com.kakao.usermgmt.response.MeV2Response;
import com.kakao.util.exception.KakaoException;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    private SessionCallback sessionCallback;

    //Retrofit
    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(RetrofitAPI.API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    public static final RetrofitAPI apiService = retrofit.create(RetrofitAPI.class);

    EditText email;
    EditText pwd;

    //kakao
    String snsId;
    String emails;
    int age;
    String gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        sessionCallback = new SessionCallback();
        Session.getCurrentSession().addCallback(sessionCallback);


        //키보드 숨기기
        email = findViewById(R.id.emailText);
        pwd = findViewById(R.id.pwdText);

        ConstraintLayout layout = findViewById(R.id.layout);
        layout.setOnClickListener(v -> {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(email.getWindowToken(), 0);
            imm.hideSoftInputFromWindow(pwd.getWindowToken(), 0);
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
            super.onActivityResult(requestCode, resultCode, data);
            return;
        }
    }

    public void onClick(View view) {


        switch(view.getId()){

            case R.id.join_btn:
                Intent join = new Intent(getApplicationContext(), JoinActivity.class);
                startActivity(join);
                finish();
                break;

            case R.id.login_btn:

                HashMap <String, Object> loginInfo = new HashMap<>();
                loginInfo.put("email",email.getText().toString());
                loginInfo.put("password",pwd.getText().toString());
                Call<Post> Login = apiService.LoginAPI(loginInfo);

                Login.enqueue(new Callback<Post>() {
                                  @Override
                                  public void onResponse(Call<Post> call, Response<Post> response) {
                                      try {
                                          //Log.i("Login",response.body().toString());
                                          Log.i("Login",response.toString());
                                      if(response.body().getSuccess()){
                                          Log.i("Login","success");
                                          Intent login = new Intent(getApplicationContext(), MainActivity.class);
                                          login.putExtra("nick",response.body().getNick());
                                          startActivity(login);
                                          finish();
                                      }
                                      else{
                                          Log.i("Login","fail");
                                          Toast.makeText(getApplicationContext(), "가입하지 않은 아이디이거나 비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                                      }}catch(Exception e){
                                          Log.i("Login","fail");
                                          Toast.makeText(getApplicationContext(), "가입하지 않은 아이디이거나 비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                                          e.printStackTrace();
                                      }
                                  }

                                  @Override
                                  public void onFailure(Call<Post> call, Throwable t) {}
                              });
                break;
        }
    }

    /**@Override
    protected void onDestroy() {
        super.onDestroy();
        Session.getCurrentSession().removeCallback(sessionCallback);
    }**/

    private class SessionCallback implements ISessionCallback {
        @Override
        public void onSessionOpened() {
            UserManagement.getInstance().me(new MeV2ResponseCallback() {
                @Override
                public void onFailure(ErrorResult errorResult) {
                    int result = errorResult.getErrorCode();

                    if (result == ApiErrorCode.CLIENT_ERROR_CODE) {
                        Toast.makeText(getApplicationContext(), "네트워크 연결이 불안정합니다. 다시 시도해 주세요.", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "로그인 도중 오류가 발생했습니다: " + errorResult.getErrorMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onSessionClosed(ErrorResult errorResult) {
                    Toast.makeText(getApplicationContext(), "세션이 닫혔습니다. 다시 시도해 주세요: " + errorResult.getErrorMessage(), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onSuccess(MeV2Response result) {
                    getInfo(result);
                }

            });

        }

        @Override
        public void onSessionOpenFailed(KakaoException e) {
           Toast.makeText(getApplicationContext(), "카카오 로그인이 종료되었습니다.", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean getInfo(MeV2Response result) {
        PackageInfo packageInfo = null;

        try {
            packageInfo = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);

            if (packageInfo == null) {
                Log.e("Info", "Info:null");
                return false;
            }

            for (Signature signature : packageInfo.signatures) {

                try{
                    String[] low = result.getKakaoAccount().getAgeRange().getValue().split("~");
                    age = (Integer.parseInt(low[0]) + Integer.parseInt(low[1])) / 2;
                }catch (Exception e){
                    age = 1;
                }

                emails =  String.valueOf(result.getKakaoAccount().getEmail());
                snsId =  String.valueOf(result.getId());
                gender = String.valueOf(result.getKakaoAccount().getGender());


                HashMap<String, Object> userInfo = new HashMap<>();
                userInfo.put("email", emails);

                Call<Post> searchNick = apiService.searchNickAPI(userInfo);
                searchNick.enqueue(new Callback<Post>() {
                    @Override
                    public void onResponse(Call<Post> call, Response<Post> response) {
                        if (response.body().getSuccess()) {
                            Log.i("kakaologin", "user");
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            intent.putExtra("nick", response.body().getNick());
                            startActivity(intent);
                            finish();
                        }
                        else{
                            Log.i("kakaologin", "first access");
                            Intent intent = new Intent(getApplicationContext(), NicknameActivity.class);
                            intent.putExtra("email", emails);
                            intent.putExtra("snsId", snsId);
                            intent.putExtra("age", age);
                            intent.putExtra("gender", gender);
                            intent.putExtra("join", "kakao");
                            startActivity(intent);
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<Post> call, Throwable t) {
                        Log.i("kakaologin", "fail ");
                        Intent intent = new Intent(getApplicationContext(), NicknameActivity.class);
                        intent.putExtra("email", emails);
                        intent.putExtra("snsId", snsId);
                        intent.putExtra("age", age);
                        intent.putExtra("gender", gender);
                        intent.putExtra("join", "kakao");
                        startActivity(intent);
                        finish();
                    }
                });
            }
            return true;

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
    }


