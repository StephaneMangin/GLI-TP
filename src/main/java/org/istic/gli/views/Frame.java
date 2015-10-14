package org.istic.gli.views;

import org.istic.gli.interfaces.view.IView;

import javax.swing.*;
import java.awt.*;

/**
 * Created by stephane on 30/09/15.
 */
public class Frame extends JFrame {

    public Frame (String title, IView view) {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add the ubiquitous "Hello World" label.
        JLabel label = new JLabel(title);
        this.getContentPane().add(label);

        // Inject view
        this.getContentPane().add((JComponent) view);

        //Display the window.
        this.setMinimumSize(new Dimension(600, 600));
        this.setSize(600, 600);
        this.pack();
    }

}
