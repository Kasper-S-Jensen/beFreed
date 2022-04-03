package io.github.KasperSJensen.beFreed.Model;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import androidx.core.os.HandlerCompat;
import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.github.KasperSJensen.beFreed.ui.Journal.Note;

public class NoteRepositoryRoom {

    private static NoteRepositoryRoom instance;
    private final INoteDao noteDao;
    private final LiveData<List<Note>> allNotes;
    private final ExecutorService executorService;
    private final Handler mainThreadHandler;

    private NoteRepositoryRoom(Application application) {
        JournalDatabase database = JournalDatabase.getInstance(application);
        noteDao = database.noteDao();
        allNotes = noteDao.getAllNotes();
        executorService = Executors.newFixedThreadPool(2);
        mainThreadHandler = HandlerCompat.createAsync(Looper.getMainLooper());
    }

    public static synchronized NoteRepositoryRoom getInstance(Application application) {
        if (instance == null)
            instance = new NoteRepositoryRoom(application);

        return instance;
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }

    public void insert(Note note) {
        executorService.execute(() -> noteDao.insert(note));
    }
    public void delete(Note note) {
        executorService.execute(() -> noteDao.delete(note));
    }

    public void deleteAllNotes() {
        executorService.execute(noteDao::deleteAllNotes);
    }

}
