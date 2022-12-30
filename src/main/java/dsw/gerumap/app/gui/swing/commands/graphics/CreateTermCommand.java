package dsw.gerumap.app.gui.swing.commands.graphics;

import dsw.gerumap.app.gui.swing.commands.AbstractCommand;
import dsw.gerumap.app.repository.elements.TermElement;
import dsw.gerumap.app.repository.models.MindMap;

import java.util.List;

public class CreateTermCommand extends AbstractCommand {

    private MindMap mindMap;
    private TermElement element;

    public CreateTermCommand(MindMap mindMap, TermElement element) {
        this.mindMap = mindMap;
        this.element = element;
    }

    @Override
    public void doCommand() {
        mindMap.addElement(element);
        mindMap.selectElements(List.of(element));
    }

    @Override
    public void undoCommand() {
        mindMap.removeElement(element);
    }
}
