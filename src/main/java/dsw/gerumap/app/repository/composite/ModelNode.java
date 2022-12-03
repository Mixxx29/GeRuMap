package dsw.gerumap.app.repository.composite;

import dsw.gerumap.app.observer.IListener;
import dsw.gerumap.app.observer.IPublisher;
import dsw.gerumap.app.observer.NotificationType;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class ModelNode implements IPublisher {
    private String name;
    private CompositeModelNode parent;
    private List<IListener> listeners;

    public ModelNode(String name) {
        this.name = name;
        listeners = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyListeners(NotificationType.RENAME, this);
    }

    public CompositeModelNode getParent() {
        return parent;
    }

    public void setParent(CompositeModelNode parent) {
        this.parent = parent;
    }

    @Override
    public void addListener(IListener listener) {
        if (!listeners.contains(listener)) {
            listeners.add(listener);
        }
    }

    @Override
    public void removeListener(IListener listener) {
        listeners.remove(listener);
    }

    @Override
    public void notifyListeners(NotificationType notificationType, Object object) {
        List<IListener> listenersCopy = new ArrayList<>(listeners);
        for (IListener listener : listenersCopy) {
            listener.update(notificationType, object);
        }
    }
}