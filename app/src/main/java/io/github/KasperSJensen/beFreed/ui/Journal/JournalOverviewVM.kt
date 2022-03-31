package io.github.KasperSJensen.beFreed.ui.Journal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.github.KasperSJensen.beFreed.NoteRepository
import io.github.KasperSJensen.beFreed.R
import java.text.DateFormat
import java.util.*
import kotlin.collections.ArrayList

class JournalOverviewVM : ViewModel() {

    private var newList: ArrayList<Note>? = ArrayList()
    private lateinit var repository: NoteRepository

    val notes: MutableLiveData<ArrayList<Note>> by lazy {
        MutableLiveData<ArrayList<Note>>()
    }
    lateinit var calendar: Calendar

    init {
        repository = NoteRepository.getInstance()


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

    fun deleteNote(note: Note) {
        repository.delete(note)
    }

    fun deleteAllNotes() {
        val currentNotes: ArrayList<Note>? = notes.value
        currentNotes?.clear()
        notes.value = currentNotes
    }


}