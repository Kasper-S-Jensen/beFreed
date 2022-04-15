package io.github.KasperSJensen.beFreed.ui.Articles

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import io.github.KasperSJensen.beFreed.R
import java.text.DateFormat
import java.util.*
import kotlin.collections.ArrayList

class ArticlesVM(application: Application) : AndroidViewModel(application) {

    private var articles: MutableList<Article> = ArrayList()
    lateinit var calendar: Calendar

    fun getAllArticles(): List<Article> {


        articles.add(Article("A new article", R.drawable.obiwan,"http://www.facebook.com"))
        articles.add(Article("A new article", R.drawable.obiwan,"http://www.hey.com"))
        articles.add(Article("A new article", R.drawable.obiwan,"http://www.hey.com"))


        return articles
    }

    fun setReadDate(article: Article) {
        calendar = Calendar.getInstance()
        val currentDate: String = DateFormat.getDateInstance().format(calendar.time)
        article.date=currentDate

    }

}