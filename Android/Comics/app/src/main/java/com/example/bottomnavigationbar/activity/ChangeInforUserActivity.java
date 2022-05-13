package com.example.bottomnavigationbar.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.example.bottomnavigationbar.HttpsTrustManager;
import com.example.bottomnavigationbar.R;
import com.example.bottomnavigationbar.RealPathUtil;
import com.example.bottomnavigationbar.object.Account;
import com.example.bottomnavigationbar.object.User;
import com.example.bottomnavigationbar.webservice.ApiUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ChangeInforUserActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private String id,user_name,email,gioiTinh,ngaySinh,sdt,diaChi="0",imgName="person.png",imgCode="0",date,radio="0",imgOldName;
    private Integer day=0,month=0,year=0;
    private TextView mDisplayDate,tvUsername;
    private RadioGroup radioGroup;
    private EditText editUsername,edtEmail,edtPhone,edtAddress;
    private ImageView imageView,imvBack;
    private ImageButton imgbt;
    private TextView btnSubmit;
    private RadioButton radioButton,rbNam,rbNu;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private static final int MY_REQUEST_CODE = 101;
    private Uri mUri;
    List<User> accountList =new ArrayList<>();
    Bitmap bitmap;
    SwipeRefreshLayout swipeRefreshLayout;

    public static final String SHARED_PREFS = "shared_prefs";
    public static final String EMAIL_KEY = "email_key";
    public static final String ID_KEY = "id_key";
    SharedPreferences sharedpreferences;

    public ActivityResultLauncher<Intent> mActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    Log.e(TAG,"onActivityResult");
                    if(result.getResultCode()== Activity.RESULT_OK){
                        Intent data = result.getData();
                        if(data==null){
                            return;
                        }
                        Uri uri = data.getData();
                        mUri=uri;
                        try {
                            bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                            imageView.setImageBitmap(bitmap);
                            getInforImage();

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_infor_user);
        init();
        loadData();
        setClick();
    }

    private void loadData() {
        sharedpreferences = this.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        user_name = sharedpreferences.getString(EMAIL_KEY, null);
        id = sharedpreferences.getString(ID_KEY, null);
        tvUsername.setText(user_name);

        ApiUtils.getAPIService().getInforUser(user_name).enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                try {
                    accountList.clear();
                    for (int i = 0; i < response.body().size(); i++) {
                        User account = new User(response.body().get(i).getId(),
                                response.body().get(i).getName(),
                                response.body().get(i).getEmail(),
                                response.body().get(i).getGender(),
                                response.body().get(i).getBirthday(),
                                response.body().get(i).getAddress(),
                                response.body().get(i).getPhone_number(),
                                response.body().get(i).getImage_path(),
                                response.body().get(i).getImage_name()

                        );
                        accountList.add(account);
                    }
                    setUpData(accountList);
                    //Toast.makeText(ChangeInforUserActivity.this, accountList.get(0).getEmail(), Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(ChangeInforUserActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setUpData(List<User> accountList) {
        edtEmail.setText(accountList.get(0).getEmail());
        date=(accountList.get(0).getBirthday());
        edtPhone.setText(accountList.get(0).getPhone_number());
        String address = accountList.get(0).getAddress();
        edtAddress.setText(address);
        String dateData = accountList.get(0).getBirthday();

        mDisplayDate.setText(dateData);
        imgOldName=accountList.get(0).getImage_name();
        if(imgOldName==null){
            imgName="0";
        }else{
            HttpsTrustManager.allowAllSSL();
            String url=getString(R.string.URL_API_IMAGE)+"/storage/users/"+imgOldName;
            //String url="https://cdn.nettruyen.vn/file/nettruyen/thumbnails/do-thi-chi-nghich-thien-tien-ton.jpg";
            Glide.with(this).load(url).into(imageView);
            // Picasso.get().load(url).networkPolicy(NetworkPolicy.OFFLINE).into(imageView);
            //Toast.makeText(this, url, Toast.LENGTH_SHORT).show();
        }

        if(accountList.get(0).getGender().equals("Nam"))
            rbNam.setChecked(true);
        if(accountList.get(0).getGender().equals("Nữ"))
            rbNu.setChecked(true);

        tvUsername.setText(accountList.get(0).getName());
        user_name=accountList.get(0).getName();
        imgCode="0";
    }

    private void getInforImage() {
        String strRealPath = RealPathUtil.getRealPath(this,mUri);
        File file = new File(strRealPath);
        imgCode = getBitMap(bitmap);
        imgName = file.getName();
    }

    private void postApi() {
        email = edtEmail.getText().toString();
        if(radio.equals("")||radio.equals("null")){
            radio="0";
        }
        gioiTinh = radio;

        ngaySinh = date;
        sdt     = edtPhone.getText().toString();
        diaChi  =edtAddress.getText().toString();
        if(diaChi.equals("")||diaChi.equals("null")){
            diaChi="0";
        }
        ApiUtils.getAPIService().changeInforUser(id,user_name,email,gioiTinh,ngaySinh,sdt,diaChi,imgName,imgCode,imgOldName).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                try {
                    String status=response.body().toString();
                    if(status.equals("1")){
                        loadData();
                        Toast.makeText(ChangeInforUserActivity.this, R.string.update, Toast.LENGTH_SHORT).show();
                    }else{
                        loadData();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(ChangeInforUserActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void onClickRequestPermission() {
        if(Build.VERSION.SDK_INT<Build.VERSION_CODES.M){
            openGallery();
            return;
        }
        if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED){
            openGallery();

        }else{
            //Toast.makeText(ChangeInforUserActivity.this, "click", Toast.LENGTH_SHORT).show();
            String [] permission = {Manifest.permission.READ_EXTERNAL_STORAGE};
            requestPermissions(permission,MY_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull  int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode ==MY_REQUEST_CODE){
            if(grantResults.length > 0 && grantResults[0] ==PackageManager.PERMISSION_GRANTED){
                openGallery();
            }
        }

    }
    private void setClick() {
        imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(ChangeInforUserActivity.this, R.string.f5, Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 500);

                loadData();

            }
        });
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {

                loadData();
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isValidEmail(edtEmail.getText().toString())){
                    postApi();

                }
                //loadData();
            }
        });

        imgbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickRequestPermission();
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                radioButton = findViewById(i);
                radio=radioButton.getText().toString();
                //Toast.makeText(ChangeInforUserActivity.this, radio, Toast.LENGTH_SHORT).show();
            }
        });

        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                year = cal.get(Calendar.YEAR);
                month = cal.get(Calendar.MONTH);
                day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        ChangeInforUserActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                date = year + "/" + month + "/" + day;
                if(year<2020){
                    mDisplayDate.setText(date);
                }else{
                    Toast.makeText(ChangeInforUserActivity.this, R.string.check_date, Toast.LENGTH_SHORT).show();
                }
            }
        };
    }

    private String getBitMap(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 50, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    private void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        mActivityResultLauncher.launch(Intent.createChooser(intent,"Select Picture"));

    }

    private void init() {
        swipeRefreshLayout =findViewById(R.id.swipeRefresh);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,R.color.colorPrimaryDark);
        tvUsername = findViewById(R.id.tvUsername);
        edtEmail = findViewById(R.id.edtEmail);
        edtPhone = findViewById(R.id.edtPhone);
        edtAddress = findViewById(R.id.edtAddress);
        mDisplayDate = (TextView) findViewById(R.id.tv_Date);
        radioGroup = findViewById(R.id.rdg);
        imageView = (ImageView) findViewById(R.id.civUser);
        imgbt = findViewById(R.id.imgbt);
        btnSubmit = findViewById(R.id.btnSubmit);
        rbNam=findViewById(R.id.rbNam);
        rbNu=findViewById(R.id.rbNu);
        imvBack=findViewById(R.id.imvBack);
    }

    private boolean isValidEmail(String target) {
        if (target.matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+"))
            return true;
        else {
            edtEmail.setError("Email sai định dạng!");
        }
        return false;
    }
}