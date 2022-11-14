package dsw.gerumap.app.repository.factory;

public class ModelFactoryManager {

    private static ModelFactoryManager instance;

    private FolderFactory folderFactory;
    private ProjectFactory projectFactory;
    private MindMapFactory mindMapFactory;

    private ModelFactoryManager() {
        folderFactory = new FolderFactory();
        projectFactory = new ProjectFactory();
        mindMapFactory = new MindMapFactory();
    }

    public static void initialize() {
        if (instance == null) instance = new ModelFactoryManager();
    }

    public static AbstractModelFactory getFactory(ModelType modelType) {
        switch (modelType) {
            case FOLDER -> { return instance.folderFactory; }
            case PROJECT -> { return instance.projectFactory; }
            case MIND_MAP -> { return instance.mindMapFactory; }
            default -> { return null; }
        }
    }
}