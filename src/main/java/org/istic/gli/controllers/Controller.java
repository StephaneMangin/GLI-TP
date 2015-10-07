package org.istic.gli.controllers;

import org.istic.gli.interfaces.IController;
import org.istic.gli.interfaces.IModel;

/**
 * Created by stephane on 22/09/15.
 */
public class Controller implements IController {

    private IModel model;

    public Controller(IModel model) {
       this.model = model;
    }

}
