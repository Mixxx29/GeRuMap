package dsw.gerumap.app.gui.swing.actions.file;

import dsw.gerumap.app.core.App;
import dsw.gerumap.app.gui.swing.actions.AbstractCustomAction;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.message.MessageType;
import dsw.gerumap.app.message.generator.error.ErrorType;
import dsw.gerumap.app.message.generator.success.SuccessType;
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

public class SaveAction extends AbstractCustomAction {

    public SaveAction(IPublisher publisher) {
        super(publisher);
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
        putValue(SMALL_ICON, ResourceLoader.load("save.png", ResourceType.ICON));
        putValue(NAME, " Save ");
        putValue(SHORT_DESCRIPTION, " Save workspace ");
    }

    @Override
    public void action(Object object) {
        if (!(object instanceof Project project)) return;

        if (project.getFileDirectory() == null || project.getFileDirectory().equals("")) {
            JFileChooser jFileChooser = new JFileChooser();
            jFileChooser.setDialogTitle("Select destination folder");
            jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int result = jFileChooser.showSaveDialog(MainFrame.getInstance());
            if (result == JFileChooser.APPROVE_OPTION) {
                File directory = jFileChooser.getSelectedFile();
                project.setFileDirectory(directory.getAbsolutePath());
            } else if (result == JFileChooser.CANCEL_OPTION) return;
        } else {
            // Rename file
            File oldFile = new File(project.getFileDirectory() + "\\" + project.getOldName() + ".json");
            File newFile = new File(project.getFileDirectory() + "\\" + project.getName() + ".json");
            oldFile.renameTo(newFile);
            oldFile.delete();
        }

        App.getSerializer().saveProject(project);
        App.getMessageGenerator().generateMessage(MessageType.SUCCESS, SuccessType.PROJECT_SAVED.ordinal());
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
            if (object instanceof Project project) {
                setEnabled(project.hasChanged());
            } else  if (object instanceof MindMap mindMap) {
                setEnabled(((Project) mindMap.getParent()).hasChanged());
            }
        }
    }
}