package dsw.gerumap.app.gui.swing.commands.graphics;

import dsw.gerumap.app.gui.swing.commands.AbstractCommand;
import dsw.gerumap.app.repository.elements.TermElement;
import dsw.gerumap.app.repository.models.MindMap;
import dsw.gerumap.app.repository.models.Project;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TermElementStrokeColorCommand extends AbstractCommand {

    private Project project;
    private Map<TermElement, Color> elements;
    private Color newColor;
    private Color oldColor;

    public TermElementStrokeColorCommand(Project project,
                                         MindMap mindMap,
                                         Map<TermElement,
                                         Color> elements,
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
        List<TermElement> termElements = new ArrayList<>(elements.keySet());
        project.setTermElementStrokeColor(newColor);
        mindMap.setTermElementStrokeColor(termElements, newColor);
        super.doCommand();
    }

    @Override
    public void undoCommand() {
        project.setTermElementStrokeColor(oldColor);
        for (TermElement termElement : elements.keySet()) {
            mindMap.setTermElementStrokeColor(List.of(termElement), elements.get(termElement));
        }
        super.undoCommand();
    }
}
