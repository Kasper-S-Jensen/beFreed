package io.github.KasperSJensen.beFreed.ui.Challenges.ViewChallenge

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import io.github.KasperSJensen.beFreed.Model.ChallengeRepository
import io.github.KasperSJensen.beFreed.ui.Challenges.Challenge

class ChallengeViewVM(application: Application) : AndroidViewModel(application) {

    private var repository: ChallengeRepository = ChallengeRepository.getInstance(application)

    fun acceptChallenge(challenge: Challenge) {
        repository.acceptChallenge(challenge)
    }
}