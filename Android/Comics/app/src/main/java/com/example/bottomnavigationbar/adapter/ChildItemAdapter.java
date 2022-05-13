package com.example.bottomnavigationbar.adapter;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bottomnavigationbar.HttpsTrustManager;
import com.example.bottomnavigationbar.R;
import com.example.bottomnavigationbar.activity.DSChuongActivity;
import com.example.bottomnavigationbar.interfaces.IRecyclerItemClickListener;
import com.example.bottomnavigationbar.object.ChildItem;
import com.example.bottomnavigationbar.object.Comics;
import com.example.bottomnavigationbar.webservice.ApiUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChildItemAdapter extends RecyclerView.Adapter<ChildItemAdapter.ChildViewHolder> {

    private List<ChildItem> ChildItemList;
    private List<Comics> arrTruyenTranh;
    private ImageView img;
    private Context context;

    // Constuctor
    ChildItemAdapter(List<Comics> childItemList, Context context) {
        this.arrTruyenTranh = childItemList;
        this.context = context;
    }

    @NonNull
    @Override
    public ChildViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.child_item, viewGroup, false);
        return new ChildViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChildViewHolder childViewHolder, int position) {

        Comics truyenTranh = arrTruyenTranh.get(position);
        childViewHolder.ChildItemTitle.setText(truyenTranh.getComics_name());
        HttpsTrustManager.allowAllSSL();
        Glide.with(this.context).load(context.getString(R.string.URL_API_IMAGE)+truyenTranh.getComics_image_path()).into(childViewHolder.img);
        childViewHolder.setRecyclerItemClickListener(new IRecyclerItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                ApiUtils.getAPIService().countView(truyenTranh.getId()).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {

                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });
                Bundle b =new Bundle();
                b.putSerializable("truyen",truyenTranh);
                Intent intent = new Intent(context, DSChuongActivity.class);
                intent.putExtra("data",b);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrTruyenTranh.size();
    }

    class ChildViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView ChildItemTitle;
        ImageView img;
        IRecyclerItemClickListener recyclerItemClickListener;

        public void setRecyclerItemClickListener(IRecyclerItemClickListener recyclerItemClickListener) {
            this.recyclerItemClickListener = recyclerItemClickListener;
        }

        ChildViewHolder(View itemView) {
            super(itemView);
            ChildItemTitle = itemView.findViewById(R.id.child_item_title);
            img = itemView.findViewById(R.id.img_child_item);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            recyclerItemClickListener.onClick(view, getAdapterPosition());
        }
    }
}
