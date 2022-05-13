package com.example.bottomnavigationbar.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bottomnavigationbar.R;
import com.example.bottomnavigationbar.adapter.CommentAdapter;
import com.example.bottomnavigationbar.object.Comics;
import com.example.bottomnavigationbar.object.Comment;
import com.example.bottomnavigationbar.webservice.ApiUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentActivity extends AppCompatActivity  implements CommentAdapter.CommentClickListener{
    RecyclerView rvComment;
    Comment comment;
    List<Comment> commentList;
    CommentAdapter commentAdapter;
    EditText edtComment;
    Button btSubmit;
    ImageView imgBack;
    SwipeRefreshLayout swipeRefreshLayout;

    public static final String SHARED_PREFS = "shared_prefs";
    public static final String ID_KEY = "id_key";
    SharedPreferences sharedpreferences;
    String id,noiDung,ngayComment;
    Comics truyenTranh;
    TextView tvTenTruyen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        init();
        loadComment();
        setClick();
    }

    private void setClick() {

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //Refresh page
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(CommentActivity.this, "Refresh", Toast.LENGTH_SHORT).show();
                loadComment();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                noiDung=edtComment.getText().toString().trim();
                ngayComment=new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                ApiUtils.getAPIService().postComment(truyenTranh.getId(),id,noiDung,ngayComment).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try{
                            edtComment.setText("");
                            loadComment();
                            Toast.makeText(CommentActivity.this, "Thêm bình luận thành công", Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            Log.e("onResponse", "Error");
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(CommentActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    public void init(){
        sharedpreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        id = sharedpreferences.getString(ID_KEY, null);
        Bundle b= getIntent().getBundleExtra("data1");
        truyenTranh=(Comics) b.getSerializable("truyen1");

        tvTenTruyen=findViewById(R.id.tvTenTruyen1);
        tvTenTruyen.setText(truyenTranh.getComics_name());

        imgBack=findViewById(R.id.imgBack);
        swipeRefreshLayout =findViewById(R.id.swipeRefresh);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,R.color.colorPrimaryDark);
        rvComment= findViewById(R.id.rvComment);
        rvComment.setLayoutManager(new LinearLayoutManager(this));
        rvComment.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        commentList=new ArrayList<>();
        edtComment=findViewById(R.id.edtComment);
        btSubmit=findViewById(R.id.btSubmit);
    }

    private void loadComment() {
        ApiUtils.getAPIService().getComment(truyenTranh.getId()).enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {

                try {

                    if(response.body().size()>0){
                        commentList.clear();
                        for (int i = 0; i < response.body().size(); i++) {
                            Comment comment = new Comment( response.body().get(i).getId()
                                    ,response.body().get(i).getContent()
                                    , response.body().get(i).getComment_date()
                                    , response.body().get(i).getComics_id()
                                    , response.body().get(i).getUser_id()
                                    , response.body().get(i).getUser_name()
                                    ,response.body().get(i).getImage_path()

                            );
                            commentList.add(comment);
                        }
                        setupData(commentList);
                    }else {
                        Toast.makeText(CommentActivity.this, "No comment in here", Toast.LENGTH_SHORT).show();

                    }
                } catch (Exception e) {
                    Toast.makeText(CommentActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    Log.d("onResponse", "Error");
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                Toast.makeText(CommentActivity.this,"Lỗi kết nối",Toast.LENGTH_SHORT).show();
                Log.d("onFailure", t.toString());
            }
        });
    }

    private void setupData(List<Comment> commentList) {
        if(commentList.get(0).getComment_date()==null){
            Toast.makeText(this, "Truyện chưa có bình luận", Toast.LENGTH_SHORT).show();
        }else{
            commentAdapter = new CommentAdapter(commentList,this,this);
            rvComment.setAdapter(commentAdapter);
        }

    }

    @Override
    public void delete(Comment comment) {
        ApiUtils.getAPIService().deleteComment(comment.getId()).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                try {
                    if(response.body().toString().equals("1")){
                        loadComment();
                        Toast.makeText(CommentActivity.this, "Delete success", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(CommentActivity.this, "Delete failed", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(CommentActivity.this, "Failer", Toast.LENGTH_SHORT).show();
            }
        });
    }


}