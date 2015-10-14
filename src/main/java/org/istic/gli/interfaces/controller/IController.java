package org.istic.gli.interfaces.controller;

import org.istic.gli.interfaces.model.IItem;

/**
 * Created by stephane on 22/09/15.
 */
public interface IController {


    IItem getCurrentItem();
    void setCurrentItem(IItem item);
}
