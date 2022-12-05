package dsw.gerumap.app.gui.swing.actions.project;

import dsw.gerumap.app.gui.swing.actions.AbstractCustomAction;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.gui.swing.view.repository.ProjectView;
import dsw.gerumap.app.observer.IPublisher;
import dsw.gerumap.app.observer.NotificationType;
import dsw.gerumap.app.repository.models.MindMap;
import dsw.gerumap.app.repository.models.Project;
import dsw.gerumap.app.resources.ResourceLoader;
import dsw.gerumap.app.resources.ResourceType;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class MoveToolAction extends AbstractCustomAction {

    public MoveToolAction(IPublisher publisher) {
        super(publisher);
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_M, 0));
        putValue(SMALL_ICON, ResourceLoader.load("move_tool.png", ResourceType.ICON));
        putValue(LARGE_ICON_KEY, ResourceLoader.load("move_tool_large.png", ResourceType.ICON));
        putValue(NAME, " Move Tool ");
        putValue(SHORT_DESCRIPTION, " Move Tool  (M) ");
    }

    @Override
    public void action(Object object) {
        ProjectView projectView = MainFrame.getInstance().getEditorWindow().getActiveProjectView();
        if (projectView != null) projectView.setMoveToolState();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        action(null);
    }

    @Override
    public void update(NotificationType notificationType, Object object) {
        super.update(notificationType, object);
        setEnabled(false);
        if (selected instanceof Project || selected instanceof MindMap) setEnabled(true);
    }
}
