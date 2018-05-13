package com.manprax.discourseplugin.net.topic;


import com.manprax.discourseplugin.model.Success;
import com.manprax.discourseplugin.model.topic.Topic;
import com.manprax.discourseplugin.model.topic.TopicResponse;
import com.manprax.discourseplugin.model.user.User;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * Created by Mukesh on 27-11-2017.
 */

public interface TopicService{

    //Create a new topic  required fields- title,raw,category
    @FormUrlEncoded
    @POST("/posts")
    Call<Topic>  createTopic(@FieldMap Map<String, String> createParam, @FieldMap Map<String, String> auth);

    //Get a single topic
    @GET("/t/{id}.json")
    Call<Topic> getSingleTopic(@Path("id") String topicId, @QueryMap Map<String, String> auth);

    //Remove a single topic
    @DELETE("/t/{id}.json")
    Call<Void> deleteTopic(@Path("id") String topicId, @QueryMap Map<String, String> auth);

    //Update a single topic
    @FormUrlEncoded
    @PUT("/t/-/{id}.json")
    Call<Topic>  updateTopic(@Path("id") String topicId, @Field("title") String title, @Field("category_id") int categoryId, @FieldMap Map<String, String> auth);

    //Invite user to topic
    @FormUrlEncoded
    @POST("/t/{id}/invite")
    Call<User> inviteUserToTopic(@Path("id") String topicId, @Field("username") String username, @FieldMap Map<String, String> auth);

    //bookmark a topic
    @FormUrlEncoded
    @PUT("/t/{id}/bookmark")
    Call<Void>  bookmarkTopic(@Path("id") String topicId, @FieldMap Map<String, String> auth);

    //close a topic
    @FormUrlEncoded
    @PUT("/t/{id}/status")
    Call<Topic>  closeTopic(@Path("id") String topicId, @Field("enabled") boolean isEnable, @Field("status") String status, @FieldMap Map<String, String> auth);

    //Get the latest topics   filter Param(order="default"|"created"|"activity"|"views"|"posts"|"category"|"likes"|"op_likes"|"posters"  && ascending=true)
    @GET("/latest.json")
    Call<TopicResponse> getLatestTopics(@QueryMap Map<String, String> filterParam, @QueryMap Map<String, String> auth);

    //Get the top topics
    @GET("/top.json")
    Call<TopicResponse> getTopTopics(@QueryMap Map<String, String> auth);

    //Get the top topics filtered by specified flag  "all" "yearly" "quarterly" "monthly" "weekly" "daily"
    @GET("/top/{flag}.json")
    Call<TopicResponse> getTop(@Path("flag") String flag, @QueryMap Map<String, String> auth);

    //Create a timed topic
    @FormUrlEncoded
    @POST("/t/{id}/status_update")
    Call<Void>  createTimedTopic(@Path("id") String topicId, @FieldMap Map<String, String> timedParam, @FieldMap Map<String, String> auth);

    //Update a topics timestamp
    @FormUrlEncoded
    @PUT("/t/{id}/change-timestamp")
    Call<Success>  updateTopicTimestamp(@Path("id") String topicId, @Field("timestamp") String timestamp, @FieldMap Map<String, String> auth);

    //set notification level  = 0 1 2 3
    @FormUrlEncoded
    @POST("/t/{id}/notifications")
    Call<Success>  setTopicNotificationLevel(@Path("id") String topicId, @Field("notification_level") int notificationLevel, @FieldMap Map<String, String> auth);

}
