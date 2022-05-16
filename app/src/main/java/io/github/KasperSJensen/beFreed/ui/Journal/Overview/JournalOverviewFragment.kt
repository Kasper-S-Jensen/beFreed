package io.github.KasperSJensen.beFreed.ui.Journal.Overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import io.github.KasperSJensen.beFreed.R
import io.github.KasperSJensen.beFreed.ui.Journal.Note
import io.github.KasperSJensen.beFreed.ui.Journal.NoteAdapter


class JournalOverviewFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_journal_overview, container, false)

        //setup recyclerview
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerview)
        recyclerView.hasFixedSize()
        recyclerView.layoutManager = LinearLayoutManager(view.context)

        //setup Adapter
        val noteAdapter = NoteAdapter()
        noteAdapter.setNotes(mutableListOf())
        recyclerView.adapter = noteAdapter

        //setup viewModel
        val viewModel: JournalOverviewVM? =
            ViewModelProvider(requireActivity())[JournalOverviewVM::class.java]

        val journalObserver = Observer<List<Note>> { newJournal ->
            noteAdapter.setNotes(newJournal)
        }
        viewModel?.getAllNotes()?.observe(this.viewLifecycleOwner, journalObserver)



        noteAdapter.setOnClickListener { note: Note ->
            val action =
                JournalOverviewFragmentDirections.actionJournalOverviewFragmentToNoteViewFragment(
                    note.title,
                    note.noteText,
                    note.date,
                    note.id,
                    note.moodRating
                )
            Navigation.findNavController(view).navigate(action)
        }


        val FAB = view.findViewById<FloatingActionButton>(R.id.floatingActionButton)
        FAB.setOnClickListener {
            val action =
                JournalOverviewFragmentDirections.actionJournalOverviewFragmentToAddNoteFragment()
            Navigation.findNavController(view).navigate(action)
        }
        return view
    }
}