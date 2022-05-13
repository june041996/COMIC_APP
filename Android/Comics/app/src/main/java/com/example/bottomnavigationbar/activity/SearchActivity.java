package com.example.bottomnavigationbar.activity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bottomnavigationbar.R;
import com.example.bottomnavigationbar.adapter.TruyenTranhAdapter;
import com.example.bottomnavigationbar.object.Comics;
import com.example.bottomnavigationbar.webservice.ApiUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity  {

    private RecyclerView rv;
    private List<Comics> truyenTranhArrayList;
    SearchView searchView;

    private TruyenTranhAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#E0E0E0")));
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // initializing our variables.
        rv = findViewById(R.id.idRVCourses);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new GridLayoutManager(this,2));
        truyenTranhArrayList=new ArrayList<>();
        //api retrofit
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
                                ,response.body().get(i).getCategory_id()
                                ,response.body().get(i).getCatefory_name()
                                ,response.body().get(i).getFollow_date()
                                , response.body().get(i).getId()
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
                Toast.makeText(SearchActivity.this,t.toString(),Toast.LENGTH_SHORT).show();
                Log.d("onFailure", t.toString());
            }
        });

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.actionSearch);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
               filter(newText);
                return false;
            }
        });
        return true;
    }

    private void filter(String text) {
        ArrayList<Comics> filteredlist = new ArrayList<>();
        ArrayList<Comics> filteredlist1 = new ArrayList<>();
        for (Comics item : truyenTranhArrayList) {
            if (item.getComics_name().toLowerCase().contains(text.toLowerCase())) {
                filteredlist.add(item);
            }
        }
        if (filteredlist.isEmpty()) {
            adapter.filterList(filteredlist1);
            Toast.makeText(this, "Không có kết quả tìm kiếm", Toast.LENGTH_SHORT).show();
        } else {
            adapter.filterList(filteredlist);
        }
    }

    private void setupData(List<Comics> truyenTranhs) {
        adapter=new TruyenTranhAdapter(truyenTranhs,this);
        rv.setAdapter(adapter);
    }

}
