package io.github.KasperSJensen.beFreed.Model;

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

public class ChallengeDAO {

    MutableLiveData<Long> experienceMutable = new MutableLiveData<>(-1L);

    public void acceptChallenge(@NotNull Challenge challenge) {
        String uId;
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() != null) {
            uId = mAuth.getCurrentUser().getUid();
            FirebaseDatabase database = FirebaseDatabase.getInstance("https://befreed-default-rtdb.europe-west1.firebasedatabase.app");
            DatabaseReference myRef = database.getReference();

            myRef.child("Users").child(uId).child("ActiveChallenge").push().setValue(challenge);
        }
    }

    public LiveData<Long> getUserExperience() {
        FirebaseAuth mAuth;
        mAuth = FirebaseAuth.getInstance();
        String uid = "";
        if (mAuth.getCurrentUser() != null) {
            uid = mAuth.getCurrentUser().getUid();
        }

        FirebaseDatabase database = FirebaseDatabase.getInstance("https://befreed-default-rtdb.europe-west1.firebasedatabase.app");
        DatabaseReference myRefTotalExperience = database.getReference("Users").child(uid).child("TotalExperience");

        myRefTotalExperience.get().addOnCompleteListener(task -> {
                    Long currentTotalExp = (Long) task.getResult().getValue();

                    experienceMutable.setValue(currentTotalExp);
                }
        );
        return experienceMutable;
    }

    public void completeChallenge(String id) {
        FirebaseAuth mAuth;
        mAuth = FirebaseAuth.getInstance();
        String uid = "";
        if (mAuth.getCurrentUser() != null) {
            uid = mAuth.getCurrentUser().getUid();
        }

        FirebaseDatabase database = FirebaseDatabase.getInstance("https://befreed-default-rtdb.europe-west1.firebasedatabase.app");
        DatabaseReference myRefActiveChallenge = database.getReference("Users").child(uid).child("ActiveChallenge");
        DatabaseReference myRefTotalExperience = database.getReference("Users").child(uid).child("TotalExperience");
        DatabaseReference myRefLevel = database.getReference("Users").child(uid).child("Level");

        myRefActiveChallenge.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Long experience;
                    Challenge challenge = postSnapshot.getValue(Challenge.class);
                    assert challenge != null;
                    experience = challenge.getExperience();
                    Long finalExperience = experience;
                    myRefTotalExperience.get().addOnCompleteListener(task -> {

                                Long currentTotalExp = (Long) task.getResult().getValue();
                                if (currentTotalExp == null) {
                                    currentTotalExp = 0L;
                                }
                                myRefTotalExperience.setValue(currentTotalExp + finalExperience);

                                myRefLevel.setValue((currentTotalExp + finalExperience) / 100);
                                getUserExperience();
                            }
                    );
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        myRefActiveChallenge.child(id).removeValue();
    }

    public LiveData<Challenge> getActiveChallenge() {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        String uId = "";
        if (mAuth.getCurrentUser() != null) {
            uId = mAuth.getCurrentUser().getUid();
        }

        FirebaseDatabase database = FirebaseDatabase.getInstance("https://befreed-default-rtdb.europe-west1.firebasedatabase.app");
        DatabaseReference myRef = database.getReference("Users").child(uId).child("ActiveChallenge");
        MutableLiveData<Challenge> firebaseMutChallenge = new MutableLiveData<>();

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Challenge challenge = postSnapshot.getValue(Challenge.class);
                    if (challenge != null) {
                        challenge.setId(postSnapshot.getKey());
                    }
                    assert challenge != null;
                    firebaseMutChallenge.setValue(challenge);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        return firebaseMutChallenge;
    }


    public LiveData<List<Challenge>> getAllChallenges() {
        List<Challenge> firebaseChallenges = new ArrayList<>();
        MutableLiveData<List<Challenge>> firebaseMutChallenges = new MutableLiveData<>();
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://befreed-default-rtdb.europe-west1.firebasedatabase.app");
        DatabaseReference myRef = database.getReference("Challenges");

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
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
