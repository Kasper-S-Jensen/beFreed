package io.github.KasperSJensen.beFreed.Model;

import android.app.Application;

import java.util.ArrayList;

import io.github.KasperSJensen.beFreed.ui.Meditation.Track;

public class MusicTrackRepository {
    private static MusicTrackRepository instance;
    private final MusicTrackDAO musicTrackDAO;

    private MusicTrackRepository(Application application) {
        musicTrackDAO = new MusicTrackDAO();
    }

    public static synchronized MusicTrackRepository getInstance(Application application) {
        if (instance == null)
            instance = new MusicTrackRepository(application);

        return instance;
    }

    public ArrayList<Track> getAllTracks() {
        return musicTrackDAO.getAllTracks();
    }
}
