package dsw.gerumap.app.state.states;

import dsw.gerumap.app.gui.swing.view.cursor.CursorType;
import dsw.gerumap.app.gui.swing.view.cursor.CustomCursor;
import dsw.gerumap.app.gui.swing.view.repository.models.MindMapView;
import dsw.gerumap.app.state.AbstractState;

import java.awt.event.MouseEvent;

public class EraserToolState extends AbstractState {

    public EraserToolState() {
        cursor = CustomCursor.getCursor(CursorType.ERASER_CURSOR);
    }


}
