package io.github.KasperSJensen.beFreed.ui.Articles

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import io.github.KasperSJensen.beFreed.Model.ArticleRepository
import io.github.KasperSJensen.beFreed.R
import java.text.DateFormat
import java.util.*
import kotlin.collections.ArrayList

class ArticlesVM(application: Application) : AndroidViewModel(application) {

    private var articles: MutableList<Article> = ArrayList()

    private var repository: ArticleRepository = ArticleRepository.getInstance()

    fun getAllArticles(): List<Article> {

        articles = repository.allArticles
        return articles
    }

    fun setReadDate(article: Article) {
       repository.setReadDate(article)

    }

}