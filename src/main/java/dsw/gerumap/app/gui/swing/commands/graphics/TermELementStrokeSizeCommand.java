package dsw.gerumap.app.gui.swing.commands.graphics;

import dsw.gerumap.app.gui.swing.commands.AbstractCommand;
import dsw.gerumap.app.repository.elements.TermElement;
import dsw.gerumap.app.repository.models.MindMap;
import dsw.gerumap.app.repository.models.Project;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TermELementStrokeSizeCommand extends AbstractCommand {

    private Project project;
    private Map<TermElement, Integer> elements;
    private int newSize;
    private int oldSize;

    public TermELementStrokeSizeCommand(Project project,
                                        MindMap mindMap,
                                        Map<TermElement, Integer> elements,
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
        List<TermElement> termElements = new ArrayList<>(elements.keySet());
        project.setTermElementStrokeSize(newSize);
        mindMap.setTermElementStrokeSize(termElements, newSize);
        super.doCommand();
    }

    @Override
    public void undoCommand() {
        project.setTermElementStrokeSize(oldSize);
        for (TermElement termElement : elements.keySet()) {
            mindMap.setTermElementStrokeSize(List.of(termElement), elements.get(termElement));
        }
        super.undoCommand();
    }
}
