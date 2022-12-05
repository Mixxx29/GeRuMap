package dsw.gerumap.app.gui.swing.view.custom;

import dsw.gerumap.app.gui.swing.actions.AbstractCustomAction;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class ToolbarButton extends JButton {

    private boolean selected;

    public ToolbarButton(Action action) {
        super(action);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusable(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setHideActionText(true);
    }

    @Override
    public void paint(Graphics g) {
        if (!selected && getModel().isPressed()) {
            g.setColor(new Color(150, 150, 150));
        } else if (!selected && getModel().isRollover()) {
            g.setColor(new Color(120, 120, 120));
        } else {
            g.setColor(new Color(80, 80, 80));
        }
        g.fillRect(0, 0, getWidth(), getHeight());
        if (selected) {
            g.setColor(Color.white);
            g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
        }
        super.paint(g);
    }

    public void select(boolean selected) {
        if (this.selected == selected) return;
        this.selected = selected;
        updateUI();
    }
}
