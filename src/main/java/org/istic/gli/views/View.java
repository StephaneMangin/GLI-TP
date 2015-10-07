package org.istic.gli.views;

import org.istic.gli.controllers.IController;
import org.istic.gli.models.IItem;
import org.istic.gli.models.ModelAdaptor;
import sun.font.DelegatingShape;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.util.*;
import java.util.List;

import javax.swing.*;

public class View extends JComponent implements IView
{

	Graphics2D g2;
	ModelAdaptor modelAdaptor;
	IController controller;
	
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
                for (Arc2D arc : sections.keySet()) {
                    if (arc.contains(posX, posY)) {
                        // TODO : increase the radius
                        // TODO : Save item in controller
                        // TODO : screen it
                        IItem item = sections.get(arc);
                        repaint();
                    }
                }
            }
        });

	}
	
	public void paint(Graphics g) {
        super.paint(g);
		this.g2 = (Graphics2D)g;
        List<IItem> items = modelAdaptor.getItems();
		drawPie(getBounds(), items);
	}

	void drawPie(Rectangle area, List<IItem> items) {
        // TODO : limit cyclomatic conplexity by splitting method
        sections.clear();
        //Process sum of items
        double total = 0.0;
		for (IItem item : items) {
			total += item.getValue();
		}

		double curValue = 0.0;
		double startAngle = 0;
		for (IItem item : items) {
			startAngle = Math.round(curValue * 360 / total);
			double arcAngle = Math.round(item.getValue() * 360 / total);

			Arc2D.Double arc = new Arc2D.Double(Arc2D.PIE);
			arc.setFrame(area.x + area.width/3.0, area.y+area.height/3.0, area.width/3.0, area.height/3.0);
			arc.setAngleStart(startAngle);
			arc.setAngleExtent(arcAngle);
            sections.put(arc, item);
            Color color = new Color(0, 0, (200 / items.size() * items.indexOf(item)) + 50);
            //Tag position
            drawTag(area, startAngle, arcAngle, area.width / 5.0, area.height / 15.0, item.getTitle(), color);
            //Draw the arc with new color:
            g2.setColor(color);
			g2.fill(arc);
			curValue += item.getValue();
		}
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
        Font font = new Font(" Verdana ",Font.BOLD, (int) height/3);
        g2.setFont(font);
        g2.drawString(title, (float) (tagX + 5), (float) (tagY + height * 0.6));
    }

    @Override
    public void update(Observable observable, Object o) {
        // mise à jour du camenbert
        System.out.println("update view");
        repaint();
    }
}
