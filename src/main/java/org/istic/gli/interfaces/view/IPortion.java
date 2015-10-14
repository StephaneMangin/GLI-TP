package org.istic.gli.interfaces.view;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by smangin on 08/10/15.
 */
public interface IPortion extends Observer {

    void setValue(double value);
    double getValue();
    void setColor(Color color);
    Color getColor();
}
