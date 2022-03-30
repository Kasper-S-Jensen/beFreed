package io.github.KasperSJensen.beFreed.ui.Journal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import io.github.KasperSJensen.beFreed.R

class NoteViewFragment : Fragment() {

    val args: NoteViewFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_note_view, container, false)

        val noteTitle = args.noteTitle
        val noteText = args.noteText


        var titleTextView: TextView = view.findViewById(R.id.view_note_title)
        var textTextView: TextView = view.findViewById(R.id.view_note_text)
        titleTextView.setText(noteTitle).toString()
        textTextView.setText(noteText).toString()


        return view
    }


}
