package io.github.KasperSJensen.beFreed.ui.Profile

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubePlayerFragment
import io.github.KasperSJensen.beFreed.R


class ProfileFragment : Fragment() {


    lateinit var view_: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true)
        view_ = inflater.inflate(R.layout.fragment_profile, container, false)



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