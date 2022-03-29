package io.github.KasperSJensen.beFreed.ui.Journal

import android.content.Intent
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.os.Bundle
import android.view.*
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import io.github.KasperSJensen.beFreed.R


class AddNoteFragment : Fragment() {

    lateinit var noteTitle: EditText
    lateinit var noteText: EditText

    companion object {
        fun newInstance() = AddNoteFragment()
    }

    lateinit var viewModel: JournalOverviewVM


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.add_note_fragment, container, false)
        setHasOptionsMenu(true)

        val toolbar = view.findViewById<Toolbar>(R.id.add_note_toolbar)
        toolbar.inflateMenu(R.menu.add_note_menu)
        (activity as AppCompatActivity?)!!.setSupportActionBar(toolbar)

          viewModel= ViewModelProvider(requireActivity())[JournalOverviewVM::class.java]

        noteTitle = view.findViewById(R.id.add_note_title)
        noteText = view.findViewById(R.id.add_note_text)




        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_note_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.save_note) {
            saveNote()
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
        //TODO: tilføjer ikke note
        viewModel?.addNote(Note(noteText, noteTitle, R.drawable.obiwan))
        activity?.onBackPressed()


    }
}