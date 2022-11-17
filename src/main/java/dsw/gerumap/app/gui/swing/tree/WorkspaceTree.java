package dsw.gerumap.app.gui.swing.tree;

import dsw.gerumap.app.gui.swing.tree.model.TreeItem;
import dsw.gerumap.app.gui.swing.tree.view.TreeView;
import dsw.gerumap.app.repository.models.Workspace;

import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

public class WorkspaceTree implements ITree {

    private TreeView treeView;
    private DefaultTreeModel treeModel;

    @Override
    public TreeView generateView(Workspace workspace) {
        TreeItem root = new TreeItem(workspace, this);
        treeModel = new DefaultTreeModel(root);
        treeView = new TreeView(treeModel);
        return treeView;
    }

    @Override
    public void addItem(TreeItem item) {

    }

    @Override
    public TreeItem getSelected() {
        return treeView.getSelected();
    }

    @Override
    public void setSelected(TreeItem item) {
        treeView.setSelected(item);
    }

    @Override
    public void expandPath(TreeItem item) {
        treeView.expandPath(new TreePath(item.getPath()));
        treeView.updateUI();
    }

    @Override
    public void refresh() {
        treeView.updateUI();
    }
}