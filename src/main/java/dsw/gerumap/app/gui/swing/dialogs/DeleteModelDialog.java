package dsw.gerumap.app.gui.swing.dialogs;

import dsw.gerumap.app.repository.composite.ModelNode;
import dsw.gerumap.app.repository.factory.ModelType;
import dsw.gerumap.app.repository.models.Folder;
import dsw.gerumap.app.repository.models.MindMap;
import dsw.gerumap.app.repository.models.Project;
import dsw.gerumap.app.resources.ResourceLoader;
import dsw.gerumap.app.resources.ResourceType;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class DeleteModelDialog extends AbstractDialog {
    private boolean delete;

    public DeleteModelDialog(ModelNode modelNode) {
        super();

        setSize(360, 170);
        setLocationRelativeTo(null);
        setResizable(false);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        JLabel label = new JLabel("Are you sure you want to delete: ", SwingConstants.CENTER);
        label.setSize(285, label.getFontMetrics(label.getFont()).getHeight());
        label.setBounds(30, 15, label.getWidth(), label.getHeight());
        add(label);

        label = new JLabel(modelNode.getName(), SwingConstants.CENTER);
        label.setFont(label.getFont().deriveFont(Font.ITALIC | Font.BOLD));
        label.setSize(285, label.getFontMetrics(label.getFont()).getHeight());
        label.setBounds(30, 40, label.getWidth(), label.getHeight());
        add(label);

        if (modelNode instanceof Folder) {
            setIconImage(ResourceLoader.load("folder.png", ResourceType.IMAGE));
            setTitle("Delete folder");
        } else if (modelNode instanceof Project) {
            setIconImage(ResourceLoader.load("project.png", ResourceType.IMAGE));
            setTitle("Delete project");
        } else if (modelNode instanceof MindMap) {
            setIconImage(ResourceLoader.load("mind_map.png", ResourceType.IMAGE));
            setTitle("Delete mind map");
        }

        JButton createButton = new JButton("Delete");
        createButton.setBorder(null);
        createButton.setBackground(new Color(235, 80, 80));
        createButton.setForeground(Color.white);
        createButton.setSize(80, 30);
        createButton.setBounds(90, 75, createButton.getWidth(), createButton.getHeight());
        add(createButton);
        createButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                delete = true;
                dispose();
            }
        });

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBorder(new LineBorder(new Color(200, 200, 200), 1));
        cancelButton.setBackground(new Color(80, 80, 80));
        cancelButton.setForeground(Color.white);
        cancelButton.setSize(80, 30);
        cancelButton.setBounds(190, 75, cancelButton.getWidth(), cancelButton.getHeight());
        add(cancelButton);
        cancelButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    public Object start(Object object) {
        setVisible(true);
        return delete;
    }
}