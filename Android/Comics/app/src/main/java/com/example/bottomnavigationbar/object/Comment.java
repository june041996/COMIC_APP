package com.example.bottomnavigationbar.object;

public class Comment {
    private String comment_id,content,comment_date,comics_id,user_id,name,image_path;

    public Comment(String comment_id, String content, String comment_date, String comics_id, String user_id, String name,String image_path) {
        this.comment_id = comment_id;
        this.content = content;
        this.comment_date = comment_date;
        this.comics_id = comics_id;
        this.user_id = user_id;
        this.name = name;
        this.image_path = image_path;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    public String getUser_name() {
        return name;
    }

    public void setUser_name(String name) {
        this.name = name;
    }

    public String getId() {
        return comment_id;
    }

    public void setId(String comment_id) {
        this.comment_id = comment_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getComment_date() {
        return comment_date;
    }

    public void setComment_date(String comment_date) {
        this.comment_date = comment_date;
    }

    public String getComics_id() {
        return comics_id;
    }

    public void setComics_id(String comics_id) {
        this.comics_id = comics_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
