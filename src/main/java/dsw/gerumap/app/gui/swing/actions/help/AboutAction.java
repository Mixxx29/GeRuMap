package dsw.gerumap.app.gui.swing.actions.help;

import dsw.gerumap.app.gui.swing.actions.AbstractCustomAction;
import dsw.gerumap.app.gui.swing.resources.ResourceLoader;
import dsw.gerumap.app.gui.swing.resources.ResourceType;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class AboutAction extends AbstractCustomAction {

    public AboutAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
        putValue(SMALL_ICON, ResourceLoader.load("about.png", ResourceType.ICON));
        putValue(NAME, " About ");
        putValue(SHORT_DESCRIPTION, " About GeRuMap ");
    }

    @Override
    public void action(Object object) {
        System.out.println("About GeRuMap");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        action(null);
    }
}