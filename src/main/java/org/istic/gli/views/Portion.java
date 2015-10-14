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
public class Portion extends JComponent implements IPortion {

    private Graphics2D g2d;
    private double value;
    private boolean active;
    private Color color;
    private String title;
    private String content;

    public Portion(double value, String title, String content) {
        setValue(value);
        setContent(title, content);
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
    public void setContent(String title, String content) {
        this.title = title;
        this.content = content;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public Color getColor() {
        return color;
    }

}
