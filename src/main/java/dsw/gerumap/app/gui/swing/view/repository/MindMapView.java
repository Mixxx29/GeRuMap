package dsw.gerumap.app.gui.swing.view.repository;

import dsw.gerumap.app.gui.swing.view.repository.composite.ModelView;
import dsw.gerumap.app.observer.NotificationType;
import dsw.gerumap.app.repository.composite.ModelNode;
import dsw.gerumap.app.repository.models.MindMap;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class MindMapView extends ModelView {

    private final JLabel title;

    public MindMapView(MindMap mindMap) {
        super(mindMap);
        setLayout(new BorderLayout());

        JPanel titlePanel = new JPanel(new BorderLayout()) {
            @Override
            public void paint(Graphics g) {
                super.paint(g);
                g.setColor(new Color(150, 150, 150));
                g.drawLine(0, getHeight() - 1, getWidth() - 1, getHeight() - 1);
            }
        };
        titlePanel.setBackground(new Color(60, 60, 60));
        titlePanel.setBorder(new EmptyBorder(10, 10, 11, 10));
        title = new JLabel(mindMap.getName(), SwingConstants.CENTER);
        title.setFont(title.getFont().deriveFont(Font.BOLD, 16.0f));
        titlePanel.add(title);
        add(titlePanel, BorderLayout.NORTH);

        JPanel content = new JPanel();
        content.setBackground(new Color(220, 220, 220));
        add(content, BorderLayout.CENTER);
    }

    @Override
    protected void updateView() {

    }

    @Override
    public void update(NotificationType notificationType, Object object) {
        switch (notificationType) {
            case DELETE -> {
                getParentView().removeView(this);
            }

            case RENAME -> {
                getParent().add(this, model.getName());
                title.setText(getName());
                title.updateUI();
            }
        }
        super.update(notificationType, object);
    }
}
