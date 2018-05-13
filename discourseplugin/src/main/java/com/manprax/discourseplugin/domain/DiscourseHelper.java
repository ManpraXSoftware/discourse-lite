package com.manprax.discourseplugin.domain;

import android.graphics.Color;

/**
 * Created by mukesh on 23/3/18.
 */

public class DiscourseHelper {
    public static int parseColor(String color) {
        try {
            if (color.length()==3)
                color=color+color;
            if (color.contains("#"))
                return Color.parseColor(color);
            return Color.parseColor(String.valueOf("#" + color));
        }catch (IllegalStateException se){
            return Color.GRAY;
        }
    }
}
