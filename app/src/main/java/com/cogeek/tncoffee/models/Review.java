package com.cogeek.tncoffee.models;

import com.cogeek.tncoffee.utils.MyConfig;
import com.cogeek.tncoffee.utils.NumberHelper;
import com.google.gson.annotations.SerializedName;

public class Review {
    @SerializedName("date")
    private String date;

    @SerializedName("name")
    private String name;

    @SerializedName("avatar")
    private String avatar;

    @SerializedName("vote")
    private String vote;

    @SerializedName("head")
    private String head;

    @SerializedName("content")
    private String content;

    public String getDate() {
        return NumberHelper.getInstance().dateFormat(Long.parseLong(date));
    }

    public String getName() {
        return name;
    }

    public String getAvatar() {
        return MyConfig.self().getBASE_URL() + avatar;
    }

    public Float getVote() {
        return Float.parseFloat(vote);
    }

    public String getHead() {
        return head;
    }

    public String getContent() {
        return content;
    }


}
