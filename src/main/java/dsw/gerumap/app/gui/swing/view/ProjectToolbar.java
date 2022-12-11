package dsw.gerumap.app.gui.swing.view;

import dsw.gerumap.app.gui.swing.actions.ActionManager;
import dsw.gerumap.app.gui.swing.view.custom.LinkSettings;
import dsw.gerumap.app.gui.swing.view.custom.TermSettings;
import dsw.gerumap.app.gui.swing.view.custom.ToolbarButton;
import dsw.gerumap.app.gui.swing.view.custom.ToolbarSeparator;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ProjectToolbar extends JToolBar {

    private ToolbarButton selected;

    private ToolbarButton selectionToolButton;
    private ToolbarButton moveToolButton;
    private ToolbarButton zoomToolButton;
    private ToolbarButton eraserToolButton;
    private ToolbarButton termToolButton;
    private ToolbarButton linkToolButton;

    private TermSettings termSettings;
    private LinkSettings linkSettings;

    public ProjectToolbar() {
        super(VERTICAL);
        setFloatable(false);
        setBorder(new EmptyBorder(8, 5, 0, 11));
        setBackground(new Color(80, 80, 80));

        ActionManager actionManager = MainFrame.getInstance().getActionManager();
        selectionToolButton = new ToolbarButton(actionManager.getSelectToolAction());
        add(selectionToolButton);
        addEmptySeparator();

        moveToolButton = new ToolbarButton(actionManager.getMoveToolAction());
        add(moveToolButton);
        addEmptySeparator();

        zoomToolButton = new ToolbarButton(actionManager.getZoomToolAction());
        add(zoomToolButton);
        addEmptySeparator();

        eraserToolButton = new ToolbarButton(actionManager.getEraseToolAction());
        add(eraserToolButton);

        addLineSeparator();

        termToolButton = new ToolbarButton(actionManager.getTermToolAction());
        add(termToolButton);
        addEmptySeparator();

        linkToolButton = new ToolbarButton(actionManager.getLinkToolAction());
        add(linkToolButton);

        addLineSeparator();

        termSettings = new TermSettings();
        add(termSettings);

        addLineSeparator();

        linkSettings = new LinkSettings();
        add(linkSettings);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(new Color(150, 150, 150));
        g.fillRect(getWidth() - 3, 0, getWidth(), getHeight());
    }

    private void addEmptySeparator() {
        add(new Separator(new Dimension(0, 5)));
    }

    private void addLineSeparator() {
        JPanel separator = new JPanel() {
            @Override
            public void paint(Graphics g) {
                super.paint(g);
                g.setColor(new Color(120, 120, 120));
                g.drawLine(2, getHeight() / 2, getWidth() - 1, getHeight() / 2);
            }
        };
        separator.setBorder(new EmptyBorder(0, -10, 0, 0));
        separator.setMaximumSize(new Dimension(90, 17));
        add(separator);
    }

    private void selectButton(ToolbarButton button) {
        if (button == null) return;
        if (selected != null) {
            selected.select(false);
        }
        selected = button;
        selected.select(true);
    }

    public void selectSelectionToolButton() {
        selectButton(selectionToolButton);
    }

    public void selectMoveToolButton() {
        selectButton(moveToolButton);
    }

    public void selectZoomToolButton() {
        selectButton(zoomToolButton);
    }

    public void selectEraserToolButton() {
        selectButton(eraserToolButton);
    }

    public void selectTermToolButton() {
        selectButton(termToolButton);
    }

    public void selectLinkToolButton() {
        selectButton(linkToolButton);
    }
}
