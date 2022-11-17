package dsw.gerumap.app.gui.swing.view.repository;

import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.gui.swing.view.custom.CreateModelButton;
import dsw.gerumap.app.gui.swing.view.custom.CustomContextMenu;
import dsw.gerumap.app.gui.swing.view.repository.composite.CompositeModelView;
import dsw.gerumap.app.gui.swing.view.repository.composite.ModelView;
import dsw.gerumap.app.gui.swing.view.repository.preview.ModelPreview;
import dsw.gerumap.app.observer.NotificationType;
import dsw.gerumap.app.repository.composite.ModelNode;
import dsw.gerumap.app.repository.factory.ModelType;
import dsw.gerumap.app.repository.models.Folder;
import dsw.gerumap.app.repository.models.MindMap;
import dsw.gerumap.app.repository.models.Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FolderView extends CompositeModelView {

    private final JPanel content;
    private final JPanel previews;

    public FolderView(Folder folder) {
        super(folder);
        content = new JPanel(new CardLayout());
        add(content);

        previews = new JPanel(new GridBagLayout());
        content.add(previews, "Previews");

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                System.out.println("Kita");
                if (!(e.getSource() instanceof FolderView)) return;
                System.out.println("Kita2");

                if (e.getSource() instanceof FolderView) {
                    System.out.println(e.getSource());
                }

                // Select null
                MainFrame.getInstance().getEditorWindow().selectPreview(null);

                // Update actions
                MainFrame.getInstance().getActionManager().notifyListeners(NotificationType.SELECTED, model);

                // Check if right mouse button
                if (e.getButton() == MouseEvent.BUTTON3) {
                    // !!!UPGRADE!!! Chow context menu
                    new CustomContextMenu(model).show(FolderView.this, e.getX(), e.getY());
                }
            }
        });
    }

    @Override
    public void addView(ModelView view) {
        if (!(view instanceof FolderView) && !(view instanceof ProjectView)) return;
        content.add(view, view.getName());
        MainFrame.getInstance().getEditorWindow().selectPreview(view.getPreview());
        super.addView(view);
        if (view instanceof ProjectView) {
            view.getModel().notifyListeners(NotificationType.SELECT, null);
            ((Project)view.getModel()).addNode(new MindMap(MindMap.DEFAULT_NAME));
        }
    }

    @Override
    public void removeView(ModelView view) {
        if (!(view instanceof FolderView) && !(view instanceof ProjectView)) return;
        content.remove(view);
        super.removeView(view);
    }

    @Override
    public void update(NotificationType notificationType, Object object) {
        super.update(notificationType, object);
        switch (notificationType) {
            case CREATE -> {
                if (object instanceof ModelNode modelNode) {
                    addView(ModelView.createView(modelNode));
                }
            }

            case DELETE -> {
                parentView.removeView(this);
            }

            case RENAME -> {
                getParent().add(this, model.getName());
                ((CardLayout)getParent().getLayout()).show(getParent(), model.getName());
            }
        }
    }

    @Override
    protected void updateView() {
        previews.removeAll(); // Clear content

        GridBagConstraints constraints = new GridBagConstraints();

        if (views.size() == 0) {
            constraints.gridx = 0;
            constraints.gridy = 0;
            previews.add(new CreateModelButton(ModelType.FOLDER), constraints);
            constraints.gridx = 1;
            constraints.gridy = 0;
            previews.add(new CreateModelButton(ModelType.PROJECT), constraints);
            updateUI();
            return;
        }

        int width = 0;
        int maxWidth = previews.getWidth(); // Get content width

        constraints.gridx = 0;
        constraints.gridy = 0;

        for (ModelView view : views) {
            ModelPreview viewPreview = view.getPreview();
            if (width + viewPreview.getPreferredSize().width > maxWidth) {
                width = 0;
                constraints.gridx = 0;
                constraints.gridy++;
            }
            width += viewPreview.getPreferredSize().width;
            previews.add(viewPreview, constraints);
            viewPreview.updateUI();
            constraints.gridx++;
        }
        updateUI();
    }

    @Override
    public void display() {
        super.display();
        MainFrame.getInstance().getEditorWindow().selectPreview(null);
        ((CardLayout)content.getLayout()).show(content, "Previews");
    }

    @Override
    public void display(ModelView view) {
        super.display(view);
        ((CardLayout)content.getLayout()).show(content, view.getName());
    }
}
