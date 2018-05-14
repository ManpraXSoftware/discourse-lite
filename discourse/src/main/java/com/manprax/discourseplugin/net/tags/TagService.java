package com.manprax.discourseplugin.net.tags;


import com.manprax.discourseplugin.model.tags.Tag;
import com.manprax.discourseplugin.model.tags.TagGroupsResponse;
import com.manprax.discourseplugin.model.tags.TagsResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * Created by Mukesh on 28-11-2017.
 */

public interface TagService {

    /**
     * Get a list of tag groups
     * https://discourse.example.com/tag_groups.json
     */
    @GET("/tag_groups.json")
    Call<TagGroupsResponse> getTagGroups();


    /**
     * Create a new tag group
     * https://discourse.example.com/tag_groups.json
     */
    @FormUrlEncoded
    @POST("/tag_groups.json")
    Call<TagGroupsResponse> createTagGroup(@Field("name") String name, @Field("tag_names") String[] tagNames, @FieldMap Map<String, String> auth);


    /**
     * Update an existing tag group
     * https://discourse.example.com/tag_groups/{id}.json
     */
    @FormUrlEncoded
    @PUT("/tag_groups/{id}.json")
    Call<TagGroupsResponse> updateTagGroup(@Path("id") int id, @Field("name") String name, @Field("tag_names") String[] tagNames, @FieldMap Map<String, String> auth);

    /**
     * get a list of tags
     */
    @GET("/tags.json")
    Call<TagsResponse> getTagsList(@QueryMap Map<String, String> auth);

    /**
     * Get a specific tag
     */
    @GET("/tags/{tag}.json")
    Call<Tag> getTag(@Path("tag") String tag);
}
