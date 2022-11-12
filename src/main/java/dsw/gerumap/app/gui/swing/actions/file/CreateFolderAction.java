package dsw.gerumap.app.gui.swing.actions.file;

import dsw.gerumap.app.gui.swing.actions.AbstractCustomAction;
import dsw.gerumap.app.gui.swing.resources.ResourceLoader;
import dsw.gerumap.app.gui.swing.resources.ResourceType;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class CreateFolderAction extends AbstractCustomAction {

    public CreateFolderAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_DOWN_MASK));
        putValue(SMALL_ICON, ResourceLoader.load("folder.png", ResourceType.ICON));
        putValue(LARGE_ICON_KEY, ResourceLoader.load("create_folder.png", ResourceType.ICON));
        putValue(NAME, " Folder ");
        putValue(SHORT_DESCRIPTION, " Create new folder ");
    }

    @Override
    public void action(Object object) {
        System.out.println("Create Folder!");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        action(null);
    }
}
