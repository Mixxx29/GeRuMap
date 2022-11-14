package dsw.gerumap.app.repository.factory;

import dsw.gerumap.app.repository.composite.ModelNode;
import dsw.gerumap.app.repository.models.MindMap;

public class MindMapFactory extends AbstractModelFactory{
    @Override
    protected ModelNode createModel(String name) {
        return new MindMap(name);
    }
}