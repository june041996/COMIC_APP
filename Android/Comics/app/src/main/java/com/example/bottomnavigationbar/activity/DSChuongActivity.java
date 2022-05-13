package com.example.bottomnavigationbar.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bottomnavigationbar.Fragments.TabInforComicFragment;
import com.example.bottomnavigationbar.Fragments.TabListChapFragment;
import com.example.bottomnavigationbar.R;
import com.example.bottomnavigationbar.adapter.PagerAdapter;
import com.example.bottomnavigationbar.adapter.TabsAdapter;
import com.example.bottomnavigationbar.object.Comics;
import com.example.bottomnavigationbar.object.TabItem;

import java.util.ArrayList;

public class DSChuongActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    RecyclerView tabsRecyclerView;
    ViewPager viewPager;
    TabsAdapter tabsAdapter;
    PagerAdapter pagerAdapter;
    ImageView imgBack;
    TextView tvTenTruyen;
    Comics truyenTranh;
    ArrayList<TabItem> tabItems = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dschuong);

        tabsRecyclerView = findViewById(R.id.tab_recyclerView);
        viewPager = findViewById(R.id.viewPager);
        imgBack = findViewById(R.id.imgBack);
        tvTenTruyen = findViewById(R.id.tvTenTruyen);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Bundle b= getIntent().getBundleExtra("data");
        truyenTranh=(Comics) b.getSerializable("truyen");
        tvTenTruyen.setText(truyenTranh.getComics_name());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        tabsRecyclerView.setLayoutManager(linearLayoutManager);
        tabsAdapter = new TabsAdapter(tabItems, this, new TabsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                viewPager.setCurrentItem(position);
            }
        }, linearLayoutManager);
        tabsRecyclerView.setAdapter(tabsAdapter);
        initTabsAdapter();
        viewPager.addOnPageChangeListener(this);
    }

    private void initTabsAdapter(){
        tabItems.add(new TabItem("Giới thiệu",  true));
        tabItems.add(new TabItem("Danh sách chương",  false));

        tabsAdapter.notifyDataSetChanged();
        setupViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        pagerAdapter = new PagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        Fragment fragment1 = new TabListChapFragment();
        Fragment fragment2 = new TabInforComicFragment();
        pagerAdapter.addFragment(fragment2, "");
        pagerAdapter.addFragment(fragment1, "");
        viewPager.setAdapter(pagerAdapter);
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onPageSelected(int i) {
        tabsAdapter.setCurrentSelected(i);
        tabsRecyclerView.scrollToPosition(i);
    }

    @Override
    public void onPageScrollStateChanged(int i) {
    }
}