package dsw.gerumap.app.gui.swing.commands;

import dsw.gerumap.app.repository.models.MindMap;
import dsw.gerumap.app.repository.models.Project;

import javax.swing.*;

public abstract class AbstractCommand {

    protected boolean wasChanged = true;

    protected MindMap mindMap;

    public AbstractCommand(MindMap mindMap) {
        this.mindMap = mindMap;
    }

    public void doCommand() {
        boolean temp = wasChanged;
        wasChanged = ((Project) mindMap.getParent()).hasChanged();
        ((Project) mindMap.getParent()).setChanged(temp);
    }

    public void undoCommand() {
        boolean temp = wasChanged;
        wasChanged = ((Project) mindMap.getParent()).hasChanged();
        ((Project) mindMap.getParent()).setChanged(temp);
    }
}
