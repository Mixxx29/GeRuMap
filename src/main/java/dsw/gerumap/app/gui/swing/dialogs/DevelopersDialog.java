package dsw.gerumap.app.gui.swing.dialogs;

import dsw.gerumap.app.resources.ResourceLoader;
import dsw.gerumap.app.resources.ResourceType;

import javax.swing.*;
import java.awt.*;

public class DevelopersDialog extends AbstractDialog {

    public DevelopersDialog() {
        super();
        setLayout(new BorderLayout());
        setSize(600, 700);
        getContentPane().setBackground(new Color(100, 100, 100));
        setLocationRelativeTo(null);
        setIconImage(ResourceLoader.load("developers.png", ResourceType.IMAGE));
        setTitle("Developers");
        Icon icon = ResourceLoader.load("developers.jpeg", ResourceType.ICON);
        JLabel iconLabel = new JLabel(icon);
        add(iconLabel, BorderLayout.NORTH);

        JPanel bottom = new JPanel(new GridLayout(1, 2)) {
            @Override
            public void paint(Graphics g) {
                super.paint(g);
                g.setColor(new Color(200, 200, 200));
                g.drawLine(0, 0, getWidth(), 0);
                g.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight() - 1);
            }
        };

        GridBagConstraints constraints = new GridBagConstraints();

        JPanel leftPanel = new JPanel(new GridBagLayout());
        JLabel leftTitleLabel = new JLabel("Project Director:", SwingConstants.CENTER);
        leftTitleLabel.setFont(leftTitleLabel.getFont().deriveFont(Font.ITALIC | Font.BOLD));
        constraints.gridx = 0;
        constraints.gridy = 0;
        leftPanel.add(leftTitleLabel, constraints);
        JLabel leftNameLabel = new JLabel("Ivan Bauk RN62/18", SwingConstants.CENTER);
        constraints.gridx = 0;
        constraints.gridy = 1;
        leftPanel.add(leftNameLabel, constraints);
        bottom.add(leftPanel);

        JPanel rightPanel = new JPanel(new GridBagLayout());
        JLabel rightTitleLabel = new JLabel("Project Manager:", SwingConstants.CENTER);
        rightTitleLabel.setFont(rightTitleLabel.getFont().deriveFont(Font.ITALIC | Font.BOLD));
        constraints.gridx = 0;
        constraints.gridy = 0;
        rightPanel.add(rightTitleLabel, constraints);
        JLabel rightNameLabel = new JLabel("Mihajlo Randjelovic RN21/18", SwingConstants.CENTER);
        constraints.gridx = 0;
        constraints.gridy = 1;
        rightPanel.add(rightNameLabel, constraints);
        bottom.add(rightPanel);

        add(bottom);
    }

    @Override
    public Object start(Object object) {
        setVisible(true);
        return null;
    }
}