package dsw.gerumap.app.gui.swing.commands.graphics;

import dsw.gerumap.app.gui.swing.commands.AbstractCommand;
import dsw.gerumap.app.repository.elements.LinkElement;
import dsw.gerumap.app.repository.models.MindMap;

import java.util.List;

public class LinkCommand extends AbstractCommand {

    private LinkElement element;

    public LinkCommand(MindMap mindMap, LinkElement element) {
        super(mindMap);
        this.element = element;
    }

    @Override
    public void doCommand() {
        mindMap.addElement(element);
        mindMap.selectElements(List.of(element));
        super.doCommand();
    }

    @Override
    public void undoCommand() {
        mindMap.removeElement(element);
        super.undoCommand();
    }
}
