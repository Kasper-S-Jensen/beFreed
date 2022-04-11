package io.github.KasperSJensen.beFreed;

import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class MeditationFragment extends Fragment {


    MediaPlayer player;

    public MeditationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_meditation, container, false);

        Button playButton = view.findViewById(R.id.playButton);
        Button pauseButton = view.findViewById(R.id.pauseButton);
        Button stopButton = view.findViewById(R.id.stopButton);


        playButton.setOnClickListener(view1 ->
                play()
                );
        pauseButton.setOnClickListener(view1 ->
                pause()
        );
        stopButton.setOnClickListener(view1 ->
                stop()
        );

        return view;

    }




        private void play() {
            if (player == null) {
                player = MediaPlayer.create(this.getContext(), R.raw.latingroove);
                player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        stopPlayer();
                    }
                });
            }
            player.start();

        }

        private void pause() {
            if (player != null)
                player.pause();

        }

        private void stop() {
            stopPlayer();
        }


        private void stopPlayer() {
            if (player != null) {
                player.release();
                player = null;
            }

        }

    @Override
    public void onStop() {
        super.onStop();
        stopPlayer();
    }
}

