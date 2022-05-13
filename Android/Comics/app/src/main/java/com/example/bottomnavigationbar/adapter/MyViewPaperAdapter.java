package com.example.bottomnavigationbar.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.bottomnavigationbar.HttpsTrustManager;
import com.example.bottomnavigationbar.R;
import com.example.bottomnavigationbar.object.Image;
import com.github.chrisbanes.photoview.PhotoView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyViewPaperAdapter extends PagerAdapter {
    Context context;
    List<Image> imageLinks;
    LayoutInflater inflater;

    public MyViewPaperAdapter(Context context, List<Image> imageLinks) {
        this.context = context;
        this.imageLinks = imageLinks;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return imageLinks.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View )object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View  image_layout = inflater.inflate(R.layout.view_paper_item,container,false);
        PhotoView page_image = image_layout.findViewById(R.id.page_image);
        HttpsTrustManager.allowAllSSL();
        //Picasso.get().load(context.getString(R.string.BASE_URL_IMAGE)+imageLinks.get(position)).into(page_image);
        Glide.with(context).load(context.getString(R.string.URL_API_IMAGE)+imageLinks.get(position).getImage_path()).into(page_image);
        int x =position+1;
        int y =imageLinks.size();
        TextView tvSoTrang = image_layout.findViewById(R.id.tvSoTrang);
        tvSoTrang.setText(x+"/"+y);
        container.addView(image_layout);
        return  image_layout;
    }
}