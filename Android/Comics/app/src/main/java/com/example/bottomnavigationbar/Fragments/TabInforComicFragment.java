package com.example.bottomnavigationbar.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.bottomnavigationbar.HttpsTrustManager;
import com.example.bottomnavigationbar.R;
import com.example.bottomnavigationbar.adapter.TheLoaiAdapter;
import com.example.bottomnavigationbar.object.Category;
import com.example.bottomnavigationbar.object.Comics;
import com.example.bottomnavigationbar.webservice.ApiUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TabInforComicFragment extends Fragment {

    ImageView imgTruyen;
    TextView tvTacGia,tvMoTa;
    LinearLayout ln;
    Comics truyenTranh;
    RecyclerView rvTheLoai;
    List<Category> truyenTranhList = new ArrayList<>();
    TheLoaiAdapter theLoaiAdapter;

    public TabInforComicFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tab_infor_comic, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ln = view.findViewById(R.id.ln);
        imgTruyen = view.findViewById(R.id.imgTruyen);
        tvTacGia = view.findViewById(R.id.tvTacGia);
        tvMoTa = view.findViewById(R.id.tvMoTa);
        Bundle b= getActivity().getIntent().getBundleExtra("data");
        truyenTranh=(Comics) b.getSerializable("truyen");
        HttpsTrustManager.allowAllSSL();
        Glide.with(this).load(getContext().getString(R.string.URL_API_IMAGE)+truyenTranh.getComics_image_path()).into(imgTruyen);
        tvTacGia.setText(truyenTranh.getAuthor());
        tvMoTa.setText(truyenTranh.getDesciption());
        rvTheLoai = view.findViewById(R.id.rvTheLoai);
        rvTheLoai.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));

        ApiUtils.getAPIService().layTheLoaiTheoTruyen((truyenTranh.getId())).enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                try {
                    truyenTranhList.clear();
                    for (int i = 0; i < response.body().size(); i++) {
                        Category truyenTranh = new Category(

                                response.body().get(i).getCategory_name()

                                );
                        truyenTranhList.add(truyenTranh);
                    }
                    setUpData(truyenTranhList);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Toast.makeText(getContext(), t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setUpData(List<Category> truyenTranhList) {
        theLoaiAdapter = new TheLoaiAdapter(truyenTranhList);
        rvTheLoai.setAdapter(theLoaiAdapter);

    }

}