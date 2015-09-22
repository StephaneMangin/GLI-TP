package org.istic.gli.models;

import org.istic.gli.models.IItem;
import org.istic.gli.models.IModel;
import org.istic.gli.models.Model;

import java.util.List;
import java.util.Observable;

/**
 * Created by stephane on 22/09/15.
 */
public class ModelAdaptor extends Observable implements IModel {

    private Model model;

    ModelAdaptor(Model model) {
        this.model  = model;
    }

    public void addItem(IItem item) {
        model.addItem(item);
        notifyObservers();
    }

    public void delItem(IItem item) {
        model.delItem(item);
        notifyObservers();
    }

}