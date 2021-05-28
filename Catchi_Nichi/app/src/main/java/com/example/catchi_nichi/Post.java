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
    private ArrayList<HashMap<String, Object>> review;

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
    public ArrayList<HashMap<String, Object>> getReview() { return review; }
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

    //myPage
    @SerializedName("reviewList")
    private List<reviewList> reviewList = new ArrayList<>();
    public List<reviewList> getReviewList(){return reviewList;}

    public class reviewList{
        @SerializedName("id") Integer id;
        public Integer getId(){return id;}

        @SerializedName("kr_brand") String kr_brand;
        public String getKrBrand(){return kr_brand;}

        @SerializedName("kr_name") String kr_name;
        public String getName(){return kr_name;}

        @SerializedName("stars") Float stars;
        public Float getStars(){return stars;}

        @SerializedName("longevity") Float longevity;
        public Float getLongevity(){return longevity;}

        @SerializedName("mood") String mood;
        public String getMood(){return mood;}

        @SerializedName("comment") String comment;
        public String getComment(){return comment;}

        @SerializedName("brand") String brand;
        public String getBrand(){return brand;}

        @SerializedName("en_name") String en_name;
        public String getEnname(){return en_name;}

        @SerializedName("UserNick") String userNick;
        public String getUserNick(){return userNick;}

        @SerializedName("Fragrance") Fragrance fragrance;
        public class Fragrance{
            @SerializedName("img") String img;
            public String getImg(){return img;}
            @SerializedName("brand") String brand;
            public String getBrand(){return brand;}
            @SerializedName("kr_name") String kr_name;
            public String getKr_name(){return kr_name;}
            @SerializedName("kr_brand") String kr_brand;
            public String getKr_brand(){return kr_brand;}
            @SerializedName("en_name") String en_name;
            public String getEn_name(){return en_name;}
            @SerializedName("countingReview") Integer countingReview;
            public Integer getCountingReview(){return countingReview;}
            @SerializedName("likes") Integer likes;
            public Integer getLikes(){return likes;}
            @SerializedName("avgStars") String avgStars;
            public String getAvgStars(){return avgStars;}

        }


    }





}
