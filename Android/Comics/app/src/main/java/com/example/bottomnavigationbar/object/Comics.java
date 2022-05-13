package com.example.bottomnavigationbar.object;

import java.io.Serializable;

public class Comics implements Serializable {
    private String comics_name,author,description,comics_image,comics_image_path,follow_date,category_id,category_name;
    private String id;
    int view;

    public Comics(String comics_name, String author, String description, String comics_image, String comics_image_path, String follow_date, String category_id, String category_name, String id,int view) {
        this.comics_name = comics_name;
        this.author = author;
        this.description = description;
        this.comics_image = comics_image;
        this.comics_image_path = comics_image_path;
        this.follow_date = follow_date;
        this.category_id = category_id;
        this.category_name = category_name;
        this.id = id;
        this.view=view;
    }

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getCatefory_name() {
        return category_name;
    }

    public void setCatefory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFollow_date() {
        return follow_date;
    }

    public void setFollow_date(String follow_date) {
        this.follow_date = follow_date;
    }

    public String getComics_name() {
        return comics_name;
    }

    public void setComics_name(String comics_name) {
        this.comics_name = comics_name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDesciption() {
        return description;
    }

    public void setDesciption(String description) {
        this.description = description;
    }

    public String getComics_image() {
        return comics_image;
    }

    public void setComics_image(String comics_image) {
        this.comics_image = comics_image;
    }

    public String getComics_image_path() {
        return comics_image_path;
    }

    public void setComics_image_path(String comics_image_path) {
        this.comics_image_path = comics_image_path;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
