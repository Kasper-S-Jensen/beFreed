package io.github.KasperSJensen.beFreed.ui.Meditation;

import java.net.URI;

import io.github.KasperSJensen.beFreed.R;

public class Track {

    private int id;
    private String title;
    private int file;
    private int picture;

    public Track(String title, int file) {
        this.title = title;
        this.file = file;
        setPicture(R.drawable.ic_music_note);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getFile() {
        if (file != 0) {
            return file;
        }
        return 0;

    }

    public void setFile(int file) {
        this.file = file;
    }

    public int getPicture() {
        return picture;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }
}
