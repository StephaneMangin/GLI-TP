package org.istic.gli.views;

import org.istic.gli.enums.WideType;
import org.istic.gli.interfaces.ICamenbert;
import org.istic.gli.interfaces.IController;
import org.istic.gli.interfaces.IItem;
import org.istic.gli.interfaces.IView;
import org.istic.gli.adaptors.ModelAdaptor;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.*;
import java.util.List;

import javax.swing.*;

public class View extends JComponent implements IView
{

	Graphics2D g2;
	ModelAdaptor modelAdaptor;
	IController controller;
    ICamenbert camenbert;
    Rectangle area;
	
    Map<Arc2D, IItem> sections;
	
	public View(ModelAdaptor im, IController ic) {
		modelAdaptor = im;
		controller = ic;
        this.sections = new HashMap<>();
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                System.out.println("mouseClicked");
                int posX = mouseEvent.getX();
                int posY = mouseEvent.getY();
                for (Portion portion: camenbert.getPortions()) {
                    if (portion.contains(posX, posY)) {
                        // TODO : increase the radius
                        // TODO : Save item in controller
                        // TODO : screen it
                        camenbert.setCurrentPortion(portion);
                        repaint();
                    }
                }
            }
        });

	}
	
	public void paint(Graphics g) {
        super.paint(g);
        area = getBounds();
		this.g2 = (Graphics2D)g;
        camenbert = new Camenbert(area, g2, WideType.Degree);
        camenbert.setSize(area.x + area.width, area.y + area.height);
        List<IItem> items = modelAdaptor.getItems();
	}

    @Override
    public void update(Observable observable, Object o) {
        // mise à jour du camenbert
        System.out.println("update view");
        repaint();
    }
}
