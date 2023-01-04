package dsw.gerumap.app.gui.swing.commands.graphics;

import dsw.gerumap.app.gui.swing.commands.AbstractCommand;
import dsw.gerumap.app.repository.elements.MindMapElement;
import dsw.gerumap.app.repository.models.MindMap;

import java.awt.*;
import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;
import java.util.List;

public class MoveElementCommand extends AbstractCommand {

    private final Point2D.Float offset;
    private final List<MindMapElement> elements;
    private final boolean deselectAll;

    public MoveElementCommand(MindMap mindMap,
                              Point2D.Float offset,
                              List<MindMapElement> elements,
                              boolean deselectAll) {
        super(mindMap);
        this.offset = offset;
        this.elements = elements;
        this.deselectAll = deselectAll;
    }

    @Override
    public void doCommand() {
        mindMap.selectElements(elements);
        mindMap.moveElements(offset);
        super.doCommand();
    }

    @Override
    public void undoCommand() {
        mindMap.moveElements(new Point2D.Float(-offset.x, -offset.y));
        if (deselectAll) mindMap.deselectAllElements();
        super.undoCommand();
    }
}
