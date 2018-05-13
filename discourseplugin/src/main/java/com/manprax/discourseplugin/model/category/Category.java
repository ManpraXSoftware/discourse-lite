package com.manprax.discourseplugin.model.category;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Prateek on 23-11-2017.
 */

public class Category implements Parcelable {
    private int id;
    private String name;
    private String color;
    private String text_color;
    private String slug;
    private String position;
    private boolean can_edit;
    private int topics_week;
    private long topic_count;
    private long topics_all_time;
    private int permission;
    private boolean read_restricted;
    private Object description_text;
    private Object[] group_permissions;
    private int topics_month;
    private String topic_url;
    private int topics_day;
    private String topic_template;
    private String background_url;
    private String notification_level;
    private boolean has_children;
    private Object description;
    private long post_count;
    private int topics_year;
    private String description_excerpt;
    private String logo_url;

    public Category() {
    }

    protected Category(Parcel in) {
        id = in.readInt();
        name = in.readString();
        color = in.readString();
        text_color = in.readString();
        slug = in.readString();
        position = in.readString();
        can_edit = in.readByte() != 0;
        topics_week = in.readInt();
        topic_count = in.readLong();
        topics_all_time = in.readLong();
        permission = in.readInt();
        read_restricted = in.readByte() != 0;
        topics_month = in.readInt();
        topic_url = in.readString();
        topics_day = in.readInt();
        topic_template = in.readString();
        background_url = in.readString();
        notification_level = in.readString();
        has_children = in.readByte() != 0;
        post_count = in.readLong();
        topics_year = in.readInt();
        description_excerpt = in.readString();
        logo_url = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(color);
        dest.writeString(text_color);
        dest.writeString(slug);
        dest.writeString(position);
        dest.writeByte((byte) (can_edit ? 1 : 0));
        dest.writeInt(topics_week);
        dest.writeLong(topic_count);
        dest.writeLong(topics_all_time);
        dest.writeInt(permission);
        dest.writeByte((byte) (read_restricted ? 1 : 0));
        dest.writeInt(topics_month);
        dest.writeString(topic_url);
        dest.writeInt(topics_day);
        dest.writeString(topic_template);
        dest.writeString(background_url);
        dest.writeString(notification_level);
        dest.writeByte((byte) (has_children ? 1 : 0));
        dest.writeLong(post_count);
        dest.writeInt(topics_year);
        dest.writeString(description_excerpt);
        dest.writeString(logo_url);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Category> CREATOR = new Creator<Category>() {
        @Override
        public Category createFromParcel(Parcel in) {
            return new Category(in);
        }

        @Override
        public Category[] newArray(int size) {
            return new Category[size];
        }
    };

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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getText_color() {
        return text_color;
    }

    public void setText_color(String text_color) {
        this.text_color = text_color;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public boolean isCan_edit() {
        return can_edit;
    }

    public void setCan_edit(boolean can_edit) {
        this.can_edit = can_edit;
    }

    public int getTopics_week() {
        return topics_week;
    }

    public void setTopics_week(int topics_week) {
        this.topics_week = topics_week;
    }

    public long getTopic_count() {
        return topic_count;
    }

    public void setTopic_count(long topic_count) {
        this.topic_count = topic_count;
    }

    public long getTopics_all_time() {
        return topics_all_time;
    }

    public void setTopics_all_time(long topics_all_time) {
        this.topics_all_time = topics_all_time;
    }

    public int getPermission() {
        return permission;
    }

    public void setPermission(int permission) {
        this.permission = permission;
    }

    public boolean isRead_restricted() {
        return read_restricted;
    }

    public void setRead_restricted(boolean read_restricted) {
        this.read_restricted = read_restricted;
    }

    public Object getDescription_text() {
        return description_text;
    }

    public void setDescription_text(Object description_text) {
        this.description_text = description_text;
    }

    public Object[] getGroup_permissions() {
        return group_permissions;
    }

    public void setGroup_permissions(Object[] group_permissions) {
        this.group_permissions = group_permissions;
    }

    public int getTopics_month() {
        return topics_month;
    }

    public void setTopics_month(int topics_month) {
        this.topics_month = topics_month;
    }

    public String getTopic_url() {
        return topic_url;
    }

    public void setTopic_url(String topic_url) {
        this.topic_url = topic_url;
    }

    public int getTopics_day() {
        return topics_day;
    }

    public void setTopics_day(int topics_day) {
        this.topics_day = topics_day;
    }

    public String getTopic_template() {
        return topic_template;
    }

    public void setTopic_template(String topic_template) {
        this.topic_template = topic_template;
    }

    public String getBackground_url() {
        return background_url;
    }

    public void setBackground_url(String background_url) {
        this.background_url = background_url;
    }

    public String getNotification_level() {
        return notification_level;
    }

    public void setNotification_level(String notification_level) {
        this.notification_level = notification_level;
    }

    public boolean isHas_children() {
        return has_children;
    }

    public void setHas_children(boolean has_children) {
        this.has_children = has_children;
    }

    public Object getDescription() {
        return description;
    }

    public void setDescription(Object description) {
        this.description = description;
    }

    public long getPost_count() {
        return post_count;
    }

    public void setPost_count(long post_count) {
        this.post_count = post_count;
    }

    public int getTopics_year() {
        return topics_year;
    }

    public void setTopics_year(int topics_year) {
        this.topics_year = topics_year;
    }

    public String getDescription_excerpt() {
        return description_excerpt;
    }

    public void setDescription_excerpt(String description_excerpt) {
        this.description_excerpt = description_excerpt;
    }

    public String getLogo_url() {
        return logo_url;
    }

    public void setLogo_url(String logo_url) {
        this.logo_url = logo_url;
    }

    @Override
    public String toString() {
        return "Category{" +
                "position='" + position + '\'' +
                ", can_edit=" + can_edit +
                ", topics_week=" + topics_week +
                ", topic_count=" + topic_count +
                ", topics_all_time=" + topics_all_time +
                ", permission=" + permission +
                ", read_restricted=" + read_restricted +
                ", description_text='" + description_text + '\'' +
                ", topics_month=" + topics_month +
                ", topic_url='" + topic_url + '\'' +
                ", topics_day=" + topics_day +
                ", topic_template='" + topic_template + '\'' +
                ", background_url='" + background_url + '\'' +
                ", id=" + id +
                ", notification_level='" + notification_level + '\'' +
                ", has_children=" + has_children +
                ", color='" + color + '\'' +
                ", description='" + description + '\'' +
                ", name='" + name + '\'' +
                ", post_count=" + post_count +
                ", topics_year=" + topics_year +
                ", slug='" + slug + '\'' +
                ", description_excerpt='" + description_excerpt + '\'' +
                ", logo_url='" + logo_url + '\'' +
                ", text_color='" + text_color + '\'' +
                '}';
    }
}
