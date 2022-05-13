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


import com.example.bottomnavigationbar.R;
import com.example.bottomnavigationbar.activity.ViewComicActivity;
import com.example.bottomnavigationbar.interfaces.IRecyclerItemClickListener;
import com.example.bottomnavigationbar.object.Chapter;

import java.util.List;

public class ChapTruyenAdapter extends RecyclerView.Adapter<ChapTruyenAdapter.ViewHolder> {
    private List<Chapter> arr;
    private Context context;

    public ChapTruyenAdapter(List<Chapter> truyenTranhs, Context context) {
        this.arr = truyenTranhs;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chap_truyen,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Chapter chap=arr.get(position);
        holder.txtTenChap.setText(chap.getChapter_name());
        holder.txtNgayDang.setText(chap.getChapter_date());
        holder.setRecyclerItemClickListener(new IRecyclerItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                Bundle b =new Bundle();
                String name= arr.get(position).getChapter_name();
                String s = arr.get(position).getId();
                b.putString("idChap",s);
                b.putString("tenChap",name);

                Intent intent = new Intent(context, ViewComicActivity.class);
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
        public TextView txtNgayDang,txtTenChap;
        ImageView linkAnh;
        IRecyclerItemClickListener recyclerItemClickListener;

        public void setRecyclerItemClickListener(IRecyclerItemClickListener recyclerItemClickListener) {
            this.recyclerItemClickListener = recyclerItemClickListener;
        }

        public ViewHolder(View convertView) {
            super(convertView);
            txtTenChap = convertView.findViewById(R.id.txtTenChaps);
            txtNgayDang = convertView.findViewById(R.id.txtNgayDang);
            convertView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            recyclerItemClickListener.onClick(view, getAdapterPosition());
        }
    }
}