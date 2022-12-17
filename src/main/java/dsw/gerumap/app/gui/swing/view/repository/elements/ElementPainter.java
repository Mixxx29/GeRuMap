package dsw.gerumap.app.gui.swing.view.repository.elements;

import dsw.gerumap.app.observer.IListener;
import dsw.gerumap.app.repository.elements.MindMapElement;

import java.awt.*;
import java.awt.geom.Point2D;

public abstract class ElementPainter implements IListener {

    protected enum Handle {
        NORTH_WEST,
        NORTH,
        NORTH_EAST,
        EAST,
        SOUTH_EAST,
        SOUTH,
        SOUTH_WEST,
        WEST
    }

    protected Shape shape;

    protected MindMapElement element;

    protected boolean selected;

    protected Stroke selectionStroke;
    protected Color selectionStrokeColor;

    protected Color handleColor;
    protected int handleSize;

    public ElementPainter(MindMapElement element) {
        this.element = element;

        selectionStroke = new BasicStroke(
                3.0f,
                BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_MITER,
                5.0f,
                new float[] {5.0f},
                0.0f
        );
        selectionStrokeColor = new Color(255, 94, 44);

        handleColor = new Color(41, 171, 226);
        handleSize = 10;
    }

    public MindMapElement getElement() {
        return element;
    }

    public void setElement(MindMapElement element) {
        if (this.element != null) {
            this.element.removeListener(this);
        }
        this.element = element;
        if (this.element != null) {
            this.element.addListener(this);
        }
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public abstract void paint(Graphics2D g2, Point2D.Float offset, float scale);

    public abstract boolean at(Point point);
    public abstract boolean intersects(Rectangle rectangle);
}