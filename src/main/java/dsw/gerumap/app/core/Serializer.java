package dsw.gerumap.app.core;

import dsw.gerumap.app.repository.models.MindMap;
import dsw.gerumap.app.repository.models.Project;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public interface Serializer {

    void saveProject(Project project);
    Project loadProject(File file);

    void saveTemplate(MindMap mindMap, String destination);
    MindMap loadTemplate(File file);
}
