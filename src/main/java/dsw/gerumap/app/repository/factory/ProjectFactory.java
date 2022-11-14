package dsw.gerumap.app.repository.factory;

import dsw.gerumap.app.repository.composite.ModelNode;
import dsw.gerumap.app.repository.models.Project;

public class ProjectFactory extends AbstractModelFactory {
    @Override
    protected ModelNode createModel(String name) {
        return new Project(name);
    }
}