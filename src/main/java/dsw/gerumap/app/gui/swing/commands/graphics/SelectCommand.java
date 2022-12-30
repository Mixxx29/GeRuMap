package dsw.gerumap.app.gui.swing.commands.graphics;

import dsw.gerumap.app.gui.swing.commands.AbstractCommand;
import dsw.gerumap.app.repository.elements.MindMapElement;
import dsw.gerumap.app.repository.models.MindMap;

import java.util.ArrayList;
import java.util.List;

public class SelectCommand extends AbstractCommand {

    private MindMap mindMap;
    private List<MindMapElement> newElements;
    private List<MindMapElement> previousElements;

    public SelectCommand(MindMap mindMap, List<MindMapElement> newElements, List<MindMapElement> previousElements) {
        this.mindMap = mindMap;
        this.newElements = new ArrayList<>(newElements);
        this.previousElements = new ArrayList<>(previousElements);
    }

    @Override
    public void doCommand() {
        mindMap.selectElements(newElements);
    }

    @Override
    public void undoCommand() {
        mindMap.selectElements(previousElements);
    }
}
