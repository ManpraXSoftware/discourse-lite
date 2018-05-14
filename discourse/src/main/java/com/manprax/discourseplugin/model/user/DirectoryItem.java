package com.manprax.discourseplugin.model.user;

/**
 * Created by Prateek on 29-11-2017.
 */

public class DirectoryItem {
    private int id;
    private int likes_received;
    private int likes_given;
    private int topics_entered;
    private int topic_count;
    private int post_count;
    private int posts_read;
    private int days_visited;
    private User user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLikes_received() {
        return likes_received;
    }

    public void setLikes_received(int likes_received) {
        this.likes_received = likes_received;
    }

    public int getLikes_given() {
        return likes_given;
    }

    public void setLikes_given(int likes_given) {
        this.likes_given = likes_given;
    }

    public int getTopics_entered() {
        return topics_entered;
    }

    public void setTopics_entered(int topics_entered) {
        this.topics_entered = topics_entered;
    }

    public int getTopic_count() {
        return topic_count;
    }

    public void setTopic_count(int topic_count) {
        this.topic_count = topic_count;
    }

    public int getPost_count() {
        return post_count;
    }

    public void setPost_count(int post_count) {
        this.post_count = post_count;
    }

    public int getPosts_read() {
        return posts_read;
    }

    public void setPosts_read(int posts_read) {
        this.posts_read = posts_read;
    }

    public int getDays_visited() {
        return days_visited;
    }

    public void setDays_visited(int days_visited) {
        this.days_visited = days_visited;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
