package org.istic.gli.models;

import java.util.List;

/**
 * Created by stephane on 22/09/15.
 */
public interface IModel {

    List<IItem> getItems();
    void addItem(IItem item);
    void delItem(IItem item);
}
