package com.example.bottomnavigationbar.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bottomnavigationbar.HttpsTrustManager;
import com.example.bottomnavigationbar.R;
import com.example.bottomnavigationbar.webservice.APIService;
import com.example.bottomnavigationbar.webservice.ApiUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;

public class ChangePassActivity extends AppCompatActivity {
    EditText edtOldPass, edtNewPass1, edtNewPass2;
    Button btnSubmit;
    ImageView imgBack;
    String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pass);

        Bundle b= getIntent().getBundleExtra("change");
        user= b.getString("user");
        edtOldPass = findViewById(R.id.edtOldPass);
        edtNewPass1 = findViewById(R.id.edtNewPass1);
        edtNewPass2 = findViewById(R.id.edtNewPass2);
        btnSubmit = findViewById(R.id.btnSubmit);
        imgBack = findViewById(R.id.imgBackPass);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String oldPass = edtOldPass.getText().toString().trim();
                String pass1 = edtNewPass1.getText().toString().trim();
                String pass2 = edtNewPass2.getText().toString().trim();
                changePass(user ,oldPass, pass1, pass2);
            }
        });
    }

    private void changePass(String user,String oldPass, String pass1, String pass2) {

        ApiUtils.getAPIService().changePass(user,oldPass,pass1,pass2).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                try {
                    String status = response.body().string().toString().trim();
                    Log.e("response", status);
                    if(status.equals("1")){
                        Toast.makeText(ChangePassActivity.this,"Đổi mật khẩu thành công",Toast.LENGTH_SHORT).show();
                    }else{
                        if(status.equals("0")){
                            Toast.makeText(ChangePassActivity.this,"Mật khẩu cũ không đúng",Toast.LENGTH_SHORT).show();
                        }else{
                            if(status.equals("2")){
                                Toast.makeText(ChangePassActivity.this,"Hai mật khẩu mới không trùng khớp",Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(ChangePassActivity.this,status,Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                } catch (Exception e) {
                    Toast.makeText(ChangePassActivity.this,"Error",Toast.LENGTH_SHORT).show();
                    Log.e("onResponse", "Error");
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(ChangePassActivity.this,t.toString(),Toast.LENGTH_SHORT).show();
                Log.e("onFailure", "Lỗi kết nối");
            }
        });
    }
}