package dsw.gerumap.app.state.states;

import dsw.gerumap.app.gui.swing.view.cursor.CursorType;
import dsw.gerumap.app.gui.swing.view.cursor.CustomCursor;
import dsw.gerumap.app.gui.swing.view.repository.elements.ElementPainter;
import dsw.gerumap.app.gui.swing.view.repository.models.MindMapView;
import dsw.gerumap.app.observer.NotificationType;
import dsw.gerumap.app.repository.elements.TermElement;
import dsw.gerumap.app.repository.models.MindMap;
import dsw.gerumap.app.state.AbstractState;
import dsw.gerumap.app.util.GraphicsUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

public class MoveToolState extends AbstractState {

    private  Point2D.Float offset;
    private  Point2D.Float totalOffset;

    private boolean allSelected;

    public MoveToolState() {
        cursor = CustomCursor.getCursor(CursorType.MOVE_CURSOR);
        totalOffset = new Point2D.Float(0.0f, 0.0f);
    }

    @Override
    public void mousePressed(MindMapView mindMapView, MouseEvent e) {
        if (SwingUtilities.isMiddleMouseButton(e)) {
            super.mousePressed(mindMapView, e);
            return;
        }

        startPoint = e.getPoint(); // Get starting point
        if (!((MindMap)mindMapView.getModel()).hasSelected()) {
            ((MindMap) mindMapView.getModel()).selectAllElements();
            allSelected = true;
        }
    }

    @Override
    public void mouseReleased(MindMapView mindMapView, MouseEvent e) {
        if (SwingUtilities.isMiddleMouseButton(e)) {
            super.mouseReleased(mindMapView, e);
            return;
        }

        if (totalOffset.x != 0.0f || totalOffset.y != 0.0f) {
            ((MindMap) mindMapView.getModel()).moveElements(new Point2D.Float(-totalOffset.x, -totalOffset.y));
            ((MindMap) mindMapView.getModel()).addMoveElementCommand(
                    new Point2D.Float(totalOffset.x, totalOffset.y), allSelected
            );
        }

        totalOffset.x = 0.0f;
        totalOffset.y = 0.0f;

        allSelected = false;
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

        offset = new Point2D.Float(
                mousePointTranslated.x - startPointTranslated.x,
                mousePointTranslated.y - startPointTranslated.y
        );
        startPoint = e.getPoint(); // Get starting point

        totalOffset.x += offset.x;
        totalOffset.y += offset.y;

        ((MindMap) mindMapView.getModel()).moveElements(offset);
    }
}