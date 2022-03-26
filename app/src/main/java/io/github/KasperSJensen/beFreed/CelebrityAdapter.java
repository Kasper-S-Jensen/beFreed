package io.github.KasperSJensen.beFreed;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CelebrityAdapter extends RecyclerView.Adapter<CelebrityAdapter.ViewHolder> {

    private ArrayList<Celebrity> celebrities;
    private OnClickListener listener;

    public void setOnClickListener(OnClickListener listener) {
        this.listener = listener;
    }


    public CelebrityAdapter(ArrayList<Celebrity> celebrities) {
        this.celebrities = celebrities;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.celebrity_list_item, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(celebrities.get(position).getName());
        holder.picture.setImageResource(celebrities.get(position).getPicture());

    }

    @Override
    public int getItemCount() {
        return celebrities.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView name;
        private final ImageView picture;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.celebrityname);
            picture = itemView.findViewById(R.id.celebritypic);
            itemView.setOnClickListener(v -> {
                listener.onClick(celebrities.get(getAdapterPosition()));
            });

        }
    }

    public interface OnClickListener {
        void onClick(Celebrity celebrity);
    }


}
