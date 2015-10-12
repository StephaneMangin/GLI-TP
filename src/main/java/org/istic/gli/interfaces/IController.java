package org.istic.gli.interfaces;

import java.util.List;

/**
 * Created by stephane on 22/09/15.
 */
public interface IController {
    IItem getCurrentItem();
    void setCurrentItem(IItem item);
    List<IItem> getItems();
}
