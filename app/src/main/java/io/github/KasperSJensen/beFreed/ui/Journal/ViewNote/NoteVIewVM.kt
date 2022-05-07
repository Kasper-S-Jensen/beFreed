package io.github.KasperSJensen.beFreed.ui.Journal.ViewNote

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import io.github.KasperSJensen.beFreed.Model.NoteRepositoryFirebase

class NoteVIewVM(application: Application) : AndroidViewModel(application) {
    private var repository: NoteRepositoryFirebase = NoteRepositoryFirebase.getInstance(application)

    fun deleteNote(id: String) {
        repository.delete(id)
    }
}