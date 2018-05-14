package com.manprax.discourseplugin.net.category;


import com.manprax.discourseplugin.model.category.CategoryResponse;
import com.manprax.discourseplugin.model.category.SingleCategoryResponse;
import com.manprax.discourseplugin.model.topic.TopicResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by Prateek on 28-11-2017.
 */

public interface CategoryService {


    /**
     * Get categories
     * @return  CategoryResponse have list of categories
     */
    @GET("/categories.json")
    Call<CategoryResponse> getCategories(@QueryMap Map<String, String> auth);

    /**
     * Create a new category  Note- Only create category when can_create_category=true in category list
     */
    @FormUrlEncoded
    @POST("/categories.json")
    Call<SingleCategoryResponse> createCategory(@Field("name") String name, @Field("color") String color, @Field("text_color") String textColor, @FieldMap Map<String, String> auth);
    /**
     * Get a list of topics in the specified category
     * @return  TopicResponse have list of topics
     */
    @GET("/c/{id}.json")
    Call<TopicResponse> getTopicsByCategoryId(@Path("id") String categoryId, @QueryMap Map<String, String> auth, @Query("page") int page);

    /**
     * Get a list of topics in the specified category with some filter
     * @return  TopicResponse have list of topics
     */
    @GET("/c/{id}/l/{filter}.json")
    Call<TopicResponse> getTopicsByFilter(@Path("id") String categoryId, @Path("filter") String filter, @QueryMap Map<String, String> auth, @Query("page") int page);
    /**
     * Get a list of topics in the specified category with some tag filter
     * @return  TopicResponse have list of topics
     */
    @GET("/tags/c/{id}/{filter}.json")
    Call<TopicResponse> getTopicsByTagFilter(@Path("id") String categoryId, @Path("filter") String filter, @QueryMap Map<String, String> auth, @Query("page") int page);



}
