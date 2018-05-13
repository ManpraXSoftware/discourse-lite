package com.manprax.discourseplugin.net.tags;


import com.manprax.discourseplugin.domain.DiscourseConfig;
import com.manprax.discourseplugin.model.tags.TagsResponse;
import com.manprax.discourseplugin.net.DiscourseBaseAPI;

import retrofit2.Call;

/**
 * Created by Mukesh on 28-11-2017.
 */

public class TagAPI extends DiscourseBaseAPI {
    TagService tagService;

    public TagAPI(DiscourseConfig config) {
        super(config);
        tagService = getDiscourseRetroClient().create(TagService.class);
    }
    public Call<TagsResponse> getAllTags(){
        return tagService.getTagsList(getAuthentication());
    }
}
