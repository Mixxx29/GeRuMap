package dsw.gerumap.app.state.states;

import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.gui.swing.view.cursor.CursorType;
import dsw.gerumap.app.gui.swing.view.cursor.CustomCursor;
import dsw.gerumap.app.gui.swing.view.repository.elements.TermPainter;
import dsw.gerumap.app.gui.swing.view.repository.models.MindMapView;
import dsw.gerumap.app.observer.NotificationType;
import dsw.gerumap.app.repository.elements.TermElement;
import dsw.gerumap.app.repository.models.MindMap;
import dsw.gerumap.app.state.AbstractState;

import java.awt.*;
import java.awt.event.MouseEvent;

public class TermToolState extends AbstractState {

    private Point startPoint;
    private TermElement currentTermElement;
    private TermPainter currentTermPainter;
    private Graphics2D g2;

    public TermToolState() {
        cursor = CustomCursor.getCursor(CursorType.TERM_CURSOR);
        currentTermPainter = new TermPainter();
    }

    @Override
    public void mousePressed(MindMapView mindMapView, MouseEvent e) {
        startPoint = e.getPoint(); // Get start point

        // Create new element
        currentTermElement = new TermElement();
        currentTermElement.setPosition(startPoint); // Set element position

        // Create element painter
        currentTermPainter.setElement(currentTermElement);
        currentTermPainter.setSelected(true); // Select element
        g2 = mindMapView.getTopBufferGraphics(); // Get top buffer graphics
    }

    @Override
    public void mouseReleased(MindMapView mindMapView, MouseEvent e) {
        // Add new element
        ((MindMap)mindMapView.getModel()).addElement(currentTermElement);
        mindMapView.clearTopBuffer(); // Clear top buffer

        // Reset references
        startPoint = null;
        currentTermElement = null;
        currentTermPainter.setElement(null);
        g2 = null;

        // Set move state
        MainFrame.getInstance().getEditorWindow().getActiveProjectView().setMoveToolState();
    }

    @Override
    public void mouseDragged(MindMapView mindMapView, MouseEvent e) {
        if (startPoint == null || currentTermElement == null) return;

        Rectangle rectangle = new Rectangle();
        if (startPoint.x > e.getX()) {
            rectangle.x = e.getX();
            rectangle.width = startPoint.x - e.getX();
        } else {
            rectangle.x = startPoint.x;
            rectangle.width = e.getX() - startPoint.x;
        }
        if (startPoint.y > e.getY()) {
            rectangle.y = e.getY();
            rectangle.height = startPoint.y - e.getY();
        } else {
            rectangle.y = startPoint.y;
            rectangle.height = e.getY() - startPoint.y;
        }

        mindMapView.clearTopBuffer(); // Clear top buffer
        currentTermElement.setPosition(rectangle.getLocation()); // Update position
        currentTermElement.setSize(rectangle.getSize()); // Update size
        currentTermElement.notifyListeners(NotificationType.UPDATE_ELEMENT, null);

        // Paint element
        currentTermPainter.paint(g2);

        // Repaint
        mindMapView.repaint();
    }
}
