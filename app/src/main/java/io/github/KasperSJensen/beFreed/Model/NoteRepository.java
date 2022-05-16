package io.github.KasperSJensen.beFreed.Model;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import org.jetbrains.annotations.NotNull;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import io.github.KasperSJensen.beFreed.ui.Journal.Note;

public class NoteRepository {
    private static NoteRepository instance;
    Calendar calendar;
    private final NoteDAO dao= new NoteDAO();

    private NoteRepository(Application application) {
    }

    public static synchronized NoteRepository getInstance(Application application) {
        if (instance == null)
            instance = new NoteRepository(application);

        return instance;
    }

    public LiveData<List<Note>> getAllNotes() {
      return dao.getAllNotes();
    }

    public void delete(String id) {
      dao.delete(id);
    }


    public void addNote(@NotNull Note note) {
        dao.addNote(note);
    }
}
