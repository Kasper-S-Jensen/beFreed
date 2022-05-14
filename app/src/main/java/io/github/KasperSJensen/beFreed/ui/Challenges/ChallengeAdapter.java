package io.github.KasperSJensen.beFreed.ui.Challenges;

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


public class ChallengeAdapter extends RecyclerView.Adapter<ChallengeAdapter.ViewHolder> {

    private List<Challenge> challenges;
    private OnClickListener listener;


    public void setOnClickListener(ChallengeAdapter.OnClickListener listener) {
        this.listener = listener;
    }

    public void setChallenges(List<Challenge> challenges) {
        this.challenges = challenges;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.challenge, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Challenge currentChallenge = challenges.get(position);
        holder.exp.setText(String.valueOf(currentChallenge.getExperience()));
      //  Glide.with(holder.picture.getContext()).load(currentChallenge.getPicture())
       //         .into(holder.picture);
          holder.picture.setImageResource(R.drawable.challengespic);

    }

    @Override
    public int getItemCount() {
        return challenges.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView exp;
        private final ImageView picture;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            exp = itemView.findViewById(R.id.challengeExperience);
            picture = itemView.findViewById(R.id.challenePic);

            itemView.setOnClickListener(v -> {
                listener.onClick(challenges.get(getAdapterPosition()));
            });

        }
    }

    public interface OnClickListener {
        void onClick(Challenge challenge);
    }


}
