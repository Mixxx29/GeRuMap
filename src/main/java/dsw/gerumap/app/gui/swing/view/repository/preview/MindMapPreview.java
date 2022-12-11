package dsw.gerumap.app.gui.swing.view.repository.preview;

import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.gui.swing.view.custom.CustomContextMenu;
import dsw.gerumap.app.gui.swing.view.repository.models.MindMapView;
import dsw.gerumap.app.observer.NotificationType;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MindMapPreview extends ModelPreview {

    private JLabel label;

    public MindMapPreview(MindMapView mindMapView) {
        super(mindMapView);

        setPreferredSize(new Dimension(200, 200));
        setLayout(new GridBagLayout());
        JPanel panel = new JPanel(new BorderLayout());
        panel.setPreferredSize(new Dimension(150, 150));
        panel.setBackground(new Color(200, 200, 200));
        panel.setBorder(new LineBorder(Color.white, 1));
        label = new JLabel(mindMapView.getName(), SwingConstants.CENTER);
        label.setForeground(Color.black);
        panel.add(label);
        add(panel);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                parentView.selectPreview(MindMapPreview.this);

                // Update actions
                MainFrame.getInstance().getActionManager().notifyListeners(NotificationType.SELECTED, mindMapView.getModel());

                // Check if right mouse button
                if (e.getButton() == MouseEvent.BUTTON3) {
                    // !!!UPGRADE!!! Chow context menu
                    new CustomContextMenu(mindMapView.getModel()).show(MindMapPreview.this, e.getX(), e.getY());
                }
            }
        });
    }

    @Override
    public void update(NotificationType notificationType, Object object) {
        switch (notificationType) {
            case SELECTED -> {
                parentView.selectPreview(this);
            }

            case RENAME -> {
                label.setText(getView().getName());
                label.updateUI();
            }
        }
    }
}
