package org.istic.gli.models;

/**
 * Created by lucas on 22/09/15.
 */
public class Item implements IItem {
    private String title;
    private String desc;
    private Float value;

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

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }
}
