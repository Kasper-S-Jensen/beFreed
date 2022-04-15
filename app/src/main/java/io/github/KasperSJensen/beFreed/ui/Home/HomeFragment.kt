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
        val informationButton = view.findViewById<CardView>(R.id.informationCardView)
        val meditationButton = view.findViewById<CardView>(R.id.meditationCardView)
        val articlesButton = view.findViewById<CardView>(R.id.articlesCardView)

        challengesButton.setOnClickListener {
            val action =
                HomeFragmentDirections.actionHomeFragmentToChallengesFragment()
            Navigation.findNavController(view).navigate(action)
        }
        meditationButton.setOnClickListener {
            val action =
                HomeFragmentDirections.actionHomeFragmentToMeditationFragment()
            Navigation.findNavController(view).navigate(action)
        }

        articlesButton.setOnClickListener {
            val action =
                HomeFragmentDirections.actionHomeFragmentToArticlesFragment()
            Navigation.findNavController(view).navigate(action)
        }


        return view
    }

}