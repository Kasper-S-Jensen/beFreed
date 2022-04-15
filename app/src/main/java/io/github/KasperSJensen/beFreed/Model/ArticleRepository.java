package io.github.KasperSJensen.beFreed.Model;

import android.app.Application;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import io.github.KasperSJensen.beFreed.R;
import io.github.KasperSJensen.beFreed.ui.Articles.Article;

public class ArticleRepository {
    private static ArticleRepository instance;
    private List<Article> allArticles= new ArrayList<>();
    private Calendar calendar;

    private ArticleRepository() {

        allArticles.add(new Article("A new article", R.drawable.obiwan,"http://www.facebook.com"));
        allArticles.add(new Article("A new article", R.drawable.obiwan,"http://www.youtube.com"));
        allArticles.add(new Article("A new article", R.drawable.obiwan,"http://www.reddit.com"));

    }


    public static synchronized ArticleRepository getInstance() {
        if (instance == null)
            instance = new ArticleRepository();

        return instance;
    }

    public List<Article> getAllArticles() {
        return allArticles;
    }

    public void setReadDate(Article article) {
        calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance().format(calendar.getTime());
        article.setDate(currentDate);

    }
}
