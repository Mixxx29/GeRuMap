package dsw.gerumap.app.gui.swing.actions;

import dsw.gerumap.app.observer.IListener;
import dsw.gerumap.app.observer.IPublisher;
import dsw.gerumap.app.observer.NotificationType;
import dsw.gerumap.app.repository.composite.ModelNode;

import javax.swing.*;
import java.nio.file.Path;

public abstract class AbstractCustomAction extends AbstractAction implements IListener {

    protected ModelNode selected;

    public AbstractCustomAction(IPublisher publisher) {
        publisher.addListener(this);
    }

    @Override
    public void update(NotificationType notificationType, Object object) {
        if (notificationType == NotificationType.SELECTED && object instanceof ModelNode modelNode) {
            selected = modelNode;
        }
    }

    public abstract void action(Object object);
}
