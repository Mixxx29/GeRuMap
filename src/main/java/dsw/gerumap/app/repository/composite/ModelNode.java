package dsw.gerumap.app.repository.composite;

import dsw.gerumap.app.observer.IListener;
import dsw.gerumap.app.observer.IPublisher;
import dsw.gerumap.app.observer.NotificationType;
import dsw.gerumap.app.repository.factory.ModelType;
import dsw.gerumap.app.repository.models.Project;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class ModelNode implements IPublisher {
    private String name;
    transient private CompositeModelNode parent;
    transient private List<IListener> listeners;

    public ModelNode(String name) {
        this.name = name;
        this.listeners = new ArrayList<>();
    }

    public ModelNode(String name, List<IListener> listeners) {
        this.name = name;
        this.listeners = listeners;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (this instanceof Project project) {
            project.setOldName(this.name);
        }
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
        if (listeners == null) listeners = new ArrayList<>();

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