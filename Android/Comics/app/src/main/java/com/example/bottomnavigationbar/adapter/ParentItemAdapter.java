package com.example.bottomnavigationbar.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bottomnavigationbar.R;
import com.example.bottomnavigationbar.object.Comics;

import java.util.ArrayList;
import java.util.List;

public class ParentItemAdapter extends RecyclerView.Adapter<ParentItemAdapter.ParentViewHolder> {

    private RecyclerView.RecycledViewPool  viewPool = new RecyclerView.RecycledViewPool();
    private List<Comics> theLoaiList;
    List<Comics> truyenTranhArrayList;
    private List<Comics> arrTruyenTranh1,arrTruyenTranh2;
    private Context context;
    String id;


    public ParentItemAdapter(List<Comics> theLoaiList, Context context) {
        this.theLoaiList = theLoaiList;
        this.context= context;
        int x =1;
        arrTruyenTranh2 = new ArrayList<>();
        arrTruyenTranh2.add(theLoaiList.get(0));
        for (int i = 0; i < theLoaiList.size(); i++) {
            int dem=0;
            for (int j = 0; j < x; j++) {
                if(theLoaiList.get(i).getCategory_name().equals(arrTruyenTranh2.get(j).getCategory_name())){
                    dem++;
                }
            }
            if(dem==0){
                arrTruyenTranh2.add(theLoaiList.get(i));
                x++;
            }
        }
    }

    @NonNull
    @Override
    public ParentViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.parent_item, viewGroup, false);
        return new ParentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ParentViewHolder parentViewHolder, int position) {
        //id=null;
        //truyenTranhArrayList = new ArrayList<>();
        Comics theLoai = arrTruyenTranh2.get(position);
        //id= theLoai.getCategory_name();
        parentViewHolder.ParentItemTitle.setText(theLoai.getCategory_name());

        arrTruyenTranh1 =new ArrayList<>();
        //String s = truyenTranh.getTheLoai();
        for (int i = 0; i < theLoaiList.size(); i++) {
            if(theLoai.getCatefory_name().equals(theLoaiList.get(i).getCatefory_name()))
                arrTruyenTranh1.add(theLoaiList.get(i));
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(parentViewHolder
                .ChildRecyclerView.getContext(),
                LinearLayoutManager.HORIZONTAL,
                false);
        layoutManager.setInitialPrefetchItemCount(arrTruyenTranh1.size());
        ChildItemAdapter childItemAdapter = new ChildItemAdapter(arrTruyenTranh1,context);
        parentViewHolder.ChildRecyclerView.setAdapter(childItemAdapter);
        parentViewHolder.ChildRecyclerView.setLayoutManager(layoutManager);
        parentViewHolder.ChildRecyclerView.setRecycledViewPool(viewPool);
    }

    @Override
    public int getItemCount() {
        return arrTruyenTranh2.size();
    }

    public class ParentViewHolder extends RecyclerView.ViewHolder {
        private TextView ParentItemTitle;
        private RecyclerView ChildRecyclerView;
       public ParentViewHolder( View itemView) {
            super(itemView);
            ParentItemTitle = itemView.findViewById(R.id.parent_item_title);
            ChildRecyclerView = itemView.findViewById(R.id.child_recyclerview);
        }
    }
}
