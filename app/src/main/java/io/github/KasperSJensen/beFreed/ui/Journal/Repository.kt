package io.github.KasperSJensen.beFreed.ui.Journal

import androidx.lifecycle.MutableLiveData
import io.github.KasperSJensen.beFreed.R

class Repository {

   val notes: MutableLiveData<ArrayList<Note>> by lazy {
       MutableLiveData<ArrayList<Note>>()
   }




    init {
        notes.value?.add(
            Note(
                "kenobi", "The boss",
                R.drawable.obiwan
            )
        )
        notes.value?.add(
            Note(
                "kenobi", "The boss",
                R.drawable.obiwan
            )
        )
        notes.value?.add(
            Note(
                "kenobi", "The boss",
                R.drawable.obiwan
            )
        )
        notes.value?.add(
            Note(
                "kenobi", "The boss",
                R.drawable.obiwan
            )
        )
    }

}