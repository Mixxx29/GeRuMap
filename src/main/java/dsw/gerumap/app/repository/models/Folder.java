package dsw.gerumap.app.repository.models;

import dsw.gerumap.app.repository.composite.CompositeModelNode;
import dsw.gerumap.app.repository.composite.ModelNode;
import dsw.gerumap.app.repository.factory.ModelType;

public class Folder extends CompositeModelNode {

    public static final String DEFAULT_NAME = "New Folder";

    public Folder(String name) {
        super(name);
    }

    @Override
    public void addNode(ModelNode node) {
        if (!(node instanceof Folder) && !(node instanceof Project)) return;
        super.addNode(node);
    }
}