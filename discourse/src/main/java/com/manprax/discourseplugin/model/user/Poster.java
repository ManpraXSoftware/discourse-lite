package com.manprax.discourseplugin.model.user;

/**
 * Created by Prateek on 27-11-2017.
 */

public class Poster {
    private int user_id;
    private String extras;
    private String description;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getExtras() {
        return extras;
    }

    public void setExtras(String extras) {
        this.extras = extras;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
