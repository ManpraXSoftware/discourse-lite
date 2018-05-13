package com.manprax.discourseplugin.net.common;

import com.manprax.discourseplugin.model.common.NotificationResponse;
import com.manprax.discourseplugin.model.common.SearchResponse;
import com.manprax.discourseplugin.model.topic.TopicResponse;
import com.manprax.discourseplugin.model.user.UserResponse;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by Prateek on 23-11-2017.
 */

public interface DiscourseCommonService {
    /**
     * provider of DiscourseCommonService  for injection
     */
//    class Provider implements com.google.inject.Provider<DiscourseCommonService>{
//       @Inject
//        RetrofitProvider retrofitProvider;
//        @Override
//        public DiscourseCommonService get() {
//            return retrofitProvider.getDiscourseBased().create(DiscourseCommonService.class);
//        }
//    }

    /**
     * Search for something
     * https://discourse.example.com/search/query    //It's not working
     * /search.json?q=hello&include_blurbs=true
     */
    @GET("/search.json")
    Call<SearchResponse> search(@Query("q") String query, @Query("include_blurbs") boolean includeBlurbs, @QueryMap Map<String, String> auth);

    /**
     *Get some notifications
     * https://discourse.example.com/notifications.json
     */
    @GET("/notifications.json")
    Call<NotificationResponse> getNotification(@QueryMap Map<String, String> auth);

    /**
     *Get a list of private messages for a user
     * https://discourse.example.com/topics/private-messages/{username}.json
     */
    @GET("/topics/private-messages/{username}.json")
    Call<TopicResponse> getPrivateMessages(@Path("username") String userName, @QueryMap Map<String, String> auth);

    /**
     *Get a list of private messages sent
     * https://discourse.example.com/topics/private-messages-sent/{username}.json
     */
    @GET("/topics/private-messages-sent/{username}.json")
    Call<TopicResponse> getPrivateMessagesSent(@Path("username") String userName, @QueryMap Map<String, String> auth);

    /**
     * Upload a file like an image or an avatar
     *  files[]
     * type= "avatar" "profile_background" "card_background" "custom_emoji" "composer"
     * user_id=> required if uploading an avatar
     */
    @Multipart
    @POST("/uploads.json")
   Call<UserResponse> getUserByUserName(@Part("files") RequestBody file, @Field("type") String type, @Field("user_id") int userId, @FieldMap Map<String, String> auth);
}
