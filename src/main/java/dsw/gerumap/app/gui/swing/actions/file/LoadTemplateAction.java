package dsw.gerumap.app.gui.swing.actions.file;

import dsw.gerumap.app.core.App;
import dsw.gerumap.app.gui.swing.actions.AbstractCustomAction;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.observer.IPublisher;
import dsw.gerumap.app.observer.NotificationType;
import dsw.gerumap.app.repository.models.Folder;
import dsw.gerumap.app.repository.models.MindMap;
import dsw.gerumap.app.repository.models.Project;
import dsw.gerumap.app.resources.ResourceLoader;
import dsw.gerumap.app.resources.ResourceType;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;

public class LoadTemplateAction extends AbstractCustomAction {

    public LoadTemplateAction(IPublisher publisher) {
        super(publisher);
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_T, InputEvent.CTRL_DOWN_MASK));
        putValue(SMALL_ICON, ResourceLoader.load("load_template.png", ResourceType.ICON));
        putValue(NAME, " Load Template ");
        putValue(SHORT_DESCRIPTION, " Load template ");
    }

    @Override
    public void action(Object object) {
        if (!(object instanceof Project project)) return;

        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setDialogTitle("Load template");
        jFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        jFileChooser.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                if (f.isDirectory()) return false;
                return f.getName().toLowerCase().endsWith(".json");
            }

            @Override
            public String getDescription() {
                return "JSON Files (*.json)";
            }
        });
        int result = jFileChooser.showDialog(MainFrame.getInstance(), "Load");
        if (result == JFileChooser.APPROVE_OPTION) {
            if (!jFileChooser.getSelectedFile().getName().endsWith(".json")) return;
            MindMap mindMap = App.getSerializer().loadTemplate(jFileChooser.getSelectedFile());
            project.addNode(mindMap);
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
            if (object instanceof Project) {
                setEnabled(true);
            }
        }
    }
}
