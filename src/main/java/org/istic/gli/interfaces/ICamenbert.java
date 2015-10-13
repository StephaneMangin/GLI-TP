package org.istic.gli.interfaces;

import org.istic.gli.enums.WideType;
import org.istic.gli.views.Portion;

import javax.swing.*;
import java.util.Observer;

/**
 * Created by smangin on 08/10/15.
 */
public interface ICamenbert extends Observer {

    void setWidenessType(WideType type);
    void addPortion(double value);
    double getWideness();
    WideType getWidenessType();
    void setSize(double width, double height);
    void setHole(Boolean value);
    double getNextStartAngle();
    IPortion getCurrentPortion();
    void setCurrentPortion(IPortion portion);
}
