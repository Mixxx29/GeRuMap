package dsw.gerumap.app.core;

import dsw.gerumap.app.gui.swing.SwingGUI;
import dsw.gerumap.app.gui.swing.dialogs.AbstractDialog;
import dsw.gerumap.app.gui.swing.dialogs.factory.DialogFactory;
import dsw.gerumap.app.gui.swing.dialogs.factory.DialogType;
import dsw.gerumap.app.logger.AbstractLogger;
import dsw.gerumap.app.logger.ConsoleLogger;
import dsw.gerumap.app.message.generator.IMessageGenerator;
import dsw.gerumap.app.message.generator.MessageGenerator;
import dsw.gerumap.app.observer.IListener;
import dsw.gerumap.app.repository.LocalRepository;
import dsw.gerumap.app.resources.ResourceLoader;
import dsw.gerumap.app.serializer.GSONSerializer;

public class App {
    private static App instance;

    private DataRepository repository;
    private GUI gui;
    private Serializer serializer;

    private final IMessageGenerator messageGenerator;

    private App(DataRepository repository, GUI gui, Serializer serializer) {
        this.repository = repository;
        this.gui = gui;
        this.serializer = serializer;
        messageGenerator = MessageGenerator.initialize();
        AbstractLogger logger = new ConsoleLogger();
        messageGenerator.addListener(logger);
        messageGenerator.addListener((IListener) DialogFactory.createDialog(DialogType.ERROR, null));
        messageGenerator.addListener((IListener) DialogFactory.createDialog(DialogType.SUCCESS, null));
    }

    public static App initialize() {
        if (instance == null) {
            ResourceLoader.initialise();
            instance = new App(
                    new LocalRepository(),
                    new SwingGUI(),
                    new GSONSerializer()
            );
        }
        return instance;
    }

    public void run() {
        repository.load();
        gui.run();
    }

    public static DataRepository getRepository() {
        if (instance != null) {
            return instance.repository;
        }
        return null;
    }

    public static Serializer getSerializer() {
        if (instance != null) {
            return instance.serializer;
        }
        return null;
    }

    public static IMessageGenerator getMessageGenerator() {
        if (instance != null) {
            return instance.messageGenerator;
        }
        return null;
    }
}