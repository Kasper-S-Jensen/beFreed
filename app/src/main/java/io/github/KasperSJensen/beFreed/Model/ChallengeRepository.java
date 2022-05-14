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

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import io.github.KasperSJensen.beFreed.ui.Challenges.Challenge;
import io.github.KasperSJensen.beFreed.ui.Journal.Note;

public class ChallengeRepository {

    private static ChallengeRepository instance;
    private MutableLiveData<List<Challenge>> allChallenges;
    Challenge challenge;

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

    public void acceptChallenge(@NotNull Challenge challenge) {
        String uId;

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() != null) {
            uId = mAuth.getCurrentUser().getUid();


            FirebaseDatabase database = FirebaseDatabase.getInstance("https://befreed-default-rtdb.europe-west1.firebasedatabase.app");
            DatabaseReference myRef = database.getReference();

            //Challenge checkActive = getActiveChallenge();
           // if (checkActive == null)
                myRef.child("Users").child(uId).child("ActiveChallenge").setValue(challenge);


        }
    }

    public Challenge getActiveChallenge() {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        String uId = "";
        if (mAuth.getCurrentUser() != null) {
            uId = mAuth.getCurrentUser().getUid();
        }
        final Challenge[] firebaseChallenge = {new Challenge()};
        MutableLiveData<Challenge> firebaseMutChallenge = new MutableLiveData<>();
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://befreed-default-rtdb.europe-west1.firebasedatabase.app");
        DatabaseReference myRef = database.getReference("Users").child(uId).child("ActiveChallenge");

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                     challenge = dataSnapshot.getValue(Challenge.class);
                    firebaseChallenge[0] = challenge;




              //  firebaseMutChallenge.postValue(firebaseChallenge[0]);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        return firebaseChallenge[0];
    }


    public LiveData<List<Challenge>> getAllChallenges() {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        String uId = "";
        if (mAuth.getCurrentUser() != null) {
            uId = mAuth.getCurrentUser().getUid();
        }


        List<Challenge> firebaseChallenges = new ArrayList<>();
        MutableLiveData<List<Challenge>> firebaseMutChallenges = new MutableLiveData<>();

        //  return allNotes;

        FirebaseDatabase database = FirebaseDatabase.getInstance("https://befreed-default-rtdb.europe-west1.firebasedatabase.app");

        DatabaseReference myRef = database.getReference("Challenges");

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Challenge challenge = postSnapshot.getValue(Challenge.class);
                    firebaseChallenges.add(challenge);
                }
                firebaseMutChallenges.postValue(firebaseChallenges);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }


        });


        return firebaseMutChallenges;
    }


}
