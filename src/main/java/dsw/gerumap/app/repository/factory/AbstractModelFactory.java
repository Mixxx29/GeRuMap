package dsw.gerumap.app.repository.factory;

import dsw.gerumap.app.repository.composite.CompositeModelNode;
import dsw.gerumap.app.repository.composite.ModelNode;

public abstract class AbstractModelFactory {

    public ModelNode createModel(String name, CompositeModelNode parent) {
        ModelNode model = createModel(name.trim());
        if (parent != null) parent.addNode(model);
        return model;
    }

    protected abstract ModelNode createModel(String name);
}