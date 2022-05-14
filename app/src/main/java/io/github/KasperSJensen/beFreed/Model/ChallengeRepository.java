package io.github.KasperSJensen.beFreed.Model;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import io.github.KasperSJensen.beFreed.R;
import io.github.KasperSJensen.beFreed.ui.Articles.Article;
import io.github.KasperSJensen.beFreed.ui.Challenges.Challenge;

public class ChallengeRepository {

    private static ChallengeRepository instance;
    private MutableLiveData<List<Challenge>> allChallenges;

    private ChallengeRepository(Application application) {


        allChallenges = new MutableLiveData<>();
        List<Challenge> newList = new ArrayList<>();
        // newList.add(new Article("A new article", R.drawable.obiwan, "http://www.facebook.com"));
        // newList.add(new Article("New article", R.drawable.obiwan, "http://www.youtube.com"));
        // newList.add(new Article("Newer article", R.drawable.obiwan, "http://www.reddit.com"));

        allChallenges.postValue(newList);
    }

    public static synchronized ChallengeRepository getInstance(Application application) {
        if (instance == null)
            instance = new ChallengeRepository(application);

        return instance;
    }


    public LiveData<List<Challenge>> getAllChallenges() {
        allChallenges = new MutableLiveData<>();
        List<Challenge> newList = new ArrayList<>();
         newList.add(new Challenge(2,"ChallengeTitel1","Veryhard",20,"blalba"));
         newList.add(new Challenge(2,"ChallengeTitel2","Veryhard",20,"blalba"));
         newList.add(new Challenge(2,"ChallengeTitel3","Veryhard",20,"blalba"));
         newList.add(new Challenge(2,"ChallengeTitel4","Veryhard",20,"blalba"));

        allChallenges.postValue(newList);
        return  allChallenges;
    }


}
