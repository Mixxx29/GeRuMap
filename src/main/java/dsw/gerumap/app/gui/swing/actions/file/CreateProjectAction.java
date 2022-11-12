package dsw.gerumap.app.gui.swing.actions.file;

import dsw.gerumap.app.gui.swing.actions.AbstractCustomAction;
import dsw.gerumap.app.gui.swing.resources.ResourceLoader;
import dsw.gerumap.app.gui.swing.resources.ResourceType;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class CreateProjectAction extends AbstractCustomAction {

    public CreateProjectAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_DOWN_MASK));
        putValue(SMALL_ICON, ResourceLoader.load("project.png", ResourceType.ICON));
        putValue(LARGE_ICON_KEY, ResourceLoader.load("create_project.png", ResourceType.ICON));
        putValue(NAME, " Project ");
        putValue(SHORT_DESCRIPTION, " Create new project ");
    }

    @Override
    public void action(Object object) {
        System.out.println("Create Project!");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        action(null);
    }
}
