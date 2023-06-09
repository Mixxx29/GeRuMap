package dsw.gerumap.app.gui.swing.view.custom;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicSplitPaneDivider;
import javax.swing.plaf.basic.BasicSplitPaneUI;
import java.awt.*;

public class CustomSplitPane extends JSplitPane {

    private double maxLocation;

    public CustomSplitPane(int orientation, double maxLocation) {
        super(orientation);
        this.maxLocation = maxLocation;

        setUI(new BasicSplitPaneUI() {
            @Override
            public BasicSplitPaneDivider createDefaultDivider() {
                return new BasicSplitPaneDivider(this) {
                    @Override
                    public void paint(Graphics g) {
                        g.setColor(new Color(150, 150, 150));
                        g.fillRect(0, 0, getWidth(), getHeight());
                    }

                    @Override
                    public void setBorder(Border border) {

                    }
                };
            }

            @Override
            protected void dragDividerTo(int location) {
                if (location > maxLocation * getParent().getWidth()) {
                    location = (int)(maxLocation * getParent().getWidth());
                }
                super.dragDividerTo(location);
            }
        });
    }

    @Override
    public void setDividerLocation(int location) {
        if (getParent() != null && location > maxLocation * getParent().getWidth()) {
            location = (int)(maxLocation * getParent().getWidth());
        }
        super.setDividerLocation(location);
    }
}
