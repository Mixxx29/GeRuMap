package dsw.gerumap.app.gui.swing.tree.model;

import dsw.gerumap.app.repository.composite.ModelNode;

import javax.swing.tree.DefaultMutableTreeNode;

public class TreeItem extends DefaultMutableTreeNode {

    private final ModelNode model;

    public TreeItem(ModelNode model) {
        this.model = model;
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
}
