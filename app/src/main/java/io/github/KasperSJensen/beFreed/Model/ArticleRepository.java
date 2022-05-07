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
    private MutableLiveData<List<Article>> allArticles;
    private Calendar calendar;

    private ArticleRepository(Application application) {
        allArticles= new MutableLiveData<>();
        List<Article> newList = new ArrayList<>();
        newList.add(new Article("A new article", R.drawable.obiwan, "http://www.facebook.com"));
        newList.add(new Article("New article", R.drawable.obiwan, "http://www.youtube.com"));
        newList.add(new Article("Newer article", R.drawable.obiwan, "http://www.reddit.com"));

        allArticles.postValue(newList);
    }


    public static synchronized ArticleRepository getInstance(Application application) {
        if (instance == null)
            instance = new ArticleRepository(application);

        return instance;
    }

    public LiveData<List<Article>> getAllArticles() {
        return allArticles;
    }

    public Article setReadDate(Article article) {
        calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance().format(calendar.getTime());
        article.setDate("Date read: " + currentDate);
        return article;
    }
}
