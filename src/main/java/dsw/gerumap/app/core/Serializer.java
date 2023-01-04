package dsw.gerumap.app.core;

import dsw.gerumap.app.repository.models.Project;

import java.io.File;
import java.io.IOException;

public interface Serializer {

    void saveProject(Project project);
    Project loadProject(File file);
}
