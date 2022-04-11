package io.github.KasperSJensen.beFreed.ui.Meditation;

import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.github.KasperSJensen.beFreed.R;


public class MeditationFragment extends Fragment {


    MediaPlayer player;
    Song defaultSong;
    Song currentSong = null;
    Button playButton;
    Button pauseButton;
    Button stopButton;


    public MeditationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_meditation, container, false);

        //setup viewmodel
        MeditationVM viewModel = new ViewModelProvider(this).get(MeditationVM.class);

        //setup recyclerview
        RecyclerView recyclerView = view.findViewById(R.id.songRecycler);
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        //setup Adapter
        SongAdapter songAdapter = new SongAdapter();
        List<Song> songList = viewModel.getAllSongs();
        songAdapter.setSongs(songList);
        recyclerView.setAdapter(songAdapter);

        songAdapter.setOnClickListener(song -> {
            currentSong = song;

            Toast.makeText(this.getContext(), song.getTitle(), Toast.LENGTH_SHORT).show();

        });


        playButton = view.findViewById(R.id.playButton);
        pauseButton = view.findViewById(R.id.pauseButton);
        stopButton = view.findViewById(R.id.stopButton);


        defaultSong = songList.get(0);
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
        if (currentSong != null) {
            pauseButton.setVisibility(View.VISIBLE);
            playButton.setVisibility(View.GONE);
            if (player == null) {
                player = MediaPlayer.create(this.getContext(), currentSong.getFile());
                player.setOnCompletionListener(mediaPlayer -> stopPlayer());

            }
            player.start();
        }
        if (currentSong == null)
            Toast.makeText(this.getContext(), "Please select a song", Toast.LENGTH_SHORT).show();
    }

    private void pause() {
        if (player != null) {
            pauseButton.setVisibility(View.GONE);
            playButton.setVisibility(View.VISIBLE);
            player.pause();
        }

    }

    private void stop() {
        stopPlayer();
        currentSong = null;
        pauseButton.setVisibility(View.GONE);
        playButton.setVisibility(View.VISIBLE);
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

