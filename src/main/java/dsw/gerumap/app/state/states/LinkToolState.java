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
import dsw.gerumap.app.state.AbstractState;

import java.awt.*;
import java.awt.event.MouseEvent;
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
        Iterator<ElementPainter> iterator = mindMapView.getPaintersIterator();
        while (iterator.hasNext()) {
            ElementPainter painter = iterator.next();
            if (painter instanceof TermPainter && painter.at(e.getPoint())) {
                currentLinkElement = new LinkElement((TermElement) painter.getElement(), cursorElement);
                currentLinkPainter.setElement(currentLinkElement);
                g2 = mindMapView.getBottomBufferGraphics();
                break;
            }
        }
    }

    @Override
    public void mouseReleased(MindMapView mindMapView, MouseEvent e) {
        if (currentLinkElement == null) return;

        Iterator<ElementPainter> iterator = mindMapView.getPaintersIterator();
        while (iterator.hasNext()) {
            ElementPainter painter = iterator.next();
            if (painter instanceof TermPainter && painter.at(e.getPoint())) {
                currentLinkElement.setTermElement2((TermElement) painter.getElement());
                ((MindMap) mindMapView.getModel()).addElement(currentLinkElement);
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
        if (currentLinkElement == null) return;

        // Clear bottom buffer
        mindMapView.clearBottomBuffer();
        cursorElement.setPosition(
            new Point(
               e.getX() - cursorElement.getSize().width / 2,
               e.getY() - cursorElement.getSize().height / 2
            )
        );
        currentLinkPainter.paint(g2);

        mindMapView.repaint();
    }
}
