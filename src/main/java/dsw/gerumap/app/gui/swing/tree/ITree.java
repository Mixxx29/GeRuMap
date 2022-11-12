package dsw.gerumap.app.gui.swing.tree;

import dsw.gerumap.app.gui.swing.tree.model.TreeItem;
import dsw.gerumap.app.gui.swing.tree.view.TreeView;
import dsw.gerumap.app.repository.models.Workspace;

public interface ITree {
    TreeView generateView(Workspace workspace);
    void addItem(TreeItem item);
    TreeItem getSelected();
    void setSelected(TreeItem item);
}
