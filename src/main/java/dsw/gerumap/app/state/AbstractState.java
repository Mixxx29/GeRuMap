package dsw.gerumap.app.state;

import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.gui.swing.view.cursor.CursorType;
import dsw.gerumap.app.gui.swing.view.cursor.CustomCursor;
import dsw.gerumap.app.gui.swing.view.repository.models.MindMapView;
import dsw.gerumap.app.repository.elements.MindMapElement;
import dsw.gerumap.app.repository.models.MindMap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class AbstractState {

    protected Cursor cursor;
    protected Point startPoint;

    public void mouseEntered(MindMapView mindMapView, MouseEvent e) {
        e.getComponent().setCursor(cursor);
    }

    public void mouseExited(MindMapView mindMapView, MouseEvent e) {
        e.getComponent().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }

    public void mouseClicked(MindMapView mindMapView, MouseEvent e) {}

    public void mousePressed(MindMapView mindMapView, MouseEvent e) {
        if (SwingUtilities.isMiddleMouseButton(e)) {
            e.getComponent().setCursor(CustomCursor.getCursor(CursorType.DRAG_CURSOR));
            startPoint = e.getPoint();
        }
    }

    public void mouseReleased(MindMapView mindMapView, MouseEvent e) {
        e.getComponent().setCursor(cursor);
    }

    public void mouseMoved(MindMapView mindMapView, MouseEvent e) {}

    public void mouseDragged(MindMapView mindMapView, MouseEvent e) {
        if (SwingUtilities.isMiddleMouseButton(e)) {
            mindMapView.offset.x -= (e.getX() - startPoint.x) / mindMapView.scale;
            mindMapView.offset.y -= (e.getY() - startPoint.y) / mindMapView.scale;
            startPoint = e.getPoint();
            mindMapView.repaint();
        }
    }

    public void keyPressed(MindMapView mindMapView, KeyEvent e) {
        if (e.isControlDown()) {
            if (e.getKeyCode() == KeyEvent.VK_A) {
                MainFrame.getInstance().getEditorWindow().getActiveProjectView().setMoveToolState();
                // Set move state
                ((MindMap) mindMapView.getModel()).addSelectionCommand(
                        ((MindMap) mindMapView.getModel()).getElements()
                );
            } else if (e.getKeyCode() == KeyEvent.VK_D && ((MindMap) mindMapView.getModel()).hasSelected()) {
                ((MindMap) mindMapView.getModel()).addSelectionCommand(List.of());
            }
        }
    }

    public void keyReleased(MindMapView mindMapView, KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_A && e.getModifiersEx() != InputEvent.CTRL_DOWN_MASK) {
            if (((MindMap) mindMapView.getModel()).hasSelected()) {
                // Set move state
                MainFrame.getInstance().getEditorWindow().getActiveProjectView().setMoveToolState();
            }
        }
    }
}