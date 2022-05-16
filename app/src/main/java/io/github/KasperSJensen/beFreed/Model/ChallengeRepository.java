package io.github.KasperSJensen.beFreed.Model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import io.github.KasperSJensen.beFreed.ui.Challenges.Challenge;

public class ChallengeRepository {

    private static ChallengeRepository instance;

    private final ChallengeDAO dao= new ChallengeDAO();

    private ChallengeRepository(Application application) {
    }

    public static synchronized ChallengeRepository getInstance(Application application) {
        if (instance == null)
            instance = new ChallengeRepository(application);
        return instance;
    }

    public void acceptChallenge(@NotNull Challenge challenge) {
      dao.acceptChallenge(challenge);
    }

    public LiveData<Long> getUserExperience() {
      return dao.getUserExperience();
    }

    public void completeChallenge(String id) {
       dao.completeChallenge(id);
    }

    public LiveData<Challenge> getActiveChallenge() {
      return dao.getActiveChallenge();
    }


    public LiveData<List<Challenge>> getAllChallenges() {
     return dao.getAllChallenges();
    }
}
