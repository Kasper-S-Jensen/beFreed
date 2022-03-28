package io.github.KasperSJensen.beFreed.ui.Journal

import androidx.lifecycle.ViewModel

class AddNoteViewModel : ViewModel() {
    private var repository: Repository = Repository()







    fun addNote(note: Note) {

       repository.notes.add(note)
    }


}