package dsw.gerumap.app.repository.elements;

import dsw.gerumap.app.observer.NotificationType;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class TermElement extends MindMapElement {
    private Point2D.Float position;
    private Dimension size;
    private int fillColorHex;

    public TermElement() {
        super();
        position = new Point2D.Float(0, 0);
        size = new Dimension(100, 50);
        setFillColor(new Color(118, 226, 248));
    }

    public TermElement(int stroke, Color strokeColor, Point2D.Float position, Dimension size, Color fillColor) {
        super(stroke, strokeColor);
        this.position = position;
        this.size = size;
        setFillColor(fillColor);
    }

    public void addLink(LinkElement link) {
        if (link.getTermElement1() == null) {

        }
    }

    public Point2D.Float getPosition() {
        return position;
    }

    public void setPosition(Point2D.Float position) {
        this.position = position;
    }

    public Dimension getSize() {
        return size;
    }

    public void setSize(Dimension size) {
        this.size = size;
    }

    public Color getFillColor() {
        return new Color(fillColorHex);
    }

    public void setFillColor(Color fillColor) {
        this.fillColorHex = fillColor.getRGB();
    }

    @Override
    public void update(NotificationType notificationType, Object object) {

    }
}