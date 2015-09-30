package org.istic.gli.views;

import org.istic.gli.controllers.IController;
import org.istic.gli.models.IItem;
import org.istic.gli.models.ModelAdaptor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JComponent;

public class View extends JComponent implements MouseListener, IView
{

	Graphics2D g2d;	
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
		super.paintComponent(g);
        List<IItem> items = modelAdaptor.getItems();
		drawPie((Graphics2D) g, getBounds(), items);
	}

	void drawPie(Graphics2D g, Rectangle area, List<IItem> items) {
		double total = 0.0D;
		for (IItem item : items) {
			total += item.getValue();
		}
		double curValue = 0.0D;
		int startAngle = 0;
		for (IItem item : items) {
			startAngle = (int) (curValue * 360 / total);
			int arcAngle = (int) (item.getValue() * 360 / total);
			g.setColor(new Color(192, 123, 123));
			g.fillArc(area.x, area.y, area.width, area.height,
					startAngle, arcAngle);
			curValue += item.getValue();
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
    }
}
