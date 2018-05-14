package com.manprax.discourseplugin.net.common;

import com.manprax.discourseplugin.domain.DiscourseConfig;
import com.manprax.discourseplugin.net.DiscourseBaseAPI;


/**
 * Created by Prateek on 23-11-2017.
 */

public class DiscourseCommonAPI extends DiscourseBaseAPI {

    private DiscourseCommonService discourseService;

    public DiscourseCommonAPI(DiscourseConfig config) {
        super(config);
    }

//    public Call<CategoryResponse> getCategories(){
//        return discourseService.getCategories(getAuthentication());
//    }
//
//    public Call<UserResponse> getUserByName(String userName){
//        return discourseService.getUserByUserName(userName,getAuthentication());
//    }

}
