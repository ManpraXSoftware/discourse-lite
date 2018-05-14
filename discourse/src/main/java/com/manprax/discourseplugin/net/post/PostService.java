package com.manprax.discourseplugin.net.post;


import com.manprax.discourseplugin.model.post.Post;
import com.manprax.discourseplugin.model.post.SinglePostResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * Created by Mukesh on 28-11-2017.
 */

public interface PostService {

    //Create a new topic or Post  required fields- topic_id,raw
    @FormUrlEncoded
    @POST("/posts")
    Call<Post> createPost(@FieldMap Map<String, String> createParam, @FieldMap Map<String, String> auth);
    //Get a single post
    @GET("/posts/{id}")
    Call<Post> getSinglePost(@Path("id") String postId, @QueryMap Map<String, String> auth);
    //Update a single post
    @FormUrlEncoded
    @PUT("/posts/{id}")
    Call<SinglePostResponse> updatePost(@Path("id") String postId, @Field("post[raw]") String raw, @FieldMap Map<String, String> auth);

     //Like a post and other actions
    @FormUrlEncoded
    @POST("/post_actions")
    Call<Post> doPostActions(@Field("id") int id, @Field("post_action_type_id") int actionTypeId, @Field("flag_topic") boolean flagTopic, @FieldMap Map<String, String> auth);
    //Un-like a post
    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "/post_actions/{id}", hasBody = true)
    Call<Post> unlikePost(@Path("id") int id, @Field("post_action_type_id") int actionTypeId, @FieldMap Map<String, String> auth);


}
