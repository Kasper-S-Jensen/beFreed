package io.github.KasperSJensen.beFreed.ui.Journal;

public class Note {

    private String noteText;
    private String title;
    private String date;
    private int picture;

   public Note(String noteText, String titel, int picture) {
        this.noteText = noteText;
        this.title = titel;
        this.picture = picture;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
