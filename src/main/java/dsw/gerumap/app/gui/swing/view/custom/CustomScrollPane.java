package dsw.gerumap.app.gui.swing.view.custom;

import javax.swing.*;
import javax.swing.plaf.ScrollBarUI;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;

public class CustomScrollPane extends JScrollPane {

    public CustomScrollPane() {
        initialize();
    }

    public CustomScrollPane(Component component) {
        super(component);
        initialize();
    }

    private void initialize() {
        ScrollBarUI ui = new BasicScrollBarUI() {
            @Override
            protected JButton createIncreaseButton(int orientation) {
                return new JButton() {
                    @Override
                    public Dimension getPreferredSize() {
                        return new Dimension(0, 0);
                    }
                };
            }

            @Override
            protected JButton createDecreaseButton(int orientation) {
                return new JButton() {
                    @Override
                    public Dimension getPreferredSize() {
                        return new Dimension(0, 0);
                    }
                };
            }

            @Override
            protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
                if (isDragging) g.setColor(new Color(170, 170, 170));
                else g.setColor(new Color(200, 200, 200));
                g.fillRect(
                        thumbBounds.x,
                        thumbBounds.y,
                        thumbBounds.width - 1,
                        thumbBounds.height - 1
                );
            }

            @Override
            protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
                g.setColor(new Color(150, 150, 150));
                g.drawRect(
                        trackBounds.x,
                        trackBounds.y,
                        trackBounds.width,
                        trackBounds.height
                );
                g.setColor(new Color(90, 90, 90));
                g.fillRect(
                        trackBounds.x + 1,
                        trackBounds.y,
                        trackBounds.width,
                        trackBounds.height
                );
            }
        };
        verticalScrollBar.setUI(ui);
        verticalScrollBar.setUnitIncrement(10);
        verticalScrollBar.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
}
