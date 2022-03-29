package io.github.KasperSJensen.beFreed.ui.Journal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.github.KasperSJensen.beFreed.R

class JournalOverviewVM : ViewModel() {

    private var newList: ArrayList<Note>? = ArrayList()
    private var repository: Repository = Repository()

    val notes: MutableLiveData<ArrayList<Note>> by lazy {
        MutableLiveData<ArrayList<Note>>()
    }


    init {
        newList?.add(
            Note(
                "kenobi", "The boss",
                R.drawable.obiwan
            )
        )
        newList?.add(
            Note(
                "kenobi", "The boss",
                R.drawable.obiwan
            )
        )
        newList?.add(
            Note(
                "kenobi", "The boss",
                R.drawable.obiwan
            )
        )
        newList?.add(
            Note(
                "kenobi", "The boss",
                R.drawable.obiwan
            )
        )
     //   newList = repository.notes.value
        notes.value = newList
    }

    fun getAllNotes(): LiveData<ArrayList<Note>> {
        return notes
    }

    fun addNote(note: Note) {
        val currentNotes: ArrayList<Note>? = notes.value
        currentNotes?.add(note)
        notes.value = currentNotes
    }

    fun deleteAllNotes() {
        val currentNotes: ArrayList<Note>? = notes.value
        currentNotes?.clear()
        notes.value = currentNotes
    }


}