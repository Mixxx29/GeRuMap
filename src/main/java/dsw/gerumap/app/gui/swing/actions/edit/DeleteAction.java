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
import dsw.gerumap.app.repository.factory.ModelType;
import dsw.gerumap.app.repository.models.Folder;
import dsw.gerumap.app.repository.models.Workspace;
import dsw.gerumap.app.resources.ResourceLoader;
import dsw.gerumap.app.resources.ResourceType;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class DeleteAction extends AbstractCustomAction {

    public DeleteAction(IPublisher publisher) {
        super(publisher);
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0));
        putValue(SMALL_ICON, ResourceLoader.load("delete.png", ResourceType.ICON));
        putValue(NAME, " Delete ");
        putValue(SHORT_DESCRIPTION, " Delete ");
    }

    @Override
    public void action(Object object) {
        if (object instanceof Workspace || !(object instanceof ModelNode modelNode)) return;

        CompositeModelNode parent = modelNode.getParent();
        AbstractDialog dialog = DialogFactory.createDialog(DialogType.DELETE, modelNode);

        if (dialog != null && (boolean) dialog.start(null)) {
            parent.removeNode(modelNode);
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
            setEnabled(true);
            if (object instanceof Workspace) {
                setEnabled(false);
            }
        }
    }
}