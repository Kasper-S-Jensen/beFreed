package io.github.KasperSJensen.beFreed.ui.Journal

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import io.github.KasperSJensen.beFreed.R


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

        //setup viewmodel
        var viewModel: JournalOverviewVM? =
            ViewModelProvider(requireActivity())[JournalOverviewVM::class.java]
        val journalObserver = Observer<List<Note>> { newJournal ->
            Log.i("XXXXX", newJournal.toString())
            noteAdapter.setNotes(newJournal)
            Toast.makeText(this.context, "was observed", Toast.LENGTH_SHORT).show()
        }
        viewModel?.getAllNotes()?.observe(this.viewLifecycleOwner, journalObserver)



        noteAdapter.setOnClickListener { note: Note ->
            val action =
                JournalOverviewFragmentDirections.actionJournalOverviewFragmentToNoteViewFragment(
                    note.title,
                    note.noteText,
                    note.date,
                    note.Id
                )
            Navigation.findNavController(view).navigate(action)
        }


        val FAB = view.findViewById<FloatingActionButton>(R.id.floatingActionButton)
        FAB.setOnClickListener() {
            val action =
                JournalOverviewFragmentDirections.actionJournalOverviewFragmentToAddNoteFragment()
            Navigation.findNavController(view).navigate(action)
            // Navigation.findNavController(view).navigate(R.id.action_journalOverviewFragment_to_addNoteFragment)
            // noteAdapter.notifyItemInserted(viewModel?.getAllNotes()?.size!! -1)
        }



        return view
    }


}