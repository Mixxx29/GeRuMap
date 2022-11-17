package dsw.gerumap.app.gui.swing.view.repository.composite;

import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.gui.swing.view.repository.FolderView;
import dsw.gerumap.app.gui.swing.view.repository.MindMapView;
import dsw.gerumap.app.gui.swing.view.repository.ProjectView;
import dsw.gerumap.app.gui.swing.view.repository.preview.ModelPreview;
import dsw.gerumap.app.observer.IListener;
import dsw.gerumap.app.observer.NotificationType;
import dsw.gerumap.app.repository.composite.ModelNode;
import dsw.gerumap.app.repository.models.Folder;
import dsw.gerumap.app.repository.models.MindMap;
import dsw.gerumap.app.repository.models.Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public abstract class ModelView extends JPanel implements IListener {

    protected ModelNode model;
    protected ModelPreview preview;
    protected CompositeModelView parentView;


    public ModelView(ModelNode model) {
        this.model = model;
        model.addListener(this);

        preview = ModelPreview.createPreview(model);

        setLayout(new BorderLayout());

        setMinimumSize(new Dimension(200, 0));

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                updateView();
                super.componentResized(e);
            }
        });
    }

    public ModelNode getModel() {
        return model;
    }

    public ModelPreview getPreview() {
        return preview;
    }

    public CompositeModelView getParentView() {
        return parentView;
    }

    public void setParentView(CompositeModelView parentView) {
        this.parentView = parentView;
    }

    @Override
    public String getName() {
        return model.getName();
    }

    public void display() {
        if (parentView == null) return;
        parentView.display(this);
    }

    @Override
    public void update(NotificationType notificationType, Object object) {
        switch (notificationType) {
            case SELECTED -> {
                MainFrame.getInstance().getEditorWindow().setDisplayedViewName(model.getName());
                display();
            }
        }
    }

    public static ModelView createView(ModelNode model) {
        if (model instanceof Folder folder) {
            return new FolderView(folder);
        } else if (model instanceof Project project) {
            return new ProjectView(project);
        } else if (model instanceof MindMap mindMap) {
            return new MindMapView(mindMap);
        }
        return null;
    }

    protected abstract void updateView();
}
