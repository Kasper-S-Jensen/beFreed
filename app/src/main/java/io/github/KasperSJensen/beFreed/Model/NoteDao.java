package io.github.KasperSJensen.beFreed.Model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import io.github.KasperSJensen.beFreed.R;
import io.github.KasperSJensen.beFreed.ui.Journal.Note;

public class NoteDao {

    private MutableLiveData<List<Note>> allNotes;
    private static NoteDao instance;

    private NoteDao() {
        allNotes = new MutableLiveData<>();
        List<Note> newList = new ArrayList<>();
        newList.add(
                new Note(
                        "kenobi", "The boss",
                        R.drawable.obiwan
                )
        );
        newList.add(
                new Note(
                        "kenobi", "The boss",
                        R.drawable.obiwan
                )
        );
        newList.add(
                new Note(
                        "kenobi", "The boss",
                        R.drawable.obiwan
                )
        );
        newList.add(
                new Note(
                        "kenobi", "The boss",
                        R.drawable.obiwan
                )
        );
        allNotes.setValue(newList);
    }

    public static NoteDao getInstance() {
        if (instance == null) {
            instance = new NoteDao();
        }
        return instance;
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }

    public void insert(Note note) {
        List<Note> currentNotes = allNotes.getValue();
        currentNotes.add(note);
        allNotes.setValue(currentNotes);
    }

    public void delete(Note note) {
        List<Note> currentNotes = allNotes.getValue();
        assert currentNotes != null;
        for (Note currentNote : currentNotes) {
            if ((currentNote.getTitle()).equals(note.getTitle()) && (currentNote.getNoteText()).equals(note.getNoteText())) {
                currentNotes.remove(note);
                break;
            }
        }

        allNotes.setValue(currentNotes);
    }

    public void deleteAllNotes() {
        List<Note> newList = new ArrayList<>();
        allNotes.setValue(newList);
    }

}
