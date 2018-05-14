package com.manprax.discourseplugin.net;

/**
 * Created by Mukesh on 24-11-2017.
 */

public class DiscourseConstants {
    public static final String API_KEY="api_key";
    public static final String API_USERNAME="api_username";
    public static final int FILTER_LIST_TYPE = 34;
    public static final int FILTER_TAGS_TYPE = 36;
    public static final int FILTER_CATEGORY_TYPE = 38;
    public static final int COMPOSE_TYPE_TOPIC=23;
    public static final int COMPOSE_TYPE_POST=25;
    public static final int COMPOSE_TYPE_REPLY=27;
    public enum FILTER {
        LIKE,
        WAS_LIKED,
        BOOKMARK,
        NEW_TOPIC,
        REPLY,
        RESPONSE,
        MENTION,
        QUOTE,
        STAR,
        EDIT,
        NEW_PRIVATE_MESSAGE,
        GOT_PRIVATE_MESSAGE
    };
    public enum ORDER {    //default
        created,
        activity,
        views,
        posts,
        category,
        likes,
        op_likes,
        posters,
    };
    public enum FLAG {
        all,
        yearly,
        quarterly,
        monthly,
        weekly,
        daily,
    };

    //  Get the top topics filtered by specified flag   "all" "yearly" "quarterly" "monthly" "weekly" "daily"

}
