package dsw.gerumap.app.repository;

import dsw.gerumap.app.core.DataRepository;
import dsw.gerumap.app.gui.swing.dialogs.AbstractDialog;
import dsw.gerumap.app.gui.swing.dialogs.factory.DialogFactory;
import dsw.gerumap.app.gui.swing.dialogs.factory.DialogType;
import dsw.gerumap.app.repository.composite.CompositeModelNode;
import dsw.gerumap.app.repository.composite.ModelNode;
import dsw.gerumap.app.repository.factory.ModelFactoryManager;
import dsw.gerumap.app.repository.factory.ModelType;
import dsw.gerumap.app.repository.models.Workspace;

public class LocalRepository implements DataRepository {

    private Workspace workspace;

    public LocalRepository() {
        ModelFactoryManager.initialize();
    }

    @Override
    public void load() {
        AbstractDialog dialog = DialogFactory.createDialog(DialogType.CREATE, ModelType.WORKSPACE);
        String name = (String) dialog.start("New Workspace");
        if (name == null) System.exit(0);
        workspace = new Workspace(name);
    }

    @Override
    public Workspace getWorkspace() {
        return workspace;
    }

    @Override
    public void add(CompositeModelNode parent, ModelNode child) {

    }

    @Override
    public void remove(CompositeModelNode parent, ModelNode child) {

    }
}