package dsw.gerumap.app.gui.swing.tree.view;

import dsw.gerumap.app.gui.swing.tree.controller.CustomTreeMouseListener;
import dsw.gerumap.app.gui.swing.tree.controller.CustomTreeSelectionListener;
import dsw.gerumap.app.gui.swing.tree.model.TreeItem;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class TreeView extends JTree {

    private int hoveredRow = -1;

    public TreeView(TreeModel treeModel) {
        setModel(treeModel);
        setCellRenderer(new TreeCellRenderer());
        addTreeSelectionListener(new CustomTreeSelectionListener(this));

        CustomTreeMouseListener mouseListener = new CustomTreeMouseListener(this);
        addMouseListener(mouseListener);
        addMouseMotionListener(mouseListener);

        setBackground(new Color(90, 90, 90));
        setBorder(new EmptyBorder(10, 10, 10, 10));

        setRootVisible(false);

        setSelected(null);
    }

    public int getHoveredRow() {
        return hoveredRow;
    }

    public void setHoveredRow(int hoveredRow) {
        this.hoveredRow = hoveredRow;
        updateUI();
    }

    public void setSelected(TreeItem item) {
        if (item != null) {
            setSelectionPath(new TreePath(item.getPath()));
        } else {
            setSelectionPath(new TreePath(((TreeItem)treeModel.getRoot()).getPath()));
        }
        updateUI();
    }

    public TreeItem getSelected() {
        return (TreeItem) getLastSelectedPathComponent();
    }
}