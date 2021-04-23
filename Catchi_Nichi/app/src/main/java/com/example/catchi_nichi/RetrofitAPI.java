package com.example.catchi_nichi;

import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RetrofitAPI {
    public static final String API_URL = "http://10.0.2.2:8001/";

    @FormUrlEncoded
    @POST("user/kakao")
    Call <Post> userInfoAPI(@FieldMap HashMap<String, Object> param);

    @FormUrlEncoded
    @POST("user/signup")
    Call <Post> signupAPI(@FieldMap HashMap<String,Object> param);

    @FormUrlEncoded
    @POST("user/vertifyPhone")
    Call <Post> certifyNumAPI(@FieldMap HashMap<String,Object> param);

}


