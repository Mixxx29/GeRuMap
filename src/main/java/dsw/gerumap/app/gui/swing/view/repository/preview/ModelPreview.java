package dsw.gerumap.app.gui.swing.view.repository.preview;

import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.gui.swing.view.custom.CustomContextMenu;
import dsw.gerumap.app.gui.swing.view.repository.FolderView;
import dsw.gerumap.app.gui.swing.view.repository.MindMapView;
import dsw.gerumap.app.gui.swing.view.repository.ProjectView;
import dsw.gerumap.app.gui.swing.view.repository.composite.CompositeModelView;
import dsw.gerumap.app.gui.swing.view.repository.composite.ModelView;
import dsw.gerumap.app.observer.IListener;
import dsw.gerumap.app.observer.NotificationType;
import dsw.gerumap.app.repository.composite.CompositeModelNode;
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

    protected final ModelView view;
    private boolean selected;
    protected CompositeModelView parentView;

    public ModelPreview(ModelView view) {
        this.view = view;
        view.getModel().addListener(this);

        setOpaque(false);
        setPreferredSize(new Dimension(150, 150));
        setCursor(new Cursor(Cursor.HAND_CURSOR));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getSource() instanceof MindMapPreview) return;

                parentView.selectPreview(ModelPreview.this);

                // Update actions
                MainFrame.getInstance().getActionManager().notifyListeners(NotificationType.SELECTED, view.getModel());

                // Check if right mouse button
                if (e.getButton() == MouseEvent.BUTTON3) {
                    // !!!UPGRADE!!! Chow context menu
                    new CustomContextMenu(view.getModel()).show(ModelPreview.this, e.getX(), e.getY());
                } else if (e.getClickCount() == 2) {
                    view.getModel().notifyListeners(NotificationType.SELECT, null);
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

    public ModelView getView() {
        return view;
    }

    public void setParentView(CompositeModelView parentView) {
        this.parentView = parentView;
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

    public static ModelPreview createPreview(ModelView modelView) {
        if (modelView instanceof FolderView folderView) {
            return new FolderPreview(folderView);
        } else if (modelView instanceof ProjectView projectView) {
            return new ProjectPreview(projectView);
        } else if (modelView instanceof MindMapView mindMapView) {
            return new MindMapPreview(mindMapView);
        }
        return null;
    }
}
