package dsw.gerumap.app.state;

import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.gui.swing.view.repository.models.MindMapView;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public abstract class AbstractState {

    protected Cursor cursor;

    public void mouseEntered(MindMapView mindMapView, MouseEvent e) {
        e.getComponent().setCursor(cursor);
    }

    public void mouseExited(MindMapView mindMapView, MouseEvent e) {
        e.getComponent().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }

    public void mouseClicked(MindMapView mindMapView, MouseEvent e) {}

    public void mousePressed(MindMapView mindMapView, MouseEvent e) {

    }

    public void mouseReleased(MindMapView mindMapView, MouseEvent e) {

    }

    public void mouseMoved(MindMapView mindMapView, MouseEvent e) {}

    public void mouseDragged(MindMapView mindMapView, MouseEvent e) {}

    public void keyPressed(MindMapView mindMapView, KeyEvent e) {
        if (e.getModifiersEx() == InputEvent.CTRL_DOWN_MASK) {
            if (e.getKeyCode() == KeyEvent.VK_A) {
                // Set move state
                MainFrame.getInstance().getEditorWindow().getActiveProjectView().setSelectionToolState();
                mindMapView.deselectAllElements();
                mindMapView.selectAllElements();
            } else if (e.getKeyCode() == KeyEvent.VK_D) {
                mindMapView.deselectAllElements();
            }
        }
    }

    public void keyReleased(MindMapView mindMapView, KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_A && e.getModifiersEx() != InputEvent.CTRL_DOWN_MASK) {
            if (mindMapView.getSelected().size() > 0) {
                // Set move state
                MainFrame.getInstance().getEditorWindow().getActiveProjectView().setMoveToolState();
            }
        }
    }
}
