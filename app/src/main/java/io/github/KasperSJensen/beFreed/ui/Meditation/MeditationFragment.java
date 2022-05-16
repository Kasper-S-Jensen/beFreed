package io.github.KasperSJensen.beFreed.ui.Meditation;

import android.annotation.SuppressLint;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.github.KasperSJensen.beFreed.R;


public class MeditationFragment extends Fragment {

    MediaPlayer player;
    Track defaultSong;
    Track currentSong = null;
    Button playButton;
    Button pauseButton;
    Button stopButton;
    SeekBar seekBar;
    Runnable runnable;
    Handler handler = new Handler();
    TextView currentPointOfSong;
    TextView songLength;


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
        List<Track> songList = viewModel.getAllSongs();
        songAdapter.setTracks(songList);
        recyclerView.setAdapter(songAdapter);

        songAdapter.setOnClickListener(song -> {
            currentSong = song;
            Toast.makeText(this.getContext(), song.getTitle(), Toast.LENGTH_SHORT).show();

        });

        playButton = view.findViewById(R.id.playButton);
        pauseButton = view.findViewById(R.id.pauseButton);
        stopButton = view.findViewById(R.id.stopButton);
        seekBar = view.findViewById(R.id.seekBar);
        currentPointOfSong = view.findViewById(R.id.currentPointOfSong);
        songLength = view.findViewById(R.id.songLength);

        runnable = new Runnable() {
            @Override
            public void run() {
                seekBar.setProgress(player.getCurrentPosition());
                handler.postDelayed(this, 500);
            }
        };

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

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser && player != null) {
                    player.seekTo(progress);
                }
                if (player != null)
                    currentPointOfSong.setText(convertFormat(player.getCurrentPosition()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        return view;
    }


    @SuppressLint("DefaultLocale")
    private String convertFormat(int duration) {
        return String.format("%02d:%02d"
                , TimeUnit.MILLISECONDS.toMinutes(duration),
                TimeUnit.MILLISECONDS.toSeconds(duration) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(duration)));
    }


    private void play() {
        if (currentSong != null) {
            pauseButton.setVisibility(View.VISIBLE);
            playButton.setVisibility(View.GONE);
            if (player == null) {
                player = MediaPlayer.create(this.getContext(), currentSong.getFile());
                player.setOnCompletionListener(mediaPlayer ->
                        stop());
            }
            player.start();

            //set song duration
            int duration = player.getDuration();
            String songDuration = convertFormat(duration);
            songLength.setText(songDuration);
            seekBar.setMax(player.getDuration());
            handler.postDelayed(runnable, 0);
        }
        if (currentSong == null)
            Toast.makeText(this.getContext(), "Please select a song", Toast.LENGTH_SHORT).show();
    }

    private void pause() {
        if (player != null) {
            pauseButton.setVisibility(View.GONE);
            playButton.setVisibility(View.VISIBLE);
            player.pause();
            handler.removeCallbacks(runnable);
        }
    }

    private void stop() {
        stopPlayer();
        currentSong = null;
        pauseButton.setVisibility(View.GONE);
        playButton.setVisibility(View.VISIBLE);
        currentPointOfSong.setText("00:00");
        songLength.setText("00:00");
        seekBar.setProgress(1);
    }


    private void stopPlayer() {
        if (player != null) {
            handler.removeCallbacks(runnable);
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

