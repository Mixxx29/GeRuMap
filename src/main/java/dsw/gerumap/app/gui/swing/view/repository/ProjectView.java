package dsw.gerumap.app.gui.swing.view.repository;

import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.gui.swing.view.custom.CreateModelButton;
import dsw.gerumap.app.gui.swing.view.custom.CustomContextMenu;
import dsw.gerumap.app.gui.swing.view.custom.CustomScrollPane;
import dsw.gerumap.app.gui.swing.view.repository.composite.CompositeModelView;
import dsw.gerumap.app.gui.swing.view.repository.composite.ModelView;
import dsw.gerumap.app.gui.swing.view.repository.preview.ModelPreview;
import dsw.gerumap.app.observer.NotificationType;
import dsw.gerumap.app.repository.composite.CompositeModelNode;
import dsw.gerumap.app.repository.factory.ModelType;
import dsw.gerumap.app.repository.models.MindMap;
import dsw.gerumap.app.repository.models.Project;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ProjectView extends CompositeModelView {

    private final JPanel content;
    private final JPanel previews;

    private final CustomScrollPane previewsScroll;

    private final CreateModelButton createMindMapButton;

    public ProjectView(Project project) {
        super(project);

        setLayout(new BorderLayout());

        content = new JPanel(new CardLayout()) {
            @Override
            public void paint(Graphics g) {
                super.paint(g);
                g.setColor(new Color(150, 150, 150));
                g.drawLine(getWidth() - 1, 0, getWidth() - 1, getHeight() - 1);
            }
        };
        content.setBorder(new EmptyBorder(0, 0, 0 ,1));
        add(content, BorderLayout.CENTER);

        previews = new JPanel(new GridBagLayout());

        previews.setBackground(new Color(90, 90, 90));
        previews.setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel previewsWrapper = new JPanel();
        previewsWrapper.setBackground(new Color(90, 90, 90));
        previewsWrapper.setBorder(new EmptyBorder(0, 0, 0, 0));
        previewsWrapper.add(previews);

        previewsScroll = new CustomScrollPane(previewsWrapper);
        previewsScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        previewsScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        previewsScroll.getVerticalScrollBar().setPreferredSize(new Dimension(10, Integer.MAX_VALUE));
        add(previewsScroll, BorderLayout.EAST);

        previewsScroll.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // Update actions
                MainFrame.getInstance().getActionManager().notifyListeners(NotificationType.SELECTED, model);

                // Check if right mouse button
                if (e.getButton() == MouseEvent.BUTTON3) {
                    // !!!UPGRADE!!! Chow context menu
                    new CustomContextMenu(model).show(previews, e.getX(), e.getY());
                }
            }
        });

        createMindMapButton = new CreateModelButton(ModelType.MIND_MAP);
        createMindMapButton.setParent((CompositeModelNode) model);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                // Update actions
                MainFrame.getInstance().getActionManager().notifyListeners(NotificationType.SELECTED, model);

                // Check if right mouse button
                if (e.getButton() == MouseEvent.BUTTON3) {
                    // !!!UPGRADE!!! Chow context menu
                    new CustomContextMenu(model).show(ProjectView.this, e.getX(), e.getY());
                }
            }
        });
    }

    @Override
    public void addView(ModelView view) {
        if (!(view instanceof MindMapView)) return;
        super.addView(view);
        content.add(view, view.getName());
        selectPreview(view.getPreview());
        previewsScroll.getVerticalScrollBar().setValue(previewsScroll.getVerticalScrollBar().getMaximum());
    }

    @Override
    public void removeView(ModelView view) {
        if (!(view instanceof MindMapView)) return;
        super.removeView(view);
        content.remove(view);
        updateView();
    }

    @Override
    public void update(NotificationType notificationType, Object object) {
        switch (notificationType) {
            case CREATE -> {
                if (object instanceof MindMap mindMap) {
                    addView(ModelView.createView(mindMap));
                }
            }

            case RENAME -> {
                getParent().add(this, model.getName());
            }

            case DELETE -> {
                parentView.removeView(this);
            }
        }
        super.update(notificationType, object);
    }

    @Override
    protected void updateView() {
        previews.removeAll();

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(5, 0, 5, 0);

        for (ModelView view : views) {
            previews.add(view.getPreview(), constraints);
            constraints.gridy++;
        }

        previews.add(createMindMapButton, constraints);

        updateUI();
    }

    @Override
    public void displayView(ModelView view) {
        super.displayView(view);
        ((CardLayout)content.getLayout()).show(content, view.getName());
    }

    @Override
    public void selectPreview(ModelPreview preview) {
        super.selectPreview(preview);
        displayView(preview.getView());
        Point pt = previewsScroll.getViewport().getViewPosition();
        Rectangle rectangle = new Rectangle(
                preview.getX(),
                preview.getY() - (previewsScroll.getViewport().getHeight() - preview.getHeight()) / 2,
                preview.getWidth(),
                previewsScroll.getViewport().getHeight()
        );
        rectangle.translate(-pt.x, -pt.y);
        previewsScroll.getViewport().scrollRectToVisible(rectangle);
    }
}
