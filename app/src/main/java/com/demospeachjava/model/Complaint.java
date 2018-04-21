package com.demospeachjava.model;

public class Complaint {
    private long cts_id;
    private long user_id;
    private long register_id;
    private String coverage_health;
    private String link_user;
    private String story;

    public Complaint() {
    }

    public Complaint(String coverage_health, String link_user, String story) {
        this.coverage_health = coverage_health;
        this.link_user = link_user;
        this.story = story;
    }

    public long getCts_id() {
        return cts_id;
    }

    public void setCts_id(long cts_id) {
        this.cts_id = cts_id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public long getRegister_id() {
        return register_id;
    }

    public void setRegister_id(long register_id) {
        this.register_id = register_id;
    }

    public String getCoverage_health() {
        return coverage_health;
    }

    public void setCoverage_health(String coverage_health) {
        this.coverage_health = coverage_health;
    }

    public String getLink_user() {
        return link_user;
    }

    public void setLink_user(String link_user) {
        this.link_user = link_user;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    @Override
    public String toString() {
        return "Complaint{" +
                "cts_id=" + cts_id +
                ", user_id=" + user_id +
                ", register_id=" + register_id +
                ", coverage_health='" + coverage_health + '\'' +
                ", link_user='" + link_user + '\'' +
                ", story='" + story + '\'' +
                '}';
    }
}
