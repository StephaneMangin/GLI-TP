package org.istic.gli.views;

import org.istic.gli.controllers.IController;
import org.istic.gli.models.IItem;
import org.istic.gli.models.ModelAdaptor;
import sun.font.DelegatingShape;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JComponent;

public class View extends JComponent implements MouseListener, IView
{

	Graphics g;
	ModelAdaptor modelAdaptor;
	IController controller;
	
	String mTexte;
	
	public View(ModelAdaptor im, IController ic) {
		modelAdaptor = im;
		mTexte = modelAdaptor.getTitle();
		controller = ic;
		addMouseListener(this);
	}
	
	public void paint(Graphics g) {
        this.g = g;
		super.paint(g);
        List<IItem> items = modelAdaptor.getItems();
		drawPie(getBounds(), items);
	}

	void drawPie(Rectangle area, List<IItem> items) {
		double total = 0.0;
		for (IItem item : items) {
			total += item.getValue();
		}
		double curValue = 0.0;
		int startAngle = 0;
		for (IItem item : items) {
			startAngle = (int) Math.round(curValue * 360 / total);
			int arcAngle = (int) Math.round(item.getValue() * 360 / total);
			g.setColor(new Color(0, 0, (200 / items.size() * items.indexOf(item)) + 50));
			g.fillArc(area.x, area.y, area.width, area.height,
                    startAngle, arcAngle);
			curValue += item.getValue();
            g.setColor(new Color(255, 255, 255));
            g.fillOval(area.x + area.width/4, area.y + area.height/4, area.width/2, area.height/2);
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
		// TODO: vérifier si un quartier de camembert a été selectionné 
		// et renvoyer vers le controlleur
		mTexte = "Mouse at "+arg0.getX()+"x"+arg0.getY();
		repaint();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


    @Override
    public void update(Observable observable, Object o) {
        // mise à jour du camenbert
        repaint();
    }
}
