package org.istic.gli.views;

import org.istic.gli.interfaces.view.ICamenbert;
import org.istic.gli.interfaces.view.IPortion;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Arc2D;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by smangin on 08/10/15.
 */
public class Portion extends JComponent implements IPortion, Observer {

    private Graphics2D g2d;
    private double value;
    private boolean active;
    private Color color;

    public Portion(double value) {
        setValue(value);
    }

    @Override
    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public double getValue() {
        return value;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void update(Observable o, Object arg) {

    }

    void reconfigure() {
    }
}
