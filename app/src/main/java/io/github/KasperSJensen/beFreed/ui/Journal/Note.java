package io.github.KasperSJensen.beFreed.ui.Journal;

public class Note {

    String id;
    String noteText;
    String title;
    String date;
    float moodRating;


    public Note(String noteText, String title, float moodRating) {
        this.noteText = noteText;
        this.title = title;
        this.moodRating = moodRating;
    }

    public Note() {

    }

    public String getDate() {

        return date;
    }


    public String getNoteText() {
        return noteText;
    }

    public float getMoodRating() {
        return moodRating;
    }


    public String getTitle() {
        return title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public void setMoodRating(float moodRating) {
        this.moodRating = moodRating;
    }

}
