package io.github.KasperSJensen.beFreed.ui.Journal.AddNote

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import io.github.KasperSJensen.beFreed.Model.NoteRepository
import io.github.KasperSJensen.beFreed.ui.Journal.Note

class AddNoteVM(application: Application) : AndroidViewModel(application) {
    private var repository: NoteRepository = NoteRepository.getInstance(application)

    fun addNote(note: Note) {
        repository.addNote(note)
    }
}