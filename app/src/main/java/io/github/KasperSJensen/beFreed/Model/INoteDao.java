package io.github.KasperSJensen.beFreed.Model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.github.KasperSJensen.beFreed.ui.Journal.Note;

@Dao
public interface INoteDao {

    @Query("SELECT * FROM note_table ORDER BY Id ASC")
    LiveData<List<Note>> getAllNotes();

    @Insert
    void insert(Note note);

    @Delete
    void delete(Note note);

    @Query("DELETE FROM note_table")
    void deleteAllNotes();

}
