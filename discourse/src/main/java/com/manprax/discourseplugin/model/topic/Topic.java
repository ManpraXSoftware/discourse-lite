package com.manprax.discourseplugin.model.topic;


import com.manprax.discourseplugin.model.post.ActionsSummary;
import com.manprax.discourseplugin.model.post.PostStream;
import com.manprax.discourseplugin.model.user.Poster;

/**
 * Created by Prateek on 27-11-2017.
 */

public class Topic {
    private PostStream post_stream;
    private String last_posted_at;
    private Object word_count;
    private String deleted_at;
    private Object draft;
    private Object draft_sequence;
    private Object unpinned;
    private Object pinned_until;
    private Object details;
    private Object deleted_by;
    private Object bookmarked;
    private String image_url;
    private Object[] timeline_lookup;
    private ActionsSummary[] actions_summary;
    private int id;
    private int posts_count;
    private int views;
    private int reply_count;
    private int participant_count;
    private int like_count;
    private int category_id;
    private int user_id;
    private int highest_post_number;
    private int chunk_size;
    private String title;
    private String fancy_title;
    private String created_at;
    private String archetype;
    private String slug;
    private String draft_key;
    private String pinned_at;
    private boolean visible;
    private boolean closed;
    private boolean archived;
    private boolean has_summary;
    private boolean pinned_globally;
    private boolean pinned;
    private Poster[] posters;

    public PostStream getPost_stream() {
        return post_stream;
    }

    public void setPost_stream(PostStream post_stream) {
        this.post_stream = post_stream;
    }

    public String getLast_posted_at() {
        return last_posted_at;
    }

    public void setLast_posted_at(String last_posted_at) {
        this.last_posted_at = last_posted_at;
    }

    public Object getWord_count() {
        return word_count;
    }

    public void setWord_count(Object word_count) {
        this.word_count = word_count;
    }

    public String getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(String deleted_at) {
        this.deleted_at = deleted_at;
    }

    public Object getDraft() {
        return draft;
    }

    public void setDraft(Object draft) {
        this.draft = draft;
    }

    public Object getDraft_sequence() {
        return draft_sequence;
    }

    public void setDraft_sequence(Object draft_sequence) {
        this.draft_sequence = draft_sequence;
    }

    public Object getUnpinned() {
        return unpinned;
    }

    public void setUnpinned(Object unpinned) {
        this.unpinned = unpinned;
    }

    public Object getPinned_until() {
        return pinned_until;
    }

    public void setPinned_until(Object pinned_until) {
        this.pinned_until = pinned_until;
    }

    public Object getDetails() {
        return details;
    }

    public void setDetails(Object details) {
        this.details = details;
    }

    public Object getDeleted_by() {
        return deleted_by;
    }

    public void setDeleted_by(Object deleted_by) {
        this.deleted_by = deleted_by;
    }

    public Object getBookmarked() {
        return bookmarked;
    }

    public void setBookmarked(Object bookmarked) {
        this.bookmarked = bookmarked;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public Object[] getTimeline_lookup() {
        return timeline_lookup;
    }

    public void setTimeline_lookup(Object[] timeline_lookup) {
        this.timeline_lookup = timeline_lookup;
    }

    public ActionsSummary[] getActions_summary() {
        return actions_summary;
    }

    public void setActions_summary(ActionsSummary[] actions_summary) {
        this.actions_summary = actions_summary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPosts_count() {
        return posts_count;
    }

    public void setPosts_count(int posts_count) {
        this.posts_count = posts_count;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public int getReply_count() {
        return reply_count;
    }

    public void setReply_count(int reply_count) {
        this.reply_count = reply_count;
    }

    public int getParticipant_count() {
        return participant_count;
    }

    public void setParticipant_count(int participant_count) {
        this.participant_count = participant_count;
    }

    public int getLike_count() {
        return like_count;
    }

    public void setLike_count(int like_count) {
        this.like_count = like_count;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getHighest_post_number() {
        return highest_post_number;
    }

    public void setHighest_post_number(int highest_post_number) {
        this.highest_post_number = highest_post_number;
    }

    public int getChunk_size() {
        return chunk_size;
    }

    public void setChunk_size(int chunk_size) {
        this.chunk_size = chunk_size;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFancy_title() {
        return fancy_title;
    }

    public void setFancy_title(String fancy_title) {
        this.fancy_title = fancy_title;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getArchetype() {
        return archetype;
    }

    public void setArchetype(String archetype) {
        this.archetype = archetype;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getDraft_key() {
        return draft_key;
    }

    public void setDraft_key(String draft_key) {
        this.draft_key = draft_key;
    }

    public String getPinned_at() {
        return pinned_at;
    }

    public void setPinned_at(String pinned_at) {
        this.pinned_at = pinned_at;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    public boolean isHas_summary() {
        return has_summary;
    }

    public void setHas_summary(boolean has_summary) {
        this.has_summary = has_summary;
    }

    public boolean isPinned_globally() {
        return pinned_globally;
    }

    public void setPinned_globally(boolean pinned_globally) {
        this.pinned_globally = pinned_globally;
    }

    public boolean isPinned() {
        return pinned;
    }

    public void setPinned(boolean pinned) {
        this.pinned = pinned;
    }

    public Poster[] getPosters() {
        return posters;
    }

    public void setPosters(Poster[] posters) {
        this.posters = posters;
    }
}
