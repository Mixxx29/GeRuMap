package dsw.gerumap.app.state.states;

import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.gui.swing.view.cursor.CursorType;
import dsw.gerumap.app.gui.swing.view.cursor.CustomCursor;
import dsw.gerumap.app.gui.swing.view.repository.elements.TermPainter;
import dsw.gerumap.app.gui.swing.view.repository.models.MindMapView;
import dsw.gerumap.app.observer.NotificationType;
import dsw.gerumap.app.repository.elements.TermElement;
import dsw.gerumap.app.repository.models.MindMap;
import dsw.gerumap.app.repository.models.Project;
import dsw.gerumap.app.state.AbstractState;
import dsw.gerumap.app.util.GraphicsUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

public class TermToolState extends AbstractState {

    private Point startPoint;
    private TermElement currentTermElement;
    private TermPainter currentTermPainter;
    private Graphics2D g2;

    public TermToolState() {
        cursor = CustomCursor.getCursor(CursorType.TERM_CURSOR);
        currentTermPainter = new TermPainter();
        currentTermPainter.setSelected(true); // Select element
    }

    @Override
    public void mousePressed(MindMapView mindMapView, MouseEvent e) {
        if (SwingUtilities.isMiddleMouseButton(e)) {
            super.mousePressed(mindMapView, e);
            return;
        }

        startPoint = e.getPoint(); // Get start point

        Project project = ((Project) mindMapView.getModel().getParent());

        // Create new element
        currentTermElement = new TermElement(
                new BasicStroke(project.getTermElementStrokeSize()),
                project.getTermElementStrokeColor(),
                GraphicsUtilities.screenToWorldPoint(e.getPoint(), mindMapView.offset, mindMapView.scale),
                new Dimension(300, 200),
                project.getTermElementFillColor()
        );

        // Create element painter
        currentTermPainter.setElement(currentTermElement);
        g2 = mindMapView.getTopBufferGraphics(); // Get top buffer graphics
    }

    @Override
    public void mouseReleased(MindMapView mindMapView, MouseEvent e) {
        if (SwingUtilities.isMiddleMouseButton(e)) {
            super.mouseReleased(mindMapView, e);
            return;
        }

        // Add new element
        ((MindMap) mindMapView.getModel()).addCreateTermCommand(currentTermElement);
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
        if (SwingUtilities.isMiddleMouseButton(e)) {
            super.mouseDragged(mindMapView, e);
            return;
        }

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

        // Update position
        currentTermElement.setPosition(
                GraphicsUtilities.screenToWorldPoint(rectangle.getLocation(), mindMapView.offset, mindMapView.scale)
        );

        // Update size
        currentTermElement.setSize(
                new Dimension(
                        (int)(rectangle.getSize().width / mindMapView.scale),
                        (int)(rectangle.getSize().height / mindMapView.scale)
                )
        );

        currentTermElement.notifyListeners(NotificationType.UPDATE_ELEMENTS, null);

        // Paint element
        currentTermPainter.paint(g2, mindMapView.offset, mindMapView.scale);

        // Repaint
        mindMapView.repaint();
    }
}