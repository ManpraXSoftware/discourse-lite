package com.manprax.discourseplugin.domain;

import android.app.Activity;
import android.content.Intent;

import com.manprax.discourseplugin.discourse_views.DiscourseActivity;

/**
 * Created by mukesh on 20/4/18.
 */

public class Discourse {
    public static final String CONFIG="discourseplugin.config";
    public static void open(Activity activity, DiscourseConfig config){
        Intent intent=new Intent(activity, DiscourseActivity.class);
        intent.putExtra(CONFIG,config);
        activity.startActivity(intent);
    }
}
