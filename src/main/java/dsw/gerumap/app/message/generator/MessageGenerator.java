package dsw.gerumap.app.message.generator;

import dsw.gerumap.app.message.Message;
import dsw.gerumap.app.message.MessageType;
import dsw.gerumap.app.message.generator.error.ErrorType;
import dsw.gerumap.app.message.generator.success.SuccessType;
import dsw.gerumap.app.observer.IListener;
import dsw.gerumap.app.observer.NotificationType;

import java.util.ArrayList;
import java.util.List;

public class MessageGenerator implements IMessageGenerator {

    private static MessageGenerator instance;

    private List<IListener> listeners;

    private MessageGenerator() {
        listeners = new ArrayList<>();
    }

    public static MessageGenerator initialize() {
        if (instance == null) {
            instance = new MessageGenerator();
        }
        return instance;
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
        for (IListener listener : listeners) {
            listener.update(notificationType, object);
        }
    }

    @Override
    public void generateMessage(MessageType type, int specific) {
        switch (type) {
            case ERROR -> {
                ErrorType errorType = ErrorType.values()[specific];
                switch (errorType) {
                    case FOLDER_EXISTS -> {
                        notifyListeners(
                                NotificationType.MESSAGE,
                                new Message("Folder already exists!", MessageType.ERROR)
                        );
                    }

                    case PROJECT_EXISTS -> {
                        notifyListeners(
                                NotificationType.MESSAGE,
                                new Message("Project already exists!", MessageType.ERROR)
                        );
                    }

                    case MIND_MAP_EXISTS -> {
                        notifyListeners(
                                NotificationType.MESSAGE,
                                new Message("Mind map already exists!", MessageType.ERROR)
                        );
                    }
                }
            }

            case SUCCESS -> {
                SuccessType successType = SuccessType.values()[specific];
                switch (successType) {
                    case PROJECT_SAVED -> {
                        notifyListeners(
                                NotificationType.MESSAGE,
                                new Message("Project saved successfully!", MessageType.SUCCESS)
                        );
                    }

                    case EXPORT_PNG -> {
                        notifyListeners(
                                NotificationType.MESSAGE,
                                new Message("Exporting png successfully!", MessageType.SUCCESS)
                        );
                    }
                    case EXPORT_TEMPLATE -> {
                        notifyListeners(
                                NotificationType.MESSAGE,
                                new Message("Exporting template successfully!", MessageType.SUCCESS)
                        );
                    }
                }
            }
        }
    }
}