package com.example.bottomnavigationbar.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.bottomnavigationbar.R;
import com.example.bottomnavigationbar.activity.SearchActivity;
import com.example.bottomnavigationbar.adapter.TruyenTranhAdapter;
import com.example.bottomnavigationbar.object.Comics;
import com.example.bottomnavigationbar.webservice.ApiUtils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SearchFragment extends Fragment  {

    private RecyclerView rv;
    private List<Comics> truyenTranhArrayList;
    private TruyenTranhAdapter adapter;
    SwipeRefreshLayout swipeRefreshLayout;
    FloatingActionButton fab;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_search,null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        loadData();
        setClick();
    }

    private void setClick() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(getContext(), "Refresh", Toast.LENGTH_SHORT).show();
                loadData();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), SearchActivity.class    );
                getContext().startActivity(intent);
            }
        });
    }

    private void init(View view) {
        swipeRefreshLayout=view.findViewById(R.id.swipeRefresh);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,R.color.colorPrimaryDark);
        fab = view.findViewById(R.id.fab);
        rv=(RecyclerView)view.findViewById(R.id.recyclerview);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new GridLayoutManager(getContext(),2));
        truyenTranhArrayList=new ArrayList<>();
    }

    private void loadData() {
        ApiUtils.getAPIService().getTruyen().enqueue(new Callback<List<Comics>>() {
            @Override
            public void onResponse(Call<List<Comics>> call, Response<List<Comics>> response) {
                try {
                    truyenTranhArrayList.clear();
                    for (int i = 0; i < response.body().size(); i++) {
                        Comics truyenTranh = new Comics(
                                response.body().get(i).getComics_name()
                                ,response.body().get(i).getAuthor()
                                ,response.body().get(i).getDesciption()
                                ,response.body().get(i).getComics_image()
                                ,response.body().get(i).getComics_image_path()
                                ,response.body().get(i).getFollow_date()
                                ,response.body().get(i).getCategory_id()
                                ,response.body().get(i).getCatefory_name()


                                ,response.body().get(i).getId()
                                ,response.body().get(i).getView()

                        );
                        truyenTranhArrayList.add(truyenTranh);
                    }
                    setupData(truyenTranhArrayList);
                } catch (Exception e) {
                    Log.d("onResponse", "Error");
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<List<Comics>> call, Throwable t) {
                Toast.makeText(getContext(),"Lỗi kết nối",Toast.LENGTH_SHORT).show();
                Log.d("onFailure", t.toString());
            }
        });
    }

    private void setupData(List<Comics> truyenTranhs) {
        adapter=new TruyenTranhAdapter(truyenTranhs,getContext());
        rv.setAdapter(adapter);
    }
}