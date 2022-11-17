package dsw.gerumap.app.gui.swing.view.repository.preview;

import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.gui.swing.view.custom.CustomContextMenu;
import dsw.gerumap.app.gui.swing.view.repository.composite.CompositeModelView;
import dsw.gerumap.app.observer.IListener;
import dsw.gerumap.app.observer.NotificationType;
import dsw.gerumap.app.repository.composite.ModelNode;
import dsw.gerumap.app.repository.models.Folder;
import dsw.gerumap.app.repository.models.MindMap;
import dsw.gerumap.app.repository.models.Project;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public abstract class ModelPreview extends JPanel implements IListener {

    protected final ModelNode model;
    private boolean selected;

    public ModelPreview(ModelNode model) {
        this.model = model;
        model.addListener(this);

        setCursor(new Cursor(Cursor.HAND_CURSOR));

        setOpaque(false);
        setPreferredSize(new Dimension(150, 150));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getSource() instanceof MindMap) return;

                MainFrame.getInstance().getEditorWindow().selectPreview(ModelPreview.this);

                // Update actions
                MainFrame.getInstance().getActionManager().notifyListeners(NotificationType.SELECTED, model);

                // Check if right mouse button
                if (e.getButton() == MouseEvent.BUTTON3) {
                    // !!!UPGRADE!!! Chow context menu
                    new CustomContextMenu(model).show(ModelPreview.this, e.getX(), e.getY());
                } else if (e.getClickCount() == 2) {
                    model.notifyListeners(NotificationType.SELECT, null);
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (selected) return;

                setOpaque(true);
                setBackground(new Color(120, 120, 120));
                setBorder(new LineBorder(new Color(200, 200, 200), 1));
                updateUI();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (selected) return;

                setOpaque(false);
                setBorder(null);
                updateUI();
            }
        });
    }

    public ModelNode getModel() {
        return model;
    }


    public void select(boolean selected) {
        this.selected = selected;

        if (selected) {
            setOpaque(true);
            setBackground(new Color(150, 150, 150));
            setBorder(new LineBorder(new Color(200, 200, 200), 1));
            updateUI();
        } else {
            setOpaque(false);
            setBorder(null);
            updateUI();
        }
    }

    public static ModelPreview createPreview(ModelNode model) {
        if (model instanceof Folder folder) {
            return new FolderPreview(folder);
        } else if (model instanceof Project project) {
            return new ProjectPreview(project);
        } else if (model instanceof MindMap mindMap) {
            return new MindMapPreview(mindMap);
        }
        return null;
    }
}
