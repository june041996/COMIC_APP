package com.example.bottomnavigationbar.Fragments;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.bumptech.glide.Glide;
import com.example.bottomnavigationbar.HttpsTrustManager;
import com.example.bottomnavigationbar.R;
import com.example.bottomnavigationbar.activity.ChangeInforUserActivity;
import com.example.bottomnavigationbar.activity.ChangePassActivity;
import com.example.bottomnavigationbar.activity.LoginActivity;
import com.example.bottomnavigationbar.object.Account;
import com.example.bottomnavigationbar.object.User;
import com.example.bottomnavigationbar.webservice.ApiUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProfileFragment extends Fragment  {

    public static final String SHARED_PREFS = "shared_prefs";
    public static final String EMAIL_KEY = "email_key";
    public static final String ID_KEY = "id_key";
    private SharedPreferences sharedpreferences;
    private String user,id;
    //private Button btnDoiTt,btnChangePass,btnLogout;
    private ImageView imgUser;
    private List<User> accountList = new ArrayList<>();
    private SwipeRefreshLayout swipeRefreshLayout;
    private TextView tvUsername,btnDoiTt,btnChangePass,btnLogout;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile,null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        loadData();
        setClick();
    }
    private void loadData() {
        tvUsername.setText(""+user);
        ApiUtils.getAPIService().getInforUser(user).enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                try {
                    accountList.clear();
                    for (int i = 0; i < response.body().size(); i++) {
                        User account = new User(
                                response.body().get(i).getId(),
                                response.body().get(i).getName(),
                                response.body().get(i).getEmail(),
                                response.body().get(i).getGender(),
                                response.body().get(i).getBirthday(),
                                response.body().get(i).getPhone_number(),
                                response.body().get(i).getAddress(),
                                response.body().get(i).getImage_path(),
                                response.body().get(i).getImage_name()

                        );
                        accountList.add(account);
                    }
                    String image = accountList.get(0).getImage_path();
                    if(image==null){
                        image=getContext().getString(R.string.image_null);
                    }
                        HttpsTrustManager.allowAllSSL();
                        String url=getString(R.string.URL_API_IMAGE)+image;
                        //String url="https://cdn.nettruyen.vn/file/nettruyen/thumbnails/do-thi-chi-nghich-thien-tien-ton.jpg";
                        Glide.with(getContext()).load(url).into(imgUser);

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(getContext(), "Lỗi kết nối", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setClick() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(getContext() , "Refresh", Toast.LENGTH_SHORT).show();
                loadData();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        btnDoiTt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), ChangeInforUserActivity.class));
            }
        });

        btnChangePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle b = new Bundle();
                b.putString("user",user);
                Intent intent  = new Intent(getContext(), ChangePassActivity.class);
                intent.putExtra("change",b);
                startActivity(intent);
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.clear();
                editor.apply();
                Intent i = new Intent(getContext(), LoginActivity.class);
                startActivity(i);

            }
        });
    }

    private void init(View view) {
        sharedpreferences = getActivity().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        user = sharedpreferences.getString(EMAIL_KEY, null);
        id = sharedpreferences.getString(ID_KEY, null);
        tvUsername = view.findViewById(R.id.tvUsername);
        imgUser=view.findViewById(R.id.imgUser);
        btnDoiTt=view.findViewById(R.id.btDoiTt);
        btnChangePass = view.findViewById(R.id.btDoiPass);
        btnLogout = view.findViewById(R.id.idBtnLogout);


        swipeRefreshLayout=view.findViewById(R.id.swipeRefresh);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,R.color.colorPrimaryDark);
    }



}


