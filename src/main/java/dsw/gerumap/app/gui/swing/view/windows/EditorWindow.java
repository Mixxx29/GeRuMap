package dsw.gerumap.app.gui.swing.view.windows;

import dsw.gerumap.app.core.App;
import dsw.gerumap.app.gui.swing.tree.model.TreeItem;
import dsw.gerumap.app.gui.swing.view.custom.PathItem;
import dsw.gerumap.app.gui.swing.view.repository.WorkspaceView;
import dsw.gerumap.app.gui.swing.view.repository.preview.ModelPreview;
import dsw.gerumap.app.observer.NotificationType;
import dsw.gerumap.app.repository.composite.ModelNode;
import dsw.gerumap.app.repository.models.MindMap;
import dsw.gerumap.app.resources.ResourceLoader;
import dsw.gerumap.app.resources.ResourceType;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class EditorWindow extends AbstractWindow {

    private final WorkspaceView workspaceView;
    private final JLabel back;

    private ModelNode model;

    public EditorWindow() {
        workspaceView = new WorkspaceView(App.getRepository().getWorkspace());

        back = new JLabel(ResourceLoader.<ImageIcon>load("back_arrow.png", ResourceType.ICON));
        back.setCursor(new Cursor(Cursor.HAND_CURSOR));
        back.setToolTipText("Go Back");
        back.setPreferredSize(new Dimension(30, 30));
        back.setBackground(new Color(150, 150, 150));
        back.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                back.setOpaque(false);
                back.setBorder(null);
                model = model.getParent();
                model.notifyListeners(NotificationType.SELECT, null);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                back.setOpaque(true);
                back.setBorder(new LineBorder(new Color(200, 200, 200)));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                back.setOpaque(false);
                back.setBorder(null);
            }
        });

        content.setLayout(new CardLayout());
        content.add(workspaceView, workspaceView.getName());

        titleLabelPanel = new JPanel(new GridBagLayout());
        JPanel wrapper = new JPanel(new BorderLayout()) {
            @Override
            public void paint(Graphics g) {
                super.paint(g);
                g.setColor(new Color(150, 150, 150));
                g.drawLine(0, getHeight() - 1, getWidth() - 1, getHeight() - 1);
            }
        };
        wrapper.setBorder(new EmptyBorder(10, 20, 11, 0));
        wrapper.add(titleLabelPanel, BorderLayout.WEST);
        add(wrapper, BorderLayout.NORTH);

        setPath(workspaceView.getModel());
    }

    public WorkspaceView getWorkspaceView() {
        return workspaceView;
    }

    public void setPath(ModelNode model) {
        this.model = model;
        List<PathItem> path = new ArrayList<>();

        ModelNode curr = model;
        while (curr != null) {
            if (!(curr instanceof MindMap)) path.add(new PathItem(curr));
            curr = curr.getParent();
        }

        titleLabelPanel.removeAll();
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        if (model.getParent() != null) {
            constraints.insets = new Insets(0, 0, 0, 20);
            titleLabelPanel.add(back, constraints);
            constraints.gridx++;
            constraints.insets = new Insets(0, 0, 0, 0);
        }
        for (int i = path.size() - 1; i >= 0; i--) {
            PathItem item = path.get(i);
            titleLabelPanel.add(item, constraints);
            constraints.gridx++;
            if (i != 0) {
                titleLabelPanel.add(new PathItem(">"), constraints);
                constraints.gridx++;
            }
        }

        titleLabelPanel.updateUI();
    }
}
