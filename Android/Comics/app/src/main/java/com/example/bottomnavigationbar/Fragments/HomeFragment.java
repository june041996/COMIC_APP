package com.example.bottomnavigationbar.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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
import com.example.bottomnavigationbar.adapter.BannerAdapter;
import com.example.bottomnavigationbar.adapter.ParentItemAdapter;
import com.example.bottomnavigationbar.object.Comics;

import com.example.bottomnavigationbar.webservice.ApiUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment  {
    private List<Comics> theLoaiList;
    private RecyclerView ParentRecyclerViewItem,banner;
    SwipeRefreshLayout swipeRefreshLayout;
    private ParentItemAdapter parentItemAdapter;
    private BannerAdapter bannerAdapter;
    LinearLayoutManager layoutManager;
    Timer timer;
    TimerTask timerTask;
    int position;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home,null);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        loadComic();
        setClick();
    }

    private void setClick() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(getContext(), "Refresh", Toast.LENGTH_SHORT).show();
                loadComic();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void init(View view) {
        theLoaiList=new ArrayList<>();
        ParentRecyclerViewItem = view.findViewById(R.id.parent_recyclerview);
        banner = view.findViewById(R.id.rvBanner);
        layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        banner.setLayoutManager(layoutManager);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        swipeRefreshLayout = view.findViewById(R.id.swipeRefresh);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,R.color.colorPrimaryDark);
        ParentRecyclerViewItem.setLayoutManager(layoutManager);
    }

    private void loadComic() {
        ApiUtils.getAPIService().getTheLoai().enqueue(new Callback<List<Comics>>() {
            @Override
            public void onResponse(Call<List<Comics>> call, Response<List<Comics>> response) {
                try {
                    theLoaiList.clear();
                    for (int i = 0; i < response.body().size(); i++) {
                        Comics theLoai = new Comics(response.body().get(i).getComics_name()
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
                        theLoaiList.add(theLoai);
                    }
                    setupData(theLoaiList);
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

    private void setupData(List<Comics> theLoais) {

        parentItemAdapter = new ParentItemAdapter(theLoais,getContext());
        ParentRecyclerViewItem.setAdapter(parentItemAdapter);

        bannerAdapter = new BannerAdapter(theLoais,getContext());
        banner.setAdapter(bannerAdapter);

        if (theLoais != null) {
            position = Integer.MAX_VALUE / 5;
            banner.scrollToPosition(position);
        }

        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(banner);
        banner.smoothScrollBy(1,0);
        banner.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull  RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == 1) {
                    stopAutoScrollBanner();
                } else if (newState == 0) {
                    position = layoutManager.findFirstCompletelyVisibleItemPosition();
                    runAutoScrollBanner();
                }
            }
        });

    }

    private void runAutoScrollBanner() {
        if (timer == null && timerTask == null) {
            timer = new Timer();
            timerTask = new TimerTask() {
                @Override
                public void run() {
                    if (position == Integer.MAX_VALUE) {
                        position = Integer.MAX_VALUE / 2;
                        banner.scrollToPosition(position);
                        banner.smoothScrollBy(1,0);
                    } else {
                        position++;
                        banner.smoothScrollToPosition(position);
                    }
                }
            };
            timer.schedule(timerTask, 2000, 2000);
        }
    }

    private void stopAutoScrollBanner() {
        if (timer != null && timerTask != null) {
            timerTask.cancel();
            timer.cancel();
            timer = null;
            timerTask = null;
            position = layoutManager.findFirstCompletelyVisibleItemPosition();
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        runAutoScrollBanner();
    }

    @Override
    public void onPause() {
        super.onPause();
        stopAutoScrollBanner();
    }


}