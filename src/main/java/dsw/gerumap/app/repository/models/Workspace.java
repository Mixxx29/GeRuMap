package dsw.gerumap.app.repository.models;

import dsw.gerumap.app.repository.composite.CompositeModelNode;
import dsw.gerumap.app.repository.composite.ModelNode;

public class Workspace extends CompositeModelNode {

    public Workspace(String name) {
        super(name);
    }

    @Override
    public void addNode(ModelNode node) {
        if (!(node instanceof Folder) && !(node instanceof Project)) return;
        super.addNode(node);
    }
}
