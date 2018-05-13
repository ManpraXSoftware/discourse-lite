package com.manprax.discourseplugin.model.user;

import java.util.Arrays;

/**
 * Created by Prateek on 24-11-2017.
 */

public class UserResponse {
    private User user;
    private String[] user_badges;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String[] getUser_badges() {
        return user_badges;
    }

    public void setUser_badges(String[] user_badges) {
        this.user_badges = user_badges;
    }

    @Override
    public String toString() {
        return "UserResponse{" +
                "user=" + user +
                ", user_badges=" + Arrays.toString(user_badges) +
                '}';
    }
}
