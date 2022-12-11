package dsw.gerumap.app.repository.elements;

import dsw.gerumap.app.observer.IListener;
import dsw.gerumap.app.observer.IPublisher;
import dsw.gerumap.app.observer.NotificationType;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class MindMapElement implements IPublisher {

    protected Stroke stroke;
    protected Color strokeColor;
    protected List<IListener> listeners;

    public MindMapElement() {
        stroke = new BasicStroke(3.0f);
        strokeColor = new Color(30, 78, 211);

        listeners = new ArrayList<>();
    }

    public MindMapElement(Stroke stroke, Color strokeColor) {
        this.stroke = stroke;
        this.strokeColor = strokeColor;

        listeners = new ArrayList<>();
    }

    public Color getStrokeColor() {
        return strokeColor;
    }

    public void setStrokeColor(Color strokeColor) {
        this.strokeColor = strokeColor;
    }

    public Stroke getStroke() {
        return stroke;
    }

    public void setStroke(Stroke stroke) {
        this.stroke = stroke;
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
