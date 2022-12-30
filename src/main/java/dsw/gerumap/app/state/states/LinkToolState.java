package dsw.gerumap.app.state.states;

import dsw.gerumap.app.gui.swing.view.cursor.CursorType;
import dsw.gerumap.app.gui.swing.view.cursor.CustomCursor;
import dsw.gerumap.app.gui.swing.view.repository.elements.ElementPainter;
import dsw.gerumap.app.gui.swing.view.repository.elements.LinkPainter;
import dsw.gerumap.app.gui.swing.view.repository.elements.TermPainter;
import dsw.gerumap.app.gui.swing.view.repository.models.MindMapView;
import dsw.gerumap.app.repository.elements.LinkElement;
import dsw.gerumap.app.repository.elements.TermElement;
import dsw.gerumap.app.repository.models.MindMap;
import dsw.gerumap.app.repository.models.Project;
import dsw.gerumap.app.state.AbstractState;
import dsw.gerumap.app.util.GraphicsUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.util.Iterator;
import java.util.List;

public class LinkToolState extends AbstractState {

    private LinkElement currentLinkElement;
    private LinkPainter currentLinkPainter;

    private TermElement cursorElement;

    private Graphics2D g2;

    public LinkToolState() {
        cursor = CustomCursor.getCursor(CursorType.LINK_CURSOR);
        currentLinkPainter = new LinkPainter(currentLinkElement);
        cursorElement = new TermElement();
    }

    @Override
    public void mousePressed(MindMapView mindMapView, MouseEvent e) {
        if (SwingUtilities.isMiddleMouseButton(e)) {
            super.mousePressed(mindMapView, e);
            return;
        }

        Project project = ((Project) mindMapView.getModel().getParent());

        Iterator<ElementPainter> iterator = mindMapView.getPaintersIterator();
        while (iterator.hasNext()) {
            ElementPainter painter = iterator.next();
            if (painter instanceof TermPainter && painter.at(e.getPoint())) {
                currentLinkElement = new LinkElement(
                        (TermElement) painter.getElement(),
                        cursorElement,
                        new BasicStroke(project.getLinkElementStrokeSize()),
                        project.getLinkElementStrokeColor()
                );
                currentLinkPainter.setElement(currentLinkElement);
                g2 = mindMapView.getBottomBufferGraphics();
                break;
            }
        }
    }

    @Override
    public void mouseReleased(MindMapView mindMapView, MouseEvent e) {
        if (SwingUtilities.isMiddleMouseButton(e)) {
            super.mouseReleased(mindMapView, e);
            return;
        }

        if (currentLinkElement == null) return;

        Iterator<ElementPainter> iterator = mindMapView.getPaintersIterator();
        while (iterator.hasNext()) {
            ElementPainter painter = iterator.next();
            if (painter instanceof TermPainter && painter.at(e.getPoint())) {
                currentLinkElement.setTermElement2((TermElement) painter.getElement());
                ((MindMap) mindMapView.getModel()).addLinkCommand(currentLinkElement);
                break;
            }
        }

        // Reset references
        currentLinkElement = null;
        currentLinkPainter.setElement(null);
        g2 = null;

        // Clear bottom buffer
        mindMapView.clearBottomBuffer();
        mindMapView.repaint();
    }

    @Override
    public void mouseDragged(MindMapView mindMapView, MouseEvent e) {
        if (SwingUtilities.isMiddleMouseButton(e)) {
            super.mouseDragged(mindMapView, e);
            return;
        }

        if (currentLinkElement == null) return;

        // Clear bottom buffer
        mindMapView.clearBottomBuffer();

        cursorElement.setPosition(
                GraphicsUtilities.screenToWorldPoint(
                        new Point(
                                (int) (e.getX() - cursorElement.getSize().width / 2.0f * mindMapView.scale),
                                (int) (e.getY() - cursorElement.getSize().height / 2.0f * mindMapView.scale)
                        ),
                        mindMapView.offset,
                        mindMapView.scale
                )

        );
        currentLinkPainter.paint(g2, mindMapView.offset, mindMapView.scale);

        mindMapView.repaint();
    }
}