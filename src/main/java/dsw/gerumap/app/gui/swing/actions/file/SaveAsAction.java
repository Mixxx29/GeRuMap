package dsw.gerumap.app.gui.swing.actions.file;

import dsw.gerumap.app.core.App;
import dsw.gerumap.app.gui.swing.actions.AbstractCustomAction;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.observer.IPublisher;
import dsw.gerumap.app.observer.NotificationType;
import dsw.gerumap.app.repository.models.MindMap;
import dsw.gerumap.app.repository.models.Project;
import dsw.gerumap.app.resources.ResourceLoader;
import dsw.gerumap.app.resources.ResourceType;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;

public class SaveAsAction extends AbstractCustomAction {

    public SaveAsAction(IPublisher publisher) {
        super(publisher);
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
        if (!(object instanceof Project project)) return;

        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setDialogTitle("Save new file");
        jFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int result = jFileChooser.showDialog(MainFrame.getInstance(), "Save As");
        if (result == JFileChooser.APPROVE_OPTION) {
            String fileName = jFileChooser.getSelectedFile().getName();
            project.setName(fileName.split(".json")[0]);

            String fileDirectory = jFileChooser.getSelectedFile().getParentFile().getAbsolutePath();
            project.setFileDirectory(fileDirectory);

        } else if (result == JFileChooser.CANCEL_OPTION) return;
        App.getSerializer().saveProject(project);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (selected instanceof Project project) action(project);
        else if (selected instanceof MindMap mindMap) action(mindMap.getParent());
    }

    @Override
    public void update(NotificationType notificationType, Object object) {
        super.update(notificationType, object);
        if (notificationType == NotificationType.SELECTED) {
            setEnabled(false);
            if (object instanceof Project || object instanceof MindMap) {
                setEnabled(true);
            }
        }
    }
}