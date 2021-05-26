package com.example.catchi_nichi;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

    @SerializedName("review")
    private ArrayList<HashMap<String, Object>> reviewList;

    @SerializedName("recommendList")
    private ArrayList<HashMap<String, String>> recommendList;

    @SerializedName("top")
    private ArrayList top;

    @SerializedName("mid")
    private ArrayList mid;

    @SerializedName("bot")
    private ArrayList bot;

    @SerializedName("mood")
    private String mood;

    @SerializedName("stars")
    private Float star;

    @SerializedName("longevity")
    private Float longevity;

    @SerializedName("countingReview")
    private Integer countingReview;

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

    public ArrayList<HashMap<String, String>> getSearchList() { return searchList; }
    public ArrayList<HashMap<String, Object>> getReviewList() { return reviewList; }
    public ArrayList<HashMap<String, String>> getRecommendList() { return recommendList; }

    public ArrayList getTop(){return top;}
    public ArrayList getMid(){return mid;}
    public ArrayList getBot(){return bot;}


    //smellNote
    @SerializedName("memoList")
    private List<memoList> memoList = new ArrayList<>();
    public List<memoList> getMemoList(){return memoList;}

    public class memoList{
        @SerializedName("id") Integer id;
        public Integer getId(){return id;}

        @SerializedName("kr_brand") String kr_brand;
        public String getBrand(){return kr_brand;}

        @SerializedName("kr_name") String kr_name;
        public String getName(){return kr_name;}

        @SerializedName("comment") String comment;
        public String getComment(){return comment;}

        @SerializedName("updatedAt") String updatedAt;
        public String getUpdateAt(){return updatedAt;}

        @SerializedName("Fragrance") Fragrance fragrance;

        public class Fragrance{
            @SerializedName("img") String img;
            public String getImg(){return img;}

            @SerializedName("likes") Integer likes;
            public Integer getLikes(){return likes;}

            @SerializedName("avgStars") String avgStars;
            public String getStars(){return avgStars;}
        }


    }





}
