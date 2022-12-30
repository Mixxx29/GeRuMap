package dsw.gerumap.app.gui.swing.commands.graphics;

import dsw.gerumap.app.gui.swing.commands.AbstractCommand;
import dsw.gerumap.app.repository.elements.MindMapElement;
import dsw.gerumap.app.repository.models.MindMap;

import java.util.List;

public class EraseElementCommand extends AbstractCommand {

    private MindMap mindMap;
    private List<MindMapElement> elements;

    public EraseElementCommand(MindMap mindMap, List<MindMapElement> elements) {
        this.mindMap = mindMap;
        this.elements = elements;
    }

    @Override
    public void doCommand() {
        for (MindMapElement element : elements) {
            mindMap.removeElement(element);
        }
    }

    @Override
    public void undoCommand() {
        for (MindMapElement element : elements) {
            mindMap.addElement(element);
        }
    }
}
