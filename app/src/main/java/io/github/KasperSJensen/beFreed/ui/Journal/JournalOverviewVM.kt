package io.github.KasperSJensen.beFreed.ui.Journal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.github.KasperSJensen.beFreed.NoteRepository
import io.github.KasperSJensen.beFreed.R

class JournalOverviewVM : ViewModel() {

    private var newList: ArrayList<Note>? = ArrayList()
    private lateinit var repository: NoteRepository

    val notes: MutableLiveData<ArrayList<Note>> by lazy {
        MutableLiveData<ArrayList<Note>>()
    }


    init {
        repository = NoteRepository.getInstance()



    }

    fun getAllNotes(): LiveData<List<Note>> {
        return repository.allNotes
    }

    fun addNote(note: Note) {
        repository.insert(note)
    }

    fun deleteAllNotes() {
        val currentNotes: ArrayList<Note>? = notes.value
        currentNotes?.clear()
        notes.value = currentNotes
    }


}