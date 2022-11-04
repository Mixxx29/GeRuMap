package dsw.gerumap.app.core;

import dsw.gerumap.app.repository.composite.CompositeModelNode;
import dsw.gerumap.app.repository.composite.ModelNode;
import dsw.gerumap.app.repository.models.Workspace;

public interface DataRepository {
    Workspace getWorkspace();
    void add(CompositeModelNode parent, ModelNode child);
}
