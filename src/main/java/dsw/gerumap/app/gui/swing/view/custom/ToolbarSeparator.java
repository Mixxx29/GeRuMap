package dsw.gerumap.app.gui.swing.view.custom;

import javax.swing.*;
import java.awt.*;

public class ToolbarSeparator extends JToolBar.Separator {

    private int direction;

    public ToolbarSeparator(Dimension dimension, int direction) {
        super(dimension);
        this.direction = direction;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(new Color(120, 120, 120));
        if (direction == VERTICAL) g.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight());
        else if (direction == HORIZONTAL) g.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2);
    }
}
