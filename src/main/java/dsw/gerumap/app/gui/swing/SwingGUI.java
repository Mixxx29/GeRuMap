package dsw.gerumap.app.gui.swing;

import dsw.gerumap.app.core.GUI;
import dsw.gerumap.app.gui.swing.view.MainFrame;

public class SwingGUI implements GUI {

    private MainFrame mainFrame;

    public SwingGUI() {
        mainFrame = MainFrame.initialize();
    }

    @Override
    public void run() {
        mainFrame.initialiseGUI();
        mainFrame.setVisible(true);
    }
}