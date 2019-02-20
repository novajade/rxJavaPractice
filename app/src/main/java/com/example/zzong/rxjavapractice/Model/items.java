package com.example.zzong.rxjavapractice.Model;

import com.google.gson.annotations.SerializedName;

public class items {

    @SerializedName("image")
    public String image;

    @SerializedName("title")
    public String title;

    @SerializedName("pubDate")
    public String pubdate;

    @SerializedName("director")
    public String director;

    @SerializedName("actor")
    public String actor;

    @SerializedName("userRating")
    public Float userrating;

    public String getImage(){ return image; }

    public String getTitle(){ return title; }

    public String getPubdate() {
        return pubdate;
    }

    public String getDirector(){
        return director;
    }

    public String getActor() {
        return actor;
    }

    public Float getUserrating() {
        return userrating;
    }

}
