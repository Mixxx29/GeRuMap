package dsw.gerumap.app.state.states;

import dsw.gerumap.app.gui.swing.view.cursor.CursorType;
import dsw.gerumap.app.gui.swing.view.cursor.CustomCursor;
import dsw.gerumap.app.gui.swing.view.repository.elements.ElementPainter;
import dsw.gerumap.app.gui.swing.view.repository.models.MindMapView;
import dsw.gerumap.app.observer.NotificationType;
import dsw.gerumap.app.repository.models.MindMap;
import dsw.gerumap.app.state.AbstractState;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.List;

public class EraserToolState extends AbstractState {

    public EraserToolState() {
        cursor = CustomCursor.getCursor(CursorType.ERASER_CURSOR);
    }

    @Override
    public void mousePressed(MindMapView mindMapView, MouseEvent e) {
        if (SwingUtilities.isMiddleMouseButton(e)) {
            super.mousePressed(mindMapView, e);
            return;
        }
    }

    @Override
    public void mouseReleased(MindMapView mindMapView, MouseEvent e) {
        if (SwingUtilities.isMiddleMouseButton(e)) {
            super.mouseReleased(mindMapView, e);
            return;
        }

        Iterator<ElementPainter> iterator = mindMapView.getPaintersIterator();
        while (iterator.hasNext()) {
            ElementPainter painter = iterator.next();
            if (painter.at(e.getPoint())) {
                ((MindMap)mindMapView.getModel()).removeElement(painter.getElement());
                break;
            }
        }
    }

    @Override
    public void mouseDragged(MindMapView mindMapView, MouseEvent e) {
        if (SwingUtilities.isMiddleMouseButton(e)) {
            super.mouseDragged(mindMapView, e);
            return;
        }
    }
}