package dsw.gerumap.app.state.states;

import dsw.gerumap.app.gui.swing.view.cursor.CursorType;
import dsw.gerumap.app.gui.swing.view.cursor.CustomCursor;
import dsw.gerumap.app.gui.swing.view.repository.elements.ElementPainter;
import dsw.gerumap.app.gui.swing.view.repository.models.MindMapView;
import dsw.gerumap.app.observer.NotificationType;
import dsw.gerumap.app.repository.elements.TermElement;
import dsw.gerumap.app.state.AbstractState;

import java.awt.*;
import java.awt.event.MouseEvent;

public class MoveToolState extends AbstractState {

    private Point startPoint;

    public MoveToolState() {
        cursor = CustomCursor.getCursor(CursorType.MOVE_CURSOR);
    }

    @Override
    public void mousePressed(MindMapView mindMapView, MouseEvent e) {
        startPoint = e.getPoint(); // Get starting point
        if (mindMapView.getSelected().size() == 0) {
            mindMapView.selectAllElements();
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
        Point difference = new Point(
            e.getX() - startPoint.x,
            e.getY() - startPoint.y
        );
        startPoint = e.getPoint(); // Get starting point

        for (ElementPainter selected : mindMapView.getSelected()) {
            if (selected.getElement() instanceof TermElement termElement) {
                termElement.setPosition(
                    new Point(
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
