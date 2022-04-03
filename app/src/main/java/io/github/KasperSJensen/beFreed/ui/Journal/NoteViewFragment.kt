package io.github.KasperSJensen.beFreed.ui.Journal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import io.github.KasperSJensen.beFreed.R

class NoteViewFragment : Fragment() {

    val args: NoteViewFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //setup viewmodel
        var viewModel: JournalOverviewVM? =
            ViewModelProvider(requireActivity())[JournalOverviewVM::class.java]

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_note_view, container, false)
        val noteDate = args.noteDate
        val noteTitle = args.noteTitle
        val noteText = args.noteText
        val noteID = args.id


        var titleTextView: TextView = view.findViewById(R.id.view_note_title)
        var textTextView: TextView = view.findViewById(R.id.view_note_text)
        var dateTextView: TextView = view.findViewById(R.id.view_note_date)
        var deleteButton: Button = view.findViewById(R.id.view_note_deleteButton)
        titleTextView.setText(noteTitle).toString()
        textTextView.setText(noteText).toString()
        dateTextView.setText(noteDate).toString()
        deleteButton.setOnClickListener {
            viewModel?.deleteNote(noteID)
            activity?.onBackPressed()
        }


        return view
    }


}
