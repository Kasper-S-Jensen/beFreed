package io.github.KasperSJensen.beFreed.ui.Profile

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import io.github.KasperSJensen.beFreed.R
import io.github.KasperSJensen.beFreed.ui.Journal.JournalOverviewFragmentDirections


class ProfileFragment : Fragment() {

    private lateinit var vieww: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true)
        vieww = inflater.inflate(R.layout.fragment_profile, container, false)



        return vieww

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        menu.findItem(R.id.save_note).isVisible = false
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.toggleDarkMode) {
            if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            else
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }

        return super.onOptionsItemSelected(item)
    }


}