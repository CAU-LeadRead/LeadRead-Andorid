package com.example.catchi_nichi;
import com.google.gson.annotations.SerializedName;

public class Post {

    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    @SerializedName("token")
    private String token;

    @SerializedName("randomNumber")
    private int randomNumber;

    public boolean getSuccess(){
        return success;
    }
    public void setSuccess(boolean success){
        this.success = success;
    }

    public String getMessage(){
        return message;
    }
    public void setMessage(String message){
        this.message = message;
    }

    public String getToken(){
        return token;
    }
    public void setToken(String token){
        this.token=token;
    }

    public int getRandomNumber(){
        return randomNumber;
    }
    public void setRandomNumber(int randomNumber){
        this.randomNumber=randomNumber;
    }
}
