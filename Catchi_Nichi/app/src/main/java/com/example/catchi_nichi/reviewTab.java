package com.example.catchi_nichi;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class reviewTab extends Fragment {
    //Retrofit
    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(RetrofitAPI.API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    public static final RetrofitAPI apiService = retrofit.create(RetrofitAPI.class);

    ArrayList<HashMap<String, String>> reviewList ;

    Bitmap bitmap1,bitmap2,bitmap3,bitmap4,bitmap5,bitmap6,bitmap7,bitmap8,bitmap9,bitmap10;
    URL url1,url2,url3,url4,url5,url6,url7,url8,url9,url10;

    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recommend_tab, container, false);
        ImageButton btn1 = view.findViewById(R.id.imgBtn1);
        ImageButton btn2 = view.findViewById(R.id.imgBtn2);
        ImageButton btn3 = view.findViewById(R.id.imgBtn3);
        ImageButton btn4 = view.findViewById(R.id.imgBtn4);
        ImageButton btn5 = view.findViewById(R.id.imgBtn5);
        ImageButton btn6 = view.findViewById(R.id.imgBtn6);
        ImageButton btn7 = view.findViewById(R.id.imgBtn7);
        ImageButton btn8 = view.findViewById(R.id.imgBtn8);
        ImageButton btn9 = view.findViewById(R.id.imgBtn9);
        ImageButton btn10 = view.findViewById(R.id.imgBtn10);

        TextView text1 = view.findViewById(R.id.text1);
        TextView text2 = view.findViewById(R.id.text2);
        TextView text3 = view.findViewById(R.id.text3);
        TextView text4 = view.findViewById(R.id.text4);
        TextView text5 = view.findViewById(R.id.text5);
        TextView text6 = view.findViewById(R.id.text6);
        TextView text7 = view.findViewById(R.id.text7);
        TextView text8 = view.findViewById(R.id.text8);
        TextView text9 = view.findViewById(R.id.text9);
        TextView text10 = view.findViewById(R.id.text10);


        Call<Post> recommend = apiService.searchAPI("","countingReview",10,0,1);
        recommend.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                reviewList = response.body().getSearchList();
                Log.i("reviewInfo","success");
                Log.i("reviewInfo",response.toString());
                Log.i("reviewResult", String.valueOf(reviewList));

                Thread mThread = new Thread(){
                    public void run(){
                        try{
                            try{
                                url1 = new URL(reviewList.get(0).get("img"));}
                            catch(Exception e){
                                url1 = new URL("https://catchinichi.s3.ap-northeast-2.amazonaws.com/+Poppy+%26+Barley.jpg");
                            }
                            HttpURLConnection conn1 = (HttpURLConnection) url1.openConnection();
                            conn1.setDoInput(true);
                            conn1.connect();
                            InputStream is1 = conn1.getInputStream();
                            bitmap1 = BitmapFactory.decodeStream(is1);

                            try{
                                url2 = new URL(reviewList.get(1).get("img"));}
                            catch(Exception e){
                                url2 = new URL("https://catchinichi.s3.ap-northeast-2.amazonaws.com/+Poppy+%26+Barley.jpg");
                            }
                            HttpURLConnection conn2 = (HttpURLConnection) url2.openConnection();
                            conn2.setDoInput(true);
                            conn2.connect();
                            InputStream is2 = conn2.getInputStream();
                            bitmap2 = BitmapFactory.decodeStream(is2);

                            try{
                                url3 = new URL(reviewList.get(2).get("img"));}
                            catch(Exception e){
                                url3 = new URL("https://catchinichi.s3.ap-northeast-2.amazonaws.com/+Poppy+%26+Barley.jpg");
                            }
                            HttpURLConnection conn3 = (HttpURLConnection) url3.openConnection();
                            conn3.setDoInput(true);
                            conn3.connect();
                            InputStream is3 = conn3.getInputStream();
                            bitmap3 = BitmapFactory.decodeStream(is3);

                            try{
                                url4 = new URL(reviewList.get(3).get("img"));}
                            catch(Exception e){
                                url4 = new URL("https://catchinichi.s3.ap-northeast-2.amazonaws.com/+Poppy+%26+Barley.jpg");
                            }
                            HttpURLConnection conn4 = (HttpURLConnection) url4.openConnection();
                            conn4.setDoInput(true);
                            conn4.connect();
                            InputStream is4 = conn4.getInputStream();
                            bitmap4 = BitmapFactory.decodeStream(is4);

                            try{
                                url5 = new URL(reviewList.get(4).get("img"));}
                            catch(Exception e){
                                url5 = new URL("https://catchinichi.s3.ap-northeast-2.amazonaws.com/+Poppy+%26+Barley.jpg");
                            }
                            HttpURLConnection conn5 = (HttpURLConnection) url5.openConnection();
                            conn5.setDoInput(true);
                            conn5.connect();
                            InputStream is5 = conn5.getInputStream();
                            bitmap5 = BitmapFactory.decodeStream(is5);

                            try{
                                url6 = new URL(reviewList.get(5).get("img"));}
                            catch(Exception e){
                                url6 = new URL("https://catchinichi.s3.ap-northeast-2.amazonaws.com/+Poppy+%26+Barley.jpg");
                            }
                            HttpURLConnection conn6 = (HttpURLConnection) url6.openConnection();
                            conn6.setDoInput(true);
                            conn6.connect();
                            InputStream is6 = conn6.getInputStream();
                            bitmap6 = BitmapFactory.decodeStream(is6);

                            try{
                                url7 = new URL(reviewList.get(6).get("img"));}
                            catch(Exception e){
                                url7 = new URL("https://catchinichi.s3.ap-northeast-2.amazonaws.com/+Poppy+%26+Barley.jpg");
                            }
                            HttpURLConnection conn7 = (HttpURLConnection) url7.openConnection();
                            conn7.setDoInput(true);
                            conn7.connect();
                            InputStream is7 = conn7.getInputStream();
                            bitmap7 = BitmapFactory.decodeStream(is7);

                            try{
                                url8 = new URL(reviewList.get(7).get("img"));}
                            catch(Exception e){
                                url8 = new URL("https://catchinichi.s3.ap-northeast-2.amazonaws.com/+Poppy+%26+Barley.jpg");
                            }
                            HttpURLConnection conn8 = (HttpURLConnection) url8.openConnection();
                            conn8.setDoInput(true);
                            conn8.connect();
                            InputStream is8 = conn8.getInputStream();
                            bitmap8 = BitmapFactory.decodeStream(is8);

                            try{
                                url9 = new URL(reviewList.get(8).get("img"));}
                            catch(Exception e){
                                url9 = new URL("https://catchinichi.s3.ap-northeast-2.amazonaws.com/+Poppy+%26+Barley.jpg");
                            }
                            HttpURLConnection conn9 = (HttpURLConnection) url9.openConnection();
                            conn9.setDoInput(true);
                            conn9.connect();
                            InputStream is9 = conn9.getInputStream();
                            bitmap9 = BitmapFactory.decodeStream(is9);

                            try{
                                url10 = new URL(reviewList.get(9).get("img"));}
                            catch(Exception e){
                                url10 = new URL("https://catchinichi.s3.ap-northeast-2.amazonaws.com/+Poppy+%26+Barley.jpg");
                            }
                            HttpURLConnection conn10 = (HttpURLConnection) url10.openConnection();
                            conn10.setDoInput(true);
                            conn10.connect();
                            InputStream is10 = conn10.getInputStream();
                            bitmap10 = BitmapFactory.decodeStream(is10);

                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                };
                mThread.start();

                try{
                    mThread.join();
                    btn1.setImageBitmap(bitmap1);
                    btn2.setImageBitmap(bitmap2);
                    btn3.setImageBitmap(bitmap3);
                    btn4.setImageBitmap(bitmap4);
                    btn5.setImageBitmap(bitmap5);
                    btn6.setImageBitmap(bitmap6);
                    btn7.setImageBitmap(bitmap7);
                    btn8.setImageBitmap(bitmap8);
                    btn9.setImageBitmap(bitmap9);
                    btn10.setImageBitmap(bitmap10);

                    text1.setText("\n"+"1위: " + reviewList.get(0).get("kr_name") + "\n" + reviewList.get(0).get("brand") + "\n" + "Likes : " + reviewList.get(0).get("likes")+ "\n" + "리뷰수 : " + reviewList.get(0).get("countingReview")+  "\n" +"평균별점 : " + reviewList.get(0).get("avgStars")+ "\n"+ "\n"+ "\n"+ "\n");
                    text2.setText("\n"+"2위: " + reviewList.get(1).get("kr_name") + "\n" + reviewList.get(1).get("brand") + "\n" + "Likes : " + reviewList.get(1).get("likes")+ "\n" + "리뷰수 : " + reviewList.get(1).get("countingReview")+  "\n" +"평균별점 : " + reviewList.get(1).get("avgStars")+"\n"+ "\n"+ "\n"+ "\n");
                    text3.setText("\n"+"3위: " + reviewList.get(2).get("kr_name") + "\n" + reviewList.get(2).get("brand") + "\n" + "Likes : " + reviewList.get(2).get("likes")+ "\n" + "리뷰수 : " + reviewList.get(2).get("countingReview")+  "\n" +"평균별점 : " + reviewList.get(2).get("avgStars")+"\n"+ "\n"+ "\n"+ "\n");
                    text4.setText("\n"+"4위: " + reviewList.get(3).get("kr_name") + "\n" + reviewList.get(3).get("brand") + "\n" + "Likes : " + reviewList.get(3).get("likes")+ "\n" + "리뷰수 : " + reviewList.get(3).get("countingReview")+  "\n" +"평균별점 : " + reviewList.get(3).get("avgStars")+"\n"+ "\n"+ "\n"+ "\n");
                    text5.setText("\n"+"5위: " + reviewList.get(4).get("kr_name") + "\n" + reviewList.get(4).get("brand") + "\n" + "Likes : " + reviewList.get(4).get("likes")+ "\n" + "리뷰수 : " + reviewList.get(4).get("countingReview")+  "\n" +"평균별점 : " + reviewList.get(4).get("avgStars")+"\n"+ "\n"+ "\n"+ "\n");
                    text6.setText("\n"+"6위: " + reviewList.get(5).get("kr_name") + "\n" + reviewList.get(5).get("brand") + "\n" + "Likes : " + reviewList.get(5).get("likes")+ "\n" + "리뷰수 : " + reviewList.get(5).get("countingReview")+  "\n" +"평균별점 : " + reviewList.get(5).get("avgStars")+"\n"+ "\n"+ "\n"+ "\n");
                    text7.setText("\n"+"7위: " + reviewList.get(6).get("kr_name") + "\n" + reviewList.get(6).get("brand") + "\n" + "Likes : " + reviewList.get(6).get("likes")+ "\n" + "리뷰수 : " + reviewList.get(6).get("countingReview")+  "\n" +"평균별점 : " + reviewList.get(6).get("avgStars")+"\n"+ "\n"+ "\n"+ "\n");
                    text8.setText("\n"+"8위: " + reviewList.get(7).get("kr_name") + "\n" + reviewList.get(7).get("brand") + "\n" + "Likes : " + reviewList.get(7).get("likes")+ "\n" + "리뷰수 : " + reviewList.get(7).get("countingReview")+  "\n" +"평균별점 : " + reviewList.get(7).get("avgStars")+"\n"+ "\n"+ "\n"+ "\n");
                    text9.setText("\n"+"9위: " + reviewList.get(8).get("kr_name") + "\n" + reviewList.get(8).get("brand") + "\n" + "Likes : " + reviewList.get(8).get("likes")+ "\n" + "리뷰수 : " + reviewList.get(8).get("countingReview")+  "\n" +"평균별점 : " + reviewList.get(8).get("avgStars")+"\n"+ "\n"+ "\n"+ "\n");
                    text10.setText("\n"+"10위: " + reviewList.get(9).get("kr_name") + "\n" + reviewList.get(9).get("brand") + "\n" + "Likes : " + reviewList.get(9).get("likes")+ "\n" + "리뷰수 : " + reviewList.get(9).get("countingReview")+  "\n" +"평균별점 : " + reviewList.get(9).get("avgStars")+"\n"+ "\n"+ "\n"+ "\n");

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                t.printStackTrace();
                Log.i("review","fail");
            }
        });

        return view;
    }
}
