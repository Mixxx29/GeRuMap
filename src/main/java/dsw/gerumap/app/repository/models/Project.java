package dsw.gerumap.app.repository.models;

import dsw.gerumap.app.repository.composite.CompositeModelNode;
import dsw.gerumap.app.repository.composite.ModelNode;

public class Project extends CompositeModelNode {

    public static final String DEFAULT_NAME = "New Project";
    public Project(String name) {
        super(name);
    }

    @Override
    public void addNode(ModelNode node) {
        if (!(node instanceof MindMap)) return;
        super.addNode(node);
    }
}