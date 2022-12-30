package dsw.gerumap.app.gui.swing.commands.graphics;

import dsw.gerumap.app.gui.swing.commands.AbstractCommand;
import dsw.gerumap.app.repository.elements.MindMapElement;
import dsw.gerumap.app.repository.models.MindMap;

import java.awt.*;
import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;
import java.util.List;

public class MoveElementCommand extends AbstractCommand {

    private final MindMap mindMap;
    private final Point2D.Float offset;
    private final List<MindMapElement> elements;

    public MoveElementCommand(MindMap mindMap, Point2D.Float offset, List<MindMapElement> elements) {
        this.mindMap = mindMap;
        this.offset = offset;
        this.elements = elements;
    }

    @Override
    public void doCommand() {
        mindMap.moveElements(offset);
    }

    @Override
    public void undoCommand() {
        mindMap.moveElements(new Point2D.Float(-offset.x, -offset.y));
    }
}
