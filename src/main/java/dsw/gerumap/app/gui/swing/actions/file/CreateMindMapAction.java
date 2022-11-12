package dsw.gerumap.app.gui.swing.actions.file;

import dsw.gerumap.app.gui.swing.actions.AbstractCustomAction;
import dsw.gerumap.app.gui.swing.resources.ResourceLoader;
import dsw.gerumap.app.gui.swing.resources.ResourceType;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class CreateMindMapAction extends AbstractCustomAction {

    public CreateMindMapAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_M, InputEvent.CTRL_DOWN_MASK));
        putValue(SMALL_ICON, ResourceLoader.load("mind_map.png", ResourceType.ICON));
        putValue(LARGE_ICON_KEY, ResourceLoader.load("create_mind_map.png", ResourceType.ICON));
        putValue(NAME, " Mind Map ");
        putValue(SHORT_DESCRIPTION, " Create new mind map ");
    }

    @Override
    public void action(Object object) {
        System.out.println("Create Mind Map!");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        action(null);
    }

}
