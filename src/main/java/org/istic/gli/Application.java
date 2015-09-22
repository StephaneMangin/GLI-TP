package org.istic.gli;

import org.istic.gli.controllers.Controller;
import org.istic.gli.controllers.IController;
import org.istic.gli.models.IModel;
import org.istic.gli.models.Model;
import org.istic.gli.views.CamenbertComponent;

import java.util.logging.Logger;

/**
 * Created by stephane on 22/09/15.
 */
public class Application {

    private static Logger log = Logger.getLogger("Application");

    /**
     * Main method, used to run the application.
     */
    public static void main(String[] args) {
        String title = "Mon camenbert";
        IModel model = new Model(title);
        IController controller = new Controller();
        CamenbertComponent app = new CamenbertComponent(model, controller);
        log.info("Application started");

    }

}
