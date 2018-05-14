package com.manprax.discourseplugin.model;

/**
 * Created by mukesh on 19/4/18.
 */

public enum PostActionType {
    BOOKMARK(1),LIKE(2),OFF_TOPIC(3),INAPPROPRIATE(4),VOTE(5),SPAM(6),NOTIFY_USER(7),NOTIFY_MODERATORS(8);

    PostActionType(int value) {
        this.value=value;
    }
    int value;

    public int getValue() {
        return value;
    }
}
