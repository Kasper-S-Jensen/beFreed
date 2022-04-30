package io.github.KasperSJensen.beFreed.Model;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import io.github.KasperSJensen.beFreed.R;
import io.github.KasperSJensen.beFreed.ui.Articles.Article;

public class ArticleRepository {
    private static ArticleRepository instance;
    private MutableLiveData<List<Article>> allArticles = new MutableLiveData<>();
    private Calendar calendar;

    private ArticleRepository(Application application) {

        allArticles.getValue().add(new Article("A new article", R.drawable.obiwan, "http://www.facebook.com"));
        allArticles.getValue().add(new Article("A new article", R.drawable.obiwan, "http://www.youtube.com"));
        allArticles.getValue().add(new Article("A new article", R.drawable.obiwan, "http://www.reddit.com"));

    }


    public static synchronized ArticleRepository getInstance(Application application) {
        if (instance == null)
            instance = new ArticleRepository(application);

        return instance;
    }

    public LiveData<List<Article>> getAllArticles() {
        return allArticles;
    }

    public void setReadDate(Article article) {
        calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance().format(calendar.getTime());
        article.setDate("Date read: " + currentDate);

    }
}
