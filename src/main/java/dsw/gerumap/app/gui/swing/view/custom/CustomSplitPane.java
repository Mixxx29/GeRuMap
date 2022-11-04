package dsw.gerumap.app.gui.swing.view.custom;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicSplitPaneDivider;
import javax.swing.plaf.basic.BasicSplitPaneUI;
import java.awt.*;

public class CustomSplitPane extends JSplitPane {

    public CustomSplitPane(int orientation) {
        super(orientation);

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
        });
    }
}
