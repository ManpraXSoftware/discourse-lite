package com.manprax.discourseplugin.model.topic;

/**
 * Created by Prateek on 27-11-2017.
 */

public class TopicList {
    private boolean can_create_topic;
    private String draft;
    private String draft_sequence;
    private String draft_key;
    private int per_page;
    private Topic[] topics;

    public boolean isCan_create_topic() {
        return can_create_topic;
    }

    public void setCan_create_topic(boolean can_create_topic) {
        this.can_create_topic = can_create_topic;
    }

    public Object getDraft() {
        return draft;
    }

    public void setDraft(String draft) {
        this.draft = draft;
    }

    public String getDraft_sequence() {
        return draft_sequence;
    }

    public void setDraft_sequence(String draft_sequence) {
        this.draft_sequence = draft_sequence;
    }

    public String getDraft_key() {
        return draft_key;
    }

    public void setDraft_key(String draft_key) {
        this.draft_key = draft_key;
    }

    public int getPer_page() {
        return per_page;
    }

    public void setPer_page(int per_page) {
        this.per_page = per_page;
    }

    public Topic[] getTopics() {
        return topics;
    }

    public void setTopics(Topic[] topics) {
        this.topics = topics;
    }
}
