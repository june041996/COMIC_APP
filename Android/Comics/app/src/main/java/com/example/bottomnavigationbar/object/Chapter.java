package com.example.bottomnavigationbar.object;

public class Chapter {
    private String id,chapter_name,chapter_date,comics_id;

    public Chapter(String id, String chapter_name, String chapter_date, String comics_id) {
        this.id = id;
        this.chapter_name = chapter_name;
        this.chapter_date = chapter_date;
        this.comics_id = comics_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getChapter_name() {
        return chapter_name;
    }

    public void setChapter_name(String chapter_name) {
        this.chapter_name = chapter_name;
    }

    public String getChapter_date() {
        return chapter_date;
    }

    public void setChapter_date(String chapter_date) {
        this.chapter_date = chapter_date;
    }

    public String getComics_id() {
        return comics_id;
    }

    public void setComics_id(String comics_id) {
        this.comics_id = comics_id;
    }
}
