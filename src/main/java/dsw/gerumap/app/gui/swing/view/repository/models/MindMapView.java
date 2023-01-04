package dsw.gerumap.app.gui.swing.view.repository.models;

import dsw.gerumap.app.gui.swing.view.repository.composite.ModelView;
import dsw.gerumap.app.gui.swing.view.repository.elements.ElementPainter;
import dsw.gerumap.app.gui.swing.view.repository.elements.LinkPainter;
import dsw.gerumap.app.gui.swing.view.repository.elements.TermPainter;
import dsw.gerumap.app.observer.NotificationType;
import dsw.gerumap.app.repository.elements.LinkElement;
import dsw.gerumap.app.repository.elements.MindMapElement;
import dsw.gerumap.app.repository.elements.TermElement;
import dsw.gerumap.app.repository.models.MindMap;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class MindMapView extends ModelView {

    private List<ElementPainter> painters;

    private BufferedImage bottomBuffer;
    private BufferedImage topBuffer;

    private Graphics2D bottomBufferGraphics;
    private Graphics2D topBufferGraphics;

    public Point2D.Float offset;
    public float scale;

    public MindMapView(MindMap mindMap) {
        super(mindMap);
        painters = new ArrayList<>();

        offset = new Point2D.Float(0.0f, 0.0f);
        scale = 1.0f;

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
        List<ElementPainter> reverseList = new ArrayList<>(painters);
        Collections.reverse(reverseList);
        return reverseList.iterator();
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
            painter.paint((Graphics2D)g, offset, scale);
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
        repaint();
    }

    public void clearTopBuffer() {
        topBufferGraphics.setBackground(new Color(0, 0, 0, 0));
        topBufferGraphics.clearRect(0, 0, topBuffer.getWidth(), topBuffer.getHeight());
        repaint();
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
                } else if (object instanceof LinkElement linkElement) {
                    linkPainter = new LinkPainter(linkElement);
                    painters.add(0, linkPainter);
                }
                repaint();
            }

            case REMOVE_ELEMENT -> {
                if (object instanceof MindMapElement element) {
                    for (ElementPainter painter : painters) {
                        if (painter.getElement() == element) {
                            painters.remove(painter);
                            repaint();
                            break;
                        }
                    }
                } else if (object instanceof List<?> list) {
                    painters.removeIf(painter -> list.contains(painter.getElement()));
                    repaint();
                }
            }

            case UPDATE_ELEMENTS -> {
                repaint();
            }

            case SELECT_ELEMENTS -> {
                if (object instanceof List<?> elements) {
                    for (ElementPainter painter : painters) {
                        painter.setSelected(elements.contains(painter.getElement()));
                    }
                    repaint();
                }
            }

            case EXPORT_AS_PNG -> {
                if (object instanceof String destination) {
                    BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
                    Graphics2D g = image.createGraphics();
                    printAll(g);
                    g.dispose();
                    try {
                        ImageIO.write(image, "png", new File(destination));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        super.update(notificationType, object);
    }
}