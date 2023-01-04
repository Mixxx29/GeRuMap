package dsw.gerumap.app.gui.swing.commands.graphics;

import dsw.gerumap.app.gui.swing.commands.AbstractCommand;
import dsw.gerumap.app.repository.elements.MindMapElement;
import dsw.gerumap.app.repository.models.MindMap;
import dsw.gerumap.app.repository.models.Project;

import java.util.List;

public class EraseElementCommand extends AbstractCommand {
    private List<MindMapElement> elements;

    public EraseElementCommand(MindMap mindMap, List<MindMapElement> elements) {
        super(mindMap);
        this.elements = elements;
    }

    @Override
    public void doCommand() {
        for (MindMapElement element : elements) {
            mindMap.removeElement(element);
        }
        super.doCommand();
    }

    @Override
    public void undoCommand() {
        for (MindMapElement element : elements) {
            mindMap.addElement(element);
        }
        super.undoCommand();
    }
}
