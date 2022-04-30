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
    lateinit var calendar: Calendar


    fun addNote(note: Note) {
        var uId:String?
        val mAuth = FirebaseAuth.getInstance()
        if (mAuth.currentUser!=null) {
             uId = mAuth.currentUser?.uid

            calendar = Calendar.getInstance()
            val currentDate: String = DateFormat.getDateInstance().format(calendar.time)


            note.date = currentDate
            // repository.insert(note)


            val database =
                Firebase.database("https://befreed-default-rtdb.europe-west1.firebasedatabase.app")

            val myRef = database.reference
            if (note.id!=null) {
                myRef.child("Notes").child(uId!!).child(note.id).setValue(note)
            }
            else
            {
                myRef.child("Notes").child(uId!!).push().setValue(note)
            }

        }



    }


}