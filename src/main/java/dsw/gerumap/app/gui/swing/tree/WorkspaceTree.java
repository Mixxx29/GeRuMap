package dsw.gerumap.app.gui.swing.tree;

import dsw.gerumap.app.gui.swing.tree.model.TreeItem;
import dsw.gerumap.app.gui.swing.tree.view.TreeView;
import dsw.gerumap.app.repository.models.Workspace;

import javax.swing.tree.DefaultTreeModel;

public class WorkspaceTree implements ITree {

    private TreeView treeView;
    private DefaultTreeModel treeModel;

    @Override
    public TreeView generateView(Workspace workspace) {
        TreeItem root = new TreeItem(workspace);
        treeModel = new DefaultTreeModel(root);
        treeView = new TreeView(treeModel);
        return treeView;
    }

    @Override
    public void addItem(TreeItem item) {

    }

    @Override
    public TreeItem getSelected() {
        return null;
    }

    @Override
    public void setSelected(TreeItem item) {

    }
}
