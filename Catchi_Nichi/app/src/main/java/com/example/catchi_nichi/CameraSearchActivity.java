package com.example.catchi_nichi;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;


import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.NameValuePair;
import cz.msebera.android.httpclient.client.ClientProtocolException;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.entity.UrlEncodedFormEntity;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import cz.msebera.android.httpclient.message.BasicNameValuePair;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CameraSearchActivity extends AppCompatActivity implements View.OnClickListener {

    //Retrofit
    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(RetrofitAPI.API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    public static final RetrofitAPI apiService = retrofit.create(RetrofitAPI.class);
    private static final String TAG = "catchiNichi";

    String nick;

    private Boolean isPermission = true;
    private static final int PICK_FROM_ALBUM = 1;
    private static final int PICK_FROM_CAMERA = 2;
    private File tempFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camerasearch);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        //수신데이터
        Intent intent10 = getIntent();
        nick = intent10.getStringExtra("nick");

        tedPermission();
    }


    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.home_btn:
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("nick", nick);
                startActivity(intent);
                finish();
                break;

            case R.id.btnGallery:
                if (isPermission) goToAlbum();
                else
                    Toast.makeText(v.getContext(), getResources().getString(R.string.permission_2), Toast.LENGTH_LONG).show();
                break;

            case R.id.btnCamera:
                if (isPermission) takePhoto();
                else
                    Toast.makeText(v.getContext(), getResources().getString(R.string.permission_2), Toast.LENGTH_LONG).show();
                break;

            case R.id.smellnote_btn:
                Intent intent3 = new Intent(getApplicationContext(), SmellNoteMainActivity.class);
                intent3.putExtra("nick", nick);
                startActivity(intent3);
                finish();
                break;

            case R.id.recommend_btn:
                Intent intent5 = new Intent(getApplicationContext(), RecommendMainActivity.class);
                intent5.putExtra("nick", nick);
                startActivity(intent5);
                finish();
                break;

            case R.id.camera_search:
                Intent intent6 = new Intent(getApplicationContext(), CameraSearchActivity.class);
                intent6.putExtra("nick", nick);
                startActivity(intent6);
                finish();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) {
            Toast.makeText(this, "취소 되었습니다.", Toast.LENGTH_SHORT).show();

            if (tempFile != null) {
                if (tempFile.exists()) {
                    if (tempFile.delete()) {
                        Log.e(TAG, tempFile.getAbsolutePath() + " 삭제 성공");
                        tempFile = null;
                    }
                }
            }

            return;
        }

        if (requestCode == PICK_FROM_ALBUM) {

            Uri photoUri = data.getData();
            Log.d(TAG, "PICK_FROM_ALBUM photoUri : " + photoUri);
            Cursor cursor = null;

            try {
                String[] proj = {MediaStore.Images.Media.DATA};
                assert photoUri != null;
                cursor = getContentResolver().query(photoUri, proj, null, null, null);
                assert cursor != null;
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                cursor.moveToFirst();
                tempFile = new File(cursor.getString(column_index));
                Log.d(TAG, "tempFile Uri : " + Uri.fromFile(tempFile));

            } finally {
                if (cursor != null) {
                    cursor.close();
                }
            }
            setImage();

        }
        else if (requestCode == PICK_FROM_CAMERA) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            if (bitmap != null) {
                ImageView imageView = findViewById(R.id.imageView);
                imageView.setImageBitmap(bitmap);
                uploadPhoto(bitmap);
            }

        }
    }

    /**
     * 앨범에서 이미지 가져오기
     */
    private void goToAlbum() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
        startActivityForResult(intent, PICK_FROM_ALBUM);
    }


    /**
     * 카메라에서 이미지 가져오기
     */
    private void takePhoto() {

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, PICK_FROM_CAMERA);

    }

    /**
     * tempFile 을 bitmap 으로 변환 후 ImageView 에 설정한다.
     */
    private void setImage() {

        BitmapFactory.Options options = new BitmapFactory.Options();
        Bitmap originalBm = BitmapFactory.decodeFile(tempFile.getAbsolutePath(), options);
        Log.d(TAG, "setImage : " + tempFile.getAbsolutePath());
        ImageView imageView = findViewById(R.id.imageView);
        imageView.setImageBitmap(originalBm);
        uploadPhoto(originalBm);

        /**
         *  tempFile 사용 후 null 처리를 해줘야 합니다.
         *  (resultCode != RESULT_OK) 일 때 tempFile 을 삭제하기 때문에
         *  기존에 데이터가 남아 있게 되면 원치 않은 삭제가 이뤄집니다.
         */
        tempFile = null;

    }

    /**
     * 권한 설정
     */
    private void tedPermission() {
        PermissionListener permissionListener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                // 권한 요청 성공
                isPermission = true;

            }

            @Override
            public void onPermissionDenied(ArrayList<String> deniedPermissions) {
                // 권한 요청 실패
                isPermission = false;

            }
        };

        TedPermission.with(this)
                .setPermissionListener(permissionListener)
                .setRationaleMessage(getResources().getString(R.string.permission_2))
                .setDeniedMessage(getResources().getString(R.string.permission_1))
                .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
                .check();
    }


    //서버에 bitmap 이미지 업로드
    private void uploadPhoto(final Bitmap bitmap) {

        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();

            //base64
            String temp = Base64.encodeToString(byteArray, Base64.DEFAULT);

            HashMap<String, Object> searchMap = new HashMap<>();
            searchMap.put("file",temp);

            Call<Post> search = apiService.searchPicAPI(searchMap);
                search.enqueue(new Callback<Post>() {
                    @Override
                    public void onResponse(Call<Post> call, Response<Post> response) {
                        Log.i("CameraSearchActivity", response.toString());

                        try{
                            //발신 데이터
                            Intent intent = new Intent(getApplicationContext(), CameraResultActivity.class);
                            intent.putExtra("searchList",response.body().getSearchList());
                            intent.putExtra("nick",nick);
                            startActivity(intent);
                            finish();
                        }
                        catch (Exception e){
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<Post> call, Throwable t) {

                    }
                });

        } catch (Exception e) {
            e.printStackTrace();

        }


    }
}
