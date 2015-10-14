package org.istic.gli.interfaces.model;

import java.util.List;

/**
 * Created by stephane on 22/09/15.
 */
public interface IModel {

    String getTitle();
    void setTitle(String title);
    List<IItem> getItems();
    void addItem(IItem item);
    void delItem(IItem item);
    void setCurrentItem(IItem item);
    IItem getCurrentItem();
}
