package io.github.KasperSJensen.beFreed.ui.Journal;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "note_table")
public class Note {

    @PrimaryKey(autoGenerate = true)
    int Id;
    String noteText;
    String title;
    String date;
    int picture;

    public Note(String noteText, String title, int picture) {
        this.noteText = noteText;
        this.title = title;
        this.picture = picture;
    }

    public String getDate() {
        return date;
    }


    public String getNoteText() {
        return noteText;
    }

    public int getPicture() {
        return picture;
    }


    public String getTitle() {
        return title;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }

}
