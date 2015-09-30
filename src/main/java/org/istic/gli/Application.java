package org.istic.gli;

import au.com.bytecode.opencsv.CSVReader;
import org.istic.gli.controllers.*;
import org.istic.gli.models.*;
import org.istic.gli.views.*;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.logging.Logger;

/**
 * Created by stephane on 22/09/15.
 */
public class Application {

    private static View view;
    private static JFrame frame;
    private static String title = "Mon camenbert";
    private static Logger log = Logger.getLogger("Application");

    private void createDatas(Model model) throws IOException {
        File csvFile = new File(getClass().getResource("/datas.csv").getFile());
        FileReader content = new FileReader(csvFile);
        CSVReader csvReader = new CSVReader(content, ',', '"', 1);

        String[] line = null;
        while ((line = csvReader.readNext()) != null) {
            Item item = new Item();
            item.setTitle(line[0]);
            item.setDesc(line[1]);
            item.setValue(Double.valueOf(line[2]));
            System.out.println("new item: " +  item.toString());
            model.addItem(item);
        }
    }

    public Application() throws Exception {
        // Set the size of the jframe
        // Manage models and controllers
        Model model = new Model(title);
        createDatas(model);
        ModelAdaptor adaptor = new ModelAdaptor(model);
        IController controller = new Controller(model);
        // And link with view
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
        frame.setMinimumSize(new Dimension(600, 600));
        frame.setMaximumSize(new Dimension(600, Integer.MAX_VALUE));
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Main method, used to run the application.
     */
    public static void main(String[] args) throws Exception {
        Application app = new Application();
        app.createAndShowGUI();
        log.info("Application started");

    }

}
