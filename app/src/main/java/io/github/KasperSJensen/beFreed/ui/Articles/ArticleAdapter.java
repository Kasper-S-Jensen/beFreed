package io.github.KasperSJensen.beFreed.ui.Articles;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;


import java.util.List;

import io.github.KasperSJensen.beFreed.R;


public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {

    private List<Article> articles;
    private OnClickListener listener;


    public void setOnClickListener(OnClickListener listener) {
        this.listener = listener;
    }


    public void setArticles(List<Article> articles) {
        this.articles = articles;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.article, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Article currentArticle = articles.get(position);
        holder.title.setText(currentArticle.getTitle());
        if (currentArticle.getDate() != null) {
            holder.date.setText(currentArticle.getDate());
        }
        Glide.with(holder.picture.getContext()).load(currentArticle.getPicture())
                .into(holder.picture);
        //  holder.picture.setImageResource(currentArticle.getPicture());


    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView title;
        private final ImageView picture;
        private final TextView date;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.articleTitle);
            picture = itemView.findViewById(R.id.articleImage);
            date = itemView.findViewById(R.id.articleDate);


            itemView.setOnClickListener(v -> {
                listener.onClick(articles.get(getAdapterPosition()));
            });
        }
    }

    public interface OnClickListener {
        void onClick(Article article);
    }

}
