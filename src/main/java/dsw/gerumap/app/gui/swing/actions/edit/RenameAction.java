package dsw.gerumap.app.gui.swing.actions.edit;

import dsw.gerumap.app.gui.swing.actions.AbstractCustomAction;
import dsw.gerumap.app.gui.swing.resources.ResourceLoader;
import dsw.gerumap.app.gui.swing.resources.ResourceType;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class RenameAction extends AbstractCustomAction {

    public RenameAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK));
        putValue(SMALL_ICON, ResourceLoader.load("rename.png", ResourceType.ICON));
        putValue(NAME, " Rename ");
        putValue(SHORT_DESCRIPTION, " Rename action ");
    }

    @Override
    public void action(Object object) {
        System.out.println("Rename Action");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        action(null);
    }
}
