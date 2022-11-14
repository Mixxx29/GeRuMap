package dsw.gerumap.app.observer;

public interface IPublisher {
    void addListener(IListener listener);
    void removeListener(IListener listener);
    void notifyListeners(NotificationType notificationType, Object object);
}