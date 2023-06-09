package dsw.gerumap.app.gui.swing.view.repository.preview;

import dsw.gerumap.app.gui.swing.view.repository.models.FolderView;
import dsw.gerumap.app.observer.NotificationType;
import dsw.gerumap.app.resources.ResourceLoader;
import dsw.gerumap.app.resources.ResourceType;

import javax.swing.*;
import java.awt.*;

public class FolderPreview extends ModelPreview {

    private final JLabel nameLabel;

    public FolderPreview(FolderView folderView) {
        super(folderView);

        // Set layout
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.ipady = 10;

        // Create icon label
        ImageIcon icon = ResourceLoader.load("folder_large.png", ResourceType.ICON);
        JLabel iconLabel = new JLabel(icon);
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(iconLabel, constraints);

        // Create name label
        nameLabel = new JLabel(folderView.getName());
        constraints.gridx = 0;
        constraints.gridy = 1;
        add(nameLabel, constraints);
    }

    @Override
    public void update(NotificationType notificationType, Object object) {
        switch (notificationType) {
            case RENAME -> {
                nameLabel.setText(view.getName());
            }
        }
    }
}
