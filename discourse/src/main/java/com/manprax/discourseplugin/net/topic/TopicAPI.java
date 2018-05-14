package com.manprax.discourseplugin.net.topic;

import com.manprax.discourseplugin.domain.DiscourseConfig;
import com.manprax.discourseplugin.model.topic.Topic;
import com.manprax.discourseplugin.net.DiscourseBaseAPI;


import retrofit2.Call;

/**
 * Created by Mukesh on 27-11-2017.
 */

public class TopicAPI extends DiscourseBaseAPI {
    private TopicService topicService;
    public TopicAPI(DiscourseConfig config) {
        super(config);
        topicService = getDiscourseRetroClient().create(TopicService.class);
    }
    public Call<Topic> getTopicById(int topicId){
        return topicService.getSingleTopic(String.valueOf(topicId),getAuthentication());
    }
}
