package org.istic.gli.models;


import org.istic.gli.interfaces.model.IItem;
import org.istic.gli.interfaces.model.IModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stephane on 22/09/15.
 */
public class Model implements IModel {
    private String title;

    private List<IItem> items = new ArrayList<>();
    private IItem currentItem;

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
        this.items.add(item);
    }

    public void delItem(IItem item) {
        this.items.remove(item);
    }

    public IItem getCurrentItem() {
        return this.currentItem;
    }

    public void setCurrentItem(IItem item) {
        this.currentItem = item;
    }
}

