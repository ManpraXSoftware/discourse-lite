package com.manprax.discourseplugin.net.post;


import com.manprax.discourseplugin.domain.DiscourseConfig;
import com.manprax.discourseplugin.model.post.Post;
import com.manprax.discourseplugin.net.DiscourseBaseAPI;

import java.util.HashMap;

import retrofit2.Call;

/**
 * Created by Mukesh on 28-11-2017.
 */

public class PostAPI extends DiscourseBaseAPI {

    PostService service;

    public PostAPI(DiscourseConfig config) {
        super(config);
        service = getDiscourseRetroClient().create(PostService.class);

    }
    public Call<Post> createPost(int topicId, String rawPost){
        HashMap<String,String> postParam= new HashMap<>();
        postParam.put("topic_id", String.valueOf(topicId));
        postParam.put("raw",rawPost);
        return service.createPost(postParam,getAuthentication());
    }
    public Call<Post> createTopic(int categoryId, String title, String raw){
        HashMap<String,String> postParam= new HashMap<>();
        postParam.put("category", String.valueOf(categoryId));
        postParam.put("title",title);
        postParam.put("raw",raw);
        return service.createPost(postParam,getAuthentication());
    }
    public Call<Post> postActions(int postId,int actionType,boolean isFlag){
//        HashMap<String,String> postParam= new HashMap<>();
//        postParam.put("id",String.valueOf(postId));
//        postParam.put("post_action_type_id","2");
        return service.doPostActions(postId,actionType,isFlag,getAuthentication());
    }
    public Call<Post> unlikePost(int postId){
        return service.unlikePost(postId,2,getAuthentication());
    }


}

