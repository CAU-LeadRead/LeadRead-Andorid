package com.example.catchi_nichi;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.HashMap;

public class Post {

    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    @SerializedName("token")
    private String token;

    @SerializedName("randomNumber")
    private int randomNumber;

    @SerializedName("email")
    private String email;

    @SerializedName("nick")
    private String nick;

    @SerializedName("count")
    private Integer count;

    @SerializedName("searchList")
    private ArrayList<HashMap<String, String>> searchList;

    @SerializedName("fragranceList")
    private ArrayList<HashMap<String, String>> fragranceList;


    public Integer getCount(){return count;}
    public String getEmail(){return email;}

    public boolean getSuccess(){
        return success;
    }

    public void setSuccess(boolean success){
        this.success = success;
    }

    public String getMessage(){
        return message;
    }

    public String getNick(){
        return nick;
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

    public ArrayList<HashMap<String, String>> getSearchList() {
        return searchList;
    }
}
