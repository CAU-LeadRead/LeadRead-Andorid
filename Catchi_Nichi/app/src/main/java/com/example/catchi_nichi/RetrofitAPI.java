package com.example.catchi_nichi;

import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RetrofitAPI {
    public static final String API_URL = "https://catchi-nichi.tk/";

    @FormUrlEncoded
    @POST("user/kakao")
    Call <Post> userInfoAPI(@FieldMap HashMap<String, Object> param);

    @FormUrlEncoded
    @POST("user/signup")
    Call <Post> signupAPI(@FieldMap HashMap<String,Object> param);

    @FormUrlEncoded
    @POST("user/verifyPhone")
    Call <Post> certifyNumAPI(@FieldMap HashMap<String,Object> param);

    @FormUrlEncoded
    @POST("user/checkEmail")
    Call <Post>  checkEmailAPI(@FieldMap HashMap<String, Object>param);

    @FormUrlEncoded
    @POST("user/signin")
    Call <Post>  LoginAPI(@FieldMap HashMap<String, Object>param);

}


