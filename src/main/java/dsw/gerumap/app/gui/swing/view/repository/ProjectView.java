package dsw.gerumap.app.gui.swing.view.repository;

import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.gui.swing.view.custom.CreateModelButton;
import dsw.gerumap.app.gui.swing.view.repository.composite.CompositeModelView;
import dsw.gerumap.app.gui.swing.view.repository.composite.ModelView;
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

    private final CreateModelButton createMindMapButton;

    public ProjectView(Project project) {
        super(project);

        setLayout(new BorderLayout());

        content = new JPanel(new CardLayout());
        add(content, BorderLayout.CENTER);

        previews = new JPanel(new GridBagLayout()) {
            @Override
            public void paint(Graphics g) {
                super.paint(g);
                g.setColor(new Color(150, 150, 150));
                g.drawLine(0, 0, 0, getHeight() - 1);
            }
        };
        previews.setBackground(new Color(90, 90, 90));
        previews.setBorder(new EmptyBorder(10, 10, 10, 10));
        add(previews, BorderLayout.EAST);

        createMindMapButton = new CreateModelButton(ModelType.MIND_MAP);
        createMindMapButton.setParent((CompositeModelNode) model);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {

            }
        });
    }

    @Override
    public void addView(ModelView view) {
        if (!(view instanceof MindMapView)) return;
        content.add(view, view.getName());
        MainFrame.getInstance().getEditorWindow().selectPreview(view.getPreview());
        super.addView(view);
    }

    @Override
    public void update(NotificationType notificationType, Object object) {
        super.update(notificationType, object);
        switch (notificationType) {
            case CREATE -> {
                if (object instanceof MindMap mindMap) {
                    addView(ModelView.createView(mindMap));
                }
            }

            case RENAME -> {
                getParent().add(this, model.getName());
                ((CardLayout)getParent().getLayout()).show(getParent(), model.getName());
            }

            case DELETE -> {
                parentView.removeView(this);
            }
        }
    }

    @Override
    protected void updateView() {
        previews.removeAll();

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(10, 0, 10, 0);

        for (ModelView view : views) {
            previews.add(view.getPreview(), constraints);
            constraints.gridy++;
        }

        previews.add(createMindMapButton, constraints);

        updateUI();
    }

    @Override
    public void display(ModelView view) {
        super.display(view);
        ((CardLayout)content.getLayout()).show(content, view.getName());
    }
}
