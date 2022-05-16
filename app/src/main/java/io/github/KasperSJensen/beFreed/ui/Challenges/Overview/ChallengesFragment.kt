package io.github.KasperSJensen.beFreed.ui.Challenges.Overview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.github.KasperSJensen.beFreed.R
import io.github.KasperSJensen.beFreed.ui.Challenges.Challenge
import io.github.KasperSJensen.beFreed.ui.Challenges.ChallengeAdapter
import io.github.KasperSJensen.beFreed.ui.Journal.Overview.JournalOverviewFragmentDirections


class ChallengesFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_challenges, container, false)

        //setup recyclerview
        val recyclerView: RecyclerView = rootView.findViewById(R.id.challengesRecyclerview)
        recyclerView.hasFixedSize()
        recyclerView.layoutManager = GridLayoutManager(rootView.context, 2)

        //setup Adapter
        val challengesAdapter = ChallengeAdapter()
        challengesAdapter.setChallenges(mutableListOf())
        recyclerView.adapter = challengesAdapter

        //setup viewModel
        val viewModel: ChallengesVM =
            ViewModelProvider(requireActivity())[ChallengesVM::class.java]

        val challengesObserve = Observer<List<Challenge>> { newChallenges ->
            challengesAdapter.setChallenges(newChallenges)
        }

        viewModel.getAllChallenges().observe(this.viewLifecycleOwner, challengesObserve)

        challengesAdapter.setOnClickListener { challenge: Challenge ->
            val action =
                ChallengesFragmentDirections
                    .actionChallengesFragmentToChallengeViewFragment(
                        challenge.recommendedLevel,
                        challenge.title,
                        challenge.description,
                        challenge.experience,
                        challenge.picture
                    )
            Navigation.findNavController(rootView).navigate(action)
        }
        return rootView
    }
}