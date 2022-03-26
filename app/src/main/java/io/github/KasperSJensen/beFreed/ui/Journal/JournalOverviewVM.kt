package io.github.KasperSJensen.beFreed.ui.Journal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.github.KasperSJensen.beFreed.R
import java.util.ArrayList

class JournalOverviewVM : ViewModel() {

    private var newList: ArrayList<Note> = ArrayList<Note>()
    private lateinit var notes: ArrayList<Note>

    init {
        notes = newList


        notes.add(
            Note(
                "kenobi","The boss",
                R.drawable.obiwan
            )
        )
        notes.add(
            Note(
                "kenobi","The boss",
                R.drawable.obiwan
            )
        )
        notes.add(
            Note(
                "kenobi","The boss",
                R.drawable.obiwan
            )
        )
        notes.add(
            Note(
                "kenobi","The boss",
                R.drawable.obiwan
            )
        )
        notes.add(
            Note(
                "kenobi","The boss",
                R.drawable.obiwan
            )
        )
        notes.add(
            Note(
                "kenobi","The boss",
                R.drawable.obiwan
            )
        )
        notes.add(
            Note(
                "kenobi","The boss",
                R.drawable.obiwan
            )
        )
        notes.add(
            Note(
                "kenobi","The boss",
                R.drawable.obiwan
            )
        )
        notes.add(
            Note(
                "kenobi","The boss",
                R.drawable.obiwan
            )
        )
        notes.add(
            Note(
                "kenobi","The boss",
                R.drawable.obiwan
            )
        )
        notes.add(
            Note(
                "kenobi","The boss",
                R.drawable.obiwan
            )
        )
        notes.add(
            Note(
                "kenobi","The boss",
                R.drawable.obiwan
            )
        )
    }

    fun getAllNotes(): ArrayList<Note> {
        return notes
    }

    fun addNote(note: Note) {
        notes.add(note)
    }

    fun deleteAllNotes() {
        notes.clear()
    }



}