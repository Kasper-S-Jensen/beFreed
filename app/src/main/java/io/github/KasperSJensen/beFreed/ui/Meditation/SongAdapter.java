package io.github.KasperSJensen.beFreed.ui.Meditation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import io.github.KasperSJensen.beFreed.R;
import io.github.KasperSJensen.beFreed.ui.Journal.Note;


public class SongAdapter extends RecyclerView.Adapter<SongAdapter.ViewHolder> {

    private List<Song> songs;
    private OnClickListener listener;

    public void setOnClickListener(OnClickListener listener) {
        this.listener = listener;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.song, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Song currentSong = songs.get(position);
        holder.title.setText(currentSong.getTitle());
        holder.picture.setImageResource(currentSong.getPicture());

    }

    @Override
    public int getItemCount() {
        return songs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView title;
        private final ImageView picture;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.songTitle);
            picture = itemView.findViewById(R.id.songPicture);

            itemView.setOnClickListener(v -> {
                listener.onClick(songs.get(getAdapterPosition()));
            });

        }
    }


    public interface OnClickListener {
        void onClick(Song song);
    }
}
