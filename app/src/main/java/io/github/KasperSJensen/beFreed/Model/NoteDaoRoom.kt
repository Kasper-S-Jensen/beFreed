package io.github.KasperSJensen.beFreed.Model

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import io.github.KasperSJensen.beFreed.ui.Journal.Note

class NoteDaoRoom : INoteDao {



    override fun getAllNotes(): LiveData<MutableList<Note>> {
        TODO("Not yet implemented")
    }


    override fun insert(note: Note?) {
        TODO("Not yet implemented")
    }

    @Delete
    override fun delete(note: Note?) {
        TODO("Not yet implemented")
    }

    @Query("DELETE FROM note_table")
    override fun deleteAllNotes() {
        TODO("Not yet implemented")
    }
}