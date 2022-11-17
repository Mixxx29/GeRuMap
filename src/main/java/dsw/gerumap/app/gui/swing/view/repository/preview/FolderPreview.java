package dsw.gerumap.app.gui.swing.view.repository.preview;

import dsw.gerumap.app.gui.swing.view.repository.composite.CompositeModelView;
import dsw.gerumap.app.gui.swing.view.repository.composite.ModelView;
import dsw.gerumap.app.observer.NotificationType;
import dsw.gerumap.app.repository.composite.ModelNode;
import dsw.gerumap.app.repository.models.Folder;
import dsw.gerumap.app.resources.ResourceLoader;
import dsw.gerumap.app.resources.ResourceType;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class FolderPreview extends ModelPreview {

    private JLabel nameLabel;

    public FolderPreview(Folder folder) {
        super(folder);

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
        nameLabel = new JLabel(folder.getName());
        constraints.gridx = 0;
        constraints.gridy = 1;
        add(nameLabel, constraints);
    }

    @Override
    public void update(NotificationType notificationType, Object object) {
        switch (notificationType) {
            case RENAME -> {
                nameLabel.setText(model.getName());
            }
        }
    }
}
