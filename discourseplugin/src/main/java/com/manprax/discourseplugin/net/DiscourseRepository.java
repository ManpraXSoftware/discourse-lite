package com.manprax.discourseplugin.net;

import com.manprax.discourseplugin.domain.DiscourseConfig;
import com.manprax.discourseplugin.net.category.CategoryAPI;
import com.manprax.discourseplugin.net.common.DiscourseCommonAPI;
import com.manprax.discourseplugin.net.invite.InviteAPI;
import com.manprax.discourseplugin.net.post.PostAPI;
import com.manprax.discourseplugin.net.tags.TagAPI;
import com.manprax.discourseplugin.net.topic.TopicAPI;
import com.manprax.discourseplugin.net.user.UserAPI;

/**
 * Created by mukesh on 13/4/18.
 */

/**
 * Repository class of discourse , Works As data Source
 */
public class DiscourseRepository {
    private static DiscourseRepository mRepository;
    private static DiscourseConfig mConfig;

    private CategoryAPI mCategoryApi;
    private DiscourseCommonAPI mCommonApi;
    private InviteAPI mInviteApi;
    private PostAPI mPostApi;
    private TagAPI mTagsApi;
    private TopicAPI mTopicApi;
    private UserAPI mUserApi;

    public DiscourseRepository() {
        this.mCategoryApi= new CategoryAPI(mConfig);
        this.mCommonApi= new DiscourseCommonAPI(mConfig);
        this.mInviteApi= new InviteAPI(mConfig);
        this.mPostApi= new PostAPI(mConfig);
        this.mTagsApi= new TagAPI(mConfig);
        this.mTopicApi= new TopicAPI(mConfig);
        this.mUserApi= new UserAPI(mConfig);
    }

    public static void setConfig(DiscourseConfig mConfig) {
        DiscourseRepository.mConfig = mConfig;
    }

    public static DiscourseRepository get(){
        if (mRepository==null)
            mRepository= new DiscourseRepository();
        return mRepository;
    }

    public CategoryAPI getCategoryApi() {
        return mCategoryApi;
    }

    public DiscourseCommonAPI getCommonApi() {
        return mCommonApi;
    }

    public InviteAPI getInviteApi() {
        return mInviteApi;
    }

    public PostAPI getPostApi() {
        return mPostApi;
    }

    public TagAPI getTagsApi() {
        return mTagsApi;
    }

    public TopicAPI getTopicApi() {
        return mTopicApi;
    }

    public UserAPI getUserApi() {
        return mUserApi;
    }

    public static DiscourseConfig getConfig() {
        return mConfig;
    }
}
