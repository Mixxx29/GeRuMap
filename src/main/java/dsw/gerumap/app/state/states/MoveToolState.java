package dsw.gerumap.app.state.states;

import dsw.gerumap.app.gui.swing.view.cursor.CursorType;
import dsw.gerumap.app.gui.swing.view.cursor.CustomCursor;
import dsw.gerumap.app.gui.swing.view.repository.elements.ElementPainter;
import dsw.gerumap.app.gui.swing.view.repository.models.MindMapView;
import dsw.gerumap.app.observer.NotificationType;
import dsw.gerumap.app.repository.elements.TermElement;
import dsw.gerumap.app.state.AbstractState;
import dsw.gerumap.app.util.GraphicsUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

public class MoveToolState extends AbstractState {

    public MoveToolState() {
        cursor = CustomCursor.getCursor(CursorType.MOVE_CURSOR);
    }

    @Override
    public void mousePressed(MindMapView mindMapView, MouseEvent e) {
        if (SwingUtilities.isMiddleMouseButton(e)) {
            super.mousePressed(mindMapView, e);
            return;
        }

        startPoint = e.getPoint(); // Get starting point
        if (mindMapView.getSelected().size() == 0) {
            mindMapView.selectAllElements();
        }
    }

    @Override
    public void mouseReleased(MindMapView mindMapView, MouseEvent e) {
        if (SwingUtilities.isMiddleMouseButton(e)) {
            super.mouseReleased(mindMapView, e);
            return;
        }
    }

    @Override
    public void mouseMoved(MindMapView mindMapView, MouseEvent e) {
        for (ElementPainter painter : mindMapView.getSelected()) {
            if (painter.getElement() instanceof TermElement termElement) {

            }
        }
    }

    @Override
    public void mouseDragged(MindMapView mindMapView, MouseEvent e) {
        if (SwingUtilities.isMiddleMouseButton(e)) {
            super.mouseDragged(mindMapView, e);
            return;
        }

        Point2D.Float mousePointTranslated = GraphicsUtilities.screenToWorldPoint(
                e.getPoint(), mindMapView.offset, mindMapView.scale
        );

        Point2D.Float startPointTranslated = GraphicsUtilities.screenToWorldPoint(
                startPoint, mindMapView.offset, mindMapView.scale
        );

        Point2D.Float difference = new Point2D.Float(
                mousePointTranslated.x - startPointTranslated.x,
                mousePointTranslated.y - startPointTranslated.y
        );
        startPoint = e.getPoint(); // Get starting point

        for (ElementPainter selected : mindMapView.getSelected()) {
            if (selected.getElement() instanceof TermElement termElement) {
                termElement.setPosition(
                        new Point2D.Float(
                                termElement.getPosition().x + difference.x,
                                termElement.getPosition().y + difference.y
                        )
                );
            }
            selected.getElement().notifyListeners(NotificationType.UPDATE_ELEMENT, null);
        }
        mindMapView.repaint();
    }
}