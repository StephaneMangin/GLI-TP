package org.istic.gli.adaptors;

import org.istic.gli.interfaces.model.IItem;
import org.istic.gli.interfaces.model.IModel;
import org.istic.gli.models.Model;

import java.util.List;
import java.util.Observable;

/**
 * Created by stephane on 22/09/15.
 */
public class ModelAdaptor extends Observable {

    private Model model;
    private IItem currentItem;

    public ModelAdaptor(Model model) {
        this.model  = model;
    }

    public String getTitle() {
         return model.getTitle();
    }

    public void setTitle(String title) {
        model.setTitle(title);
        notifyObservers();
    }

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

    public void setCurrentItem(String title) {
        for(IItem item: model.getItems()) {
            if (item.getTitle() == title) {
                model.setCurrentItem(item);
            }
        }
    }

    public IItem getCurrentItem() {
        return model.getCurrentItem();
    }

}
