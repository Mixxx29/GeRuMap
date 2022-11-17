package dsw.gerumap.app.repository.models;

import dsw.gerumap.app.repository.composite.CompositeModelNode;
import dsw.gerumap.app.repository.composite.ModelNode;

public class Workspace extends Folder {

    public static final String DEFAULT_NAME = "New Workspace";

    public Workspace(String name) {
        super(name);
    }

}