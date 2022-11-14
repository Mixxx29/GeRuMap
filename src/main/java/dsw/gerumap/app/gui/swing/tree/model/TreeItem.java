package dsw.gerumap.app.gui.swing.tree.model;

import dsw.gerumap.app.gui.swing.tree.ITree;
import dsw.gerumap.app.gui.swing.tree.view.TreeView;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.observer.IListener;
import dsw.gerumap.app.observer.NotificationType;
import dsw.gerumap.app.repository.composite.ModelNode;

import javax.swing.tree.DefaultMutableTreeNode;

public class TreeItem extends DefaultMutableTreeNode implements IListener {

    private final ModelNode model;
    private ITree tree;

    public TreeItem(ModelNode model, ITree tree) {
        this.model = model;
        this.tree = tree;
        model.addListener(this);
    }

    public ModelNode getModel() {
        return model;
    }

    public String getName() {
        return model.getName();
    }

    @Override
    public String toString() {
        return model.getName();
    }

    @Override
    public void update(NotificationType notificationType, Object object) {
        switch (notificationType) {
            case CREATE -> {
                if (object instanceof ModelNode modelNode) {
                    TreeItem newItem = new TreeItem(modelNode, tree);
                    add(newItem);
                    tree.setSelected(newItem);
                }
            }

            case DELETE -> {
                tree.setSelected((TreeItem) parent);
                parent.remove(this);
            }
        }

        tree.refresh();
    }
}