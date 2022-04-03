package io.github.KasperSJensen.beFreed.ui.Journal.ViewNote

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import io.github.KasperSJensen.beFreed.Model.NoteRepositoryRoom

class NoteVIewVM(application: Application) : AndroidViewModel(application) {
    private var repository: NoteRepositoryRoom = NoteRepositoryRoom.getInstance(application)

    fun deleteNote(id: Int) {
        repository.delete(id)
    }
}