package io.github.KasperSJensen.beFreed.ui.Journal.AddNote

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

import io.github.KasperSJensen.beFreed.Model.NoteRepositoryRoom
import io.github.KasperSJensen.beFreed.ui.Journal.Note
import java.text.DateFormat
import java.util.*

class AddNoteVM(application: Application) : AndroidViewModel(application) {
    private var repository: NoteRepositoryRoom = NoteRepositoryRoom.getInstance(application)
    lateinit var calendar: Calendar


    fun addNote(note: Note) {

        val mAuth = FirebaseAuth.getInstance()
        val uId = mAuth.currentUser?.uid


        calendar = Calendar.getInstance()
        val currentDate: String = DateFormat.getDateInstance().format(calendar.time)

        note.date = currentDate
        repository.insert(note)


        val database =
            Firebase.database("https://befreed-default-rtdb.europe-west1.firebasedatabase.app")

        val myRef = database.reference
        myRef.child("Notes").child(uId!!).push().setValue(note)


    }


}