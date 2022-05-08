package io.github.KasperSJensen.beFreed.ui.Challenges;

public class Challenge {

    private int id;
    private int recommendedLevel;
    private String title;
    private String description;
    private int experience;
    private int picture;

    public Challenge(int id, int recommendedLevel, String title, String description, int experience, int picture) {
        this.id = id;
        this.recommendedLevel = recommendedLevel;
        this.title = title;
        this.description = description;
        this.experience = experience;
        this.picture = picture;
    }

    public Challenge() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRecommendedLevel() {
        return recommendedLevel;
    }

    public void setRecommendedLevel(int recommendedLevel) {
        this.recommendedLevel = recommendedLevel;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getPicture() {
        return picture;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }
}
