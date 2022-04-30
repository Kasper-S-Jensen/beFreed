package io.github.KasperSJensen.beFreed.ui.Journal.Overview

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import io.github.KasperSJensen.beFreed.Model.NoteRepositoryRoom
import io.github.KasperSJensen.beFreed.ui.Journal.Note

class JournalOverviewVM(application: Application) : AndroidViewModel(application) {

    private var repository: NoteRepositoryRoom = NoteRepositoryRoom.getInstance(application)

    fun getAllNotes(): LiveData<List<Note>> {


        return repository.allNotes



    }

    fun deleteAllNotes() {
        repository.deleteAllNotes()
    }

}