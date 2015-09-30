package org.istic.gli;

import org.istic.gli.controllers.Controller;
import org.istic.gli.controllers.IController;
import org.istic.gli.models.*;
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
        Model model = new Model(title);
        IItem item1 = new Item();
        item1.setDesc("test");
        item1.setTitle("test");
        item1.setValue(1.0);
        model.addItem(item1);
        IItem item2 = new Item();
        item2.setDesc("test");
        item2.setTitle("test");
        item2.setValue(2.0);
        model.addItem(item2);
        ModelAdaptor adaptor = new ModelAdaptor(model);
        IController controller = new Controller(model);
        view = new View(adaptor, controller);
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
