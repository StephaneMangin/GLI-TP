package org.istic.gli.views;

import org.istic.gli.interfaces.ICamenbert;
import org.istic.gli.interfaces.IItem;
import org.istic.gli.interfaces.IPortion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Arc2D;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by smangin on 08/10/15.
 */
public class Portion extends JComponent implements IPortion, Observer {

    private Graphics2D g2d;
    private Arc2D arc = new Arc2D.Double(Arc2D.PIE);
    private double value;
    private boolean active;
    private ICamenbert camenbert;

    public Portion(ICamenbert camenbert, double value) {
        this.camenbert = camenbert;
        this.value = value;
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
    public void setActive() {
        this.active = true;
        reconfigure();
    }

    @Override
    public void setInactive() {
        this.active = false;
        reconfigure();
    }

    @Override
    public void setColor(Color color) {

    }

    @Override
    public double getWideAngle() {
        return Math.round(value * camenbert.getWidenessType().getValue() / camenbert.getWideness());
    }

    @Override
    public void update(Observable o, Object arg) {

    }

    void reconfigure() {
        double wideNess = camenbert.getWidenessType().getValue();
        double startAngle = camenbert.getNextStartAngle();

        double ratioX = 3.0;
        double ratioY = 3.0;
        double ratioW = 3.0;
        double ratioH = 3.0;
        double ratioWT = 5.0;
        double ratioHT = 15.0;
        if (this.equals(camenbert.getCurrentPortion())) {
            ratioX = 4.0;
            ratioY = 4.0;
            ratioW = 2.0;
            ratioH = 2.0;
            ratioWT = 5.0;
            ratioHT = 15.0;
        }
        arc.setFrame(area.x + area.width / ratioX, area.y + area.height / ratioY, area.width / ratioW, area.height / ratioH);
        arc.setAngleStart(startAngle);
        arc.setAngleExtent(getWideAngle());
    }
}
