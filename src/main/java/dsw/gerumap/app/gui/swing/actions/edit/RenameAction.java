package dsw.gerumap.app.gui.swing.actions.edit;

import dsw.gerumap.app.gui.swing.actions.AbstractCustomAction;
import dsw.gerumap.app.gui.swing.dialogs.AbstractDialog;
import dsw.gerumap.app.gui.swing.dialogs.factory.DialogFactory;
import dsw.gerumap.app.gui.swing.dialogs.factory.DialogType;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.observer.IPublisher;
import dsw.gerumap.app.observer.NotificationType;
import dsw.gerumap.app.repository.composite.CompositeModelNode;
import dsw.gerumap.app.repository.composite.ModelNode;
import dsw.gerumap.app.repository.factory.AbstractModelFactory;
import dsw.gerumap.app.repository.factory.ModelFactoryManager;
import dsw.gerumap.app.repository.factory.ModelType;
import dsw.gerumap.app.repository.models.Folder;
import dsw.gerumap.app.repository.models.Workspace;
import dsw.gerumap.app.resources.ResourceLoader;
import dsw.gerumap.app.resources.ResourceType;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class RenameAction extends AbstractCustomAction {

    public RenameAction(IPublisher publisher) {
        super(publisher);
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK));
        putValue(SMALL_ICON, ResourceLoader.load("rename.png", ResourceType.ICON));
        putValue(NAME, " Rename ");
        putValue(SHORT_DESCRIPTION, " Rename ");
    }

    @Override
    public void action(Object object) {
        if (object instanceof Workspace || !(object instanceof ModelNode modelNode)) return;

        CompositeModelNode parent = modelNode.getParent();
        AbstractDialog dialog = DialogFactory.createDialog(DialogType.RENAME, modelNode);

        // Get default name
        String name = modelNode.getName();

        if (dialog != null) while (true) {
            name = (String) dialog.start(null);
            if (name == null || name.equals(modelNode.getName())) return;
            if (parent.getNode(name) == null) break;
            System.out.println("Name already exists!");
        }

        modelNode.setName(name);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        action(selected);
    }

    @Override
    public void update(NotificationType notificationType, Object object) {
        super.update(notificationType, object);
        if (notificationType == NotificationType.SELECTED) {
            setEnabled(true);
            if (object instanceof Workspace) {
                setEnabled(false);
            }
        }
    }
}