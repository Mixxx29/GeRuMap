package dsw.gerumap.app.gui.swing.view.repository.elements;

import dsw.gerumap.app.observer.NotificationType;
import dsw.gerumap.app.repository.elements.LinkElement;
import dsw.gerumap.app.repository.elements.MindMapElement;
import dsw.gerumap.app.repository.elements.TermElement;
import dsw.gerumap.app.util.GraphicsUtilities;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class LinkPainter extends ElementPainter {


    public LinkPainter(MindMapElement element) {
        super(element);
        if (element != null)
            handleSize = (int)((BasicStroke)element.getStroke()).getLineWidth() + 2;
    }

    @Override
    public void paint(Graphics2D g2, Point2D.Float offset, float scale) {
        // Cache terms
        TermElement termElement1 = ((LinkElement)element).getTermElement1();
        TermElement termElement2 = ((LinkElement)element).getTermElement2();

        // Set color
        g2.setColor(element.getStrokeColor());

        // Set stroke
        if (selected) {
            g2.setStroke(new BasicStroke(((BasicStroke)element.getStroke()).getLineWidth() * scale + 2.0f));
        } else {
            g2.setStroke(new BasicStroke(((BasicStroke)element.getStroke()).getLineWidth() * scale));
        }

        // Translate shape to screen position
        Point screenPosition1 = GraphicsUtilities.worldToScreenPoint(termElement1.getPosition(), offset, scale);
        Point screenPosition2 = GraphicsUtilities.worldToScreenPoint(termElement2.getPosition(), offset, scale);

        Point start = new Point(
                (int)(screenPosition1.x + termElement1.getSize().width * scale / 2),
                (int)(screenPosition1.y  + termElement1.getSize().height * scale / 2)
        );

        Point end = new Point(
                (int)(screenPosition2.x + termElement2.getSize().width * scale / 2),
                (int)(screenPosition2.y + termElement2.getSize().height * scale / 2)
        );

        shape = new Line2D.Float(start, end);

        // Draw line
        g2.draw(shape);

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
        return shape.intersects(
                new Rectangle(
                        point.x - 5,
                        point.y - 5,
                        5,
                        5
                )
        );
    }

    @Override
    public boolean intersects(Rectangle rectangle) {
        return shape.intersects(rectangle);
    }

    @Override
    public void update(NotificationType notificationType, Object object) {

    }
}