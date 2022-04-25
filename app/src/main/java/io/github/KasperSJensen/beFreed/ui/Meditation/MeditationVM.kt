package io.github.KasperSJensen.beFreed.ui.Meditation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import io.github.KasperSJensen.beFreed.R


class MeditationVM(application: Application) : AndroidViewModel(application) {

    private var songs: MutableList<Song> = ArrayList()

    fun getAllSongs(): MutableList<Song> {

        if (songs.size==0) {
            songs.add(Song("Latin music", R.raw.latingroove))

            songs.add(Song("Best song", R.raw.nevergonna))
        }


        return songs
    }



}