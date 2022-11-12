package dsw.gerumap.app.gui.swing.tree.view;

import dsw.gerumap.app.gui.swing.resources.ResourceLoader;
import dsw.gerumap.app.gui.swing.resources.ResourceType;
import dsw.gerumap.app.gui.swing.tree.model.TreeItem;
import dsw.gerumap.app.repository.composite.ModelNode;
import dsw.gerumap.app.repository.models.Folder;
import dsw.gerumap.app.repository.models.Workspace;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;

public class TreeCellRenderer extends DefaultTreeCellRenderer {

    @Override
    public Component getTreeCellRendererComponent(JTree tree,
                                                  Object value,
                                                  boolean sel,
                                                  boolean expanded,
                                                  boolean leaf,
                                                  int row,
                                                  boolean hasFocus) {

        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);

        setBorder(new EmptyBorder(2, 5, 2, 5));
        setOpaque(true);

        if (tree.getLastSelectedPathComponent() == value) {
            setBackground(new Color(180, 180, 180));
            setForeground(new Color(50, 50, 50));
        } else if (((TreeView)tree).getHoveredRow() == row) {
            setBackground(new Color(110, 110, 110));
            setForeground(Color.white);
        } else {
            setBackground(UIManager.getColor("Tree.background"));
            setForeground(Color.white);
        }

        ModelNode model = ((TreeItem)value).getModel();

        if (model instanceof Folder || model instanceof Workspace) {
            setIcon(ResourceLoader.load("folder.png", ResourceType.ICON));
        }

        return this;
    }
}
