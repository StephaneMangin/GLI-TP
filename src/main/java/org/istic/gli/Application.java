package org.istic.gli;

import org.istic.gli.controllers.Controller;
import org.istic.gli.controllers.IController;
import org.istic.gli.models.IModel;
import org.istic.gli.models.Model;
import org.istic.gli.views.IView;
import org.istic.gli.views.View;

import javax.swing.*;
import java.util.logging.Logger;

/**
 * Created by stephane on 22/09/15.
 */
public class Application {

    private static View view;
    private static JFrame frame;
    private static String title = "Mon camenbert";
    private static Logger log = Logger.getLogger("Application");

    public Application() {
        IModel model = new Model(title);
        IController controller = new Controller(model);
        view = new View(model, controller);
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add the ubiquitous "Hello World" label.
        JLabel label = new JLabel(title);
        frame.getContentPane().add(label);

        // Inject view
        frame.getContentPane().add(view);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Main method, used to run the application.
     */
    public static void main(String[] args) {
        Application app = new Application();
        app.createAndShowGUI();
        log.info("Application started");

    }

}
