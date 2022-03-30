package io.github.KasperSJensen.beFreed;

import androidx.lifecycle.LiveData;

import java.util.List;

import io.github.KasperSJensen.beFreed.ui.Journal.Note;

public class NoteRepository {
    private NoteDao noteDao;
    private static NoteRepository instance;

    private NoteRepository(){
        noteDao = NoteDao.getInstance();
    }

    public static NoteRepository getInstance(){
        if(instance == null)
            instance = new NoteRepository();

        return instance;
    }

    public LiveData<List<Note>> getAllNotes(){
        return noteDao.getAllNotes();
    }

    public void insert(Note note) {
        noteDao.insert(note);
    }

    public void deleteAllNotes(){
        noteDao.deleteAllNotes();
    }

}
