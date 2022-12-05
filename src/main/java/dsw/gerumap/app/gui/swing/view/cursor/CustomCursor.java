package dsw.gerumap.app.gui.swing.view.cursor;

import dsw.gerumap.app.resources.ResourceLoader;
import dsw.gerumap.app.resources.ResourceType;

import java.awt.*;

public class CustomCursor {

    public static Cursor getCursor(CursorType type) {
        Cursor cursor = ResourceLoader.load(type.toString(), ResourceType.CURSOR);
        if (cursor == null) cursor = new Cursor(Cursor.DEFAULT_CURSOR);
        return cursor;
    }
}
