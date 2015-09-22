package org.istic.gli.controllers;

import org.istic.gli.models.IModel;

/**
 * Created by stephane on 22/09/15.
 */
public class Controller implements IController {

    private IModel model;

    public Controller(IModel model) {
       this.model = model;
    }

}
