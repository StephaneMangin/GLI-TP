package org.istic.gli.models;

import java.util.DoubleSummaryStatistics;

/**
 * Created by lucas on 22/09/15.
 */
public class Item implements IItem {
    private String title;
    private String desc;
    private Double value;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
