package org.istic.gli.controllers;

import org.istic.gli.adaptors.ModelAdaptor;
import org.istic.gli.interfaces.IController;
import org.istic.gli.interfaces.IItem;
import org.istic.gli.interfaces.IModel;

import java.util.List;
import java.util.Observable;

/**
 * Created by stephane on 22/09/15.
 */
public class Controller extends Observable implements IController {

    private IItem currentItem;
    ModelAdaptor adaptor;

    public Controller(ModelAdaptor adaptor) {
       this.adaptor = adaptor;
    }

    @Override
    public void setCurrentItem(IItem item) {
        this.currentItem = item;
        notifyAll();
    }

    @Override
    public List<IItem> getItems() {
        return adaptor.getItems();
    }

    public IItem getCurrentItem() {
        return currentItem;
    }
}
