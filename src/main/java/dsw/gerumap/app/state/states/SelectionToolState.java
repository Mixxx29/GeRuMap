package dsw.gerumap.app.state.states;

import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.gui.swing.view.cursor.CursorType;
import dsw.gerumap.app.gui.swing.view.cursor.CustomCursor;
import dsw.gerumap.app.gui.swing.view.repository.elements.ElementPainter;
import dsw.gerumap.app.gui.swing.view.repository.models.MindMapView;
import dsw.gerumap.app.state.AbstractState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.List;

public class SelectionToolState extends AbstractState {

    private Point startPoint;
    private final Color fillColor;
    private final Color strokeColor;
    private final BasicStroke stroke;

    private Graphics2D g2;

    private boolean ctrl_down;

    public SelectionToolState() {
        cursor = CustomCursor.getCursor(CursorType.SELECTION_CURSOR);
        fillColor = new Color(41, 171, 226, 100);
        strokeColor = new Color(40, 88, 221, 100);
        stroke = new BasicStroke(
                2.0f,
                BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_MITER,
                5.0f,
                new float[] {5.0f},
                0.0f
        );
    }

    public void mousePressed(MindMapView mindMapView, MouseEvent e) {
        if (SwingUtilities.isMiddleMouseButton(e)) {
            super.mousePressed(mindMapView, e);
            return;
        }

        startPoint = e.getPoint();
        g2 = mindMapView.getTopBufferGraphics();
    }

    public void mouseReleased(MindMapView mindMapView, MouseEvent e) {
        if (SwingUtilities.isMiddleMouseButton(e)) {
            super.mouseReleased(mindMapView, e);
            return;
        }

        // Reset references
        startPoint = null;
        g2 = null;

        // Clear top buffer
        mindMapView.clearTopBuffer();
        mindMapView.repaint();

        // Set move state
        if (!ctrl_down && mindMapView.getSelected().size() > 0) {
            MainFrame.getInstance().getEditorWindow().getActiveProjectView().setMoveToolState();
        }
    }

    @Override
    public void mouseClicked(MindMapView mindMapView, MouseEvent e) {
        // Select element
        if (!ctrl_down) mindMapView.deselectAllElements();
        Iterator<ElementPainter> iterator = mindMapView.getPaintersIterator();
        while (iterator.hasNext()) {
            ElementPainter painter = iterator.next();
            if (painter.at(e.getPoint())) {
                if (painter.isSelected()) {
                    mindMapView.deselectElements(List.of(painter));
                } else {
                    mindMapView.selectElements(List.of(painter));
                }
                break;
            }
        }

        if (!ctrl_down && mindMapView.getSelected().size() > 0) {
            // Set move state
            MainFrame.getInstance().getEditorWindow().getActiveProjectView().setMoveToolState();
        }
    }

    public void mouseDragged(MindMapView mindMapView, MouseEvent e) {
        if (SwingUtilities.isMiddleMouseButton(e)) {
            super.mouseDragged(mindMapView, e);
            return;
        }

        if (startPoint == null || g2 == null) return;

        Rectangle selectionRect = new Rectangle();
        if (startPoint.x > e.getX()) {
            selectionRect.x = e.getX();
            selectionRect.width = startPoint.x - e.getX();
        } else {
            selectionRect.x = startPoint.x;
            selectionRect.width = e.getX() - startPoint.x;
        }
        if (startPoint.y > e.getY()) {
            selectionRect.y = e.getY();
            selectionRect.height = startPoint.y - e.getY();
        } else {
            selectionRect.y = startPoint.y;
            selectionRect.height = e.getY() - startPoint.y;
        }

        // Clear buffer
        mindMapView.clearTopBuffer();
        // Draw fill
        g2.setColor(fillColor);
        g2.fillRect(selectionRect.x, selectionRect.y, selectionRect.width, selectionRect.height);

        // Draw stroke
        g2.setColor(strokeColor);
        g2.setStroke(stroke);
        g2.drawRect(selectionRect.x, selectionRect.y, selectionRect.width, selectionRect.height);

        // Select elements
        mindMapView.deselectAllElements();
        Iterator<ElementPainter> iterator = mindMapView.getPaintersIterator();
        while (iterator.hasNext()) {
            ElementPainter painter = iterator.next();
            if (painter.intersects(selectionRect)) mindMapView.selectElements(List.of(painter));
        }

        // Update
        mindMapView.repaint();
    }

    @Override
    public void keyPressed(MindMapView mindMapView, KeyEvent e) {
        super.keyPressed(mindMapView, e);
        if (e.getKeyCode() == KeyEvent.VK_CONTROL) ctrl_down = true;
    }

    @Override
    public void keyReleased(MindMapView mindMapView, KeyEvent e) {
        super.keyReleased(mindMapView, e);
        if (e.getKeyCode() == KeyEvent.VK_CONTROL)
        {
            ctrl_down = false;
            if (mindMapView.getSelected().size() > 0) {
                // Set move state
                MainFrame.getInstance().getEditorWindow().getActiveProjectView().setMoveToolState();
            }
        }
    }
}