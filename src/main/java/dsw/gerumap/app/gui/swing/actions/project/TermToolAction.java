package dsw.gerumap.app.gui.swing.actions.project;

import dsw.gerumap.app.gui.swing.actions.AbstractCustomAction;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.gui.swing.view.repository.models.ProjectView;
import dsw.gerumap.app.observer.IPublisher;
import dsw.gerumap.app.observer.NotificationType;
import dsw.gerumap.app.repository.models.MindMap;
import dsw.gerumap.app.repository.models.Project;
import dsw.gerumap.app.resources.ResourceLoader;
import dsw.gerumap.app.resources.ResourceType;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class TermToolAction extends AbstractCustomAction {

    public TermToolAction(IPublisher publisher) {
        super(publisher);
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_T, 0));
        putValue(SMALL_ICON, ResourceLoader.load("term_tool.png", ResourceType.ICON));
        putValue(LARGE_ICON_KEY, ResourceLoader.load("term_tool_large.png", ResourceType.ICON));
        putValue(NAME, " Term Tool ");
        putValue(SHORT_DESCRIPTION, " Term Tool  (T) ");
    }

    @Override
    public void action(Object object) {
        ProjectView projectView = MainFrame.getInstance().getEditorWindow().getActiveProjectView();
        if (projectView != null) projectView.setTermToolState();
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
