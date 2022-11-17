package dsw.gerumap.app.gui.swing.view.windows;

import dsw.gerumap.app.core.App;
import dsw.gerumap.app.gui.swing.view.repository.WorkspaceView;
import dsw.gerumap.app.gui.swing.view.repository.preview.ModelPreview;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class EditorWindow extends AbstractWindow {

    private final WorkspaceView workspaceView;

    private ModelPreview selectedPreview;

    private final JLabel displayedViewNameLabel;

    public EditorWindow() {
        workspaceView = new WorkspaceView(App.getRepository().getWorkspace());

        content.setLayout(new CardLayout());
        content.add(workspaceView, workspaceView.getName());

        displayedViewNameLabel = new JLabel(workspaceView.getName(), SwingConstants.CENTER) {
            @Override
            public void paint(Graphics g) {
                super.paint(g);
                g.setColor(new Color(150, 150, 150));
                g.drawLine(0, getHeight() - 1, getWidth() - 1, getHeight() - 1);
            }
        };
        displayedViewNameLabel.setFont(
                displayedViewNameLabel.getFont().deriveFont(Font.ITALIC | Font.BOLD, 16.0f)
        );
        displayedViewNameLabel.setBorder(new EmptyBorder(20, 0, 21, 0));
        add(displayedViewNameLabel, BorderLayout.NORTH);
    }

    public WorkspaceView getWorkspaceView() {
        return workspaceView;
    }

    public void selectPreview(ModelPreview preview) {
        if (selectedPreview != null) {
            selectedPreview.select(false);
        }

        selectedPreview = preview;
        if (selectedPreview != null) selectedPreview.select(true);
    }

    public void setDisplayedViewName(String name) {
        displayedViewNameLabel.setText(name);
        displayedViewNameLabel.updateUI();
    }
}
