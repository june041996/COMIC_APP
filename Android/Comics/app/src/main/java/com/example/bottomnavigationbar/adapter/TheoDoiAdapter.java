package com.example.bottomnavigationbar.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import com.example.bottomnavigationbar.HttpsTrustManager;
import com.example.bottomnavigationbar.R;
import com.example.bottomnavigationbar.activity.DSChuongActivity;
import com.example.bottomnavigationbar.interfaces.IRecyclerItemClickListener;
import com.example.bottomnavigationbar.object.Comics;
import com.example.bottomnavigationbar.webservice.APIService;
import com.example.bottomnavigationbar.webservice.ApiUtils;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TheoDoiAdapter extends RecyclerView.Adapter<TheoDoiAdapter.ViewHolder> {
    List<Comics> theoDoiList ;
    private Context context;
    APIService mAPIService;
    public static final String SHARED_PREFS = "shared_prefs";
    public static final String EMAIL_KEY = "email_key";
    public static final String ID_KEY = "id_key";
    SharedPreferences sharedpreferences;
    String name,id;

    public TheoDoiAdapter(List<Comics> theoDoiList, Context context) {
        this.theoDoiList = theoDoiList;
        this.context = context;
        sharedpreferences = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        name = sharedpreferences.getString(EMAIL_KEY, null);
        id = sharedpreferences.getString(ID_KEY,null);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_theodoi,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Comics theoDoi=theoDoiList.get(position);
        holder.txtTenTruyen.setText(theoDoi.getComics_name());
        holder.txtTacGia.setText(theoDoi.getAuthor());
        holder.txtNgayTheoDoi.setText(theoDoi.getFollow_date());

            holder.checkBox.setChecked(true);

        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                String s=String.valueOf(b);
                mAPIService = ApiUtils.getAPIService();
                mAPIService.deleteFollow(theoDoi.getId(),name).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        try {
                            String kq=response.body();
                            if(kq.equals("1")){
                                theoDoiList.remove(position);
                                notifyDataSetChanged();
                            }else{
                            }
                        } catch (Exception e) {
                            Log.d("onResponse", "Error");
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(context,R.string.failed,Toast.LENGTH_SHORT).show();
                        Log.d("onFailure", t.toString());
                    }
                });
            }
        });
        HttpsTrustManager.allowAllSSL();
        Glide.with(this.context).load(context.getString(R.string.URL_API_IMAGE)+theoDoi.getComics_image_path()).into(holder.linkAnh);

        holder.setRecyclerItemClickListener(new IRecyclerItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                ApiUtils.getAPIService().countView(theoDoi.getId()).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {

                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });
                Bundle b =new Bundle();
                b.putSerializable("truyen",theoDoi);

                Intent intent = new Intent(context, DSChuongActivity.class    );
                intent.putExtra("data",b);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return theoDoiList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView txtTenTruyen,txtTacGia,txtNgayTheoDoi;
        ImageView linkAnh;
        IRecyclerItemClickListener recyclerItemClickListener;
        CheckBox checkBox;

        public void setRecyclerItemClickListener(IRecyclerItemClickListener recyclerItemClickListener) {
            this.recyclerItemClickListener = recyclerItemClickListener;
        }

        public ViewHolder(View convertView) {
            super(convertView);
            checkBox = convertView.findViewById(R.id.checkbox);
            txtTenTruyen = convertView.findViewById(R.id.tvTenTruyen);
            txtNgayTheoDoi = convertView.findViewById(R.id.tvNgayTheoDoi);
            txtTacGia = convertView.findViewById(R.id.tvTacGia);
            linkAnh = convertView.findViewById(R.id.imgTruyen);
            convertView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            recyclerItemClickListener.onClick(view, getAdapterPosition());
        }
    }
}