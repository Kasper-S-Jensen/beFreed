package io.github.KasperSJensen.beFreed.ui.Meditation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import io.github.KasperSJensen.beFreed.Model.MusicTrackRepository
import io.github.KasperSJensen.beFreed.R


class MeditationVM(application: Application) : AndroidViewModel(application) {
    private val repository: MusicTrackRepository = MusicTrackRepository.getInstance(application)

    fun getAllSongs(): ArrayList<Track> {
        return repository.allTracks
    }
}