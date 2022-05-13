package com.example.bottomnavigationbar.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bottomnavigationbar.R;
import com.example.bottomnavigationbar.activity.CommentActivity;
import com.example.bottomnavigationbar.adapter.ChapTruyenAdapter;
import com.example.bottomnavigationbar.object.Chapter;
import com.example.bottomnavigationbar.object.Comics;
import com.example.bottomnavigationbar.webservice.ApiUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TabListChapFragment extends Fragment {
    public static final String SHARED_PREFS = "shared_prefs";
    public static final String EMAIL_KEY = "email_key";
    public static final String ID_KEY = "id_key";
    Comics truyenTranh;
    ArrayList<Chapter> arrChap;
    private ChapTruyenAdapter adapter;
    private RecyclerView lvDanhSachChap;
    CheckBox checkBox;
    ImageButton btComment;
    TextView tvCheckbox,tvView;
    SharedPreferences sharedpreferences;
    String name,id;

    public TabListChapFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tab_list_chap, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        loadData();
        setClick();
    }
    private void loadData() {
        //load check box

        ApiUtils.getAPIService().getFollow(truyenTranh.getId(),id).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String status = response.body().string().toString().trim();
                    Log.e("response", status);
                    if (status.equals("1")) {
                        checkBox.setChecked(true);
                        setTextCheckBoxTrue();
                    } else {
                        checkBox.setChecked(false);
                        setTextCheckBoxFalse();
                    }
                } catch (Exception e) {
                    Log.e("onResponse", "Error");
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getContext(), "Lỗi kết nối", Toast.LENGTH_SHORT).show();
                Log.e("onFailure", t.toString());
            }
        });

        //load list chapter
        ApiUtils.getAPIService().getChap(truyenTranh.getId()).enqueue(new Callback<List<Chapter>>() {
            @Override
            public void onResponse(Call<List<Chapter>> call, retrofit2.Response<List<Chapter>> response) {
                try {
                    arrChap.clear();
                    for (int i = 0; i < response.body().size(); i++) {
                        Chapter chapTruyen = new Chapter(response.body().get(i).getId()
                                ,response.body().get(i).getChapter_name()
                                ,response.body().get(i).getChapter_date()
                                ,response.body().get(i).getComics_id()

                        );
                        arrChap.add(chapTruyen);
                    }
                    setupData(arrChap);
                } catch (Exception e) {
                    Log.d("onResponse", "Error");
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<List<Chapter>> call, Throwable t) {
                Toast.makeText(getContext(),"Lỗi kết nối",Toast.LENGTH_SHORT).show();
                Log.d("onFailure", t.toString());
            }
        });
    }

    private void setClick() {
        //click checkbox
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                //Toast.makeText(compoundButton.getContext(),""+b,Toast.LENGTH_SHORT).show();
                String follow_date=new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                if(b){
                    //Toast.makeText(getContext(),"ok", Toast.LENGTH_SHORT).show();
                    ApiUtils.getAPIService().insertFollow(truyenTranh.getId(), id,follow_date).enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            try {
                                String status = response.body().string().toString().trim();
                                Log.e("response", status);
                                setTextCheckBoxTrue();
                                if (status.length() > 0) {

                                } else {

                                }
                            } catch (Exception e) {
                                Log.e("onResponse", "Error");
                                e.printStackTrace();
                            }
                        }
                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Toast.makeText(getContext(), "Lỗi kết nối", Toast.LENGTH_SHORT).show();
                            Log.e("onFailure", t.toString());
                        }
                    });
                }else{
                    ApiUtils.getAPIService().deleteFollow(truyenTranh.getId(),name).enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {

                            try {
                                String kq=response.body();
                                setTextCheckBoxFalse();
                                if(kq.equals("1")){
                                    // Toast.makeText(getContext(), "Delete success", Toast.LENGTH_SHORT).show();
                                }else{
                                    // Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();
                                }
                            } catch (Exception e) {
                                Log.d("onResponse", "Error");
                                e.printStackTrace();
                            }
                        }
                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            Toast.makeText(getContext(),"Lỗi kết nối",Toast.LENGTH_SHORT).show();
                            Log.d("onFailure", t.toString());
                        }
                    });
                }
            }
        });
        //chuyen qua man hinh comment
        btComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle b =new Bundle();
                b.putSerializable("truyen1",truyenTranh);
                Intent intent= new Intent(getContext(), CommentActivity.class);
                intent.putExtra("data1",b);
                startActivity(intent);
            }
        });
    }

    private void init(View view) {
        sharedpreferences = getActivity().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        name = sharedpreferences.getString(EMAIL_KEY, null);
        id = sharedpreferences.getString(ID_KEY,null);

        Bundle b= getActivity().getIntent().getBundleExtra("data");
        truyenTranh=(Comics) b.getSerializable("truyen");

        tvCheckbox=view.findViewById(R.id.tvCheckbox);
        btComment =view.findViewById(R.id.btComment);
        tvView = view.findViewById(R.id.tvView);
        tvView.setText(Integer.toString(truyenTranh.getView()));
        arrChap=new ArrayList<>();
        lvDanhSachChap = view.findViewById(R.id.lvDanhSachChap);
        lvDanhSachChap.setHasFixedSize(true);
        lvDanhSachChap.setLayoutManager(new LinearLayoutManager(getContext()));
        checkBox = view.findViewById(R.id.checkbox);
    }

    private void setupData(List<Chapter> truyenTranhs) {
        if(truyenTranhs.size()==0){
            Toast.makeText(getContext(), R.string.no_chapter, Toast.LENGTH_SHORT).show();
        }
        adapter=new ChapTruyenAdapter(truyenTranhs,getContext());
        lvDanhSachChap.setAdapter(adapter);
    }

    public void setTextCheckBoxTrue(){
        tvCheckbox.setText("Hủy theo dõi");
    }
    public void setTextCheckBoxFalse(){
        tvCheckbox.setText("Theo dõi");
    }
}