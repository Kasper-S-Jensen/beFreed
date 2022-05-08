package io.github.KasperSJensen.beFreed.ui;

import io.github.KasperSJensen.beFreed.ui.Challenges.Challenge;

public class User {

    private int Experience;
    private int Level;
    private Challenge activeChallenge;

    public User() {
    }

    public int getExperience() {
        return Experience;
    }

    public void setExperience(int experience) {
        Experience = experience;
    }

    public int getLevel() {
        return Level;
    }

    public void setLevel(int Experience) {
        Level = Experience/100;
    }

    public Challenge getActiveChallenge() {
        return activeChallenge;
    }

    public void setActiveChallenge(Challenge activeChallenge) {
        this.activeChallenge = activeChallenge;
    }
}
