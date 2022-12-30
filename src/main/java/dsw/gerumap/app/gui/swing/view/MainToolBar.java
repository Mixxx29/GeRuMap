package dsw.gerumap.app.gui.swing.view;

import dsw.gerumap.app.gui.swing.actions.ActionManager;
import dsw.gerumap.app.gui.swing.view.custom.ToolbarButton;
import dsw.gerumap.app.gui.swing.view.custom.ToolbarSeparator;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MainToolBar extends JToolBar {

    public MainToolBar() {
        super(HORIZONTAL);
        setFloatable(false);
        setBorder(new EmptyBorder(0, 5, 1, 5));
        setBackground(new Color(80, 80, 80));

        ActionManager actionManager = MainFrame.getInstance().getActionManager();

        addButton(actionManager.getCreateFolderAction());
        addButton(actionManager.getCreateProjectAction());
        addButton(actionManager.getCreateMindMapAction());

        addVerticalSeparator();

        addButton(actionManager.getSaveAction());
        addButton(actionManager.getSaveAsAction());

        addVerticalSeparator();

        addButton(actionManager.getExportAction());

        addVerticalSeparator();

        addButton(actionManager.getUndoAction());
        addButton(actionManager.getRedoAction());

        addVerticalSeparator();

        addButton(actionManager.getRenameAction());
        addButton(actionManager.getDeleteAction());

        addVerticalSeparator();

        addButton(actionManager.getMindMapSettingsAction());

        addVerticalSeparator();

        addButton(actionManager.getDevelopersAction());
        addButton(actionManager.getAboutAction());
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(new Color(150, 150, 150));
        g.drawLine(0, getHeight() - 1, getWidth(), getHeight() - 1);
    }

    private void addButton(Action action) {
        add(new ToolbarButton(action));
    }

    private void addVerticalSeparator() {
        add(new ToolbarSeparator(new Dimension(11, 22), VERTICAL));
    }
}
