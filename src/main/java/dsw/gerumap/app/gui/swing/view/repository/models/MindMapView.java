package dsw.gerumap.app.gui.swing.view.repository.models;

import dsw.gerumap.app.gui.swing.view.repository.composite.ModelView;
import dsw.gerumap.app.gui.swing.view.repository.elements.ElementPainter;
import dsw.gerumap.app.gui.swing.view.repository.elements.LinkPainter;
import dsw.gerumap.app.gui.swing.view.repository.elements.TermPainter;
import dsw.gerumap.app.observer.NotificationType;
import dsw.gerumap.app.repository.elements.LinkElement;
import dsw.gerumap.app.repository.elements.TermElement;
import dsw.gerumap.app.repository.models.MindMap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MindMapView extends ModelView {

    private List<ElementPainter> painters;
    private List<ElementPainter> selected;

    private BufferedImage bottomBuffer;
    private BufferedImage topBuffer;

    private Graphics2D bottomBufferGraphics;
    private Graphics2D topBufferGraphics;

    public MindMapView(MindMap mindMap) {
        super(mindMap);
        painters = new ArrayList<>();
        selected = new ArrayList<>();

        setLayout(new BorderLayout());

        JPanel content = new JPanel();
        content.setBackground(new Color(220, 220, 220));
        add(content, BorderLayout.CENTER);

        addComponentListener(new ComponentAdapter() {

            @Override
            public void componentResized(ComponentEvent e) {
                bottomBuffer = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
                topBuffer = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);

                if (bottomBufferGraphics != null) bottomBufferGraphics.dispose();
                if (topBufferGraphics != null) topBufferGraphics.dispose();

                bottomBufferGraphics = bottomBuffer.createGraphics();
                topBufferGraphics = topBuffer.createGraphics();
            }
        });
    }

    public Iterator<ElementPainter> getPaintersIterator() {
        return painters.iterator();
    }

    public void selectElements(List<ElementPainter> toSelect) {
        for (ElementPainter elementPainter : toSelect) {
            selected.add(elementPainter);
            elementPainter.setSelected(true);
        }
    }

    public void deselectElements(List<ElementPainter> toDeselect) {
        for (ElementPainter elementPainter : toDeselect) {
            selected.remove(elementPainter);
            elementPainter.setSelected(false);
        }
    }

    public void selectAllElements() {
        selectElements(new ArrayList<>(painters));
        repaint();
    }

    public void deselectAllElements() {
        deselectElements(new ArrayList<>(selected));
        repaint();
    }

    public List<ElementPainter> getSelected() {
        return selected;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // Draw bottom buffer
        if (bottomBuffer != null) {
            g.drawImage(bottomBuffer, 0, 0, bottomBuffer.getWidth(), bottomBuffer.getHeight(), null);
        }

        // Draw elements
        for (ElementPainter painter : painters) {
            painter.paint((Graphics2D)g);
        }

        // Draw top buffer
        if (topBuffer != null) {
            g.drawImage(topBuffer, 0, 0, topBuffer.getWidth(), topBuffer.getHeight(), null);
        }
    }

    public Graphics2D getBottomBufferGraphics() {
        return bottomBufferGraphics;
    }

    public Graphics2D getTopBufferGraphics() {
        return topBufferGraphics;
    }

    public void clearBottomBuffer() {
        bottomBufferGraphics.setBackground(new Color(0, 0, 0, 0));
        bottomBufferGraphics.clearRect(0, 0, bottomBuffer.getWidth(), bottomBuffer.getHeight());
    }

    public void clearTopBuffer() {
        topBufferGraphics.setBackground(new Color(0, 0, 0, 0));
        topBufferGraphics.clearRect(0, 0, topBuffer.getWidth(), topBuffer.getHeight());
    }

    @Override
    protected void updateView() {

    }

    @Override
    public void update(NotificationType notificationType, Object object) {
        switch (notificationType) {
            case DELETE -> {
                getParentView().removeView(this);
            }

            case RENAME -> {
                getParent().add(this, model.getName());
                ((ProjectView)getParentView()).updateTitle(model.getName());
            }

            case ADD_ELEMENT -> {
                ElementPainter linkPainter;
                if (object instanceof TermElement termElement) {
                    linkPainter = new TermPainter(termElement);
                    painters.add(linkPainter);
                    selectElements(List.of(linkPainter));
                } else if (object instanceof LinkElement linkElement) {
                    linkPainter = new LinkPainter(linkElement);
                    painters.add(0, linkPainter);
                    selectElements(List.of(linkPainter));
                }
                repaint();
            }
        }
        super.update(notificationType, object);
    }
}
