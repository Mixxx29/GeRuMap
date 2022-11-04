package dsw.gerumap.app.gui.swing.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.KeyEvent;

public class MainMenuBar extends JMenuBar {

    public MainMenuBar() {
        setBorder(new EmptyBorder(2, 2, 3, 2));

        // Crate 'File' menu
        JMenu fileMenu = new JMenu(" File ");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        fileMenu.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add(fileMenu);

        // Crate 'Edit' menu
        JMenu editMenu = new JMenu(" Edit ");
        editMenu.setMnemonic(KeyEvent.VK_E);
        editMenu.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add(editMenu);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(new Color(150, 150, 150));
        g.drawLine(0, getHeight() - 1, getWidth() - 1, getHeight() - 1);
    }
}
