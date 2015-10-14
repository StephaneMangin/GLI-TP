package org.istic.gli.views;

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

    private JFrame frame;
	private Graphics2D g2;
	private ModelAdaptor modelAdaptor;
	private IController controller;
    private double currentTotal;

    Map<Arc2D, IItem> sections;
	
	public View(ModelAdaptor im, IController ic, int width, int height) {
		modelAdaptor = im;
		controller = ic;
        sections = new HashMap<>();
        currentTotal = 0;
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
                        controller.setCurrentItem(item);
                        repaint();
                    }
                }
            }
        });
        frame = new Frame(modelAdaptor.getTitle(), this);
        frame.setSize(width, height);
        frame.setVisible(true);
        frame.setResizable(false);
	}
	
	public void paint(Graphics g) {
        super.paint(g);
		this.g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, // Anti-alias!
                RenderingHints.VALUE_ANTIALIAS_ON);
        List<IItem> items = modelAdaptor.getItems();
		drawPie(items);
        drawHole(5.0, 1.3);
        drawTitle(8.0);
	}

	private void drawPie(List<IItem> items) {
        // TODO : limit cyclomatic conplexity by splitting method
        sections.clear();
        //Process sum of items
        int total = 0;
        for (IItem item : items) {
            total += item.getValue();
        }
        this.currentTotal = total;
		double curValue = 0.0;
        double width = getBounds().getWidth();
        double height = getBounds().getHeight();
        for (IItem item : items) {
            double startAngle = (curValue * 360 / total) - 1;
            double arcAngle = (item.getValue() * 360 / total) - 1;
            Arc2D.Double arc = new Arc2D.Double(Arc2D.PIE);
            Color color = new Color(
                    (50 * items.size() * items.indexOf(item)) % 255,
                    (150 * items.size() * items.indexOf(item)) % 255,
                    (200 * items.size() * items.indexOf(item)) % 255);
            double currentWidth = width / 2.5;
            double currentHeight = height / 2.5;
            List<Double> position = getTagPosition(startAngle, arcAngle, width / 4, height / 20);
            drawTag(position, width / 4, height / 20, item.getTitle(), color);
            // If selected, increase slightly the radius
            if (item.equals(controller.getCurrentItem())) {
                currentWidth = width / 2.3;
                currentHeight = height / 2.3;
                //Tag position
                position.set(1, position.get(1) + height / 21);
                drawTag(position, width / 4, height / 20, item.getDesc(), color);
            }
            double x = (width - currentWidth) / 2;
            double y = (height - currentHeight) / 2;
            arc.setFrame(x, y, currentWidth, currentHeight);
            arc.setAngleStart(startAngle);
            arc.setAngleExtent(arcAngle);
            sections.put(arc, item);

            //Draw the arc with new color:
            g2.setColor(color);
            g2.fill(arc);
            curValue += item.getValue();
        }

	}

    private void drawTitle(double proportion) {
        double width = getBounds().getWidth();
        double height = getBounds().getHeight();
        double propWidth = width/proportion;
        g2.setColor(new Color(255, 255, 255));
        Font font = new Font(" Verdana ",Font.BOLD, 9);
        g2.setFont(font);
        g2.drawString(this.modelAdaptor.getTitle(), (float)(width - propWidth) / 2, (float)(height / 2) - 11);
        g2.drawString(Double.toString(this.currentTotal) + " €", (float)(width - propWidth) / 2, (float)(height / 2)  + 11);
    }

    private void drawHole(double proportion, double inside) {
        double width = getBounds().getWidth();
        double height = getBounds().getHeight();
        double propWidth = width/proportion;
        double propHeight = height/proportion;
        //Draw a circle to make a hole in the pie

        g2.setColor(new Color(255, 255, 255));
        Ellipse2D.Double cercle = new Ellipse2D.Double((width - propWidth) / 2, (height - propHeight) / 2, propWidth, propHeight);
        g2.fill(cercle);
        if (inside != 0.0) {
            propWidth = width/proportion/inside;
            propHeight = height/proportion/inside;
            g2.setColor(new Color(55, 55, 50));
            Ellipse2D.Double cercleData = new Ellipse2D.Double((width - propWidth) / 2, (height - propHeight) / 2, propWidth, propHeight);
            g2.fill(cercleData);
        }

    }

    private List<Double> getTagPosition(double startAngle, double arcAngle, double width, double height) {
        List<Double> result = new ArrayList<>();
        Rectangle area = getBounds();
        double tagX = area.width/2.0 + (area.width/4.0 * Math.sin(Math.toRadians(startAngle+90+(arcAngle/2))));
        double tagY = area.height/2.0 + (area.height/4.0 * Math.cos(Math.toRadians(startAngle+90+(arcAngle/2))));
        //Placing nearest corner at the right position
        if(tagX > area.width/2.0 && tagY < area.height/2.0) {
            tagY = tagY - height;
        } else if (tagX < area.width/2.0 && tagY > area.height/2.0) {
            tagX = tagX - width;
        } else if (tagX < area.width/2.0 && tagY < area.height/2.0) {
            tagX = tagX - width;
            tagY = tagY - height;
        }
        result.add(tagX);
        result.add(tagY);
        return result;
    }

    private void drawTag(List<Double> position, double width, double height, String title, Color color) {
        double tagX = position.get(0);
        double tagY = position.get(1);
        Rectangle2D.Double tag = new Rectangle2D.Double();
        tag.setFrame(tagX, tagY, width, height);
        g2.setColor(color);
        g2.fill(tag);
        g2.setColor(new Color(255, 255, 255));
        Font font = new Font(" Verdana ",Font.BOLD, 9);
        g2.setFont(font);
        g2.drawString(String.format(title), (float) (tagX + 1), (float) (tagY + height * 0.6));
    }

    @Override
    public void update(Observable observable, Object o) {
        // mise à jour du camenbert
        System.out.println("update view");
        repaint();
    }
}
