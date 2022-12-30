package dsw.gerumap.app.gui.swing.view.custom;

import dsw.gerumap.app.gui.swing.dialogs.factory.DialogFactory;
import dsw.gerumap.app.gui.swing.dialogs.factory.DialogType;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.observer.NotificationType;
import dsw.gerumap.app.repository.models.MindMap;
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

    private final JLabel strokeLabel;

    private final JPanel strokeColorPanel;

    private int strokeSize = 4;

    public LinkSettings() {
        super(new GridBagLayout());

        defaultStrokeColor = new Color(250, 74, 84);

        GridBagConstraints constraints = new GridBagConstraints();

        setMaximumSize(new Dimension(275, 200));

        JLabel label = new JLabel("Link", SwingConstants.CENTER);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(0, 10, -5, 10);
        add(label, constraints);

        label = new JLabel("Settings", SwingConstants.CENTER);
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.insets = new Insets(-5, 10, 0, 10);
        add(label, constraints);

        label = new JLabel("Stroke Color", SwingConstants.CENTER);
        label.setFont(label.getFont().deriveFont(Font.PLAIN));
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.insets = new Insets(0, 10, 5, 10);
        add(label, constraints);

        strokeColorPanel = new JPanel();
        strokeColorPanel.setMaximumSize(new Dimension(20, 20));
        strokeColorPanel.setMinimumSize(new Dimension(20, 20));
        strokeColorPanel.setPreferredSize(new Dimension(20, 20));
        strokeColorPanel.setBorder(new LineBorder(Color.white, 1));
        strokeColorPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        strokeColorPanel.setBackground(defaultStrokeColor);
        strokeColorPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                Color c = (Color) DialogFactory.createDialog(
                        DialogType.COLOR_CHOOSER,
                        "Choose term stroke color"
                ).start(strokeColorPanel.getBackground());
                ((MindMap) MainFrame.getInstance().getEditorWindow().getActiveProjectView().getDisplayed().getModel())
                        .addLinkElementStrokeColorCommand(c, strokeColorPanel.getBackground());
                strokeColorPanel.setBackground(c);
            }
        });
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.insets = new Insets(0, 10, 3, 10);
        add(strokeColorPanel, constraints);

        label = new JLabel("Stroke Size", SwingConstants.CENTER);
        label.setFont(label.getFont().deriveFont(Font.PLAIN));
        constraints.gridx = 2;
        constraints.gridy = 0;
        constraints.gridwidth = 3;
        constraints.insets = new Insets(0, 10, 5, 10);
        add(label, constraints);

        label = new JLabel(ResourceLoader.<ImageIcon>load("decrease_arrow.png", ResourceType.ICON));
        label.setCursor(new Cursor(Cursor.HAND_CURSOR));
        label.setBackground(new Color(120, 120, 120));
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (strokeSize <= 0) return;
                strokeSize--;
                strokeLabel.setText(String.valueOf(strokeSize));
                strokeLabel.updateUI();
                ((MindMap) MainFrame.getInstance().getEditorWindow().getActiveProjectView().getDisplayed().getModel())
                        .addLinkElementStrokeSizeCommand(strokeSize, strokeSize + 1);
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
        constraints.gridx = 2;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.insets = new Insets(0, 5, 5, 0);
        add(label, constraints);

        strokeLabel = new JLabel(String.valueOf(strokeSize), SwingConstants.CENTER);
        strokeLabel.setFont(label.getFont().deriveFont(14.0f));
        strokeLabel.setPreferredSize(
                new Dimension(
                        strokeLabel.getFontMetrics(strokeLabel.getFont()).stringWidth("99"),
                        strokeLabel.getFont().getSize()
                )
        );
        constraints.gridx = 3;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(0, 5, 5, 5);
        add(strokeLabel, constraints);

        label = new JLabel(ResourceLoader.<ImageIcon>load("increase_arrow.png", ResourceType.ICON));
        label.setBackground(new Color(120, 120, 120));
        label.setCursor(new Cursor(Cursor.HAND_CURSOR));
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (strokeSize >= 99) return;
                strokeSize++;
                strokeLabel.setText(String.valueOf(strokeSize));
                strokeLabel.updateUI();
                ((MindMap) MainFrame.getInstance().getEditorWindow().getActiveProjectView().getDisplayed().getModel())
                        .addLinkElementStrokeSizeCommand(strokeSize, strokeSize - 1);
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
        constraints.gridx = 4;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.NONE;
        constraints.insets = new Insets(0, 0, 5, 5);
        add(label, constraints);
    }

    public void setStrokeColor(Color strokeColor) {
        strokeColorPanel.setBackground(strokeColor);
        strokeColorPanel.repaint();
    }

    public void setStrokeSize(int strokeSize) {
        this.strokeSize = strokeSize;
        strokeLabel.setText(String.valueOf(strokeSize));
        strokeLabel.repaint();
    }
}
