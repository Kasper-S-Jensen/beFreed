package io.github.KasperSJensen.beFreed.ui.Home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.navigation.Navigation
import io.github.KasperSJensen.beFreed.R
import io.github.KasperSJensen.beFreed.ui.Journal.Overview.JournalOverviewFragmentDirections

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val challengesButton = view.findViewById<CardView>(R.id.challengesCardView)

        challengesButton.setOnClickListener {
            val action =
                HomeFragmentDirections.actionHomeFragmentToChallengesFragment()
            Navigation.findNavController(view).navigate(action)
        }


        return view
    }

}