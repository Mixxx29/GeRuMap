package dsw.gerumap.app.gui.swing.view;

import dsw.gerumap.app.gui.swing.actions.ActionManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MainToolBar extends JToolBar {

    public MainToolBar() {
        super(HORIZONTAL);
        setBorder(new EmptyBorder(0, 5, 1, 5));
        setBackground(new Color(80, 80, 80));

        ActionManager actionManager = MainFrame.getInstance().getActionManager();

        addButton(actionManager.getCreateFolderAction());
        addButton(actionManager.getCreateProjectAction());
        addButton(actionManager.getCreateMindMapAction());

        addVerticalSeparator();

        addButton(actionManager.getSaveAction());
        addButton(actionManager.getSaveAsAction());

        addVerticalSeparator();

        addButton(actionManager.getExportAction());

        addVerticalSeparator();

        addButton(actionManager.getMindMapSettingsAction());

        addVerticalSeparator();

        addButton(actionManager.getRenameAction());
        addButton(actionManager.getDeleteAction());

        addVerticalSeparator();

        addButton(actionManager.getDevelopersAction());
        addButton(actionManager.getAboutAction());
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(new Color(150, 150, 150));
        g.drawLine(0, getHeight() - 1, getWidth(), getHeight() - 1);
    }

    private void addButton(Action action) {
        JButton newButton = new JButton(action) {
            @Override
            public void paint(Graphics g) {
                if (getModel().isPressed()) {
                    g.setColor(new Color(150, 150, 150));
                } else if (getModel().isRollover()) {
                    g.setColor(new Color(120, 120, 120));
                } else {
                    g.setColor(new Color(80, 80, 80));
                }
                g.fillRect(0, 0, getWidth(), getHeight());
                super.paint(g);
            }
        };
        newButton.setContentAreaFilled(false);
        newButton.setBorderPainted(false);
        newButton.setFocusable(false);
        newButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        newButton.setHideActionText(true);
        add(newButton);
    }

    private void addVerticalSeparator() {
        add(new Separator(new Dimension(11, 22)) {
            @Override
            public void paint(Graphics g) {
                super.paint(g);
                g.setColor(new Color(120, 120, 120));
                g.drawLine(getWidth() / 2, -5, getWidth() / 2, getHeight());
            }
        });
    }
}
