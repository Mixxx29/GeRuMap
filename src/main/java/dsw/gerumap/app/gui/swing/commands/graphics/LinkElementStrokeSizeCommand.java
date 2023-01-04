package dsw.gerumap.app.gui.swing.commands.graphics;

import dsw.gerumap.app.gui.swing.commands.AbstractCommand;
import dsw.gerumap.app.repository.elements.LinkElement;
import dsw.gerumap.app.repository.models.MindMap;
import dsw.gerumap.app.repository.models.Project;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LinkElementStrokeSizeCommand extends AbstractCommand {

    private Project project;
    private Map<LinkElement, Integer> elements;
    private int newSize;
    private int oldSize;

    public LinkElementStrokeSizeCommand(Project project,
                                        MindMap mindMap,
                                        Map<LinkElement, Integer> elements,
                                        int newSize,
                                        int oldSize) {
        super(mindMap);
        this.project = project;
        this.elements = elements;
        this.newSize = newSize;
        this.oldSize = oldSize;
    }

    @Override
    public void doCommand() {
        List<LinkElement> termElements = new ArrayList<>(elements.keySet());
        project.setLinkElementStrokeSize(newSize);
        mindMap.setLinkElementStrokeSize(termElements, newSize);
        super.doCommand();
    }

    @Override
    public void undoCommand() {
        project.setLinkElementStrokeSize(oldSize);
        for (LinkElement linkElement : elements.keySet()) {
            mindMap.setLinkElementStrokeSize(List.of(linkElement), elements.get(linkElement));
        }
        super.undoCommand();
    }
}
