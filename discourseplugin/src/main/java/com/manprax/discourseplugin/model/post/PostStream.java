package com.manprax.discourseplugin.model.post;


import com.manprax.discourseplugin.model.post.Post;

/**
 * Created by Prateek on 06-12-2017.
 */

public class PostStream {
    private Post[] posts;
    private String[] stream;

    public Post[] getPosts() {
        return posts;
    }

    public void setPosts(Post[] posts) {
        this.posts = posts;
    }

    public String[] getStream() {
        return stream;
    }

    public void setStream(String[] stream) {
        this.stream = stream;
    }
}
