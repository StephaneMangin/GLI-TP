package org.istic.gli.interfaces.view;

import java.awt.*;
import java.util.Observer;

/**
 * Created by stephane on 22/09/15.
 */
public interface IView extends Observer {
    Graphics2D getG2d();
}
