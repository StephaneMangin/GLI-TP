package org.istic.gli.models;


import java.util.List;

/**
 * Created by stephane on 22/09/15.
 */
public class Model implements IModel {

    private String title;

    private List<IItem> items;

    public Model(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<IItem> getItems() {
        return items;
    }

    public void addItem(IItem item) {
        this.items.add((IItem) item);
    }

    public void delItem(IItem item) {
        this.items.remove((Item) item);
    }
}

