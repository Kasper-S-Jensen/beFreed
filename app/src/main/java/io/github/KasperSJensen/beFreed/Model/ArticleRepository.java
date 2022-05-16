package io.github.KasperSJensen.beFreed.Model;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import io.github.KasperSJensen.beFreed.ui.Articles.Article;

public class ArticleRepository {
    private static ArticleRepository instance;

    private ArticleRepository(Application application) {
    }


    public static synchronized ArticleRepository getInstance(Application application) {
        if (instance == null)
            instance = new ArticleRepository(application);

        return instance;
    }

    public LiveData<List<Article>> getAllArticles() {
        List<Article> firebaseArticles = new ArrayList<>();
        MutableLiveData<List<Article>> firebaseMutArticles = new MutableLiveData<>();

        FirebaseDatabase database = FirebaseDatabase.getInstance("https://befreed-default-rtdb.europe-west1.firebasedatabase.app");
        DatabaseReference myRef = database.getReference("Articles");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
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
        return firebaseMutArticles;
    }

    public Article setReadDate(Article article) {
        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance().format(calendar.getTime());
        article.setDate("Date read: " + currentDate);
        return article;
    }
}
