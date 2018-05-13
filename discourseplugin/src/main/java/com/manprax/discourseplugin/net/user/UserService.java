package com.manprax.discourseplugin.net.user;


import com.manprax.discourseplugin.model.Success;
import com.manprax.discourseplugin.model.user.PublicUsersResponse;
import com.manprax.discourseplugin.model.user.User;
import com.manprax.discourseplugin.model.user.UserActionResponse;
import com.manprax.discourseplugin.model.user.UserResponse;

import java.util.List;
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
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by Mukesh on 29-11-2017.
 */

public interface UserService {
    /**
     * get Single user by their username
     *
     * @param userName user name
     * @return
     */
    @GET("/users/{username}.json")
    Call<UserResponse> getUserByUserName(@Path("username") String userName, @QueryMap Map<String, String> auth);


    //update avatar
    @FormUrlEncoded
    @PUT("/users/{username}/preferences/avatar/pick")
    Call<Success> updateAvatar(@Path("username") String userName, @Field("upload_id") int uploadId, @Field("type") String type, @FieldMap Map<String, String> auth);

    //update email
    @FormUrlEncoded
    @PUT("/users/{username}/preferences/email")
    Call<Success> updateEmail(@Path("username") String userName, @Field("email") String email, @FieldMap Map<String, String> auth);

    //Creates a new user   required fields-name,email,password,username || optional Field- active,approved, user_fields[1]

    /**
     * {
     * "name": "string",
     * "email": "string",
     * "password": "string",
     * "username": "string",
     * "active": true,
     * "approved": true,
     * "user_fields[1]": "string"
     * }
     */
    @FormUrlEncoded
    @POST("/users")
    Call<Object> createUser(@FieldMap Map<String, String> createParam, @FieldMap Map<String, String> auth);

    /**
     * get a public list of users
     * https://discourse.example.com/directory_items.json?period={period}&order={order}
     * <p>
     * period="daily" "weekly" "monthly" "quarterly" "yearly" "all"
     * order= "likes_received" "likes_given" "topic_count" "post_count" "topics_entered" "posts_read" "days_visited"
     * asc=true
     * page
     */
    @GET("/directory_items.json")
    Call<PublicUsersResponse> getPublicUsersList(@Query("period") String period, @Query("order") String order, @Query("asc") boolean asc, @Query("page") int page, @QueryMap Map<String, String> auth);

   //Delete a user
   @FormUrlEncoded
    @DELETE("/admin/users/{id}.json")
    Call<Void> deleteUser(@Path("id") int id, @FieldMap Map<String, String> auth);

    /**
     * returns a list of users
     * https://discourse.example.com/admin/users/list/{flag}.json
     * <p>
     *  flag="active" "new" "staff" "suspended" "blocked" "suspect"
     * order= "created" "last_emailed" "seen" "username" "email" "trust_level" "days_visited" "posts_read" "topics_viewed" "posts" "read_time"
     * ascending=true
     * page
     * show_emails=true/false
     */
    @GET("/admin/users/list/{flag}.json")
    Call<List<User>> UsersList(@Path("flag") String flag, @Query("order") String order, @Query("ascending") boolean ascending, @Query("page") int page, @Query("show_emails") boolean showEmails, @QueryMap Map<String, String> auth);

    /**
     * get a list of user actions  required fields- offset,username,filter
     * https://discourse.example.com/user_actions.json
     *
     */
    @GET("/user_actions.json")
    Call<UserActionResponse> getUserActions(@Path("offset") String offset, @Query("username") String username, @Query("filter") boolean filter, @Query("show_emails") boolean showEmails, @QueryMap Map<String, String> auth);
    /**
     *log a user out
     * https://discourse.example.com/admin/users/{id}/log_out
     */
    @FormUrlEncoded
    @POST("/admin/users/{id}/log_out")
    Call<Success> logoutUser(@Path("id") int id, @FieldMap Map<String, String> auth);

    /**
     *lRefresh a users gravatar
     * https://discourse.example.com/user_avatar/{username}/refresh_gravatar.json
     */
    @FormUrlEncoded
    @POST("/user_avatar/{username}/refresh_gravatar.json")
    Call<Success> refreshGravatar(@Path("username") int userName, @FieldMap Map<String, String> auth);

}
