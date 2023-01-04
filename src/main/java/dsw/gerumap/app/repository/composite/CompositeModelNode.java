package dsw.gerumap.app.repository.composite;

import dsw.gerumap.app.observer.IListener;
import dsw.gerumap.app.observer.NotificationType;
import dsw.gerumap.app.repository.models.MindMap;
import dsw.gerumap.app.repository.models.Project;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class CompositeModelNode extends ModelNode {

    protected List<ModelNode> nodes;

    public CompositeModelNode(String name) {
        super(name);
        nodes = new ArrayList<>();
    }

    public void addNode(ModelNode node) {
        if (nodes == null) nodes = new ArrayList<>();
        nodes.add(node);
        node.setParent(this);
        notifyListeners(NotificationType.CREATE, node);
        if (node instanceof Project project) {
            project.load();
        }
    }

    public void removeNode(ModelNode node) {
        nodes.remove(node);
        node.notifyListeners(NotificationType.DELETE, node);
    }

    public ModelNode getNode(String name) {
        name = name.trim();
        Iterator<ModelNode> iterator = nodes.iterator();
        while (iterator.hasNext()) {
            ModelNode node = iterator.next();
            if (node.getName().equals(name)) return node;
        }
        return null;
    }
}