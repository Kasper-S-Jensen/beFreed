package io.github.KasperSJensen.beFreed.ui.Articles

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.github.KasperSJensen.beFreed.R


class ArticlesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_articles, container, false)


        //setup recyclerview
        val recyclerView: RecyclerView = view.findViewById(R.id.articlesRecyclerview)
        recyclerView.hasFixedSize()
        recyclerView.layoutManager = LinearLayoutManager(view.context)


        //setup Adapter
        val articlesAdapter = ArticleAdapter()
        articlesAdapter.setArticles(mutableListOf())
        recyclerView.adapter = articlesAdapter

        //setup viewModel
        val viewModel: ArticlesVM =
            ViewModelProvider(requireActivity())[ArticlesVM::class.java]


        val articlesObserver = Observer<List<Article>> { newArticles ->
            articlesAdapter.setArticles(newArticles)
        }
        viewModel.getAllArticles().observe(this.viewLifecycleOwner, articlesObserver)


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