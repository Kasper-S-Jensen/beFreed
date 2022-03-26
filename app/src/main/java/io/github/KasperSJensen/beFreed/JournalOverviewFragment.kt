package io.github.KasperSJensen.beFreed

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class JournalOverviewFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_journal_overview, container, false)

        val celebrityList: RecyclerView
        val celebrityAdapter: CelebrityAdapter

        celebrityList = view.findViewById(R.id.recyclerview)
        celebrityList.hasFixedSize()
        celebrityList.layoutManager= LinearLayoutManager(view.context)

        var celebrities = ArrayList<Celebrity>()
        celebrities.add(Celebrity("kenobi",R.drawable.obiwan))
        celebrities.add(Celebrity("jens",R.drawable.obiwan))
        celebrities.add(Celebrity("bob",R.drawable.obiwan))
        celebrities.add(Celebrity("hans",R.drawable.obiwan))
        celebrities.add(Celebrity("kennet",R.drawable.obiwan))
        celebrities.add(Celebrity("kenobi",R.drawable.obiwan))
        celebrities.add(Celebrity("jens",R.drawable.obiwan))
        celebrities.add(Celebrity("bob",R.drawable.obiwan))
        celebrities.add(Celebrity("hans",R.drawable.obiwan))
        celebrities.add(Celebrity("kennet",R.drawable.obiwan))
        celebrities.add(Celebrity("kenobi",R.drawable.obiwan))
        celebrities.add(Celebrity("jens",R.drawable.obiwan))
        celebrities.add(Celebrity("bob",R.drawable.obiwan))
        celebrities.add(Celebrity("hans",R.drawable.obiwan))
        celebrities.add(Celebrity("kennet",R.drawable.obiwan))
        celebrities.add(Celebrity("kenobi",R.drawable.obiwan))
        celebrities.add(Celebrity("jens",R.drawable.obiwan))
        celebrities.add(Celebrity("bob",R.drawable.obiwan))
        celebrities.add(Celebrity("hans",R.drawable.obiwan))
        celebrities.add(Celebrity("kennet",R.drawable.obiwan))
        celebrityAdapter = CelebrityAdapter(celebrities);
        celebrityList.adapter=celebrityAdapter

        celebrityAdapter.setOnClickListener { celebrity: Celebrity ->
            Toast.makeText(
                this.context,
                celebrity.name,
                Toast.LENGTH_SHORT
            ).show()
        }

        return view
    }


}