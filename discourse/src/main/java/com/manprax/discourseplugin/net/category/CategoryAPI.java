package com.manprax.discourseplugin.net.category;



import com.manprax.discourseplugin.domain.DiscourseConfig;
import com.manprax.discourseplugin.model.category.CategoryResponse;
import com.manprax.discourseplugin.model.topic.TopicResponse;
import com.manprax.discourseplugin.net.DiscourseBaseAPI;
import com.manprax.discourseplugin.net.DiscourseConstants;

import retrofit2.Call;

/**
 * Created by Mukesh on 28-11-2017.
 */
public class CategoryAPI extends DiscourseBaseAPI {

    private CategoryService categoryService;

    public CategoryAPI(DiscourseConfig config) {
        super(config);
        categoryService = getDiscourseRetroClient().create(CategoryService.class);
    }

    public Call<CategoryResponse> getCategories() {
        return categoryService.getCategories(getAuthentication());
    }
    public Call<TopicResponse> getTopicList(int categoryId, int page){
        return categoryService.getTopicsByCategoryId(String.valueOf(categoryId),getAuthentication(),page);
    }
    public Call<TopicResponse> getTopicListByFilter(int categoryId, String filter, int type, int page){
        return type== DiscourseConstants.FILTER_LIST_TYPE?categoryService.getTopicsByFilter(String.valueOf(categoryId),filter,getAuthentication(),page)
              :categoryService.getTopicsByTagFilter(String.valueOf(categoryId),filter,getAuthentication(),page);
    }
}
