package com.manprax.discourseplugin.model.tags;

/**
 * Created by Prateek on 29-11-2017.
 */

public class Tag {
    private String id;
    private String text;
    private int count;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
