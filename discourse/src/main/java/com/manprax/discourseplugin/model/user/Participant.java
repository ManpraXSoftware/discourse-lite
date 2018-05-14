package com.manprax.discourseplugin.model.user;

/**
 * Created by mukesh on 19/4/18.
 */

public class Participant {
    private int id;
    private String username;
    private String avatar_template;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar_template() {
        return avatar_template;
    }

    public void setAvatar_template(String avatar_template) {
        this.avatar_template = avatar_template;
    }

    @Override
    public String toString() {
        return "Participant{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", avatar_template='" + avatar_template + '\'' +
                '}';
    }
}
