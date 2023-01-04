package dsw.gerumap.app.gui.swing.actions.file;

import dsw.gerumap.app.gui.swing.actions.AbstractCustomAction;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.observer.IPublisher;
import dsw.gerumap.app.observer.NotificationType;
import dsw.gerumap.app.repository.models.Folder;
import dsw.gerumap.app.repository.models.MindMap;
import dsw.gerumap.app.repository.models.Project;
import dsw.gerumap.app.repository.models.Workspace;
import dsw.gerumap.app.resources.ResourceLoader;
import dsw.gerumap.app.resources.ResourceType;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.List;

public class ExportAction extends AbstractCustomAction {

    public ExportAction(IPublisher publisher) {
        super(publisher);
        putValue(
                ACCELERATOR_KEY,
                KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK)
        );
        putValue(SMALL_ICON, ResourceLoader.load("export.png", ResourceType.ICON));
        putValue(NAME, " Export ");
        putValue(SHORT_DESCRIPTION, " Export ");
    }

    @Override
    public void action(Object object) {
        if (!(object instanceof MindMap mindMap)) return;

        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setDialogTitle("Select destination folder");
        jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int result = jFileChooser.showSaveDialog(MainFrame.getInstance());
        if (result == JFileChooser.APPROVE_OPTION) {
            if (mindMap.hasSelected()) mindMap.addSelectionCommand(List.of());
            mindMap.exportAsPNG(jFileChooser.getSelectedFile().getAbsolutePath());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        action(selected);
    }

    @Override
    public void update(NotificationType notificationType, Object object) {
        super.update(notificationType, object);
        if (notificationType == NotificationType.SELECTED) {
            setEnabled(false);
            if (object instanceof MindMap) {
                setEnabled(true);
            }
        }
    }
}