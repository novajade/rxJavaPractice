package com.example.zzong.rxjavapractice.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class MovieData {
    @SerializedName("lastBuildDate")
    public String lastdate;

    @SerializedName("total")
    public Integer total;

    @SerializedName("start")
    public Integer start;

    @SerializedName("display")
    public Integer display;

    @SerializedName("items")
    public List<items> items = new ArrayList<items>();

    public String getLastdate() {
        return lastdate;
    }

    public Integer getTotal() {
        return total;
    }

    public Integer getStart() {
        return start;
    }

    public Integer getDisplay() {
        return display;
    }

    public List<items> getItems() { return items; }

}

