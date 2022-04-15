package io.github.KasperSJensen.beFreed.ui.Articles

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.github.KasperSJensen.beFreed.R
import java.text.DateFormat
import java.util.*


class ArticlesFragment : Fragment() {

    lateinit var calendar: Calendar
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_articles, container, false)


        //setup viewModel
        val viewModel: ArticlesVM =
            ViewModelProvider(requireActivity())[ArticlesVM::class.java]


        //setup recyclerview
        val recyclerView: RecyclerView = view.findViewById(R.id.articlesRecyclerview)
        recyclerView.hasFixedSize()
        recyclerView.layoutManager = LinearLayoutManager(view.context)


        //setup Adapter
        val articlesAdapter = ArticleAdapter()
        val articleList: List<Article> = viewModel.getAllArticles()
        articlesAdapter.setArticles(articleList)
        recyclerView.adapter = articlesAdapter


        articlesAdapter.setOnClickListener { article: Article ->
            viewModel.setReadDate(article)

            val action = Intent.ACTION_VIEW
            val uri: Uri = Uri.parse(article.url)

            val intent = Intent(action, uri)
            startActivity(intent)

        }

        return view
    }

}