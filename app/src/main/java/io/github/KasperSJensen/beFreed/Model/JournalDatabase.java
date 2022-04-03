package io.github.KasperSJensen.beFreed.Model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import io.github.KasperSJensen.beFreed.ui.Journal.Note;

@Database(entities = {Note.class}, version = 1)
public abstract class JournalDatabase extends RoomDatabase {

    private static JournalDatabase instance;
    public abstract INoteDao noteDao();

    public static synchronized JournalDatabase getInstance(Context context){
        if(instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    JournalDatabase.class, "note_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }


}
