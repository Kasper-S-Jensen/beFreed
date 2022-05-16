package io.github.KasperSJensen.beFreed.ui.Journal.AddNote

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

import io.github.KasperSJensen.beFreed.Model.NoteRepositoryFirebase
import io.github.KasperSJensen.beFreed.ui.Journal.Note
import java.text.DateFormat
import java.util.*

class AddNoteVM(application: Application) : AndroidViewModel(application) {
    private var repository: NoteRepositoryFirebase = NoteRepositoryFirebase.getInstance(application)

    fun addNote(note: Note) {
        repository.addNote(note)
    }
}