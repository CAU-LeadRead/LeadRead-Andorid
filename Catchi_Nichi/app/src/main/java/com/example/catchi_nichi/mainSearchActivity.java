package com.example.catchi_nichi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class mainSearchActivity extends AppCompatActivity {

    Fragment recommend_tab , review_tab, star_tab ;
    String nick;

    //Retrofit
    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(RetrofitAPI.API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static final RetrofitAPI apiService = retrofit.create(RetrofitAPI.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchmain);

        //수신데이터
        Intent intent = getIntent();
        nick = intent.getStringExtra("nick");

        //tabLayout
        recommend_tab = new recommendTab();
        review_tab = new reviewTab();
        star_tab = new starTab();

        getSupportFragmentManager().beginTransaction().add(R.id.frame, recommend_tab).commit();
        TabLayout tabs = (TabLayout) findViewById (R.id.tabs);

        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                int position = tab.getPosition();

                Fragment selected = null;
                if(position == 0){
                    selected = recommend_tab;
                }
                else if(position ==1){
                    selected = review_tab;
                }
                else if(position ==2){
                    selected = star_tab;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.frame, selected).commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    //buttonListener 구현
    public void onClick(View v) {

        EditText shText = findViewById(R.id.search);
        String searchText = shText.getText().toString();

        switch (v.getId()){

            case R.id.searchButton:

                Call<Post> search = apiService.searchAPI(searchText,"likes", 10,0);
                search.enqueue(new Callback<Post>() {
                    @Override
                    public void onResponse(Call<Post> call, Response<Post> response) {
                        Log.i("searchInfo","success");
                        Log.i("searchInfo",response.toString());

                        //발신 데이터
                        Intent intent = new Intent(getApplicationContext(), searchResultActivity.class);
                        try{
                        intent.putExtra("searchList",response.body().getSearchList());}
                        catch (Exception e){
                            e.printStackTrace();
                        }
                        intent.putExtra("getCount",response.body().getCount());
                        intent.putExtra("enterSearch",searchText);
                        intent.putExtra("nick",nick);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onFailure(Call<Post> call, Throwable t) {
                        Log.i("searchInfo","fail");
                        t.printStackTrace();
                    }

                });
                break;

            case R.id.home_btn:
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("nick",nick);
                startActivity(intent);
                finish();
                break;


        }

    }

}
