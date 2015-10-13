package org.istic.gli.views;

import org.istic.gli.enums.WideType;
import org.istic.gli.interfaces.ICamenbert;
import org.istic.gli.interfaces.IPortion;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.util.List;
import java.util.Observable;

/**
 * Created by smangin on 08/10/15.
 */
public class Camenbert extends Observable implements ICamenbert {

    private Rectangle surface;
    private Graphics2D g2d;
    private WideType widenessType = WideType.Degree;
    private List<IPortion> portions;
    private double width;
    private double height;
    private Boolean hole = true;

    public Camenbert(Rectangle surface, Graphics2D g, WideType type) {
        this.surface = surface;
        this.g2d = g;
        this.widenessType = type;
    }

    @Override
    public void setWidenessType(WideType type) {
        this.widenessType = type;
    }

    @Override
    public void addPortion(double value) {
        IPortion portion = new Portion(this, value);
        this.addObserver(portion);
        Color color = new Color(
                (50 * (int) Math.random()) % 255,
                (150 * (int) Math.random()) % 255,
                (200 * (int) Math.random()) % 255);
        portion.setColor(color);
        this.portions.add(portion);

        //Tag position
        //drawTag(area, startAngle, arcAngle, area.width / ratioWT, area.height / ratioHT, item.getTitle(), color);

        //Draw the arc with new color:
    }

    @Override
    public double getWideness() {
        return this.widenessType.getValue();
    }

    @Override
    public WideType getWidenessType() {
        return widenessType;
    }

    @Override
    public void setSize(double width, double height) {
        this.width = width;
        this.height = height;
    }

    private void drawHole() {
        //Draw a circle to make a hole in the pie
        g2d.setColor(new Color(255, 255, 255));
        Ellipse2D.Double cercle = new Ellipse2D.Double(surface.x + surface.width/5.0*2.0, surface.y + surface.height/5.0*2.0, surface.width/5.0, surface.height/5.0);
        g2d.fill(cercle);
        g2d.setColor(new Color(55, 55, 50));
        Ellipse2D.Double cercleData = new Ellipse2D.Double(surface.x + surface.width/6.0*2.5, surface.y + surface.height/6.0*2.5, surface.width/6.0, surface.height/6.0);
        g2d.fill(cercleData);
    }

    @Override
    public void setHole(Boolean value) {
        this.hole = value;
    }

    public void reconfigure() {
        if (this.hole) {
            drawHole();
        }
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
