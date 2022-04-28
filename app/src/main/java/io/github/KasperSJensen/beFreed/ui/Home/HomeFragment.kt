package io.github.KasperSJensen.beFreed.ui.Home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import io.github.KasperSJensen.beFreed.R
import io.github.KasperSJensen.beFreed.ui.Journal.AddNote.AddNoteVM


class HomeFragment : Fragment() {

    private lateinit var mAuth: FirebaseAuth
    lateinit var viewModel: HomeVM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        viewModel= ViewModelProvider(requireActivity())[HomeVM::class.java]
        viewModel.init()





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