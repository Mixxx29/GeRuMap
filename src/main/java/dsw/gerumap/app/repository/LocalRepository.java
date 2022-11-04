package dsw.gerumap.app.repository;

import dsw.gerumap.app.core.DataRepository;
import dsw.gerumap.app.repository.composite.CompositeModelNode;
import dsw.gerumap.app.repository.composite.ModelNode;
import dsw.gerumap.app.repository.models.Workspace;

public class LocalRepository implements DataRepository {

    private Workspace workspace;

    public LocalRepository(String name) {
        workspace = new Workspace(name);
    }

    @Override
    public Workspace getWorkspace() {
        return workspace;
    }

    @Override
    public void add(CompositeModelNode parent, ModelNode child) {

    }
}