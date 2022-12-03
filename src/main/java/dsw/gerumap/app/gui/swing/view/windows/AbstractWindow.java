package dsw.gerumap.app.gui.swing.view.windows;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public abstract class AbstractWindow extends JPanel {

    private JLabel titleLabel;
    protected JPanel content;

    protected JPanel titleLabelPanel;

    public AbstractWindow() {
        // Set layout
        setLayout(new BorderLayout());

        // Create title label
        titleLabel = new JLabel("", SwingConstants.CENTER);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBorder(new EmptyBorder(5, 0, 6, 0));

        // Create title label panel
        titleLabelPanel = new JPanel(new BorderLayout()) {
            @Override
            public void paint(Graphics g) {
                super.paint(g);
                g.setColor(new Color(150, 150, 150));
                g.drawLine(0, getHeight() - 1, getWidth() - 1, getHeight() - 1);
            }
        };
        titleLabelPanel.setBackground(new Color(60, 60, 60));
        titleLabelPanel.add(titleLabel, BorderLayout.CENTER);
        add(titleLabelPanel, BorderLayout.NORTH);

        content = new JPanel(new BorderLayout());
        add(content);
    }

    public void setTitle(String title) {
        titleLabel.setText(title);
    }
}
