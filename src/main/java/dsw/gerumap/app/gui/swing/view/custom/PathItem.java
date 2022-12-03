package dsw.gerumap.app.gui.swing.view.custom;

import dsw.gerumap.app.observer.IListener;
import dsw.gerumap.app.observer.NotificationType;
import dsw.gerumap.app.repository.composite.ModelNode;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;

public class PathItem extends JLabel implements IListener {

    private ModelNode model;

    public PathItem(ModelNode model) {
        this.model = model;
        model.addListener(this);
        setText(model.getName());
        setFont(getFont().deriveFont(Font.ITALIC | Font.BOLD, 16.0f));
        setBorder(new EmptyBorder(5, 5, 5, 5));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                model.notifyListeners(NotificationType.SELECT, null);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                Map<TextAttribute, Object> attributes = new HashMap<>(getFont().getAttributes());
                attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
                setFont(getFont().deriveFont(attributes));
                setCursor(new Cursor(Cursor.HAND_CURSOR));
                updateUI();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                Map<TextAttribute, Object> attributes = new HashMap<>(getFont().getAttributes());
                attributes.put(TextAttribute.UNDERLINE, -1);
                setFont(getFont().deriveFont(attributes));
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                updateUI();
            }
        });
    }

    public PathItem(String text) {
        setText(text);
        setFont(getFont().deriveFont(Font.ITALIC | Font.BOLD, 16.0f));
        setBorder(new EmptyBorder(5, 5, 5, 5));
    }

    @Override
    public void update(NotificationType notificationType, Object object) {
        if (notificationType == NotificationType.RENAME) {
            setText(model.getName());
            updateUI();
        }
    }
}
