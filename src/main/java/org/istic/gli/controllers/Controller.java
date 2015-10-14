package org.istic.gli.controllers;

import org.istic.gli.interfaces.controller.IController;
import org.istic.gli.interfaces.model.IItem;
import org.istic.gli.interfaces.model.IModel;

/**
 * Created by stephane on 22/09/15.
 */
public class Controller implements IController {

    private IModel model;
    private IItem currentItem;

    public Controller(IModel model) {
       this.model = model;
    }

    @Override
    public void setCurrentItem(IItem item) {
        this.currentItem = item;
    }

    public IItem getCurrentItem() {
        return currentItem;
    }
}
