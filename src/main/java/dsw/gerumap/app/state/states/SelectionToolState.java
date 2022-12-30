package dsw.gerumap.app.state.states;

import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.gui.swing.view.cursor.CursorType;
import dsw.gerumap.app.gui.swing.view.cursor.CustomCursor;
import dsw.gerumap.app.gui.swing.view.repository.elements.ElementPainter;
import dsw.gerumap.app.gui.swing.view.repository.models.MindMapView;
import dsw.gerumap.app.observer.NotificationType;
import dsw.gerumap.app.repository.elements.MindMapElement;
import dsw.gerumap.app.repository.models.MindMap;
import dsw.gerumap.app.state.AbstractState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SelectionToolState extends AbstractState {

    private Point startPoint;
    private final Color selectionFillColor;
    private final Color selectionStrokeColor;
    private final BasicStroke selectionStroke;

    private Graphics2D g2;

    private List<MindMapElement> selected;
    private List<MindMapElement> original;
    private boolean clicked;
    private boolean first = true;

    public SelectionToolState() {
        cursor = CustomCursor.getCursor(CursorType.SELECTION_CURSOR);
        selectionFillColor = new Color(41, 171, 226, 100);
        selectionStrokeColor = new Color(40, 88, 221, 100);
        selectionStroke = new BasicStroke(
                2.0f,
                BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_MITER,
                5.0f,
                new float[] {5.0f},
                0.0f
        );

        selected = new ArrayList<>();
        original = new ArrayList<>();
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

        // Check if clicked
        clicked = false;
        if (startPoint.x == e.getX() && startPoint.y == e.getY()) {
            clicked = true;

            if (!e.isControlDown()) selected.clear();
            else if (first){
                original.addAll(selected);
                first = false;
            }

            Iterator<ElementPainter> paintersIterator = mindMapView.getPaintersIterator();
            while (paintersIterator.hasNext()) {
                ElementPainter painter = paintersIterator.next();
                if (painter.at(e.getPoint())) {
                    if (selected.contains(painter.getElement())) {
                        selected.remove(painter.getElement());
                    } else {
                        selected.add(painter.getElement());
                    }
                    break;
                }
            }
        }

        // Clear top buffer
        mindMapView.clearTopBuffer();

        // Exit selection state
        if (!clicked || !e.isControlDown()) {
            if (selected.size() > 0) {
                finish((MindMap) mindMapView.getModel());
            }
        } else {
            ((MindMap) mindMapView.getModel()).selectElements(selected);
        }

        // Reset references
        startPoint = null;
        g2 = null;
    }

    public void mouseDragged(MindMapView mindMapView, MouseEvent e) {
        if (SwingUtilities.isMiddleMouseButton(e)) {
            super.mouseDragged(mindMapView, e);
            return;
        }

        if (startPoint == null || g2 == null) return;

        if (first){
            original.addAll(selected);
            first = false;
        }

        Rectangle selectionRectangle = new Rectangle();
        if (startPoint.x > e.getX()) {
            selectionRectangle.x = e.getX();
            selectionRectangle.width = startPoint.x - e.getX();
        } else {
            selectionRectangle.x = startPoint.x;
            selectionRectangle.width = e.getX() - startPoint.x;
        }
        if (startPoint.y > e.getY()) {
            selectionRectangle.y = e.getY();
            selectionRectangle.height = startPoint.y - e.getY();
        } else {
            selectionRectangle.y = startPoint.y;
            selectionRectangle.height = e.getY() - startPoint.y;
        }

        // Clear buffer
        mindMapView.clearTopBuffer();

        // Draw fill
        g2.setColor(selectionFillColor);
        g2.fillRect(selectionRectangle.x, selectionRectangle.y, selectionRectangle.width, selectionRectangle.height);

        // Draw stroke
        g2.setColor(selectionStrokeColor);
        g2.setStroke(selectionStroke);
        g2.drawRect(selectionRectangle.x, selectionRectangle.y, selectionRectangle.width, selectionRectangle.height);

        selected.clear();
        Iterator<ElementPainter> paintersIterator = mindMapView.getPaintersIterator();
        while (paintersIterator.hasNext()) {
            ElementPainter painter = paintersIterator.next();
            if (painter.intersects(selectionRectangle)) {
                selected.add(painter.getElement());
            }
        }

        ((MindMap) mindMapView.getModel()).selectElements(selected);
    }

    @Override
    public void keyReleased(MindMapView mindMapView, KeyEvent e) {
        super.keyReleased(mindMapView, e);
        if (e.getKeyCode() == KeyEvent.VK_CONTROL) {
            if (((MindMap) mindMapView.getModel()).hasSelected()) {
                finish((MindMap) mindMapView.getModel());
            }
        }
    }

    private void finish(MindMap mindMap) {
        mindMap.selectElements(original);
        mindMap.addSelectionCommand(selected);
        selected.clear();
        original.clear();
        first = true;
        MainFrame.getInstance()
                .getEditorWindow()
                .getActiveProjectView()
                .setMoveToolState();
    }
}