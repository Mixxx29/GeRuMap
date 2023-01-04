package dsw.gerumap.app.gui.swing.view.custom;

import dsw.gerumap.app.gui.swing.actions.ActionManager;
import dsw.gerumap.app.gui.swing.tree.model.TreeItem;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.repository.composite.ModelNode;
import dsw.gerumap.app.resources.ResourceLoader;
import dsw.gerumap.app.resources.ResourceType;

import javax.swing.*;

public class CustomContextMenu extends JPopupMenu {

    public CustomContextMenu(ModelNode modelNode) {
        // Get action manager
        ActionManager actionManager = MainFrame.getInstance().getActionManager();

        setFocusable(false);

        // Create 'Create' menu
        JMenu createMenu = new JMenu(" Create ");
        createMenu.getPopupMenu().setFocusable(false);
        createMenu.setIcon(ResourceLoader.load("create.png", ResourceType.ICON));
        createMenu.add(actionManager.getCreateFolderAction());
        createMenu.add(actionManager.getCreateProjectAction());
        createMenu.add(actionManager.getCreateMindMapAction());
        add(createMenu);

        add(actionManager.getOpenProjectAction());
        add(actionManager.getLoadTemplateAction());

        add(new CustomMenuSeparator());

        add(actionManager.getRenameAction());
        add(actionManager.getDeleteAction());

        add(new CustomMenuSeparator());

        add(actionManager.getMindMapSettingsAction());

        add(new CustomMenuSeparator());

        // Create 'Create' menu
        JMenu exportMenu = new JMenu(" Export ");
        exportMenu.setIcon(ResourceLoader.load("export.png", ResourceType.ICON));
        exportMenu.add(actionManager.getExportAsPNGAction());
        exportMenu.add(actionManager.getExportAsTemplateAction());
        add(exportMenu);
    }
}
