package com.example.bottomnavigationbar.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bottomnavigationbar.R;
import com.example.bottomnavigationbar.webservice.APIService;
import com.example.bottomnavigationbar.webservice.ApiUtils;


import java.net.Inet4Address;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;

public class LoginActivity extends AppCompatActivity {

    private EditText edtUserName;
    private EditText edtPassWord;
    private Button btnLogin;
    private Button btnRegister;
    public static final String SHARED_PREFS = "shared_prefs";
    public static final String EMAIL_KEY = "email_key";
    public static final String PASSWORD_KEY = "password_key";
    public static final String ID_KEY = "id_key";

    SharedPreferences sharedpreferences;
    String email, password,id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sharedpreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        id = sharedpreferences.getString(ID_KEY,null);
        email = sharedpreferences.getString(EMAIL_KEY, null);
        password = sharedpreferences.getString(PASSWORD_KEY, null);
        addControl();
        addEvent();
    }

    private void addEvent() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtUserName.getText().toString().trim();
                String password = edtPassWord.getText().toString().trim();
                if(checkEditText(edtUserName) && checkEditText(edtPassWord)){
                    ApiUtils.getAPIService().login(username,password).enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                            try {
                                String status = response.body().string().toString().trim();
                                Log.e("response", status);
                                if(status.equals("0")){
                                    Toast.makeText(LoginActivity.this, R.string.username_incorred, Toast.LENGTH_SHORT).show();
                                }else{
                                    if(status.equals("2")){
                                        Toast.makeText(LoginActivity.this, R.string.password_incorred, Toast.LENGTH_SHORT).show();
                                    }else{
                                        SharedPreferences.Editor editor = sharedpreferences.edit();
                                        editor.putString(ID_KEY,status);
                                        editor.putString(EMAIL_KEY, edtUserName.getText().toString());
                                        editor.putString(PASSWORD_KEY, edtPassWord.getText().toString());
                                        editor.apply();
                                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                        startActivity(intent);
                                    }
                                }
                            } catch (Exception e) {
                                Toast.makeText(LoginActivity.this,"Error",Toast.LENGTH_SHORT).show();
                                Log.e("onResponse", "Error");
                                e.printStackTrace();
                            }
                        }
                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Toast.makeText(LoginActivity.this,R.string.failed,Toast.LENGTH_SHORT).show();
                            Log.e("onFailure", t.toString());
                        }
                    });
                }
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(email!=null && password!=null){
            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);
        }
    }

    private void addControl() {
        edtUserName = (EditText) findViewById(R.id.editUsername);
        edtPassWord = (EditText) findViewById(R.id.editPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnRegister = (Button) findViewById(R.id.btnRegister);
    }

    private boolean checkEditText(EditText editText) {
        if (editText.getText().toString().trim().length() > 0)
            return true;
        else {
            editText.setError("Vui lòng nhập dữ liệu!");
        }
        return false;
    }
}