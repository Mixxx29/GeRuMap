package dsw.gerumap.app.gui.swing.actions.file;

import dsw.gerumap.app.core.App;
import dsw.gerumap.app.gui.swing.actions.AbstractCustomAction;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.message.MessageType;
import dsw.gerumap.app.message.generator.success.SuccessType;
import dsw.gerumap.app.observer.IPublisher;
import dsw.gerumap.app.observer.NotificationType;
import dsw.gerumap.app.repository.models.MindMap;
import dsw.gerumap.app.resources.ResourceLoader;
import dsw.gerumap.app.resources.ResourceType;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.List;

public class ExportAsTemplateAction extends AbstractCustomAction {

    public ExportAsTemplateAction(IPublisher publisher) {
        super(publisher);
        putValue(
                ACCELERATOR_KEY,
                KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK)
        );
        putValue(SMALL_ICON, ResourceLoader.load("export_as_template.png", ResourceType.ICON));
        putValue(LARGE_ICON_KEY, ResourceLoader.load("export_as_template_large.png", ResourceType.ICON));
        putValue(NAME, " As Template ");
        putValue(SHORT_DESCRIPTION, " Export as Template ");
    }

    @Override
    public void action(Object object) {
        if (!(object instanceof MindMap mindMap)) return;

        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setDialogTitle("Export template");
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
        int result = jFileChooser.showDialog(MainFrame.getInstance(), "Export");
        if (result == JFileChooser.APPROVE_OPTION) {
            String fileName = jFileChooser.getSelectedFile().getName().split(".json")[0];
            String destination = jFileChooser.getSelectedFile().getParentFile().getAbsolutePath();
            String oldName = mindMap.getName();
            mindMap.setName(fileName);
            App.getSerializer().saveTemplate(mindMap, destination + "\\" + fileName + ".json");
            mindMap.setName(oldName);
            App.getMessageGenerator().generateMessage(MessageType.SUCCESS, SuccessType.EXPORT_TEMPLATE.ordinal());
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
