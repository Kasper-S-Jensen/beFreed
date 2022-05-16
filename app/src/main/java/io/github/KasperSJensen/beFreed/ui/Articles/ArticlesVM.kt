package io.github.KasperSJensen.beFreed.ui.Articles

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import io.github.KasperSJensen.beFreed.Model.ArticleRepository


class ArticlesVM(application: Application) : AndroidViewModel(application) {
    private var repository: ArticleRepository = ArticleRepository.getInstance(application)

    fun getAllArticles(): LiveData<List<Article>> {
        return repository.allArticles
    }

    fun setReadDate(article: Article) {
        repository.setReadDate(article)
    }
}