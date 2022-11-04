package dsw.gerumap.app.repository.models;

import dsw.gerumap.app.repository.composite.CompositeModelNode;
import dsw.gerumap.app.repository.composite.ModelNode;

public class Folder extends CompositeModelNode {
    public Folder(String name) {
        super(name);
    }

    @Override
    public void addNode(ModelNode node) {
        if (!(node instanceof Folder)) return;
        super.addNode(node);
    }
}
