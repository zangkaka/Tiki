package com.zang.tiki.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Rang on 19-Aug-20.
 */
public class QuickLinkDto {
    @SerializedName("data")
    @Expose
    private List<List<QuickLink>> data = null;

    public List<List<QuickLink>> getData() {
        return data;
    }

    public void setData(List<List<QuickLink>> data) {
        this.data = data;
    }
}
