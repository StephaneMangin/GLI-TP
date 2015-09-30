package org.istic.gli;

import au.com.bytecode.opencsv.CSVReader;
import org.istic.gli.controllers.*;
import org.istic.gli.models.*;
import org.istic.gli.views.*;
import org.istic.gli.views.Frame;

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
     * Main method, used to run the application.
     */
    public static void main(String[] args) throws Exception {
        Application app = new Application();
        frame = new Frame(title, view);
        log.info("Application started");

    }

}
