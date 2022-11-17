package dsw.gerumap.app.gui.swing.view.custom;

import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.gui.swing.view.repository.preview.ModelPreview;
import dsw.gerumap.app.observer.NotificationType;
import dsw.gerumap.app.repository.composite.CompositeModelNode;
import dsw.gerumap.app.repository.factory.ModelType;
import dsw.gerumap.app.resources.ResourceLoader;
import dsw.gerumap.app.resources.ResourceType;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CreateModelButton extends JPanel {

    private CompositeModelNode parent;

    public CreateModelButton(ModelType modelType) {
        setCursor(new Cursor(Cursor.HAND_CURSOR));

        setOpaque(false);
        setPreferredSize(new Dimension(150, 150));

        // Set layout
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.ipady = 10;

        // Create icon label
        JLabel iconLabel = new JLabel();
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(iconLabel, constraints);

        // Create name label
        JLabel nameLabel = new JLabel("", SwingConstants.CENTER);
        constraints.gridx = 0;
        constraints.gridy = 1;
        add(nameLabel, constraints);

        switch (modelType) {
            case FOLDER -> {
                iconLabel.setIcon(ResourceLoader.load("create_folder_large.png", ResourceType.ICON));
                nameLabel.setText("Create Folder");
            }

            case PROJECT -> {
                iconLabel.setIcon(ResourceLoader.load("create_project_large.png", ResourceType.ICON));
                nameLabel.setText("Create Project");
            }

            case MIND_MAP -> {
                iconLabel.setIcon(ResourceLoader.load("create_mind_map_large.png", ResourceType.ICON));
                nameLabel.setText("Create Mind Map");
            }
        }

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setBackground(new Color(150, 150, 150));
                setBorder(new LineBorder(new Color(200, 200, 200), 1));
                updateUI();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                setBackground(new Color(120, 120, 120));
                updateUI();
                switch (modelType) {
                    case FOLDER -> {
                        MainFrame.getInstance().getActionManager().getCreateFolderAction().actionPerformed(null);
                    }

                    case PROJECT -> {
                        MainFrame.getInstance().getActionManager().getCreateProjectAction().actionPerformed(null);
                    }

                    case MIND_MAP -> {
                        MainFrame.getInstance().getActionManager().getCreateMindMapAction().action(parent);
                    }
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setOpaque(true);
                setBackground(new Color(120, 120, 120));
                setBorder(new LineBorder(new Color(200, 200, 200), 1));
                updateUI();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setOpaque(false);
                setBorder(null);
                updateUI();
            }
        });
    }

    public void setParent(CompositeModelNode parent) {
        this.parent = parent;
    }
}
