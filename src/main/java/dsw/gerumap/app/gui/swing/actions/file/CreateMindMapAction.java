package dsw.gerumap.app.gui.swing.actions.file;

import dsw.gerumap.app.gui.swing.actions.AbstractCustomAction;
import dsw.gerumap.app.gui.swing.dialogs.AbstractDialog;
import dsw.gerumap.app.gui.swing.dialogs.factory.DialogFactory;
import dsw.gerumap.app.gui.swing.dialogs.factory.DialogType;
import dsw.gerumap.app.observer.IPublisher;
import dsw.gerumap.app.observer.NotificationType;
import dsw.gerumap.app.repository.models.Folder;
import dsw.gerumap.app.repository.models.Workspace;
import dsw.gerumap.app.resources.ResourceLoader;
import dsw.gerumap.app.resources.ResourceType;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.repository.composite.CompositeModelNode;
import dsw.gerumap.app.repository.factory.AbstractModelFactory;
import dsw.gerumap.app.repository.factory.ModelFactoryManager;
import dsw.gerumap.app.repository.factory.ModelType;
import dsw.gerumap.app.repository.models.MindMap;
import dsw.gerumap.app.repository.models.Project;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class CreateMindMapAction extends AbstractCustomAction {

    public CreateMindMapAction(IPublisher publisher) {
        super(publisher);
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_M, InputEvent.CTRL_DOWN_MASK));
        putValue(SMALL_ICON, ResourceLoader.load("mind_map.png", ResourceType.ICON));
        putValue(LARGE_ICON_KEY, ResourceLoader.load("create_mind_map.png", ResourceType.ICON));
        putValue(NAME, " Mind Map ");
        putValue(SHORT_DESCRIPTION, " Create new mind map ");
    }

    @Override
    public void action(Object object) {
        if (!(object instanceof Project)) return;

        CompositeModelNode parent = (CompositeModelNode) object;
        AbstractDialog dialog = DialogFactory.createDialog(DialogType.CREATE, ModelType.MIND_MAP);

        // Get default name
        String name = MindMap.DEFAULT_NAME;
        int counter = 1;
        while (parent.getNode(name) != null) {
            name = MindMap.DEFAULT_NAME + " " + counter++;
        }

        if (dialog != null) while (true) {
            name = (String) dialog.start(name);
            if (name == null) return;
            if (parent.getNode(name) == null) break;
            System.out.println("Mind map already exists!");
        }

        AbstractModelFactory factory = ModelFactoryManager.getFactory(ModelType.MIND_MAP);
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
            if (object instanceof Project) {
                setEnabled(true);
            }
        }
    }
}