package com.example.bottomnavigationbar.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bottomnavigationbar.R;
import com.example.bottomnavigationbar.object.Category;

import java.util.List;

public class TheLoaiAdapter extends RecyclerView.Adapter<TheLoaiAdapter.ViewHolder> {
    List<Category> truyenTranhList;
    public TheLoaiAdapter(List<Category> truyenTranhList) {
        this.truyenTranhList=truyenTranhList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_the_loai,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  ViewHolder holder, int position) {
        Category truyenTranh = truyenTranhList.get(position);
        holder.tvTheLoai.setText(truyenTranh.getCategory_name());
    }


    @Override
    public int getItemCount() {
        return truyenTranhList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvTheLoai;
        public ViewHolder(@NonNull  View itemView) {
            super(itemView);
            tvTheLoai=itemView.findViewById(R.id.tvTheLoai);
        }
    }
}
