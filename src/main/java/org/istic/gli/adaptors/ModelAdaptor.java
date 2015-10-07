package org.istic.gli.adaptors;

import org.istic.gli.interfaces.IItem;
import org.istic.gli.interfaces.IModel;
import org.istic.gli.models.Model;

import java.util.List;
import java.util.Observable;

/**
 * Created by stephane on 22/09/15.
 */
public class ModelAdaptor extends Observable implements IModel {

    private Model model;

    public ModelAdaptor(Model model) {
        this.model  = model;
    }

    @Override
    public String getTitle() {
         return model.getTitle();
    }

    @Override
    public void setTitle(String title) {
        model.setTitle(title);
        notifyObservers();
    }

    @Override
    public List<IItem> getItems() {
        return model.getItems();
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
