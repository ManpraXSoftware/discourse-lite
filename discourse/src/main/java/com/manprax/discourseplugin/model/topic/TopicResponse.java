package com.manprax.discourseplugin.model.topic;


import com.manprax.discourseplugin.model.user.User;

/**
 * Created by Prateek on 27-11-2017.
 */

public class TopicResponse {
    private User[] users;
    private TopicList topic_list;

    public User[] getUsers() {
        return users;
    }

    public void setUsers(User[] users) {
        this.users = users;
    }

    public TopicList getTopic_list() {
        return topic_list;
    }

    public void setTopic_list(TopicList topic_list) {
        this.topic_list = topic_list;
    }
}
