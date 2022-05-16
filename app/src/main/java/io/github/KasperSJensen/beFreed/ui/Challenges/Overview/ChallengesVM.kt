package io.github.KasperSJensen.beFreed.ui.Challenges.Overview

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import io.github.KasperSJensen.beFreed.Model.ChallengeRepository
import io.github.KasperSJensen.beFreed.ui.Challenges.Challenge

class ChallengesVM(application: Application) : AndroidViewModel(application) {
    private var repository: ChallengeRepository = ChallengeRepository.getInstance(application)


    fun getAllChallenges(): LiveData<List<Challenge>> {
        return repository.allChallenges
    }
}