package dsw.gerumap.app.repository.elements;

import dsw.gerumap.app.observer.NotificationType;

import java.awt.*;
import java.awt.geom.Point2D;

public class TermElement extends MindMapElement {
    private Point2D.Float position;
    private Dimension size;
    private Color fillColor;

    public TermElement() {
        super();
        position = new Point2D.Float(0, 0);
        size = new Dimension(100, 50);
        fillColor = new Color(118, 226, 248);
    }

    public TermElement(Stroke stroke, Color strokeColor, Point2D.Float position, Dimension size, Color fillColor) {
        super(stroke, strokeColor);
        this.position = position;
        this.size = size;
        this.fillColor = fillColor;
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
        return fillColor;
    }

    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }

    @Override
    public void update(NotificationType notificationType, Object object) {

    }
}