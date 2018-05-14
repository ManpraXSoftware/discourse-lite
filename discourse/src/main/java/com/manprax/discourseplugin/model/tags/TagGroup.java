package com.manprax.discourseplugin.model.tags;

/**
 * Created by Prateek on 29-11-2017.
 */

public class TagGroup {
    private int id;
    private String name;
    private String[] tag_names;
    private String[] parent_tag_name;
    private boolean one_per_topic;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getTag_names() {
        return tag_names;
    }

    public void setTag_names(String[] tag_names) {
        this.tag_names = tag_names;
    }

    public String[] getParent_tag_name() {
        return parent_tag_name;
    }

    public void setParent_tag_name(String[] parent_tag_name) {
        this.parent_tag_name = parent_tag_name;
    }

    public boolean isOne_per_topic() {
        return one_per_topic;
    }

    public void setOne_per_topic(boolean one_per_topic) {
        this.one_per_topic = one_per_topic;
    }
}
