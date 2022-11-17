package dsw.gerumap.app.gui.swing.tree.controller;

import dsw.gerumap.app.gui.swing.view.custom.CustomContextMenu;
import dsw.gerumap.app.gui.swing.tree.view.TreeView;

import java.awt.*;
import java.awt.event.*;

public class CustomTreeMouseListener implements MouseListener, MouseMotionListener {

    private TreeView treeView;

    public CustomTreeMouseListener(TreeView treeView) {
        this.treeView = treeView;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        treeView.setSelected(null);
        treeView.requestFocus();

        // Select item under
        int row = treeView.getRowForLocation(e.getX(), e.getY());
        if (row != -1) {
            treeView.setSelectionRow(row);
        }

        // Check if right mouse button
        if (e.getButton() == MouseEvent.BUTTON3) {
            // !!!UPGRADE!!! Chow context menu
            new CustomContextMenu(treeView.getSelected().getModel()).show(treeView, e.getX(), e.getY());

        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        int row = treeView.getRowForLocation(e.getX(), e.getY());
        if (row == -1) e.getComponent().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        else e.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
        if (treeView.getHoveredRow() == row) return;
        treeView.setHoveredRow(row);
    }

}