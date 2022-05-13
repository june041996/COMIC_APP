package com.example.bottomnavigationbar.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bottomnavigationbar.R;
import com.example.bottomnavigationbar.adapter.MyViewPaperAdapter;
import com.example.bottomnavigationbar.object.Image;
import com.example.bottomnavigationbar.webservice.APIService;
import com.example.bottomnavigationbar.webservice.ApiUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewComicActivity extends AppCompatActivity {

    ViewPager viewPager;
    TextView txt_chapter_name;
    View back;
    String idChap,tenChap;
    ArrayList<Image> arrUrlAnh =new ArrayList<>();
    MyViewPaperAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_comic);
        init();
        loadData();
        setClick();
    }

    private void setClick() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void loadData() {
        ApiUtils.getAPIService().getAnh(idChap).enqueue(new Callback<List<Image>>() {
            @Override
            public void onResponse(Call<List<Image>> call, Response<List<Image>> response) {
                try {
                    arrUrlAnh.clear();
                    for (int i = 0; i < response.body().size(); i++) {
                        Image image = new Image(
                                response.body().get(i).getImage_name()
                                ,response.body().get(i).getImage_path()
                        );
                        arrUrlAnh.add(image);
                    }
                    setupData(arrUrlAnh);
                } catch (Exception e) {
                    Log.d("onResponse", "Error");
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<List<Image>> call, Throwable t) {
                Toast.makeText(ViewComicActivity.this,"loi",Toast.LENGTH_SHORT).show();
                Log.d("onFailure", t.toString());
            }
        });
    }

    private void setupData(ArrayList<Image> truyenTranhs) {
        adapter=new MyViewPaperAdapter(this,truyenTranhs);
        if(truyenTranhs.size()==0){
            Toast.makeText(this, "Đang cập nhập", Toast.LENGTH_SHORT).show();
        }
        viewPager.setAdapter(adapter);
    }
    public void init(){
        arrUrlAnh = new ArrayList<>();
        viewPager = (ViewPager) findViewById(R.id.view_paper);
        txt_chapter_name = (TextView) findViewById(R.id.txt_chapter_name);
        back = findViewById(R.id.chapter_back);
        Bundle b = getIntent().getBundleExtra("data");
        idChap= b.getString("idChap");
        tenChap = b.getString("tenChap");
        txt_chapter_name.setText(tenChap);
    }
}