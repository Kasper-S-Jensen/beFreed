package io.github.KasperSJensen.beFreed.ui.Challenges;

public class Challenge {

    private String id;
    private int recommendedLevel;
    private String title;
    private String description;
    private Long experience;
    private String picture;

    public Challenge(int recommendedLevel, String title, String description, Long experience, String picture) {
        this.recommendedLevel = recommendedLevel;
        this.title = title;
        this.description = description;
        this.experience = experience;
        this.picture = picture;
    }

    public Challenge() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public Long getExperience() {
        return experience;
    }

    public void setExperience(Long experience) {
        this.experience = experience;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
