package dsw.gerumap.app.gui.swing.view;

import dsw.gerumap.app.gui.swing.view.custom.LinkSettings;
import dsw.gerumap.app.gui.swing.view.custom.TermSettings;
import dsw.gerumap.app.gui.swing.view.custom.ToolbarSeparator;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ProjectBottomToolbar extends JToolBar {

    private TermSettings termSettings;
    private LinkSettings linkSettings;

    public ProjectBottomToolbar() {
        super(HORIZONTAL);
        setFloatable(false);
        setBorder(new EmptyBorder(8, 0, 5, 0));
        setBackground(new Color(80, 80, 80));

        termSettings = new TermSettings();
        add(termSettings);

        addVerticalSeparator();

        linkSettings = new LinkSettings();
        add(linkSettings);

        addVerticalSeparator();
    }

    private void addVerticalSeparator() {
        add(new ToolbarSeparator(new Dimension(1, 42), VERTICAL));
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(new Color(150, 150, 150));
        g.fillRect(0, 0, getWidth() - 1, 3);
    }
}