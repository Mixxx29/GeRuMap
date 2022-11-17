package dsw.gerumap.app.gui.swing.view.repository;

import dsw.gerumap.app.gui.swing.view.repository.composite.ModelView;
import dsw.gerumap.app.repository.composite.ModelNode;
import dsw.gerumap.app.repository.models.MindMap;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class MindMapView extends ModelView {

    public MindMapView(MindMap mindMap) {
        super(mindMap);

        setLayout(new GridBagLayout());
        JPanel content = new JPanel();
        content.setPreferredSize(new Dimension(800, 800));
        content.setBackground(new Color(200, 200, 200));
        content.setBorder(new LineBorder(Color.white, 2));
        content.setLayout(new BorderLayout());
        JLabel label = new JLabel(mindMap.getName(), SwingConstants.CENTER);
        label.setForeground(Color.black);
        content.add(label);
        add(content);
    }

    @Override
    protected void updateView() {

    }
}
