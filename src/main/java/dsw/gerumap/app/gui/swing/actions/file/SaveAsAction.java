package dsw.gerumap.app.gui.swing.actions.file;

import dsw.gerumap.app.gui.swing.actions.AbstractCustomAction;
import dsw.gerumap.app.resources.ResourceLoader;
import dsw.gerumap.app.resources.ResourceType;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class SaveAsAction extends AbstractCustomAction {

    public SaveAsAction() {
        putValue(
                ACCELERATOR_KEY,
                KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK)
        );
        putValue(SMALL_ICON, ResourceLoader.load("save_as.png", ResourceType.ICON));
        putValue(NAME, " Save As ");
        putValue(SHORT_DESCRIPTION, " Save workspace as ");
    }

    @Override
    public void action(Object object) {
        System.out.println("Save workspace as");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        action(null);
    }
}