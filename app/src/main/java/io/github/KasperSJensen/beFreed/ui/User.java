package io.github.KasperSJensen.beFreed.ui;

import io.github.KasperSJensen.beFreed.ui.Challenges.Challenge;

public class User {

    private Long TotalExperience;
    private int Level;
    private Challenge activeChallenge;

    public User() {
    }

    public Long getTotalExperience() {
        return TotalExperience;
    }

    public void setTotalExperience(Long totalExperience) {
        TotalExperience = totalExperience;
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
