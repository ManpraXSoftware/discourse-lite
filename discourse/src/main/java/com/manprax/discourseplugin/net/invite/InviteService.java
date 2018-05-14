package com.manprax.discourseplugin.net.invite;


import com.manprax.discourseplugin.model.Success;
import com.manprax.discourseplugin.model.user.UserResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Mukesh on 28-11-2017.
 */

public interface InviteService {

    //Invite user to topic
    @FormUrlEncoded
    @POST("/t/{id}/invite")
    Call<UserResponse> inviteToTopic(@Path("id") String topicId, @Field("username") String username, @FieldMap Map<String, String> auth);

    //Invite a user by email to join your forum
    @FormUrlEncoded
    @POST("/invites")
    Call<Success> inviteToSendEmail(@Field("email") String email, @Field("group_names") String group_names, @Field("custom_message") String custom_message, @FieldMap Map<String, String> auth);

    //generate an invite link but don't send an email
    @FormUrlEncoded
    @POST("/invites/link")
    Call<String> getInviteUrl(@Field("email") String email, @Field("group_names") String group_names, @Field("custom_message") String custom_message, @FieldMap Map<String, String> auth);

}
