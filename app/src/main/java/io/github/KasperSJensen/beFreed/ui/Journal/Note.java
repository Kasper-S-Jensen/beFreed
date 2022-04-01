package io.github.KasperSJensen.beFreed.ui.Journal;

public class Note {

    String noteText;
    String title;
    String date;
    int picture;

    public Note(String noteText, String titel, int picture) {
        this.noteText = noteText;
        this.title = titel;
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
}
