package io.github.KasperSJensen.beFreed.ui.Profile

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import io.github.KasperSJensen.beFreed.Model.ChallengeRepository
import io.github.KasperSJensen.beFreed.Model.NoteRepositoryFirebase
import io.github.KasperSJensen.beFreed.ui.Challenges.Challenge

class ProfileVM(application: Application) : AndroidViewModel(application) {

    private var repository: ChallengeRepository = ChallengeRepository.getInstance(application)

    fun  getActiveChallenge(): Challenge? {
        return repository.activeChallenge
    }
}