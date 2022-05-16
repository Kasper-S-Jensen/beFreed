package io.github.KasperSJensen.beFreed.ui.Challenges.ViewChallenge

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import io.github.KasperSJensen.beFreed.R
import io.github.KasperSJensen.beFreed.ui.Challenges.Challenge

class ChallengeViewFragment : Fragment() {

    private val args: ChallengeViewFragmentArgs by navArgs()
    private lateinit var viewModel: ChallengeViewVM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_challenge_view, container, false)

        //setup viewModel
        viewModel = ViewModelProvider(requireActivity())[ChallengeViewVM::class.java]


        //create variables to store data from safeArgs
        val challengeRecommendedLevel = args.recommendedLevel
        val challengeTitle = args.title
        val challengeDescription = args.description
        val challengeExperience = args.experience
        val challengePicture = args.picture

        // Inflate the layout for this fragment
        val titleTextView: TextView = rootView.findViewById(R.id.challengeTitle)
        val recLvlTextView: TextView = rootView.findViewById(R.id.challengeRecommendedLevel)
        val descriptionTextView: TextView = rootView.findViewById(R.id.challengeDescrip)
        descriptionTextView.movementMethod = ScrollingMovementMethod()
        val experience: TextView = rootView.findViewById(R.id.challengeVIewExp)
        val picture: ImageView = rootView.findViewById(R.id.challengeViewPic)
        val button: Button = rootView.findViewById(R.id.acceptChallengeButton)

        titleTextView.text = challengeTitle
        recLvlTextView.text = challengeRecommendedLevel.toString()
        descriptionTextView.text = challengeDescription
        experience.text = challengeExperience.toString()
        picture.setImageResource(R.drawable.challengespic)


        val dialogClickListener =
            DialogInterface.OnClickListener { _, which ->
                when (which) {
                    DialogInterface.BUTTON_POSITIVE -> {
                        val challenge = Challenge(
                            challengeRecommendedLevel,
                            challengeTitle,
                            challengeDescription,
                            challengeExperience,
                            challengePicture
                        )
                        Toast.makeText(this.context, "Challenge accepted", Toast.LENGTH_SHORT).show()
                        viewModel.acceptChallenge(challenge)
                        activity?.onBackPressed()
                    }
                    DialogInterface.BUTTON_NEGATIVE -> {}
                }
            }

        button.setOnClickListener {
            val builder: AlertDialog.Builder = AlertDialog.Builder(context)
            builder.setMessage("Are you sure you want to accept this challenge?")
                .setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show()
        }
        return rootView
    }
}