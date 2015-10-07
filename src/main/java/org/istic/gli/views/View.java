package org.istic.gli.views;

import org.istic.gli.controllers.IController;
import org.istic.gli.models.IItem;
import org.istic.gli.models.ModelAdaptor;
import sun.font.DelegatingShape;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.util.*;
import java.util.List;

import javax.swing.JComponent;

public class View extends JComponent implements MouseListener, IView
{

	Graphics g;
	Graphics2D g2;
	ModelAdaptor modelAdaptor;
	IController controller;
	
	String mTexte;
    Collection<Arc2D> sections;
	
	public View(ModelAdaptor im, IController ic) {
		modelAdaptor = im;
		mTexte = modelAdaptor.getTitle();
		controller = ic;
		addMouseListener(this);
	}
	
	public void paint(Graphics g) {
        this.g = g;
		this.g2 = (Graphics2D)g;
        this.sections = new ArrayList<>();
		super.paint(g);
        List<IItem> items = modelAdaptor.getItems();
		drawPie(getBounds(), items);
	}

	void drawPie(Rectangle area, List<IItem> items) {
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
            sections.add(arc);
            g2.setColor(new Color(0, 0, (200 / items.size() * items.indexOf(item)) + 50));
            //Tag position
            drawTag(area, startAngle, arcAngle);
            //Draw the arc with new color:
			g2.fill(arc);

			curValue += item.getValue();
		}
        //Draw a circle to make a hole in the pie
        g2.setColor(new Color(255, 255, 255));
        Ellipse2D.Double cercle = new Ellipse2D.Double(area.x + area.width/5.0*2.0, area.y + area.height/5.0*2.0, area.width/5.0, area.height/5.0);
        g2.fill(cercle);
	}

    private void drawTag(Rectangle area, double startAngle, double arcAngle) {
        double tagX = area.width/2.0 + (area.width/4.0 * Math.sin(Math.toRadians(startAngle+90+(arcAngle/2))));
        double tagY = area.height/2.0 + (area.height/4.0 * Math.cos(Math.toRadians(startAngle+90+(arcAngle/2))));
        //Placing nearest corner at the right position
        Rectangle2D.Double tag = new Rectangle2D.Double(tagX, tagY, area.width/8.0, area.width/10.0);
        g2.fill(tag);
    }

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
		// TODO: vérifier si un quartier de camembert a été selectionné 
		// et renvoyer vers le controlleur
		mTexte = "Mouse at "+arg0.getX()+"x"+arg0.getY();
        System.out.println("mouseClicked");
        repaint();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
        System.out.println("mouseEntered");

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
        System.out.println("mouseExited");

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
        System.out.println("mousePressed");

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
        System.out.println("mouseReleased");

	}


    @Override
    public void update(Observable observable, Object o) {
        // mise à jour du camenbert
        System.out.println("update view");
        repaint();
    }
}
