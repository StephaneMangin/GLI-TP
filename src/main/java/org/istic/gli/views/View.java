package org.istic.gli.views;

import org.istic.gli.enums.WideType;
import org.istic.gli.interfaces.view.ICamenbert;
import org.istic.gli.interfaces.controller.IController;
import org.istic.gli.interfaces.model.IItem;
import org.istic.gli.interfaces.view.IPortion;
import org.istic.gli.interfaces.view.IView;
import org.istic.gli.adaptors.ModelAdaptor;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;

import javax.swing.*;

public class View extends JComponent implements IView
{

	private ModelAdaptor modelAdaptor;
    private ICamenbert camenbert;
    private Graphics2D g2d;

    public View(ModelAdaptor im, IController ic) {
        super();
		modelAdaptor = im;
        camenbert = new Camenbert(WideType.Degree, 2.0, 1.9);
        for (IItem item: modelAdaptor.getItems()) {
            camenbert.addPortion(item.getValue(), item.getTitle(), item.getDesc());
        }
        camenbert.setHole(true);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                System.out.println("mouseClicked");
                for (IPortion portion : camenbert.getPortions()) {
                    if (camenbert.hasPosition(portion, mouseEvent.getPoint())) {
                        // TODO : increase the radius
                        // TODO : Save item in controller
                        // TODO : screen it
                        camenbert.setCurrentPortion(portion);
                        modelAdaptor.setCurrentItem(portion.getTitle());
                        repaint();
                    }
                }
            }
        });
	}

    @Override
	public void paint(Graphics g) {
        super.paint(g);
        this.g2d = (Graphics2D)g;
        this.g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, // Anti-alias!
                RenderingHints.VALUE_ANTIALIAS_ON);
        camenbert.fillInto(this);
	}

    @Override
    public void update(Observable observable, Object o) {
        System.out.println("update view");
        repaint();
    }

    @Override
    public Graphics2D getG2d() {
        return g2d;
    }

    @Override
    public int getWidth() {
        return super.getWidth();
    }

    @Override
    public int getHeight() {
        return super.getHeight();
    }
}
