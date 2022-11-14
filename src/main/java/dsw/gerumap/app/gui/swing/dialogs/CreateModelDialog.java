package dsw.gerumap.app.gui.swing.dialogs;

import dsw.gerumap.app.resources.ResourceLoader;
import dsw.gerumap.app.resources.ResourceType;
import dsw.gerumap.app.repository.factory.ModelType;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CreateModelDialog extends AbstractDialog {

    JTextField nameField;

    public CreateModelDialog(ModelType type) {
        super();

        setSize(360, 160);
        setLocationRelativeTo(null);
        setResizable(false);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                nameField.setText("");
                dispose();
            }
        });

        JLabel nameLabel = new JLabel("", SwingConstants.LEFT);
        nameLabel.setSize(285, nameLabel.getFontMetrics(nameLabel.getFont()).getHeight());
        nameLabel.setBounds(35, 15, nameLabel.getWidth(), nameLabel.getHeight());
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBorder(
                new CompoundBorder(
                        new LineBorder(new Color(200, 200, 200), 1),
                        new EmptyBorder(0 , 5, 0, 5)
                )
        );
        nameField.setSize(285, nameLabel.getFontMetrics(nameLabel.getFont()).getHeight() + 10);
        nameField.setBounds(30, 35, nameField.getWidth(), nameField.getHeight());
        add(nameField);

        switch (type) {
            case WORKSPACE -> {
                setIconImage(ResourceLoader.load("workspace.png", ResourceType.IMAGE));
                setTitle("Create new workspace");
                nameLabel.setText("Enter workspace name:");
            }

            case FOLDER -> {
                setIconImage(ResourceLoader.load("folder.png", ResourceType.IMAGE));
                setTitle("Create new folder");
                nameLabel.setText("Enter folder name:");
            }

            case PROJECT -> {
                setIconImage(ResourceLoader.load("project.png", ResourceType.IMAGE));
                setTitle("Create new project");
                nameLabel.setText("Enter project name:");
            }

            case MIND_MAP -> {
                setIconImage(ResourceLoader.load("mind_map.png", ResourceType.IMAGE));
                setTitle("Create new mind map");
                nameLabel.setText("Enter mind map name:");
            }
        }

        JButton createButton = new JButton("Create");
        createButton.setBorder(null);
        createButton.setBackground(new Color(50, 160, 50));
        createButton.setForeground(Color.white);
        createButton.setSize(80, 30);
        createButton.setBounds(135, 75, createButton.getWidth(), createButton.getHeight());
        add(createButton);
        createButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBorder(new LineBorder(new Color(200, 200, 200), 1));
        cancelButton.setBackground(new Color(80, 80, 80));
        cancelButton.setForeground(Color.white);
        cancelButton.setSize(80, 30);
        cancelButton.setBounds(235, 75, cancelButton.getWidth(), cancelButton.getHeight());
        add(cancelButton);
        cancelButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nameField.setText("");
                dispose();
            }
        });
    }

    public Object start(Object object) {
        if (object instanceof String name) {
            nameField.setText(name);
            nameField.selectAll();
        }
        setVisible(true);
        return nameField.getText().equals("") ? null : nameField.getText();
    }
}
