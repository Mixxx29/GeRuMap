package dsw.gerumap.app.gui.swing.view.repository;

import dsw.gerumap.app.gui.swing.view.ProjectToolbar;
import dsw.gerumap.app.gui.swing.view.repository.composite.ModelView;
import dsw.gerumap.app.observer.NotificationType;
import dsw.gerumap.app.repository.models.MindMap;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MindMapView extends ModelView {
    public MindMapView(MindMap mindMap) {
        super(mindMap);
        setLayout(new BorderLayout());

        JPanel content = new JPanel();
        content.setBackground(new Color(220, 220, 220));
        add(content, BorderLayout.CENTER);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                /*Color newColor = JColorChooser.showDialog(
                        MindMapView.this,
                        "Choose Background Color",
                        getBackground()
                );*/
            }
        });
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
                ((ProjectView)getParentView()).updateTitle(model.getName());
            }
        }
        super.update(notificationType, object);
    }
}
