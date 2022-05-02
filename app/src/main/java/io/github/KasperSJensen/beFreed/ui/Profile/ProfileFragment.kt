package io.github.KasperSJensen.beFreed.ui.Profile

import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubePlayerFragment
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

        val userName: TextView = view_.findViewById(R.id.username)
        val userImage: ImageView = view_.findViewById(R.id.profilePic)
        if (user != null) {
            val name = user.displayName
            userName.text = name

            val userPic = user.photoUrl
            if (userPic != null)
                Glide.with(this).load(user.photoUrl.toString()).into(userImage);
            else
                userImage.setImageResource(R.drawable.androidlogo);
        }
        val progressBar: ProgressBar = view_.findViewById(R.id.progressBar)
        val eXPProgress: TextView = view_.findViewById(R.id.totalEXP)

        val setExPProgressText = progressBar.progress.toString() + "/" + progressBar.max

        eXPProgress.text = setExPProgressText






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