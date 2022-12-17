package dsw.gerumap.app.gui.swing.view.repository.elements;

import dsw.gerumap.app.observer.NotificationType;
import dsw.gerumap.app.repository.elements.TermElement;
import dsw.gerumap.app.util.GraphicsUtilities;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

public class TermPainter extends ElementPainter {

    public TermPainter() {
        super(null);
    }

    public TermPainter(TermElement element) {
        super(element);
        element.addListener(this);

        // Create shape
        shape = new Ellipse2D.Double(
                element.getPosition().x,
                element.getPosition().y,
                element.getSize().width,
                element.getSize().height
        );
    }

    @Override
    public void paint(Graphics2D g2, Point2D.Float offset, float scale) {
        // Translate position to screen position
        Point screenPosition = GraphicsUtilities.worldToScreenPoint(((TermElement) element).getPosition(), offset, scale);

        // Create shape
        shape = new Ellipse2D.Float(
                screenPosition.x,
                screenPosition.y,
                ((TermElement) element).getSize().width * scale,
                ((TermElement) element).getSize().height * scale
        );

        // Fill shape
        g2.setColor(((TermElement)element).getFillColor());
        g2.fill(shape);

        // Draw shape stroke
        g2.setColor(element.getStrokeColor());
        g2.setStroke(
                new BasicStroke(((BasicStroke) element.getStroke()).getLineWidth() * scale)
        );
        g2.draw(shape);

        // Draw selection
        if (selected) {
            // Draw selection outline
            g2.setColor(selectionStrokeColor);
            g2.setStroke(selectionStroke);
            g2.drawRect(
                    shape.getBounds().x,
                    shape.getBounds().y,
                    shape.getBounds().width,
                    shape.getBounds().height
            );

            g2.setColor(handleColor);

            // Draw top left handle
            g2.fillRect(
                    shape.getBounds().x - handleSize / 2,
                    shape.getBounds().y - handleSize / 2,
                    handleSize,
                    handleSize
            );

            // Draw top handle
            g2.fillRect(
                    shape.getBounds().x + shape.getBounds().width / 2 - handleSize / 2,
                    shape.getBounds().y - handleSize / 2,
                    handleSize,
                    handleSize
            );

            // Draw top right handle
            g2.fillRect(
                    shape.getBounds().x + shape.getBounds().width - handleSize / 2,
                    shape.getBounds().y - handleSize / 2,
                    handleSize,
                    handleSize
            );

            // Draw left handle
            g2.fillRect(
                    shape.getBounds().x - handleSize / 2,
                    shape.getBounds().y + shape.getBounds().height / 2 - handleSize / 2,
                    handleSize,
                    handleSize
            );

            // Draw right handle
            g2.fillRect(
                    shape.getBounds().x + shape.getBounds().width - handleSize / 2,
                    shape.getBounds().y + shape.getBounds().height / 2 - handleSize / 2,
                    handleSize,
                    handleSize
            );

            // Draw bottom left handle
            g2.fillRect(
                    shape.getBounds().x - handleSize / 2,
                    shape.getBounds().y + shape.getBounds().height - handleSize / 2,
                    handleSize,
                    handleSize
            );

            // Draw bottom handle
            g2.fillRect(
                    shape.getBounds().x + shape.getBounds().width / 2 - handleSize / 2,
                    shape.getBounds().y + shape.getBounds().height - handleSize / 2,
                    handleSize,
                    handleSize
            );

            // Draw bottom right handle
            g2.fillRect(
                    shape.getBounds().x + shape.getBounds().width - handleSize / 2,
                    shape.getBounds().y + shape.getBounds().height - handleSize / 2,
                    handleSize,
                    handleSize
            );
        }
    }

    @Override
    public boolean at(Point point) {
        return shape.contains(point);
    }

    @Override
    public boolean intersects(Rectangle rectangle) {
        return shape.intersects(rectangle);
    }

    public Handle onHandle() {
        return null;
    }

    @Override
    public void update(NotificationType notificationType, Object object) {

    }
}