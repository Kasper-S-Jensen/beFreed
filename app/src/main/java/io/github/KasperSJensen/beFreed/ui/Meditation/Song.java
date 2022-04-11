package io.github.KasperSJensen.beFreed.ui.Meditation;

import io.github.KasperSJensen.beFreed.R;

public class Song {

    private int Id;
    private String Title;
    private int file;
    private int picture;

    public Song(String title, int file) {
        Title = title;
        this.file = file;
        setPicture(R.drawable.ic_music_note);
    }


    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public int getFile() {
        if (file!=0)
        {
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
