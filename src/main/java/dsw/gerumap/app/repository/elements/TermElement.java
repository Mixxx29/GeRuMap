package dsw.gerumap.app.repository.elements;

import java.awt.*;

public class TermElement extends MindMapElement {
    private Point position;
    private Dimension size;
    private Color fillColor;

    public TermElement() {
        super();
        position = new Point(0, 0);
        size = new Dimension(100, 50);
        fillColor = new Color(118, 226, 248);
    }

    public TermElement(Stroke stroke, Color strokeColor, Point position, Dimension size, Color fillColor) {
        super(stroke, strokeColor);
        this.position = position;
        this.size = size;
        this.fillColor = fillColor;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
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
}
