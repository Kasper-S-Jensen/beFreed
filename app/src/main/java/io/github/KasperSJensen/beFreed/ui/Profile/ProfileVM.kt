package io.github.KasperSJensen.beFreed.ui.Profile

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.github.KasperSJensen.beFreed.Model.ChallengeRepository
import io.github.KasperSJensen.beFreed.Model.NoteRepositoryFirebase
import io.github.KasperSJensen.beFreed.ui.Challenges.Challenge

class ProfileVM(application: Application) : AndroidViewModel(application) {

    private var repository: ChallengeRepository = ChallengeRepository.getInstance(application)

    var activeChallengeId: String = ""


    fun getActiveChallenge(): LiveData<Challenge> {
        println(repository.activeChallenge.value.toString() + " inside VM")
        return repository.activeChallenge
    }

    fun completeChallenge() {
        if (activeChallengeId!="") {
            repository.completeChallenge(activeChallengeId)
        }
    }

    fun getUserExperience(): LiveData<Long>{
       return repository.userExperience

    }



}