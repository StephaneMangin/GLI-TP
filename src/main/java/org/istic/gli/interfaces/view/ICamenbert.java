package org.istic.gli.interfaces.view;

import org.istic.gli.enums.WideType;
import org.istic.gli.views.View;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Observer;
import java.util.Set;

/**
 * Created by smangin on 08/10/15.
 */
public interface ICamenbert extends Observer {

    void setWideType(WideType type);
    void addPortion(double value, String title, String content);
    double getWideness();
    WideType getWideType();
    void setSize(double width, double height);
    void setHole(Boolean value);
    IPortion getCurrentPortion();
    void setCurrentPortion(IPortion portion);
    Set<IPortion> getPortions();
    boolean hasPosition(IPortion portion, Point2D point);
    void fillInto(IView view);
}
