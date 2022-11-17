package dsw.gerumap.app.gui.swing.dialogs;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public abstract class AbstractDialog extends JDialog {

    protected JPanel content;

    public AbstractDialog() {
        setModal(true);
        setLayout(null);
        getContentPane().setBackground(new Color(100, 100, 100));
        ((JPanel)getContentPane()).setBorder(new LineBorder(new Color(200, 200, 200), 1));
    }

    public abstract Object start(Object object);
}