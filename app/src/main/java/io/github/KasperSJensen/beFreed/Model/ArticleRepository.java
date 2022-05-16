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
    private final ArticleDAO dao= new ArticleDAO();

    private ArticleRepository(Application application) {
    }


    public static synchronized ArticleRepository getInstance(Application application) {
        if (instance == null)
            instance = new ArticleRepository(application);

        return instance;
    }

    public LiveData<List<Article>> getAllArticles() {
       return dao.getAllArticles();
    }

    public Article setReadDate(Article article) {
     return dao.setReadDate(article);
    }
}
