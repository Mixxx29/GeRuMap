package dsw.gerumap.app.gui.swing.actions;

import dsw.gerumap.app.gui.swing.actions.edit.*;
import dsw.gerumap.app.gui.swing.actions.file.*;
import dsw.gerumap.app.gui.swing.actions.help.AboutAction;
import dsw.gerumap.app.gui.swing.actions.help.DevelopersAction;
import dsw.gerumap.app.gui.swing.actions.project.*;
import dsw.gerumap.app.observer.IListener;
import dsw.gerumap.app.observer.IPublisher;
import dsw.gerumap.app.observer.NotificationType;

import java.util.ArrayList;
import java.util.List;

public class ActionManager implements IPublisher {

    private List<IListener> listeners;

    private CreateFolderAction createFolderAction;
    private OpenProjectAction openProjectAction;
    private LoadTemplateAction loadTemplateAction;
    private CreateProjectAction createProjectAction;
    private CreateMindMapAction createMindMapAction;
    private SaveAction saveAction;
    private SaveAsAction saveAsAction;
    private ExportAsPNGAction exportAsPNGAction;
    private ExportAsTemplateAction exportAsTemplateAction;

    private UndoAction undoAction;
    private RedoAction redoAction;
    private RenameAction renameAction;
    private DeleteAction deleteAction;
    private ProjectSettingsAction mindMapSettingsAction;

    private SelectToolAction selectToolAction;
    private MoveToolAction moveToolAction;
    private ZoomToolAction zoomToolAction;
    private EraseToolAction eraseToolAction;
    private TermToolAction termToolAction;
    private LinkToolAction linkToolAction;

    private DevelopersAction developersAction;
    private AboutAction aboutAction;

    public ActionManager() {
        listeners = new ArrayList<>();

        createFolderAction = new CreateFolderAction(this);
        createProjectAction = new CreateProjectAction(this);
        createMindMapAction = new CreateMindMapAction(this);
        openProjectAction = new OpenProjectAction(this);
        loadTemplateAction = new LoadTemplateAction(this);
        saveAction = new SaveAction(this);
        saveAsAction = new SaveAsAction(this);
        exportAsPNGAction = new ExportAsPNGAction(this);
        exportAsTemplateAction = new ExportAsTemplateAction(this);

        undoAction = new UndoAction(this);
        redoAction = new RedoAction(this);
        renameAction = new RenameAction(this);
        deleteAction = new DeleteAction(this);
        mindMapSettingsAction = new ProjectSettingsAction(this);

        selectToolAction = new SelectToolAction(this);
        moveToolAction = new MoveToolAction(this);
        zoomToolAction = new ZoomToolAction(this);
        eraseToolAction = new EraseToolAction(this);
        termToolAction = new TermToolAction(this);
        linkToolAction = new LinkToolAction(this);

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

    public OpenProjectAction getOpenProjectAction() {
        return openProjectAction;
    }

    public LoadTemplateAction getLoadTemplateAction() {
        return loadTemplateAction;
    }

    public SaveAction getSaveAction() {
        return saveAction;
    }

    public SaveAsAction getSaveAsAction() {
        return saveAsAction;
    }

    public ExportAsPNGAction getExportAsPNGAction() {
        return exportAsPNGAction;
    }

    public ExportAsTemplateAction getExportAsTemplateAction() {
        return exportAsTemplateAction;
    }

    public UndoAction getUndoAction() {
        return undoAction;
    }

    public RedoAction getRedoAction() {
        return redoAction;
    }

    public RenameAction getRenameAction() {
        return renameAction;
    }

    public DeleteAction getDeleteAction() {
        return deleteAction;
    }
    public ProjectSettingsAction getMindMapSettingsAction() {
        return mindMapSettingsAction;
    }

    public SelectToolAction getSelectToolAction() {
        return selectToolAction;
    }

    public MoveToolAction getMoveToolAction() {
        return moveToolAction;
    }

    public ZoomToolAction getZoomToolAction() {
        return zoomToolAction;
    }

    public EraseToolAction getEraseToolAction() {
        return eraseToolAction;
    }

    public TermToolAction getTermToolAction() {
        return termToolAction;
    }

    public LinkToolAction getLinkToolAction() {
        return linkToolAction;
    }

    public DevelopersAction getDevelopersAction() {
        return developersAction;
    }

    public AboutAction getAboutAction() {
        return aboutAction;
    }
}
