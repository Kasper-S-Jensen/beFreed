package io.github.KasperSJensen.beFreed.Model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
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
    MutableLiveData<Long> experienceMutable= new MutableLiveData<>(-1L);



    private ChallengeRepository(Application application) {
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

            myRef.child("Users").child(uId).child("ActiveChallenge").push().setValue(challenge);

        }
    }

    public LiveData<Long> getUserExperience() {
        System.out.println("inside you know experience");
        FirebaseAuth mAuth;
        mAuth = FirebaseAuth.getInstance();
        String uid = "";
        if (mAuth.getCurrentUser() != null) {
            uid = mAuth.getCurrentUser().getUid();
        }

        FirebaseDatabase database = FirebaseDatabase.getInstance("https://befreed-default-rtdb.europe-west1.firebasedatabase.app");


        DatabaseReference myRefTotalExperience = database.getReference("Users").child(uid).child("TotalExperience");

      /*  myRefTotalExperience.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Long totalExperience =  postSnapshot.getValue(Long.class);
                    experienceMutable.postValue(totalExperience);
                   // experienceMutable.setValue(totalExperience);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                throw error.toException();
            }
        });*/

        myRefTotalExperience.get().addOnCompleteListener(task -> {
                    Long currentTotalExp = (Long) task.getResult().getValue();

                    experienceMutable.setValue(currentTotalExp);
                }
        );

        return experienceMutable;

    }

    public void completeChallenge(String id) {
        System.out.println("inside you know");
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
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Long experience = null;
                    Challenge challenge = postSnapshot.getValue(Challenge.class);
                    experience = challenge.getExperience();
                    System.out.println(experience + "    hey experince");
                    Long finalExperience = experience;
                    myRefTotalExperience.get().addOnCompleteListener(task -> {

                                Long currentTotalExp = (Long) task.getResult().getValue();
                                if (currentTotalExp==null)
                                {
                                    currentTotalExp=0L;
                                }
                                myRefTotalExperience.setValue(currentTotalExp + finalExperience);

                                myRefLevel.setValue((currentTotalExp + finalExperience)/100);
                                getUserExperience();
                            }
                    );


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            //    throw error.toException();
            }
        });


        myRefActiveChallenge.child(id).removeValue();
        // executorService.execute(() -> noteDao.deleteById(id));
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
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Challenge challenge = postSnapshot.getValue(Challenge.class);
                    if (challenge != null) {
                        challenge.setId(postSnapshot.getKey());
                    }
                    assert challenge != null;
                    System.out.println(challenge.getTitle() + "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW");

                    firebaseMutChallenge.setValue(challenge);

                    System.out.println(firebaseMutChallenge.getValue().getTitle() + "MMMMMMMMMMMMMMMMMMMMMM");

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
             //   throw error.toException();
            }
        });
        // System.out.println(firebaseMutChallenge.getValue().getTitle() + "MMMQQQMMMMM");
        return firebaseMutChallenge;
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
