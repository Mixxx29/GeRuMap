package dsw.gerumap.app.gui.swing.tree.controller;

import com.sun.source.tree.Tree;
import dsw.gerumap.app.gui.swing.tree.model.TreeItem;
import dsw.gerumap.app.gui.swing.tree.view.TreeView;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

public class CustomTreeSelectionListener implements TreeSelectionListener {

    private TreeView treeView;

    public CustomTreeSelectionListener(TreeView treeView) {
        this.treeView = treeView;
    }

    @Override
    public void valueChanged(TreeSelectionEvent e) {
        TreeItem selected = (TreeItem) treeView.getLastSelectedPathComponent();

        if (selected == null) {
            treeView.setSelectionRow(0);
        }
    }
}
