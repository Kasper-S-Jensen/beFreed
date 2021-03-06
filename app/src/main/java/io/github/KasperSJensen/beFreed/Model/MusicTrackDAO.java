package io.github.KasperSJensen.beFreed.Model;

import java.util.ArrayList;

import io.github.KasperSJensen.beFreed.R;
import io.github.KasperSJensen.beFreed.ui.Meditation.Track;

public class MusicTrackDAO {
    private final ArrayList<Track> tracks;

    public MusicTrackDAO() {
        tracks= new ArrayList<>();
        tracks.add(new Track("Latin Music", R.raw.latingroove));
        tracks.add(new Track("Track#2", R.raw.latingroove));
        tracks.add(new Track("Not a Rick Roll", R.raw.nevergonna));
        tracks.add(new Track("Track#4", R.raw.latingroove));
        tracks.add(new Track("Track#5", R.raw.latingroove));
    }

    public ArrayList<Track> getAllTracks() {
        return tracks;
    }
}
