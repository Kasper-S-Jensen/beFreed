package io.github.KasperSJensen.beFreed.ui.Journal.AddNote

import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import io.github.KasperSJensen.beFreed.R
import io.github.KasperSJensen.beFreed.ui.Journal.Note


class AddNoteFragment : Fragment() {

    lateinit var noteTitle: EditText
    lateinit var noteText: EditText
    lateinit var ratingBar: RatingBar

    lateinit var viewModel: AddNoteVM


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.add_note_fragment, container, false)

        setHasOptionsMenu(true)

        // val toolbar = view.findViewById<Toolbar>(R.id.add_note_toolbar)
        //  toolbar.inflateMenu(R.menu.add_note_menu)
        // (activity as AppCompatActivity?)!!.setSupportActionBar(toolbar)

        viewModel = ViewModelProvider(requireActivity())[AddNoteVM::class.java]
        ratingBar = view.findViewById(R.id.ratingBar)
        noteTitle = view.findViewById(R.id.add_note_title)
        noteText = view.findViewById(R.id.add_note_text)









        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        menu.findItem(R.id.toggleDarkMode).isVisible = false
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.save_note) {
            saveNote()
        } else if (id == R.id.toggleDarkMode) {
            AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO)
        }

        return super.onOptionsItemSelected(item)
    }

    private fun saveNote() {
        var noteTitle: String = noteTitle.text.toString()
        var noteText: String = noteText.text.toString()

        if (noteTitle.trim().isEmpty() || noteText.trim().isEmpty()) {
            Toast.makeText(this.context, "Please write both a title and a text", Toast.LENGTH_SHORT)
                .show()
            return
        }

        viewModel.addNote(Note(noteText, noteTitle, ratingBar.rating))
        activity?.onBackPressed()


    }

    override fun onDestroy() {
        super.onDestroy()


        val manager: FragmentManager = parentFragmentManager
        val transaction = manager.beginTransaction()
        //  transaction.remove(AddNoteFragment).commit()

    }
}