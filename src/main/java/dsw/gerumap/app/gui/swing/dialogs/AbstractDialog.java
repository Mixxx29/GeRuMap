package dsw.gerumap.app.gui.swing.dialogs;

import javax.swing.*;
import java.awt.*;

public abstract class AbstractDialog extends JDialog {

    protected JPanel content;

    public AbstractDialog() {
        setModal(true);
        setLayout(null);
        getContentPane().setBackground(new Color(100, 100, 100));
    }

    public abstract Object start(Object object);
}