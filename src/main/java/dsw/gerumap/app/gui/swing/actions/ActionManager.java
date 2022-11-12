package dsw.gerumap.app.gui.swing.actions;

import dsw.gerumap.app.gui.swing.actions.edit.DeleteAction;
import dsw.gerumap.app.gui.swing.actions.edit.MindMapSettingsAction;
import dsw.gerumap.app.gui.swing.actions.edit.RenameAction;
import dsw.gerumap.app.gui.swing.actions.file.*;
import dsw.gerumap.app.gui.swing.actions.help.AboutAction;
import dsw.gerumap.app.gui.swing.actions.help.DevelopersAction;

public class ActionManager {

    private CreateFolderAction createFolderAction;
    private CreateProjectAction createProjectAction;
    private CreateMindMapAction createMindMapAction;
    private SaveAction saveAction;
    private SaveAsAction saveAsAction;
    private ExportAction exportAction;

    private RenameAction renameAction;
    private DeleteAction deleteAction;
    private MindMapSettingsAction mindMapSettingsAction;

    private DevelopersAction developersAction;
    private AboutAction aboutAction;

    public ActionManager() {
        createFolderAction = new CreateFolderAction();
        createProjectAction = new CreateProjectAction();
        createMindMapAction = new CreateMindMapAction();
        saveAction = new SaveAction();
        saveAsAction = new SaveAsAction();
        exportAction = new ExportAction();

        renameAction = new RenameAction();
        deleteAction = new DeleteAction();
        mindMapSettingsAction = new MindMapSettingsAction();

        developersAction = new DevelopersAction();
        aboutAction = new AboutAction();
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

    public MindMapSettingsAction getMindMapSettingsAction() {
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
