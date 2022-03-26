package io.github.KasperSJensen.beFreed.ui.Journal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
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



        //setup viewmodel
        var viewModel: JournalOverviewVM?= ViewModelProvider(this)[JournalOverviewVM::class.java]

        //setup recyclerview
        val noteList: RecyclerView
        noteList = view.findViewById(R.id.recyclerview)
        noteList.hasFixedSize()
        noteList.layoutManager= LinearLayoutManager(view.context)

        //setup Adapter
        val noteAdapter: NoteAdapter
        noteAdapter =
            NoteAdapter(
                viewModel?.getAllNotes()
            );
        noteList.adapter=noteAdapter
        noteAdapter.setOnClickListener { note: Note ->
            Toast.makeText(
                this.context,
                note.noteText,
                Toast.LENGTH_SHORT
            ).show()
        }

        val FAB = view.findViewById<FloatingActionButton>(R.id.floatingActionButton)
        FAB.setOnClickListener(){
            viewModel?.addNote(Note("Hey buddy","KENOBII",R.drawable.obiwan))
            noteAdapter.notifyItemInserted(viewModel?.getAllNotes()?.size!! -1)
        }







        return view
    }


}