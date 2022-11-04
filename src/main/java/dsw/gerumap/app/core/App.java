package dsw.gerumap.app.core;

import dsw.gerumap.app.gui.swing.SwingGUI;
import dsw.gerumap.app.repository.LocalRepository;

public class App {
    private static App instance;

    private DataRepository repository;
    private GUI gui;

    private App(DataRepository repository, GUI gui) {
        this.repository = repository;
        this.gui = gui;
    }

    public static App initialize() {
        if (instance == null) {
            instance = new App(new LocalRepository("WorkspaceTest"), new SwingGUI());
        }
        return instance;
    }

    public void run() {
        gui.run();
    }

    public static DataRepository getRepository() {
        if (instance != null) {
            return instance.repository;
        }
        return null;
    }
}
