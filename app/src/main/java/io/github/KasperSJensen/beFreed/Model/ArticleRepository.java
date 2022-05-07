package io.github.KasperSJensen.beFreed.Model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import io.github.KasperSJensen.beFreed.R;
import io.github.KasperSJensen.beFreed.ui.Articles.Article;
import io.github.KasperSJensen.beFreed.ui.Journal.Note;

public class ArticleRepository {
    private static ArticleRepository instance;
    private MutableLiveData<List<Article>> allArticles;
    private Calendar calendar;

    private ArticleRepository(Application application) {


        allArticles = new MutableLiveData<>();
        List<Article> newList = new ArrayList<>();
       // newList.add(new Article("A new article", R.drawable.obiwan, "http://www.facebook.com"));
       // newList.add(new Article("New article", R.drawable.obiwan, "http://www.youtube.com"));
       // newList.add(new Article("Newer article", R.drawable.obiwan, "http://www.reddit.com"));

        allArticles.postValue(newList);
    }


    public static synchronized ArticleRepository getInstance(Application application) {
        if (instance == null)
            instance = new ArticleRepository(application);

        return instance;
    }

    public LiveData<List<Article>> getAllArticles() {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        List<Article> firebaseArticles = new ArrayList<>();
        MutableLiveData<List<Article>> firebaseMutArticles = new MutableLiveData<>();

        FirebaseDatabase database = FirebaseDatabase
                .getInstance("https://befreed-default-rtdb.europe-west1.firebasedatabase.app");
        DatabaseReference myRef = database.getReference("Articles");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Article article = postSnapshot.getValue(Article.class);
                    firebaseArticles.add(article);
                }
                firebaseMutArticles.postValue(firebaseArticles);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }


        });


      //  return allArticles;
        return firebaseMutArticles;
    }

    public Article setReadDate(Article article) {
        calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance().format(calendar.getTime());
        article.setDate("Date read: " + currentDate);
        return article;
    }
}
