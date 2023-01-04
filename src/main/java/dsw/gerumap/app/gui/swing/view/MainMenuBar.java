package dsw.gerumap.app.gui.swing.view;

import dsw.gerumap.app.gui.swing.actions.ActionManager;
import dsw.gerumap.app.resources.ResourceLoader;
import dsw.gerumap.app.resources.ResourceType;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.KeyEvent;

public class MainMenuBar extends JMenuBar {

    public MainMenuBar() {
        setBorder(new EmptyBorder(2, 2, 3, 2));

        ActionManager actionManager = MainFrame.getInstance().getActionManager();

        // Crate 'File' menu
        JMenu fileMenu = new JMenu(" File ");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        fileMenu.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Create 'Create' menu
        JMenu createMenu = new JMenu(" Create ");
        createMenu.setIcon(ResourceLoader.load("create.png", ResourceType.ICON));
        createMenu.add(actionManager.getCreateFolderAction());
        createMenu.add(actionManager.getCreateProjectAction());
        createMenu.add(actionManager.getCreateMindMapAction());
        fileMenu.add(createMenu);
        fileMenu.add(actionManager.getOpenProjectAction());
        fileMenu.add(createSeparator());
        fileMenu.add(actionManager.getSaveAction());
        fileMenu.add(actionManager.getSaveAsAction());
        fileMenu.add(createSeparator());
        fileMenu.add(actionManager.getExportAction());
        add(fileMenu);

        // Crate 'Edit' menu
        JMenu editMenu = new JMenu(" Edit ");
        editMenu.setMnemonic(KeyEvent.VK_E);
        editMenu.setCursor(new Cursor(Cursor.HAND_CURSOR));
        editMenu.add(actionManager.getUndoAction());
        editMenu.add(actionManager.getRedoAction());
        editMenu.add(createSeparator());
        editMenu.add(actionManager.getRenameAction());
        editMenu.add(actionManager.getDeleteAction());
        editMenu.add(createSeparator());
        editMenu.add(actionManager.getMindMapSettingsAction());
        add(editMenu);

        // Crate 'Project' menu
        JMenu projectMenu = new JMenu(" Project ");
        projectMenu.setMnemonic(KeyEvent.VK_P);
        projectMenu.setCursor(new Cursor(Cursor.HAND_CURSOR));
        projectMenu.add(actionManager.getSelectToolAction());
        projectMenu.add(actionManager.getMoveToolAction());
        projectMenu.add(actionManager.getZoomToolAction());
        projectMenu.add(actionManager.getEraseToolAction());
        projectMenu.add(createSeparator());
        projectMenu.add(actionManager.getTermToolAction());
        projectMenu.add(actionManager.getLinkToolAction());
        add(projectMenu);

        // Crate 'Help' menu
        JMenu helpMenu = new JMenu(" Help ");
        helpMenu.setMnemonic(KeyEvent.VK_H);
        helpMenu.setCursor(new Cursor(Cursor.HAND_CURSOR));
        helpMenu.add(actionManager.getDevelopersAction());
        helpMenu.add(actionManager.getAboutAction());
        add(helpMenu);
    }

    private JSeparator createSeparator() {
        JSeparator sep = new JSeparator();
        sep.setBackground(new Color(150, 150, 150));
        sep.setForeground(new Color(150, 150, 150));
        return sep;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(new Color(150, 150, 150));
        g.drawLine(0, getHeight() - 1, getWidth() - 1, getHeight() - 1);
    }
}
