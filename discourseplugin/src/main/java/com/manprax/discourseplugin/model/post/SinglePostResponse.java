package com.manprax.discourseplugin.model.post;

/**
 * Created by Prateek on 28-11-2017.
 */

public class SinglePostResponse {
    private Post post;

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    @Override
    public String toString() {
        return "SinglePostResponse{" +
                "post=" + post +
                '}';
    }
}
