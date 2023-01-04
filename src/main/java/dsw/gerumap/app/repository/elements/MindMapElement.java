package dsw.gerumap.app.repository.elements;

import dsw.gerumap.app.observer.IListener;
import dsw.gerumap.app.observer.IPublisher;
import dsw.gerumap.app.observer.NotificationType;
import dsw.gerumap.app.repository.models.MindMap;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class MindMapElement implements IPublisher, IListener {

    protected int stroke;
    protected int strokeColorHex;

    transient protected MindMap parent;
    transient protected List<IListener> listeners;

    public MindMapElement() {
        stroke = 3;
        setStrokeColor(new Color(30, 78, 211));
        listeners = new ArrayList<>();
    }

    public MindMapElement(int stroke, Color strokeColor) {
        this.stroke = stroke;
        setStrokeColor(strokeColor);

        listeners = new ArrayList<>();
    }

    public Color getStrokeColor() {
        return new Color(strokeColorHex);
    }

    public void setStrokeColor(Color strokeColor) {
        this.strokeColorHex = strokeColor.getRGB();
    }

    public Stroke getStroke() {
        return new BasicStroke(stroke);
    }

    public void setStroke(int stroke) {
        this.stroke = stroke;
    }

    public void setParent(MindMap parent) {
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