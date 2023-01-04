package dsw.gerumap.app.serializer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dsw.gerumap.app.core.Serializer;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.repository.composite.ModelNode;
import dsw.gerumap.app.repository.elements.LinkElement;
import dsw.gerumap.app.repository.elements.MindMapElement;
import dsw.gerumap.app.repository.elements.TermElement;
import dsw.gerumap.app.repository.models.MindMap;
import dsw.gerumap.app.repository.models.Project;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class GSONSerializer implements Serializer {

    private Gson gson;

    public GSONSerializer() {
        RuntimeTypeAdapterFactory<ModelNode> modelNodeAdapter = RuntimeTypeAdapterFactory.of(
                ModelNode.class, "type"
        )
        .registerSubtype(Project.class)
        .registerSubtype(MindMap.class);

        RuntimeTypeAdapterFactory<MindMapElement> mindElementAdapter = RuntimeTypeAdapterFactory.of(
                        MindMapElement.class, "type"
        )
        .registerSubtype(TermElement.class)
        .registerSubtype(LinkElement.class);

        gson = new GsonBuilder()
                .registerTypeAdapterFactory(modelNodeAdapter)
                .registerTypeAdapterFactory(mindElementAdapter)
                .create();
    }

    @Override
    public void saveProject(Project project) {
        File file = new File(project.getFileDirectory() + "\\" + project.getName() + ".json");
        try (FileWriter writer = new FileWriter(file.getAbsolutePath())) {
            project.save();
            gson.toJson(project, writer);
            MainFrame.getInstance().getActionManager().getSaveAction().setEnabled(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Project loadProject(File file) {
        try {
            String projectJSON = Files.readString(Path.of(file.getAbsolutePath()));
            return gson.fromJson(projectJSON, Project.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void saveTemplate(MindMap mindMap, String destination) {
        File file = new File(destination);
        try (FileWriter writer = new FileWriter(file.getAbsolutePath())) {
            mindMap.saveElements();
            gson.toJson(mindMap, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public MindMap loadTemplate(File file) {
        try {
            String mindMapJSON = Files.readString(Path.of(file.getAbsolutePath()));
            return gson.fromJson(mindMapJSON, MindMap.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
