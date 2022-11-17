package dsw.gerumap.app.gui.swing.view.repository.preview;

import dsw.gerumap.app.gui.swing.view.repository.MindMapView;
import dsw.gerumap.app.observer.NotificationType;
import dsw.gerumap.app.repository.composite.ModelNode;
import dsw.gerumap.app.repository.models.MindMap;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class MindMapPreview extends ModelPreview {

    public MindMapPreview(MindMap mindMap) {
        super(mindMap);

        setPreferredSize(new Dimension(200, 200));
        setLayout(new GridBagLayout());
        JPanel panel = new JPanel(new BorderLayout());
        panel.setPreferredSize(new Dimension(150, 150));
        panel.setBackground(new Color(200, 200, 200));
        panel.setBorder(new LineBorder(Color.white, 1));
        JLabel label = new JLabel(mindMap.getName(), SwingConstants.CENTER);
        label.setForeground(Color.black);
        panel.add(label);
        add(panel);
    }

    @Override
    public void update(NotificationType notificationType, Object object) {

    }
}
