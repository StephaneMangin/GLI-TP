package org.istic.gli.views;

import org.istic.gli.controllers.Controller;
import org.istic.gli.interfaces.IController;
import org.istic.gli.interfaces.IItem;
import org.istic.gli.interfaces.IView;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.*;
import java.util.List;

import javax.swing.*;

public class View extends JComponent implements IView {

	Graphics2D g2;
	Controller controller;
    Rectangle area;
	
    Map<Arc2D, IItem> sections;
    List<IItem> items;

	public View(Controller ic) {
		controller = ic;
        controller.addObserver(this);
        this.sections = new HashMap<>();
        this.items = controller.getItems(); // First initialization, next by update
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                System.out.println("mouseClicked");
                int posX = mouseEvent.getX();
                int posY = mouseEvent.getY();
                boolean found = false;
                for (Arc2D arc : sections.keySet()) {
                    if (arc.contains(posX, posY)) {
                        found = true;
                        // TODO : increase the radius
                        // TODO : Save item in controller
                        // TODO : screen it
                        IItem item = sections.get(arc);
                        controller.setCurrentItem(item);
                        break;
                    }
                }
                if (!found) {
                    controller.setCurrentItem(null);
                }
            }
        });
	}
	
	public void paint(Graphics g) {
        super.paint(g);
        area = getBounds();
		this.g2 = (Graphics2D)g;
        drawPie();
        drawHole();
	}

	private void drawPie() {
        // TODO : limit cyclomatic conplexity by splitting method
        sections.clear();
        int total = getTotal();
		double nextStartAngle = 0.0;
        for (IItem item : items) {
            double startAngle = Math.round(nextStartAngle * 360 / total);
            double arcAngle = Math.round(item.getValue() * 360 / total);
            Arc2D.Double arc = new Arc2D.Double(Arc2D.PIE);
            Color color = new Color(
                    (50 * items.size() * items.indexOf(item)) % 255,
                    (150 * items.size() * items.indexOf(item)) % 255,
                    (200 * items.size() * items.indexOf(item)) % 255);
            double ratioX = 3.0;
            double ratioY = 3.0;
            double ratioW = 3.0;
            double ratioH = 3.0;
            double ratioWT = 5.0;
            double ratioHT = 15.0;
            if (item.equals(controller.getCurrentItem())) {
                ratioX = 3.4;
                ratioY = 3.5;
                ratioW = 2.5;
                ratioH = 2.34;
                ratioWT = 5.0;
                ratioHT = 15.0;
            }
            arc.setFrame(area.x + area.width / ratioX, area.y + area.height / ratioY, area.width / ratioW, area.height / ratioH);
            arc.setAngleStart(startAngle);
            arc.setAngleExtent(arcAngle);

            //Tag position
            drawTag(area, startAngle, arcAngle, area.width / ratioWT, area.height / ratioHT, item.getTitle(), color);

            //Draw the arc with new color:
            g2.setColor(color);
            g2.fill(arc);
            sections.put(arc, item); // Keep an eye on association to allow mouse event listening
            nextStartAngle += item.getValue();
        }

	}

    private int getTotal() {
        int total = 0;
        for (IItem item : items) {
            total += item.getValue();
        }
        return total;
    }

    private void drawHole() {
        //Draw a circle to make a hole in the pie
        g2.setColor(new Color(255, 255, 255));
        Ellipse2D.Double cercle = new Ellipse2D.Double(area.x + area.width/5.0*2.0, area.y + area.height/5.0*2.0, area.width/5.0, area.height/5.0);
        g2.fill(cercle);
        g2.setColor(new Color(55, 55, 50));
        Ellipse2D.Double cercleData = new Ellipse2D.Double(area.x + area.width/6.0*2.5, area.y + area.height/6.0*2.5, area.width/6.0, area.height/6.0);
        g2.fill(cercleData);
    }

    private void drawTag(Rectangle area, double startAngle, double arcAngle, double width, double height, String title, Color color) {
        double tagX = area.width/2.0 + (area.width/5.0 * Math.sin(Math.toRadians(startAngle+90+(arcAngle/2))));
        double tagY = area.height/2.0 + (area.height/5.0 * Math.cos(Math.toRadians(startAngle+90+(arcAngle/2))));
        //Placing nearest corner at the right position
        if(tagX > area.width/2.0 && tagY < area.height/2.0) {
            tagY = tagY - height;
        } else if (tagX < area.width/2.0 && tagY > area.height/2.0) {
            tagX = tagX - width;
        } else if (tagX < area.width/2.0 && tagY < area.height/2.0) {
            tagX = tagX - width;
            tagY = tagY - height;
        }

        Rectangle2D.Double tag = new Rectangle2D.Double(tagX, tagY, width, height);
        g2.setColor(color);
        g2.fill(tag);
        g2.setColor(new Color(255, 255, 255));
        Font font = new Font(" Verdana ",Font.BOLD, (int) height/5);
        g2.setFont(font);
        g2.drawString(title, (float) (tagX + 5), (float) (tagY + height * 0.6));
    }

    @Override
    public void update(Observable observable, Object o) {
        // mise à jour du camenbert
        System.out.println("update view");
        if (observable instanceof IController) {
            items = ((IController) observable).getItems();
        }
        repaint();
    }
}
