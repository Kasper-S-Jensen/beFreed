package io.github.KasperSJensen.beFreed.ui.Meditation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import io.github.KasperSJensen.beFreed.R


class MeditationVM(application: Application) : AndroidViewModel(application) {

    private var songs: MutableList<Song> = ArrayList()

    fun getAllSongs(): MutableList<Song> {

        songs.add(Song("Latiin music", R.raw.latingroove))
        songs.add(Song("Latiiin music", R.raw.latingroove))
        songs.add(Song("Latiiiin music", R.raw.latingroove))
        songs.add(Song("Latiiin music", R.raw.latingroove))
        songs.add(Song("Latiin music", R.raw.latingroove))
        songs.add(Song("Latiiin music", R.raw.latingroove))
        songs.add(Song("Latiuin music", R.raw.latingroove))
        songs.add(Song("Best song", R.raw.nevergonna))


        return songs
    }



}