package dsw.gerumap.app.repository.factory;

import dsw.gerumap.app.repository.composite.ModelNode;
import dsw.gerumap.app.repository.models.Folder;

public class FolderFactory extends AbstractModelFactory {
    @Override
    public ModelNode createModel(String name) {
        return new Folder(name);
    }
}