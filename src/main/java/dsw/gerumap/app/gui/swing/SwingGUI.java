package dsw.gerumap.app.gui.swing;

import dsw.gerumap.app.core.GUI;
import dsw.gerumap.app.gui.swing.view.MainFrame;

public class SwingGUI implements GUI {

    @Override
    public void run() {
        MainFrame.initialize();
    }
}