package io.github.KasperSJensen.beFreed.ui.Journal.ViewNote

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import io.github.KasperSJensen.beFreed.R

class NoteViewFragment : Fragment() {

    val args: NoteViewFragmentArgs by navArgs()
    lateinit var viewModel: NoteVIewVM
    private var noteID: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //setup viewmodel
        viewModel = ViewModelProvider(requireActivity())[NoteVIewVM::class.java]
        setHasOptionsMenu(true)


        //create variables to store data from safeArgs
        val view = inflater.inflate(R.layout.fragment_note_view, container, false)
        val noteDate = args.noteDate
        val noteTitle = args.noteTitle
        val noteText = args.noteText
        val moodRatingArg = args.moodRating
        noteID = args.id

        // Inflate the layout for this fragment
        var titleTextView: TextView = view.findViewById(R.id.view_note_title)
        var textTextView: TextView = view.findViewById(R.id.view_note_text)
        textTextView.movementMethod = ScrollingMovementMethod()
        var dateTextView: TextView = view.findViewById(R.id.view_note_date)
        var moodRating: TextView = view.findViewById(R.id.moodRatingView)

        titleTextView.setText(noteTitle).toString()
        textTextView.setText(noteText).toString()
        dateTextView.setText(noteDate).toString()
        moodRating.setText(moodRatingArg.toString()).toString()

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.view_note_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.delete) {
            viewModel.deleteNote(noteID)
            activity?.onBackPressed()
        }

        return super.onOptionsItemSelected(item)
    }
}
