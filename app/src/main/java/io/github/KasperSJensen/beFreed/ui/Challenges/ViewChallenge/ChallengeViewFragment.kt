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

    val args: ChallengeViewFragmentArgs by navArgs()
    lateinit var viewModel: ChallengeViewVM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_challenge_view, container, false)

        //setup viewmodel
        viewModel = ViewModelProvider(requireActivity())[ChallengeViewVM::class.java]


        // Inflate the layout for this fragment
        val challengeRecommendedLevel = args.recommendedLevel
        val challengeTitle = args.title
        val challengeDescription = args.description
        val challengeExperience = args.experience
        val challengePicture = args.picture

        var titleTextView: TextView = rootView.findViewById(R.id.challengeTitle)
        var recLvlTextView: TextView = rootView.findViewById(R.id.challengeRecommendedLevel)
        var descriptionTextView: TextView = rootView.findViewById(R.id.challengeDescrip)
        descriptionTextView.movementMethod = ScrollingMovementMethod()
        var experience: TextView = rootView.findViewById(R.id.challengeVIewExp)
        var picture: ImageView = rootView.findViewById(R.id.challengeViewPic)
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

                        val challenge = Challenge(challengeRecommendedLevel,challengeTitle,challengeDescription,challengeExperience,challengePicture)
                        viewModel.acceptChallenge(challenge)
                        Toast.makeText(context, "yes", Toast.LENGTH_SHORT).show();

                    }
                    DialogInterface.BUTTON_NEGATIVE -> {
                        Toast.makeText(context, "no", Toast.LENGTH_SHORT).show();
                    }
                }
            }

        button.setOnClickListener {
            val builder: AlertDialog.Builder = AlertDialog.Builder(context)
            builder.setMessage("Are you sure you want to accept this challenge?").setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show()
        }

        return rootView
    }


}