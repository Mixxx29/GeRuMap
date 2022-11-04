package dsw.gerumap.app.repository.composite;

import java.util.Iterator;
import java.util.List;

public class CompositeModelNode extends ModelNode {

    private List<ModelNode> nodes;

    public CompositeModelNode(String name) {
        super(name);
    }

    public void addNode(ModelNode node) {
        nodes.add(node);
    }

    public void removeNode(ModelNode node) {
        nodes.remove(node);
    }

    public ModelNode getNode(String name) {
        Iterator<ModelNode> iterator = nodes.iterator();
        while (iterator.hasNext()) {
            ModelNode node = iterator.next();
            if (node.getName().equals(name)) return node;
        }
        return null;
    }
}
