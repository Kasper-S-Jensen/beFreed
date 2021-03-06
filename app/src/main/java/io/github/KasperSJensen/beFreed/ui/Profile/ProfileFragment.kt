package io.github.KasperSJensen.beFreed.ui.Profile

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import io.github.KasperSJensen.beFreed.R


class ProfileFragment : Fragment() {

    private lateinit var view_: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true)
        val user = FirebaseAuth.getInstance().currentUser
        view_ = inflater.inflate(R.layout.fragment_profile, container, false)

        //setup viewmodel
        val viewModel = ViewModelProvider(requireActivity())[ProfileVM::class.java]


        val userName: TextView = view_.findViewById(R.id.username)
        val userImage: ImageView = view_.findViewById(R.id.profilePic)
        val activeChallenge: TextView = view_.findViewById(R.id.activeChallenge)
        val userLevel: TextView = view_.findViewById(R.id.userLevel)
        val progressBar: ProgressBar = view_.findViewById(R.id.progressBar)
        val eXPProgress: TextView = view_.findViewById(R.id.totalEXP)
        val button: Button = view_.findViewById(R.id.completeChallengeBut)

        if (user != null) {
            viewModel.getUserExperience().observe(viewLifecycleOwner) {
                if (it != null) {
                    userLevel.text = (it / 100).toString()
                    progressBar.progress = ((it % 100).toInt())
                    val setExPProgressText = progressBar.progress.toString() + "/" + progressBar.max
                    eXPProgress.text = setExPProgressText
                }
            }

            viewModel.getActiveChallenge().observe(viewLifecycleOwner) {
                if (it != null) {
                    button.visibility = View.VISIBLE
                    activeChallenge.text = it.title
                    viewModel.activeChallengeId = it.id
                } else {
                    button.visibility = View.INVISIBLE
                    activeChallenge.text = getString(R.string.noactivechall)
                    viewModel.activeChallengeId = ""
                }
            }
        }

        if (user != null) {
            val name = user.displayName
            userName.text = name
            val userPic = user.photoUrl
            if (userPic != null)
                Glide.with(this).load(user.photoUrl.toString()).into(userImage)
            else
                userImage.setImageResource(R.drawable.androidlogo)
        }

        val dialogClickListener =
            DialogInterface.OnClickListener { _, which ->
                when (which) {
                    DialogInterface.BUTTON_POSITIVE -> {
                        viewModel.completeChallenge()
                        Toast.makeText(context, getString(R.string.yes), Toast.LENGTH_SHORT).show()
                    }
                    DialogInterface.BUTTON_NEGATIVE -> {
                        Toast.makeText(context, getString(R.string.no), Toast.LENGTH_SHORT).show()
                    }
                }
            }

        button.setOnClickListener {
            val builder: AlertDialog.Builder = AlertDialog.Builder(context)
            builder.setMessage("Did you complete the challenge?")
                .setPositiveButton("Yes, I promise", dialogClickListener)
                .setNegativeButton(R.string.no, dialogClickListener).show()
        }
        return view_
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.profile_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == R.id.settingsButton) {
            val action = ProfileFragmentDirections.actionProfileFragmentToSettingsFragment()
            Navigation.findNavController(view_).navigate(action)
        }
        return super.onOptionsItemSelected(item)
    }
}