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
import com.example.bottomnavigationbar.object.Comics;
import com.example.bottomnavigationbar.webservice.ApiUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TruyenTranhAdapter extends RecyclerView.Adapter<TruyenTranhAdapter.ViewHolder> {
     List<Comics> arr ;
    private Context context;

    public TruyenTranhAdapter(List<Comics> truyenTranhs, Context context) {
        this.arr = truyenTranhs;
        this.context = context;
    }
    public void filterList(ArrayList<Comics> filterllist) {
        arr = filterllist;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_truyen,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Comics truyenTranh=arr.get(position);
        holder.txtTenTruyen.setText(truyenTranh.getComics_name());
        holder.tvView.setText(Integer.toString(truyenTranh.getView()));
        HttpsTrustManager.allowAllSSL();
        Glide.with(this.context).load(context.getString(R.string.URL_API_IMAGE)+truyenTranh.getComics_image_path()).into(holder.linkAnh);
        holder.setRecyclerItemClickListener(new IRecyclerItemClickListener() {
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
                b.putSerializable("truyen", truyenTranh);

                Intent intent = new Intent(context, DSChuongActivity.class    );
                intent.putExtra("data",b);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arr.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView txtTenTruyen,txtTenChap,tvView;
        ImageView linkAnh;
        IRecyclerItemClickListener recyclerItemClickListener;
        public void setRecyclerItemClickListener(IRecyclerItemClickListener recyclerItemClickListener) {
            this.recyclerItemClickListener = recyclerItemClickListener;
        }

        public ViewHolder(View convertView) {
            super(convertView);
                txtTenTruyen = convertView.findViewById(R.id.txtTenTruyen);
                linkAnh = convertView.findViewById(R.id.imgAnh);
                tvView =convertView.findViewById(R.id.tvView);
                convertView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            recyclerItemClickListener.onClick(view, getAdapterPosition());
        }
    }
}