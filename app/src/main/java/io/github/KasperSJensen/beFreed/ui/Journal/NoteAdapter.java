package io.github.KasperSJensen.beFreed.ui.Journal;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import io.github.KasperSJensen.beFreed.R;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {

    private List<Note> notes;
    private OnClickListener listener;

    public void setOnClickListener(OnClickListener listener) {
        this.listener = listener;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.note, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Note currentNote = notes.get(position);
        holder.title.setText(currentNote.getTitle());
        holder.noteText.setText(currentNote.getNoteText());
        holder.noteDate.setText(currentNote.getDate());
        holder.moodRating.setText(String.valueOf(currentNote.getMoodRating()));

        if (currentNote.getMoodRating() <= 1) {
            holder.moodRating.setTextColor(Color.parseColor("#F44336"));
        } else if (currentNote.getMoodRating() < 4 && currentNote.getMoodRating() > 1) {
            holder.moodRating.setTextColor(Color.parseColor("#FFEB3B"));
        } else if (currentNote.getMoodRating() <= 5 && currentNote.getMoodRating() >= 4) {
            holder.moodRating.setTextColor(Color.parseColor("#8BC34A"));
        }
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView title;
        private final TextView noteText;
        private final TextView moodRating;
        private final TextView noteDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.noteTitle);
            noteText = itemView.findViewById(R.id.noteText);
            moodRating = itemView.findViewById(R.id.moodRating);
            noteDate = itemView.findViewById(R.id.noteDate);
            itemView.setOnClickListener(v -> listener.onClick(notes.get(getAdapterPosition())));
        }
    }

    public interface OnClickListener {
        void onClick(Note note);
    }
}
