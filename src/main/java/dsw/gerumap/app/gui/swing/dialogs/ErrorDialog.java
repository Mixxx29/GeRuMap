package dsw.gerumap.app.gui.swing.dialogs;

import dsw.gerumap.app.message.Message;
import dsw.gerumap.app.message.MessageType;
import dsw.gerumap.app.observer.IListener;
import dsw.gerumap.app.observer.IPublisher;
import dsw.gerumap.app.observer.NotificationType;
import dsw.gerumap.app.resources.ResourceLoader;
import dsw.gerumap.app.resources.ResourceType;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ErrorDialog extends AbstractDialog implements IListener {

    private JLabel messageLabel;

    public ErrorDialog() {
        setLayout(new BorderLayout());
        setTitle("Error");
        setSize(350, 170);
        setLocationRelativeTo(null);
        setIconImage(ResourceLoader.load("error.png", ResourceType.IMAGE));
        messageLabel = new JLabel("", SwingConstants.CENTER);
        messageLabel.setForeground(Color.white);
        add(messageLabel);

        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setOpaque(false);
        buttonPanel.setBorder(new EmptyBorder(0, 0, 20, 0));

        JButton createButton = new JButton("Ok");
        createButton.setBorder(null);
        createButton.setBackground(new Color(235, 80, 80));
        createButton.setForeground(Color.white);
        createButton.setPreferredSize(new Dimension(50, 30));
        buttonPanel.add(createButton);
        createButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        add(buttonPanel, BorderLayout.SOUTH);

    }

    @Override
    public Object start(Object object) {
        if (object instanceof Message message) {
            messageLabel.setText("<html>" + message.getMessage());
            setVisible(true);
        }
        return null;
    }

    @Override
    public void update(NotificationType notificationType, Object object) {
        if (notificationType == NotificationType.MESSAGE) {
            if (object instanceof Message message) {
                if (message.getType() == MessageType.ERROR) {
                    start(message);
                }
            }
        }
    }
}