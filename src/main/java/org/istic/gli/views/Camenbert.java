package org.istic.gli.views;

import org.istic.gli.enums.WideType;
import org.istic.gli.interfaces.view.ICamenbert;
import org.istic.gli.interfaces.view.IPortion;
import org.istic.gli.interfaces.view.IView;

import java.awt.*;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.*;
import java.util.List;

/**
 * Created by smangin on 08/10/15.
 */
public class Camenbert implements ICamenbert {

    private WideType wideType = WideType.Degree;
    private Map<IPortion, Arc2D> portions;
    private double width;
    private double height;
    private Boolean hole = true;
    private IPortion currentPortion;
    private double currentStartAngle = 0;
    private double ratioMin = 2.0;
    private double ratioMax = 1.8;

    public Camenbert(WideType type, double ratioMin, double ratioMax) {
        this.wideType = type;
        this.portions = new HashMap<>();
        this.ratioMin = ratioMin;
        this.ratioMax = ratioMax;
    }

    @Override
    public void setWideType(WideType type) {
        this.wideType = type;
    }

    @Override
    public void addPortion(double value, String title, String content) {
        IPortion portion = new Portion(value, title, content);
        Arc2D arc = new Arc2D.Double(Arc2D.PIE);
        Color color = new Color(
                (portions.size() * 200) % 255,
                (portions.size() * 150) % 255,
                (portions.size() * 100) % 255);
        portion.setColor(color);
        System.out.println("Add new portion " + portion.getValue());
        this.portions.put(portion, arc);
        System.out.println("New total " + getWideness());
    }

    @Override
    public double getWideness() {
        double total = 0;
        for (IPortion portion: portions.keySet()) {
            total += portion.getValue();
        }
        return total;
    }

    @Override
    public WideType getWideType() {
        return wideType;
    }

    @Override
    public void setSize(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public void setHole(Boolean value) {
        this.hole = value;
    }

    @Override
    public IPortion getCurrentPortion() {
        return this.currentPortion;
    }

    @Override
    public void setCurrentPortion(IPortion portion) {
        this.currentPortion = portion;
    }

    @Override
    public Set<IPortion> getPortions() {
        return portions.keySet();
    }

    @Override
    public boolean hasPosition(IPortion portion, Point2D point) {
        return portions.get(portion).contains(point);
    }

    public void fillInto(IView view) {
        currentStartAngle = 0;
        width = view.getWidth();
        height = view.getHeight();
        for (IPortion portion: portions.keySet()) {
            drawArc(portion, view.getG2d());
        }
        if (this.hole) {
            //Draw a circle to make a hole in the pie
            drawHole(view.getG2d());
        }
    }

    private List<Double> getTagPosition(double startAngle, double arcAngle) {
        List<Double> result = new ArrayList<>();
        double tagX = width/ratioMin + (width/(ratioMin*2) * Math.sin(Math.toRadians(startAngle+90+(arcAngle/2))));
        double tagY = height/ratioMin + (height/(ratioMin*2) * Math.cos(Math.toRadians(startAngle+90+(arcAngle/2))));
        //Placing nearest corner at the right position
        if(tagX > width/ratioMin && tagY < height/ratioMin) {
            tagY = tagY - width / ratioMin;
        } else if (tagX < width/ratioMin && tagY > height/ratioMin) {
            tagX = tagX - width / ratioMin;
        } else if (tagX < width/ratioMin && tagY < height/ratioMin) {
            tagX = tagX - width / ratioMin;
            tagY = tagY - height / ratioMin;
        }
        result.add(tagX);
        result.add(tagY);
        return result;
    }

    private Arc2D drawArc(IPortion portion, Graphics2D view) {
        Arc2D arc = portions.get(portion);
        System.out.println("Configure portion " + portion.getValue());
        double arcAngle = Math.round(portion.getValue() * getWideType().getValue() / getWideness());
        double ratio = ratioMin;
        if (portion == currentPortion) {
            ratio = ratioMax;
        }
        arc.setFrame(
                (width - width / ratio) / 2,
                (height - height / ratio) / 2,
                width / ratio,
                height / ratio
        );
        arc.setAngleExtent(arcAngle);
        arc.setAngleStart(currentStartAngle);
        currentStartAngle += arcAngle;
        view.setColor(portion.getColor());
        view.fill(arc);
        drawTag(portion, view, arc);
        if (portion == currentPortion) {
            drawTagContent(portion, view, arc);
        }
        return arc;
    }

    private void drawTag(IPortion portion, Graphics2D view, Arc2D arc) {
        List<Double> position = getTagPosition(arc.getAngleStart(), arc.getAngleExtent());
        double tagX = position.get(0);
        double tagY = position.get(1);
        Rectangle2D.Double tag = new Rectangle2D.Double();
        tag.setFrame(tagX, tagY, width / 5 / ratioMin, height / 9 / ratioMin);
        view.setColor(portion.getColor());
        view.fill(tag);
        view.setColor(new Color(255, 255, 255));
        Font font = new Font(" Verdana ",Font.BOLD, 9);
        view.setFont(font);
        view.drawString(portion.getTitle() + " " + Double.toString(portion.getValue()) + " €", (float) tagX, (float) tagY);
    }

    private void drawTagContent(IPortion portion, Graphics2D view, Arc2D arc) {
        List<Double> position = getTagPosition(arc.getAngleStart(), arc.getAngleExtent());
        double tagX = position.get(0);
        double tagY = position.get(1);
        Rectangle2D.Double tag = new Rectangle2D.Double();
        tag.setFrame(tagX, tagY, width / 5 / ratioMin, height / 8 / ratioMin);
        view.setColor(portion.getColor());
        view.fill(tag);
        view.setColor(new Color(255, 255, 255));
        Font font = new Font(" Verdana ",Font.BOLD, 9);
        view.setFont(font);
        view.drawString(String.format(portion.getContent()), (float)tagX, (float)tagY);
    }

    private void drawHole(Graphics2D view) {
        view.setColor(new Color(255, 255, 255));
        Ellipse2D.Double cercle = new Ellipse2D.Double(width/5.0*2.0, height/5.0*2.0, width/5.0, height/5.0);
        view.fill(cercle);
        view.setColor(new Color(55, 55, 50));
        Ellipse2D.Double cercleData = new Ellipse2D.Double(width/6.0*2.5, height/6.0*2.5, width/6.0, height/6.0);
        view.fill(cercleData);
        float propWidth = (float)((width / ratioMin) - (width / 16));
        float propHeight = (float)((height / ratioMin) - (height / 80));
        view.setColor(new Color(255, 255, 255));
        Font font = new Font(" Verdana ",Font.BOLD, (int)(width / height) * 8);
        view.setFont(font);
        view.drawString("Mon camenbert", propWidth, propHeight);
        view.drawString(Double.toString(getWideness()) + " €", propWidth, propHeight + 11);
    }

    @Override
    public void update(Observable o, Object arg) {
    }
}
