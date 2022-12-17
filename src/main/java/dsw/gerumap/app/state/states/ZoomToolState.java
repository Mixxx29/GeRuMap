package dsw.gerumap.app.state.states;

import dsw.gerumap.app.gui.swing.view.cursor.CursorType;
import dsw.gerumap.app.gui.swing.view.cursor.CustomCursor;
import dsw.gerumap.app.gui.swing.view.repository.models.MindMapView;
import dsw.gerumap.app.state.AbstractState;
import dsw.gerumap.app.util.GraphicsUtilities;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

public class ZoomToolState extends AbstractState {

    public ZoomToolState() {
        cursor = CustomCursor.getCursor(CursorType.ZOOM_CURSOR);
    }

    @Override
    public void mousePressed(MindMapView mindMapView, MouseEvent e) {
        if (SwingUtilities.isMiddleMouseButton(e)) {
            super.mousePressed(mindMapView, e);
            return;
        }

        Point2D.Float beforeZoom = GraphicsUtilities.screenToWorldPoint(
                e.getPoint(), mindMapView.offset, mindMapView.scale
        );

        if (e.getButton() == MouseEvent.BUTTON1) mindMapView.scale *= 1.2f;
        else if (e.getButton() == MouseEvent.BUTTON3) mindMapView.scale *= 0.8f;
        else return;

        Point2D.Float afterZoom = GraphicsUtilities.screenToWorldPoint(
                e.getPoint(), mindMapView.offset, mindMapView.scale
        );

        Point2D.Float difference = new Point2D.Float(
                afterZoom.x - beforeZoom.x,
                afterZoom.y - beforeZoom.y
        );

        mindMapView.offset.x -= difference.x;
        mindMapView.offset.y -= difference.y;

        mindMapView.repaint();
    }

    @Override
    public void mouseReleased(MindMapView mindMapView, MouseEvent e) {
        if (SwingUtilities.isMiddleMouseButton(e)) {
            super.mouseReleased(mindMapView, e);
            return;
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