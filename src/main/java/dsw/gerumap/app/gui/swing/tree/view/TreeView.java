package dsw.gerumap.app.gui.swing.tree.view;

import dsw.gerumap.app.gui.swing.tree.controller.CustomTreeMouseListener;
import dsw.gerumap.app.gui.swing.tree.controller.CustomTreeSelectionListener;
import dsw.gerumap.app.gui.swing.tree.model.TreeItem;

import javax.swing.*;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

public class TreeView extends JTree {

    private int hoveredRow = -1;

    public TreeView(TreeModel treeModel) {
        setModel(treeModel);
        setCellRenderer(new TreeCellRenderer());
        addTreeSelectionListener(new CustomTreeSelectionListener(this));

        CustomTreeMouseListener mouseListener = new CustomTreeMouseListener(this);
        addMouseListener(mouseListener);
        addMouseMotionListener(mouseListener);

        setRootVisible(false);

        setSelectionPath(new TreePath(((TreeItem)treeModel.getRoot()).getPath()));
    }

    public int getHoveredRow() {
        return hoveredRow;
    }

    public void setHoveredRow(int hoveredRow) {
        this.hoveredRow = hoveredRow;
        updateUI();
    }

}