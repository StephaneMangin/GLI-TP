package org.istic.gli.interfaces;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by smangin on 08/10/15.
 */
public interface IPortion extends Observer {

    void setValue(double value);
    double getValue();
    void setActive();
    void setInactive();
    void setColor(Color color);

}
