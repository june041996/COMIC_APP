package com.example.bottomnavigationbar.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.bottomnavigationbar.R;
import com.example.bottomnavigationbar.adapter.TheoDoiAdapter;
import com.example.bottomnavigationbar.object.Comics;
import com.example.bottomnavigationbar.webservice.ApiUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavouritesFragment extends Fragment{

    private RecyclerView rv;
    private List<Comics> truyenTranhArrayList;
    private TheoDoiAdapter adapter;
    public static final String SHARED_PREFS = "shared_prefs";
    public static final String ID_KEY = "id_key";
    public static final String EMAIL_KEY = "email_key";
    SharedPreferences sharedpreferences;
    String name;
    SwipeRefreshLayout swipeRefreshLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_favourites, null);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        loadData();
        setClick();
    }

    private void setClick() {
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,R.color.colorPrimaryDark);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(getContext(), "Refresh", Toast.LENGTH_SHORT).show();
                loadData();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void init(View view) {
        sharedpreferences = getActivity().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        name = sharedpreferences.getString(EMAIL_KEY, null);
        swipeRefreshLayout=view.findViewById(R.id.swipeRefresh);
        rv = (RecyclerView) view.findViewById(R.id.recyclerview);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(view.getContext()));
        truyenTranhArrayList = new ArrayList<>();
    }

    private void loadData() {
        ApiUtils.getAPIService().getTruyenTheoDoi(name).enqueue(new Callback<List<Comics>>() {
            @Override
            public void onResponse(Call<List<Comics>> call, Response<List<Comics>> response) {
                try {
                    truyenTranhArrayList.clear();
                    for (int i = 0; i < response.body().size(); i++) {
                        Comics truyenTranh = new Comics(response.body().get(i).getComics_name()
                                ,response.body().get(i).getAuthor()
                                ,response.body().get(i).getDescription()
                                ,response.body().get(i).getComics_image()
                                ,response.body().get(i).getComics_image_path()
                                ,response.body().get(i).getFollow_date()
                                ,response.body().get(i).getCategory_id()
                                ,response.body().get(i).getCatefory_name()
                                ,response.body().get(i).getId()
                                ,response.body().get(i).getView()

                        );truyenTranhArrayList.add(truyenTranh);
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
        adapter = new TheoDoiAdapter(truyenTranhs, getContext());
        rv.setAdapter(adapter);
    }
}