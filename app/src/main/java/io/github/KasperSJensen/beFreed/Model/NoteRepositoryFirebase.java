package io.github.KasperSJensen.beFreed.Model;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.core.os.HandlerCompat;
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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.github.KasperSJensen.beFreed.ui.Journal.Note;

public class NoteRepositoryFirebase {

    private static NoteRepositoryFirebase instance;
    //  private final INoteDao noteDao;
    //  private final LiveData<List<Note>> allNotes;
    Calendar calendar;

    private final ExecutorService executorService;
    private final Handler mainThreadHandler;

    private NoteRepositoryFirebase(Application application) {
        // JournalDatabase database = JournalDatabase.getInstance(application);
        // noteDao = database.noteDao();
        //  allNotes = noteDao.getAllNotes();
        executorService = Executors.newFixedThreadPool(2);
        mainThreadHandler = HandlerCompat.createAsync(Looper.getMainLooper());

    }

    public static synchronized NoteRepositoryFirebase getInstance(Application application) {
        if (instance == null)
            instance = new NoteRepositoryFirebase(application);

        return instance;
    }

    public LiveData<List<Note>> getAllNotes() {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        String uId = "";
        if (mAuth.getCurrentUser() != null) {
            uId = mAuth.getCurrentUser().getUid();
        }


        List<Note> firebaseNotes = new ArrayList<>();
        MutableLiveData<List<Note>> firebaseMutNotes = new MutableLiveData<>();

        //  return allNotes;

        FirebaseDatabase database = FirebaseDatabase.getInstance("https://befreed-default-rtdb.europe-west1.firebasedatabase.app");

        DatabaseReference myRef = database.getReference("Users").child(uId).child("Notes");

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Note note = postSnapshot.getValue(Note.class);
                    if (note != null) {
                        note.setId(postSnapshot.getKey());
                    }
                    firebaseNotes.add(note);

                }
                firebaseMutNotes.postValue(firebaseNotes);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }


        });


        return firebaseMutNotes;
    }

    public void insert(Note note) {
        // executorService.execute(() -> noteDao.insert(note));
    }

    public void delete(String id) {
        FirebaseAuth mAuth;
        mAuth = FirebaseAuth.getInstance();

        FirebaseDatabase database = FirebaseDatabase.getInstance("https://befreed-default-rtdb.europe-west1.firebasedatabase.app");
        String uid = mAuth.getCurrentUser().getUid();
        DatabaseReference myRef = database.getReference("Notes").child(uid);

        myRef.child(id).removeValue();

        // executorService.execute(() -> noteDao.deleteById(id));


    }

    public void deleteAllNotes() {
        //   executorService.execute(noteDao::deleteAllNotes);
    }

    public void addNote(@NotNull Note note) {
        String uId;

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() != null) {
            uId = mAuth.getCurrentUser().getUid();

            calendar = Calendar.getInstance();
            String currentDate = DateFormat.getDateInstance().format(calendar.getTime());


            note.setDate(currentDate);
            // repository.insert(note)


            FirebaseDatabase database = FirebaseDatabase.getInstance("https://befreed-default-rtdb.europe-west1.firebasedatabase.app");

            DatabaseReference myRef = database.getReference();
            if (note.getId() != null) {
                myRef.child("Users").child(uId).child("Notes").child(note.getId()).setValue(note);
            } else {
                myRef.child("Users").child(uId).child("Notes").push().setValue(note);
            }

        }

    }
}
