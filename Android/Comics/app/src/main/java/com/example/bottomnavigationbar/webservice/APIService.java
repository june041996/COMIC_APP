package com.example.bottomnavigationbar.webservice;

import com.example.bottomnavigationbar.object.Category;
import com.example.bottomnavigationbar.object.Chapter;
import com.example.bottomnavigationbar.object.Comics;
import com.example.bottomnavigationbar.object.Comment;
import com.example.bottomnavigationbar.object.Image;
import com.example.bottomnavigationbar.object.User;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface APIService {

    @GET("layTruyen")
    Call<List<Comics>> getTruyen();

    //commentActivity
    @GET("getComment/{id}")
    Call<List<Comment>> getComment(@Path("id") String idTruyen);
    //parentItemAdapter
    //home
    @GET("layTheLoai")
    Call<List<Comics>> getTheLoai();

    //frame1
    @GET("layTheLoaiTheoTruyen/{id}")
    Call<List<Category>> layTheLoaiTheoTruyen(@Path("id") String id);

    @GET("layChap/{comics_id}")
    Call<List<Chapter>> getChap(@Path("comics_id") String id);

    @GET("layAnh/{id}")
    Call<List<Image>> getAnh(@Path("id") String idChap);


    @GET("theodoi/{name}")
    Call<List<Comics>> getTruyenTheoDoi(@Path("name") String name);



    @FormUrlEncoded
    @POST("login")
    Call<ResponseBody> login(@Field("username") String checkbox, @Field("password") String password);

    @FormUrlEncoded
    @POST("register")
    Call<ResponseBody> register(@Field("username") String checkbox, @Field("password") String password, @Field("email") String email);

    @FormUrlEncoded
    @POST("changePass")
    Call<ResponseBody> changePass(@Field("user") String checkbox, @Field("oldPass") String oldPass, @Field("pass1") String pass1,@Field("pass2") String pass2);

    //FrameOne
    @FormUrlEncoded
    @POST("createFollow")
    Call<ResponseBody> insertFollow(@Field("comics_id") String comics_id, @Field("user_id") String user_id, @Field("follow_date") String follow_date);

    @FormUrlEncoded
    @POST("getFollow")
    Call<ResponseBody> getFollow(@Field("comics_id") String comics_id, @Field("user_id") String user_id);
    //TheoDoiAdapter

    @FormUrlEncoded
    @POST("deleteFollow")
    Call<String> deleteFollow(@Field("comics_id") String comics_id,@Field("name") String name);
    //CommmentAdapter
    @GET("deleteComment/{id}")
    Call<String> deleteComment(@Path("id") String id);
    //CommentActivity
    @FormUrlEncoded
    @POST("postComment")
    Call<ResponseBody> postComment(@Field("comics_id") String idTruyen, @Field("user_id") String idUser,@Field("content") String noiDung,@Field("comment_date") String ngayComment);
    //ChangeInforUser
    @FormUrlEncoded
    @POST("changeInforUser")
    Call<String> changeInforUser(@Field("id") String id,@Field("name") String name,
                    @Field("email") String email,
                           @Field("gender") String gender,
                           @Field("birthday") String birthday,
                           @Field("phone_number") String phone_number,
                            @Field("address") String address,
                            @Field("img_name") String img_name,
                            @Field("img_code") String img_code,
                                 @Field("img_old_name") String img_old_name
    );
    @GET("getInforUser/{name}")
    Call<List<User>> getInforUser(@Path("name") String name);
    //profile

    @FormUrlEncoded
    @POST("countView")
    Call<String> countView(@Field("comics_id") String comics_id);
}