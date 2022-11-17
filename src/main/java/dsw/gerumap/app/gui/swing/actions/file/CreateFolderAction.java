package dsw.gerumap.app.gui.swing.actions.file;

import dsw.gerumap.app.gui.swing.actions.AbstractCustomAction;
import dsw.gerumap.app.gui.swing.actions.ActionManager;
import dsw.gerumap.app.gui.swing.dialogs.AbstractDialog;
import dsw.gerumap.app.gui.swing.dialogs.factory.DialogFactory;
import dsw.gerumap.app.gui.swing.dialogs.factory.DialogType;
import dsw.gerumap.app.observer.IPublisher;
import dsw.gerumap.app.observer.NotificationType;
import dsw.gerumap.app.repository.composite.ModelNode;
import dsw.gerumap.app.resources.ResourceLoader;
import dsw.gerumap.app.resources.ResourceType;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.repository.composite.CompositeModelNode;
import dsw.gerumap.app.repository.factory.AbstractModelFactory;
import dsw.gerumap.app.repository.factory.ModelFactoryManager;
import dsw.gerumap.app.repository.factory.ModelType;
import dsw.gerumap.app.repository.models.Folder;
import dsw.gerumap.app.repository.models.Workspace;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class CreateFolderAction extends AbstractCustomAction {

    public CreateFolderAction(IPublisher publisher) {
        super(publisher);
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_DOWN_MASK));
        putValue(SMALL_ICON, ResourceLoader.load("folder.png", ResourceType.ICON));
        putValue(LARGE_ICON_KEY, ResourceLoader.load("create_folder.png", ResourceType.ICON));
        putValue(NAME, " Folder ");
        putValue(SHORT_DESCRIPTION, " Create new folder ");
    }

    @Override
    public void action(Object object) {
        if (!(object instanceof Workspace) && !(object instanceof Folder)) return;

        CompositeModelNode parent = (CompositeModelNode) object;
        AbstractDialog dialog = DialogFactory.createDialog(DialogType.CREATE, ModelType.FOLDER);

        // Get default name
        String name = Folder.DEFAULT_NAME;
        int counter = 1;
        while (parent.getNode(name) != null) {
            name = Folder.DEFAULT_NAME + " " + counter++;
        }

        if (dialog != null) while (true) {
            name = (String) dialog.start(name);
            if (name == null) return;
            if (parent.getNode(name) == null) break;
            System.out.println("Folder already exists!");
        }

        AbstractModelFactory factory = ModelFactoryManager.getFactory(ModelType.FOLDER);
        if (factory != null) factory.createModel(name, parent);
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
            if (object instanceof Folder) {
                setEnabled(true);
            }
        }
    }
}