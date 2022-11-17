package dsw.gerumap.app.gui.swing.tree.controller;

import com.sun.source.tree.Tree;
import dsw.gerumap.app.gui.swing.tree.model.TreeItem;
import dsw.gerumap.app.gui.swing.tree.view.TreeView;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.observer.NotificationType;

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
            selected = (TreeItem) treeView.getModel().getRoot();
        }

        // Update actions
        MainFrame.getInstance().getActionManager().notifyListeners(NotificationType.SELECTED, selected.getModel());

        // Notify model listeners
        selected.getModel().notifyListeners(NotificationType.SELECTED, null);
    }
}
