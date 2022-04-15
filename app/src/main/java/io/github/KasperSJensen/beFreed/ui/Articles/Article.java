package io.github.KasperSJensen.beFreed.ui.Articles;

public class Article {

    private String title;
    private int picture;
    private int id;
    private String date;
    private String URL;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getDate() {
        return date;
    }

    public Article(String title, int picture, String URL) {
        this.title = title;
        this.picture = picture;
        this.URL = URL;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPicture() {
        return picture;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }
}
