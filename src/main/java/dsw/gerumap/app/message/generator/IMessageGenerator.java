package dsw.gerumap.app.message.generator;

import dsw.gerumap.app.message.MessageType;
import dsw.gerumap.app.observer.IPublisher;

public interface IMessageGenerator extends IPublisher {
    void generateMessage(MessageType type, int specific);
}