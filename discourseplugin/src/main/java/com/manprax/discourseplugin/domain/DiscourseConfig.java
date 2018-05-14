package com.manprax.discourseplugin.domain;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by mukesh on 19/4/18.
 */

public class DiscourseConfig implements Parcelable {
    private String basUrl;
    private String userName;
    private String apiKey;

    public DiscourseConfig() {
    }

    public DiscourseConfig(String basUrl, String userName, String apiKey) {
        this.basUrl = basUrl;
        this.userName = userName;
        this.apiKey = apiKey;
    }

    protected DiscourseConfig(Parcel in) {
        basUrl = in.readString();
        userName = in.readString();
        apiKey = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(basUrl);
        dest.writeString(userName);
        dest.writeString(apiKey);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DiscourseConfig> CREATOR = new Creator<DiscourseConfig>() {
        @Override
        public DiscourseConfig createFromParcel(Parcel in) {
            return new DiscourseConfig(in);
        }

        @Override
        public DiscourseConfig[] newArray(int size) {
            return new DiscourseConfig[size];
        }
    };

    public String getBasUrl() {
        return basUrl;
    }

    public String getUserName() {
        return userName;
    }

    public String getApiKey() {
        return apiKey;
    }

}
