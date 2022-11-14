package dsw.gerumap.app.gui.swing.actions.edit;

import dsw.gerumap.app.gui.swing.actions.AbstractCustomAction;
import dsw.gerumap.app.resources.ResourceLoader;
import dsw.gerumap.app.resources.ResourceType;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class MindMapSettingsAction extends AbstractCustomAction {

    public MindMapSettingsAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_DOWN_MASK));
        putValue(SMALL_ICON, ResourceLoader.load("mind_map_settings.png", ResourceType.ICON));
        putValue(NAME, " Mind Map Settings ");
        putValue(SHORT_DESCRIPTION, " Mind map settings ");
    }

    @Override
    public void action(Object object) {
        System.out.println("Mind Map Settings Action");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        action(null);
    }
}