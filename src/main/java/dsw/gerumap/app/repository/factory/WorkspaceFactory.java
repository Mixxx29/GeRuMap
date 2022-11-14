package dsw.gerumap.app.repository.factory;

import dsw.gerumap.app.repository.composite.ModelNode;
import dsw.gerumap.app.repository.models.Workspace;

public class WorkspaceFactory extends AbstractModelFactory {
    @Override
    protected ModelNode createModel(String name) {
        return new Workspace(name);
    }
}