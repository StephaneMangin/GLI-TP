package org.istic.gli.models.adapters;

import org.istic.gli.models.IItem;
import org.istic.gli.models.IModel;
import org.istic.gli.models.Model;

import java.util.Observable;

/**
 * Created by stephane on 22/09/15.
 */
public class ModelAdaptor extends Observable {

    private Model model;

    ModelAdaptor(Model model) {
        this.model  = model;
    }

    public void addItem(IItem item) {
        model.addItem(item);
    }

    public void delItem(IItem item) {
        model.delItem(item);
    }

}
