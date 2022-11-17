package dsw.gerumap.app.logger;

import dsw.gerumap.app.message.Message;

public class ConsoleLogger extends AbstractLogger {

    @Override
    protected void log(Message message) {
        System.out.println(message.getMessage());
    }
}