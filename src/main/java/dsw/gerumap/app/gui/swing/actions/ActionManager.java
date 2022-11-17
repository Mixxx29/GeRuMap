package dsw.gerumap.app.gui.swing.actions;

import dsw.gerumap.app.gui.swing.actions.edit.DeleteAction;
import dsw.gerumap.app.gui.swing.actions.edit.ProjectSettingsAction;
import dsw.gerumap.app.gui.swing.actions.edit.RenameAction;
import dsw.gerumap.app.gui.swing.actions.file.*;
import dsw.gerumap.app.gui.swing.actions.help.AboutAction;
import dsw.gerumap.app.gui.swing.actions.help.DevelopersAction;
import dsw.gerumap.app.observer.IListener;
import dsw.gerumap.app.observer.IPublisher;
import dsw.gerumap.app.observer.NotificationType;
import dsw.gerumap.app.repository.composite.ModelNode;

import java.util.ArrayList;
import java.util.List;

public class ActionManager implements IPublisher {

    private List<IListener> listeners;

    private CreateFolderAction createFolderAction;
    private CreateProjectAction createProjectAction;
    private CreateMindMapAction createMindMapAction;
    private SaveAction saveAction;
    private SaveAsAction saveAsAction;
    private ExportAction exportAction;

    private RenameAction renameAction;
    private DeleteAction deleteAction;
    private ProjectSettingsAction mindMapSettingsAction;

    private DevelopersAction developersAction;
    private AboutAction aboutAction;

    public ActionManager() {
        listeners = new ArrayList<>();

        createFolderAction = new CreateFolderAction(this);
        createProjectAction = new CreateProjectAction(this);
        createMindMapAction = new CreateMindMapAction(this);
        saveAction = new SaveAction(this);
        saveAsAction = new SaveAsAction(this);
        exportAction = new ExportAction(this);

        renameAction = new RenameAction(this);
        deleteAction = new DeleteAction(this);
        mindMapSettingsAction = new ProjectSettingsAction(this);

        developersAction = new DevelopersAction(this);
        aboutAction = new AboutAction(this);
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

    public CreateFolderAction getCreateFolderAction() {
        return createFolderAction;
    }

    public CreateProjectAction getCreateProjectAction() {
        return createProjectAction;
    }

    public CreateMindMapAction getCreateMindMapAction() {
        return createMindMapAction;
    }

    public SaveAction getSaveAction() {
        return saveAction;
    }

    public SaveAsAction getSaveAsAction() {
        return saveAsAction;
    }

    public ExportAction getExportAction() {
        return exportAction;
    }

    public ProjectSettingsAction getMindMapSettingsAction() {
        return mindMapSettingsAction;
    }

    public RenameAction getRenameAction() {
        return renameAction;
    }

    public DeleteAction getDeleteAction() {
        return deleteAction;
    }

    public DevelopersAction getDevelopersAction() {
        return developersAction;
    }

    public AboutAction getAboutAction() {
        return aboutAction;
    }
}
