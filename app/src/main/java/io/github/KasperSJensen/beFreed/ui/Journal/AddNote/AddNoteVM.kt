package io.github.KasperSJensen.beFreed.ui.Journal.AddNote

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import io.github.KasperSJensen.beFreed.Model.NoteRepositoryRoom
import io.github.KasperSJensen.beFreed.ui.Journal.Note
import java.text.DateFormat
import java.util.*

class AddNoteVM(application: Application) : AndroidViewModel(application) {
    private var repository: NoteRepositoryRoom = NoteRepositoryRoom.getInstance(application)
    lateinit var calendar: Calendar


    fun addNote(note: Note) {

        calendar = Calendar.getInstance()
        val currentDate: String = DateFormat.getDateInstance().format(calendar.time)

        note.date = currentDate
        repository.insert(note)
    }




}