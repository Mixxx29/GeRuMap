package dsw.gerumap.app.gui.swing.actions.edit;

import dsw.gerumap.app.gui.swing.actions.AbstractCustomAction;
import dsw.gerumap.app.gui.swing.resources.ResourceLoader;
import dsw.gerumap.app.gui.swing.resources.ResourceType;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class DeleteAction extends AbstractCustomAction {

    public DeleteAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0));
        putValue(SMALL_ICON, ResourceLoader.load("delete.png", ResourceType.ICON));
        putValue(NAME, " Delete ");
        putValue(SHORT_DESCRIPTION, " Delete action ");
    }

    @Override
    public void action(Object object) {
        System.out.println("Delete action");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        action(null);
    }
}
