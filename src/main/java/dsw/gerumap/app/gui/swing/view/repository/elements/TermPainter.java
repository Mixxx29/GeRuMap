package dsw.gerumap.app.gui.swing.view.repository.elements;

import dsw.gerumap.app.observer.NotificationType;
import dsw.gerumap.app.repository.elements.TermElement;

import java.awt.*;
import java.awt.geom.Ellipse2D;

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
    public void paint(Graphics2D g2) {
        // Draw fill
        g2.setColor(((TermElement)element).getFillColor());
        g2.fill(shape);

        // Draw stroke
        g2.setColor(element.getStrokeColor());
        g2.setStroke(element.getStroke());
        g2.draw(shape);

        // Draw selection
        if (selected) {
            // Draw selection outline
            g2.setColor(selectionStrokeColor);
            g2.setStroke(selectionStroke);
            g2.drawRect(
                ((TermElement) element).getPosition().x,
                ((TermElement) element).getPosition().y,
                ((TermElement) element).getSize().width,
                ((TermElement) element).getSize().height
            );

            g2.setColor(handleColor);

            // Draw top left handle
            g2.fillRect(
                ((TermElement) element).getPosition().x - handleSize / 2,
                ((TermElement) element).getPosition().y - handleSize / 2,
                    handleSize,
                    handleSize
            );

            // Draw top handle
            g2.fillRect(
                    ((TermElement) element).getPosition().x + ((TermElement) element).getSize().width / 2 - handleSize / 2,
                    ((TermElement) element).getPosition().y - handleSize / 2,
                    handleSize,
                    handleSize
            );

            // Draw top right handle
            g2.fillRect(
                    ((TermElement) element).getPosition().x + ((TermElement) element).getSize().width - handleSize / 2,
                    ((TermElement) element).getPosition().y - handleSize / 2,
                    handleSize,
                    handleSize
            );

            // Draw left handle
            g2.fillRect(
                    ((TermElement) element).getPosition().x - handleSize / 2,
                    ((TermElement) element).getPosition().y + ((TermElement) element).getSize().height / 2 - handleSize / 2,
                    handleSize,
                    handleSize
            );

            // Draw right handle
            g2.fillRect(
                    ((TermElement) element).getPosition().x + ((TermElement) element).getSize().width - handleSize / 2,
                    ((TermElement) element).getPosition().y + ((TermElement) element).getSize().height / 2 - handleSize / 2,
                    handleSize,
                    handleSize
            );

            // Draw bottom left handle
            g2.fillRect(
                    ((TermElement) element).getPosition().x - handleSize / 2,
                    ((TermElement) element).getPosition().y + ((TermElement) element).getSize().height - handleSize / 2,
                    handleSize,
                    handleSize
            );

            // Draw bottom handle
            g2.fillRect(
                    ((TermElement) element).getPosition().x + ((TermElement) element).getSize().width / 2 - handleSize / 2,
                    ((TermElement) element).getPosition().y + ((TermElement) element).getSize().height - handleSize / 2,
                    handleSize,
                    handleSize
            );

            // Draw bottom right handle
            g2.fillRect(
                    ((TermElement) element).getPosition().x + ((TermElement) element).getSize().width - handleSize / 2,
                    ((TermElement) element).getPosition().y + ((TermElement) element).getSize().height - handleSize / 2,
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
        if (notificationType == NotificationType.UPDATE_ELEMENT) {
            shape = new Ellipse2D.Double(
                ((TermElement) element).getPosition().x,
                ((TermElement) element).getPosition().y,
                ((TermElement) element).getSize().width,
                ((TermElement) element).getSize().height
            );
        }
    }
}
