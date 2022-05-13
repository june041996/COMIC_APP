package com.example.bottomnavigationbar.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bottomnavigationbar.HttpsTrustManager;
import com.example.bottomnavigationbar.R;
import com.example.bottomnavigationbar.interfaces.IRecyclerItemClickListener;
import com.example.bottomnavigationbar.object.Comment;
import com.example.bottomnavigationbar.webservice.APIService;
import com.example.bottomnavigationbar.webservice.ApiUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {
    List<Comment> commentList;
    Context context;
    APIService mAPIService;
    private CommentClickListener commentClickListener;

    public static final String SHARED_PREFS = "shared_prefs";
    public static final String ID_KEY = "id_key";
    SharedPreferences sharedpreferences;
    String id;

    public interface CommentClickListener {
        void delete(Comment comment);

    }
    public CommentAdapter(List<Comment> commentList, Context context,CommentClickListener commentClickListener) {
        this.commentList = commentList;
        this.context = context;
        this.commentClickListener=commentClickListener;
        mAPIService = ApiUtils.getAPIService();
        sharedpreferences = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        id = sharedpreferences.getString(ID_KEY, null);
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Comment comment = commentList.get(position);
        holder.tvName.setText(comment.getUser_name());
        holder.tvNoiDung.setText(comment.getContent());
        holder.tvNgayComment.setText(comment.getComment_date());
       // holder.ibtDelete.setVisibility(View.VISIBLE);
        if(comment.getUser_id().equals(id)){
            holder.ibtDelete.setVisibility(View.VISIBLE);
        }else{
            holder.ibtDelete.setVisibility(View.INVISIBLE);
        }
        HttpsTrustManager.allowAllSSL();
        String imgName = comment.getImage_path();
        if(imgName==null){
            imgName=context.getString(R.string.image_null);
        }
            String url = context.getString(R.string.URL_API_IMAGE)+imgName;
            Glide.with(context).load(url).into(holder.imgUser);


    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName,tvNoiDung,tvNgayComment;
        ImageButton ibtDelete;
        ImageView imgUser;

        public ViewHolder(@NonNull  View itemView) {
            super(itemView);
            ibtDelete=itemView.findViewById(R.id.ibtDelete);
            tvName = itemView.findViewById(R.id.tvname);
            tvNoiDung = itemView.findViewById(R.id.tvNoiDung);
            tvNgayComment = itemView.findViewById(R.id.tvNgayComment);
            imgUser = itemView.findViewById(R.id.imgUser);
            ibtDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    commentClickListener.delete(commentList.get(getAdapterPosition()));
                }
            });
        }
    }
}
