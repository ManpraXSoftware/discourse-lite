package com.manprax.discourseplugin.model.post;


import com.manprax.discourseplugin.model.PostActionType;
import com.manprax.discourseplugin.model.user.Participant;

import java.util.Arrays;

/**
 * Created by Mukesh on 27-11-2017.
 */

public class Post {
    private int id;
    private int post_number;
    private int post_type;
    private int reply_count;
    private int quote_count;
    private int incoming_link_count;
    private int reads;
    private double score;
    private int topic_id;
    private int version;
    private int user_id;
    private int trust_level;

    private String name;
    private String username;
    private String avatar_template;
    private String created_at;
    private String cooked;
    private String updated_at;
    private String topic_slug;
    private String display_username;
    private String raw;
    private String action_code;

    private int reply_to_post_number;
    private Object avg_time;
    private Object primary_group_name;
    private Object primary_group_flair_url;
    private Object primary_group_flair_bg_color;
    private Object primary_group_flair_color;
    private Object user_title;
    private Object hidden_reason_id;
    private Object deleted_at;
    private Object edit_reason;

    private ActionsSummary[] actions_summary;
    private Participant reply_to_user;
    private boolean yours;
    private boolean can_edit;
    private boolean can_delete;
    private boolean can_recover;
    private boolean can_wiki;
    private boolean moderator;
    private boolean admin;
    private boolean staff;
    private boolean hidden;
    private boolean user_deleted;
    private boolean can_view_edit_history;
    private boolean wiki;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPost_number() {
        return post_number;
    }

    public void setPost_number(int post_number) {
        this.post_number = post_number;
    }

    public int getPost_type() {
        return post_type;
    }

    public void setPost_type(int post_type) {
        this.post_type = post_type;
    }

    public int getReply_count() {
        return reply_count;
    }

    public void setReply_count(int reply_count) {
        this.reply_count = reply_count;
    }

    public int getQuote_count() {
        return quote_count;
    }

    public void setQuote_count(int quote_count) {
        this.quote_count = quote_count;
    }

    public int getIncoming_link_count() {
        return incoming_link_count;
    }

    public void setIncoming_link_count(int incoming_link_count) {
        this.incoming_link_count = incoming_link_count;
    }

    public int getReads() {
        return reads;
    }

    public void setReads(int reads) {
        this.reads = reads;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public int getTopic_id() {
        return topic_id;
    }

    public void setTopic_id(int topic_id) {
        this.topic_id = topic_id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getTrust_level() {
        return trust_level;
    }

    public void setTrust_level(int trust_level) {
        this.trust_level = trust_level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Participant getReply_to_user() {
        return reply_to_user;
    }

    public void setReply_to_user(Participant reply_to_user) {
        this.reply_to_user = reply_to_user;
    }

    public String getAvatar_template() {
        return avatar_template;
    }
    public String getAvatarUrl(int size, String baseUrl){
        if (getAvatar_template()==null)
            return "";  //return here default one
        String withSize=getAvatar_template().replace("{size}",""+size);
        return baseUrl+withSize;
    }

    public String prepareShareLink(String baseUrl, String userName){
        //http://10.5.50.31:2018/t/hiring-for-php-anyone-you-guys-know/22/4?u=mukesh
        //https://meta.discourse.org/t/get-number-of-likes-for-a-single-post/69017/2?u=mukesh
        return baseUrl+"/t/"+getTopic_slug()+"/"+getTopic_id()+"/"+getPost_number()+"?u="+userName;
    }
    public void setAvatar_template(String avatar_template) {
        this.avatar_template = avatar_template;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getCooked() {
        return cooked;
    }

    public void setCooked(String cooked) {
        this.cooked = cooked;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getTopic_slug() {
        return topic_slug;
    }

    public void setTopic_slug(String topic_slug) {
        this.topic_slug = topic_slug;
    }

    public String getDisplay_username() {
        return display_username;
    }

    public void setDisplay_username(String display_username) {
        this.display_username = display_username;
    }

    public String getRaw() {
        return raw;
    }

    public void setRaw(String raw) {
        this.raw = raw;
    }

    public int getReply_to_post_number() {
        return reply_to_post_number;
    }

    public void setReply_to_post_number(int reply_to_post_number) {
        this.reply_to_post_number = reply_to_post_number;
    }

    public Object getAvg_time() {
        return avg_time;
    }

    public void setAvg_time(Object avg_time) {
        this.avg_time = avg_time;
    }

    public Object getPrimary_group_name() {
        return primary_group_name;
    }

    public void setPrimary_group_name(Object primary_group_name) {
        this.primary_group_name = primary_group_name;
    }

    public Object getPrimary_group_flair_url() {
        return primary_group_flair_url;
    }

    public void setPrimary_group_flair_url(Object primary_group_flair_url) {
        this.primary_group_flair_url = primary_group_flair_url;
    }

    public Object getPrimary_group_flair_bg_color() {
        return primary_group_flair_bg_color;
    }

    public void setPrimary_group_flair_bg_color(Object primary_group_flair_bg_color) {
        this.primary_group_flair_bg_color = primary_group_flair_bg_color;
    }

    public Object getPrimary_group_flair_color() {
        return primary_group_flair_color;
    }

    public void setPrimary_group_flair_color(Object primary_group_flair_color) {
        this.primary_group_flair_color = primary_group_flair_color;
    }

    public Object getUser_title() {
        return user_title;
    }

    public void setUser_title(Object user_title) {
        this.user_title = user_title;
    }

    public Object getHidden_reason_id() {
        return hidden_reason_id;
    }

    public void setHidden_reason_id(Object hidden_reason_id) {
        this.hidden_reason_id = hidden_reason_id;
    }

    public Object getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(Object deleted_at) {
        this.deleted_at = deleted_at;
    }

    public Object getEdit_reason() {
        return edit_reason;
    }

    public void setEdit_reason(Object edit_reason) {
        this.edit_reason = edit_reason;
    }

    public ActionsSummary[] getActions_summary() {
        return actions_summary;
    }

    public void setActions_summary(ActionsSummary[] actions_summary) {
        this.actions_summary = actions_summary;
    }

    public boolean isYours() {
        return yours;
    }

    public void setYours(boolean yours) {
        this.yours = yours;
    }

    public boolean isCan_edit() {
        return can_edit;
    }

    public void setCan_edit(boolean can_edit) {
        this.can_edit = can_edit;
    }

    public boolean isCan_delete() {
        return can_delete;
    }

    public void setCan_delete(boolean can_delete) {
        this.can_delete = can_delete;
    }

    public boolean isCan_recover() {
        return can_recover;
    }

    public void setCan_recover(boolean can_recover) {
        this.can_recover = can_recover;
    }

    public boolean isCan_wiki() {
        return can_wiki;
    }

    public void setCan_wiki(boolean can_wiki) {
        this.can_wiki = can_wiki;
    }

    public boolean isModerator() {
        return moderator;
    }

    public void setModerator(boolean moderator) {
        this.moderator = moderator;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean isStaff() {
        return staff;
    }

    public void setStaff(boolean staff) {
        this.staff = staff;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public boolean isUser_deleted() {
        return user_deleted;
    }

    public void setUser_deleted(boolean user_deleted) {
        this.user_deleted = user_deleted;
    }

    public boolean isCan_view_edit_history() {
        return can_view_edit_history;
    }

    public void setCan_view_edit_history(boolean can_view_edit_history) {
        this.can_view_edit_history = can_view_edit_history;
    }

    public boolean isWiki() {
        return wiki;
    }

    public void setWiki(boolean wiki) {
        this.wiki = wiki;
    }
    public boolean isMyPost(int userId){
       return getUser_id()==userId;
    }
    public boolean isPostLiked(){
        return summaryLookup(PostActionType.LIKE);
    }
    public boolean canUndoLike(){
        if (getActions_summary()==null)
            return false;
        for(ActionsSummary summary:getActions_summary()) {
            if (summary.getId() == 2 && summary.isActed()&&summary.isCan_undo())
                return true;
        }
        return false;
    }
    public String getLikeCount(){
        if (getActions_summary()==null)
            return "";
        for(ActionsSummary summary:getActions_summary()) {
            if (summary.getId() == 2 && summary.isActed())
                return summary.getCount()==1?"1 Like":summary.getCount()+" Likes";
        }
        return "";
    }
    public boolean isMyPost(String username){
        return getUsername().equalsIgnoreCase(username);
    }
    public String getAction_code() {
        return action_code;
    }

    public void setAction_code(String action_code) {
        this.action_code = action_code;
    }
    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", post_number=" + post_number +
                ", post_type=" + post_type +
                ", reply_count=" + reply_count +
                ", quote_count=" + quote_count +
                ", incoming_link_count=" + incoming_link_count +
                ", reads=" + reads +
                ", score=" + score +
                ", topic_id=" + topic_id +
                ", version=" + version +
                ", user_id=" + user_id +
                ", trust_level=" + trust_level +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", avatar_template='" + avatar_template + '\'' +
                ", created_at='" + created_at + '\'' +
                ", cooked='" + cooked + '\'' +
                ", updated_at='" + updated_at + '\'' +
                ", topic_slug='" + topic_slug + '\'' +
                ", display_username='" + display_username + '\'' +
                ", raw='" + raw + '\'' +
                ", reply_to_post_number=" + reply_to_post_number +
                ", avg_time=" + avg_time +
                ", primary_group_name=" + primary_group_name +
                ", primary_group_flair_url=" + primary_group_flair_url +
                ", primary_group_flair_bg_color=" + primary_group_flair_bg_color +
                ", primary_group_flair_color=" + primary_group_flair_color +
                ", user_title=" + user_title +
                ", hidden_reason_id=" + hidden_reason_id +
                ", deleted_at=" + deleted_at +
                ", edit_reason=" + edit_reason +
                ", actions_summary=" + Arrays.toString(actions_summary) +
                ", yours=" + yours +
                ", can_edit=" + can_edit +
                ", can_delete=" + can_delete +
                ", can_recover=" + can_recover +
                ", can_wiki=" + can_wiki +
                ", moderator=" + moderator +
                ", admin=" + admin +
                ", staff=" + staff +
                ", hidden=" + hidden +
                ", user_deleted=" + user_deleted +
                ", can_view_edit_history=" + can_view_edit_history +
                ", wiki=" + wiki +
                '}';
    }
    private boolean summaryLookup(PostActionType actionType){
        if (getActions_summary()==null)
            return false;
        for(ActionsSummary summary:getActions_summary()) {
            if (summary.getId() == actionType.getValue() && summary.isActed())
                return true;
        }
        return false;
    }

    public boolean isBookmarked() {
       return summaryLookup(PostActionType.BOOKMARK);

    }
}
