package io.github.KasperSJensen.beFreed.ui.Journal

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.github.KasperSJensen.beFreed.Model.NoteRepositoryRoom
import java.text.DateFormat
import java.util.*
import kotlin.collections.ArrayList

class JournalOverviewVM(application: Application) : AndroidViewModel(application) {

    private var newList: ArrayList<Note>? = ArrayList()
    private lateinit var repository: NoteRepositoryRoom

    val notes: MutableLiveData<ArrayList<Note>> by lazy {
        MutableLiveData<ArrayList<Note>>()
    }
    lateinit var calendar: Calendar

    init {
        repository = NoteRepositoryRoom.getInstance(application)
    }

    fun getAllNotes(): LiveData<List<Note>> {
        return repository.allNotes
    }

    fun addNote(note: Note) {

        calendar = Calendar.getInstance()
        val currentDate: String = DateFormat.getDateInstance().format(calendar.time)

        note.date = currentDate
        repository.insert(note)
    }

    fun deleteNote(id: Int) {
        repository.delete(id)
    }

    fun deleteAllNotes() {
       repository.deleteAllNotes()
    }


}