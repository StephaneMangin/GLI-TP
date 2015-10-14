package org.istic.gli.views;

import org.istic.gli.enums.WideType;
import org.istic.gli.interfaces.view.ICamenbert;
import org.istic.gli.interfaces.view.IPortion;

import java.awt.*;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.*;

/**
 * Created by smangin on 08/10/15.
 */
public class Camenbert extends Observable implements ICamenbert {

    private WideType wideType = WideType.Degree;
    private Map<IPortion, Arc2D> portions;
    private double width;
    private double height;
    private Boolean hole = true;
    private IPortion currentPortion;

    public Camenbert(WideType type) {
        this.wideType = type;
        this.portions = new HashMap<>();
    }

    @Override
    public void setWideType(WideType type) {
        this.wideType = type;
    }

    @Override
    public void addPortion(double value) {
        IPortion portion = new Portion(value);
        this.addObserver(portion);
        Arc2D arc = new Arc2D.Double(Arc2D.PIE);
        Color color = new Color(
                (50 * (int) Math.random()) % 255,
                (150 * (int) Math.random()) % 255,
                (200 * (int) Math.random()) % 255);
        portion.setColor(color);
        System.out.println("Add portion " + portion.getValue());
        double ratioX = 3.0;
        double ratioY = 3.0;
        double ratioW = 3.0;
        double ratioH = 3.0;
        arc.setFrame(width / ratioX, height / ratioY, width / ratioW, height / ratioH);
        this.portions.put(portion, arc);
    }

    @Override
    public double getWideness() {
        double total = 0;
        for (IPortion portion: portions.keySet()) {
            total += portion.getValue();
        }
        return total;
    }

    @Override
    public WideType getWideType() {
        return wideType;
    }

    @Override
    public void setSize(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public void setHole(Boolean value) {
        this.hole = value;
    }

    @Override
    public double getNextStartAngle() {
        double next = 0;
        for (IPortion portion: portions.keySet()) {
           next += Math.round(portion.getValue() * wideType.getValue() / getWideness());
        }
        return next;
    }

    @Override
    public IPortion getCurrentPortion() {
        return this.currentPortion;
    }

    @Override
    public void setCurrentPortion(IPortion portion) {
        unselectPortion();
        double ratioX = 4.0;
        double ratioY = 4.0;
        double ratioW = 2.0;
        double ratioH = 2.0;
        portions.get(portion).setFrame(width / ratioX, height / ratioY, width / ratioW, height / ratioH);
        this.currentPortion = portion;
    }

    private void unselectPortion(){
        if (currentPortion != null) {
            double ratioX = 3.0;
            double ratioY = 3.0;
            double ratioW = 3.0;
            double ratioH = 3.0;
            portions.get(currentPortion).setFrame(width / ratioX, height / ratioY, width / ratioW, height / ratioH);
        }
    }

    @Override
    public Set<IPortion> getPortions() {
        return portions.keySet();
    }

    @Override
    public boolean hasPosition(IPortion portion, Point2D point) {
        return portions.get(portion).contains(point);
    }

    public void configure(View view) {
        width = view.getWidth();
        height = view.getHeight();
        for (IPortion portion: portions.keySet()) {
            Arc2D arc = portions.get(portion);
            System.out.println("Configure portion " + portion.getValue());
            double arcAngle = Math.round(portion.getValue() * getWideType().getValue() / getWideness());
            arc.setAngleStart(getNextStartAngle());
            arc.setAngleExtent(arcAngle);
            view.getG2d().setColor(portion.getColor());
            view.getG2d().fill(arc);
        }
        if (this.hole) {
            //Draw a circle to make a hole in the pie
            view.getG2d().setColor(new Color(255, 255, 255));
            Ellipse2D.Double cercle = new Ellipse2D.Double(width/5.0*2.0, height/5.0*2.0, width/5.0, height/5.0);
            view.getG2d().fill(cercle);
            view.getG2d().setColor(new Color(55, 55, 50));
            Ellipse2D.Double cercleData = new Ellipse2D.Double(width/6.0*2.5, height/6.0*2.5, width/6.0, height/6.0);
            view.getG2d().fill(cercleData);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
    }
}
