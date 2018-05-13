package com.manprax.discourseplugin.model.common;

import com.manprax.discourseplugin.model.category.Category;
import com.manprax.discourseplugin.model.post.Post;
import com.manprax.discourseplugin.model.topic.Topic;
import com.manprax.discourseplugin.model.user.User;

import org.json.JSONObject;

/**
 * Created by Prateek on 29-11-2017.
 */

public class SearchResponse {
    private Post[] posts;
    private Topic[] topics;
    private User[] users;
    private Category categories;
    private JSONObject grouped_search_result;

    public Post[] getPosts() {
        return posts;
    }

    public void setPosts(Post[] posts) {
        this.posts = posts;
    }

    public Topic[] getTopics() {
        return topics;
    }

    public void setTopics(Topic[] topics) {
        this.topics = topics;
    }

    public User[] getUsers() {
        return users;
    }

    public void setUsers(User[] users) {
        this.users = users;
    }

    public Category getCategories() {
        return categories;
    }

    public void setCategories(Category categories) {
        this.categories = categories;
    }

    public JSONObject getGrouped_search_result() {
        return grouped_search_result;
    }

    public void setGrouped_search_result(JSONObject grouped_search_result) {
        this.grouped_search_result = grouped_search_result;
    }
}
