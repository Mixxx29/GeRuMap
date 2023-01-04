package dsw.gerumap.app.gui.swing.commands.graphics;

import dsw.gerumap.app.gui.swing.commands.AbstractCommand;
import dsw.gerumap.app.repository.elements.LinkElement;
import dsw.gerumap.app.repository.elements.TermElement;
import dsw.gerumap.app.repository.models.MindMap;
import dsw.gerumap.app.repository.models.Project;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LinkElementStrokeColorCommand extends AbstractCommand {

    private Project project;
    private Map<LinkElement, Color> elements;
    private Color newColor;
    private Color oldColor;

    public LinkElementStrokeColorCommand(Project project,
                                         MindMap mindMap,
                                         Map<LinkElement, Color> elements,
                                         Color newColor,
                                         Color oldColor) {
        super(mindMap);
        this.project = project;
        this.elements = elements;
        this.newColor = newColor;
        this.oldColor = oldColor;
    }

    @Override
    public void doCommand() {
        List<LinkElement> termElements = new ArrayList<>(elements.keySet());
        project.setLinkElementStrokeColor(newColor);
        mindMap.setLinkElementStrokeColor(termElements, newColor);
        super.doCommand();
    }

    @Override
    public void undoCommand() {
        project.setLinkElementStrokeColor(oldColor);
        for (LinkElement linkElement : elements.keySet()) {
            mindMap.setLinkElementStrokeColor(List.of(linkElement), elements.get(linkElement));
        }
        super.undoCommand();
    }
}
