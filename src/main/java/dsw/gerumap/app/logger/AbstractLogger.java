package dsw.gerumap.app.logger;

import dsw.gerumap.app.gui.swing.dialogs.AbstractDialog;
import dsw.gerumap.app.message.Message;
import dsw.gerumap.app.message.MessageType;
import dsw.gerumap.app.observer.IListener;
import dsw.gerumap.app.observer.NotificationType;

public abstract class AbstractLogger implements IListener {

    public AbstractLogger() {

    }

    @Override
    public void update(NotificationType notificationType, Object object) {
        if (notificationType == NotificationType.MESSAGE) {
            if (object instanceof Message message) {
                if (message.getType() == MessageType.ERROR) {
                    log(message);
                }
            }
        }
    }

    protected abstract void log(Message message);
}