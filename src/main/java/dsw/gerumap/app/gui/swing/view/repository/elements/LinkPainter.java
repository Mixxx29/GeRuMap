package dsw.gerumap.app.gui.swing.view.repository.elements;

import dsw.gerumap.app.observer.NotificationType;
import dsw.gerumap.app.repository.elements.LinkElement;
import dsw.gerumap.app.repository.elements.MindMapElement;
import dsw.gerumap.app.repository.elements.TermElement;

import java.awt.*;
import java.awt.geom.Line2D;

public class LinkPainter extends ElementPainter {


    public LinkPainter(MindMapElement element) {
        super(element);
        if (element != null)
            handleSize = (int)((BasicStroke)element.getStroke()).getLineWidth() + 2;
    }

    @Override
    public void paint(Graphics2D g2) {
        // Cache terms
        TermElement termElement1 = ((LinkElement)element).getTermElement1();
        TermElement termElement2 = ((LinkElement)element).getTermElement2();

        // Set color
        g2.setColor(element.getStrokeColor());

        // Set stroke
        if (selected) {
            g2.setStroke(new BasicStroke(((BasicStroke)element.getStroke()).getLineWidth() + 2.0f));
        } else {
            g2.setStroke(element.getStroke());
        }

        Point start = new Point(
            termElement1.getPosition().x + termElement1.getSize().width / 2,
            termElement1.getPosition().y  + termElement1.getSize().height / 2
        );

        Point end = new Point(
            termElement2.getPosition().x + termElement2.getSize().width / 2,
            termElement2.getPosition().y + termElement2.getSize().height / 2
        );

        // Draw line
        g2.drawLine(
                start.x,
                start.y,
                end.x,
                end.y
        );

        /*if (selected) {
            g2.setColor(handleColor);
            g2.drawOval( // Start handle
                    start.x - handleSize / 2,
                    start.y - handleSize / 2,
                    handleSize,
                    handleSize
            );
            g2.drawOval( // Middle handle
                start.x + ((end.x - start.x) / 2) - handleSize / 2,
                start.y + ((end.y - start.y) / 2) - handleSize / 2,
                handleSize,
                handleSize
            );
            g2.drawOval( // End handle
                    end.x - handleSize / 2,
                    end.y - handleSize / 2,
                    handleSize,
                    handleSize
            );
        }*/
    }

    @Override
    public boolean at(Point point) {
        return false;
    }

    @Override
    public boolean intersects(Rectangle rectangle) {
        return false;
    }

    @Override
    public void update(NotificationType notificationType, Object object) {

    }
}
