package dsw.gerumap.app.gui.swing.view.custom;

import dsw.gerumap.app.gui.swing.dialogs.factory.DialogFactory;
import dsw.gerumap.app.gui.swing.dialogs.factory.DialogType;
import dsw.gerumap.app.resources.ResourceLoader;
import dsw.gerumap.app.resources.ResourceType;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LinkSettings extends JPanel {
    private Color defaultStrokeColor;

    public LinkSettings() {
        super(new GridBagLayout());

        defaultStrokeColor = new Color(250, 74, 84);

        GridBagConstraints constraints = new GridBagConstraints();

        setMaximumSize(new Dimension(90, 210));
        setBorder(new EmptyBorder(0, -20, 0, -20));

        JLabel label = new JLabel("Link", SwingConstants.CENTER);
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(label, constraints);

        label = new JLabel("Settings", SwingConstants.CENTER);
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.insets = new Insets(0, 0, 10, 0);
        add(label, constraints);


        label = new JLabel("Stroke", SwingConstants.CENTER);
        label.setFont(label.getFont().deriveFont(Font.PLAIN));
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.insets = new Insets(0, 0, 0, 0);
        add(label, constraints);

        label = new JLabel("Color", SwingConstants.CENTER);
        label.setFont(label.getFont().deriveFont(Font.PLAIN));
        constraints.gridx = 0;
        constraints.gridy = 3;
        add(label, constraints);

        JPanel strokeColorPanel = new JPanel();
        strokeColorPanel.setMaximumSize(new Dimension(20, 20));
        strokeColorPanel.setMinimumSize(new Dimension(20, 20));
        strokeColorPanel.setPreferredSize(new Dimension(20, 20));
        strokeColorPanel.setBorder(new LineBorder(Color.white, 1));
        strokeColorPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        strokeColorPanel.setBackground(defaultStrokeColor);
        strokeColorPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                strokeColorPanel.setBackground(
                        (Color) DialogFactory.createDialog(
                                DialogType.COLOR_CHOOSER,
                                "Choose link stroke color"
                        ).start(strokeColorPanel.getBackground())
                );
            }
        });
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.insets = new Insets(5, 0, 12, 0);
        add(strokeColorPanel, constraints);

        label = new JLabel("Stroke", SwingConstants.CENTER);
        label.setFont(label.getFont().deriveFont(Font.PLAIN));
        constraints.gridx = 0;
        constraints.gridy = 5;
        constraints.insets = new Insets(0, 0, 0, 0);
        add(label, constraints);

        label = new JLabel("Size", SwingConstants.CENTER);
        label.setFont(label.getFont().deriveFont(Font.PLAIN));
        constraints.gridx = 0;
        constraints.gridy = 6;
        add(label, constraints);

        label = new JLabel(ResourceLoader.<ImageIcon>load("increase_arrow.png", ResourceType.ICON));
        label.setCursor(new Cursor(Cursor.HAND_CURSOR));
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                ((JLabel)e.getComponent()).setOpaque(true);
                updateUI();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                ((JLabel)e.getComponent()).setOpaque(false);
                updateUI();
            }
        });
        constraints.gridx = 0;
        constraints.gridy = 7;
        constraints.insets = new Insets(5, 0, 0, 0);
        add(label, constraints);

        label = new JLabel("2", SwingConstants.CENTER);
        label.setFont(label.getFont().deriveFont(14.0f));
        constraints.gridx = 0;
        constraints.gridy = 8;
        constraints.insets = new Insets(0, 0, 0, 0);
        add(label, constraints);

        label = new JLabel(ResourceLoader.<ImageIcon>load("decrease_arrow.png", ResourceType.ICON));
        label.setCursor(new Cursor(Cursor.HAND_CURSOR));
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                ((JLabel)e.getComponent()).setOpaque(true);
                updateUI();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                ((JLabel)e.getComponent()).setOpaque(false);
                updateUI();
            }
        });
        constraints.gridx = 0;
        constraints.gridy = 9;
        add(label, constraints);
    }
}
