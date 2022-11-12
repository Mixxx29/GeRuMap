package dsw.gerumap.app.gui.swing.tree.view;

import dsw.gerumap.app.gui.swing.tree.controller.CustomTreeMouseListener;
import dsw.gerumap.app.gui.swing.tree.controller.CustomTreeSelectionListener;

import javax.swing.*;
import javax.swing.tree.TreeModel;

public class TreeView extends JTree {

    private int hoveredRow = -1;

    public TreeView(TreeModel treeModel) {
        setModel(treeModel);
        setCellRenderer(new TreeCellRenderer());
        addTreeSelectionListener(new CustomTreeSelectionListener(this));

        CustomTreeMouseListener mouseListener = new CustomTreeMouseListener(this);
        addMouseListener(mouseListener);
        addMouseMotionListener(mouseListener);

        setSelectionRow(0);
    }

    public int getHoveredRow() {
        return hoveredRow;
    }

    public void setHoveredRow(int hoveredRow) {
        this.hoveredRow = hoveredRow;
        updateUI();
    }

}
