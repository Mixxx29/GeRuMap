package dsw.gerumap.app.gui.swing.view;

import dsw.gerumap.app.core.App;
import dsw.gerumap.app.gui.swing.view.custom.CustomSplitPane;
import dsw.gerumap.app.gui.swing.view.windows.TreeWindow;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MainFrame extends JFrame {

    private static MainFrame instance;

    static {
        UIManager.put("Button.defaultButtonFollowsFocus", Boolean.TRUE);
        UIManager.put("ToolTip.background", new Color(215, 215, 215));
        UIManager.put("MenuBar.background", new Color(80, 80, 80));
        UIManager.put("Menu.foreground", Color.white);
        UIManager.put("Menu.selectionBackground", new Color(120, 120, 120));
        UIManager.put("Menu.selectionForeground", Color.white);
        UIManager.put("Menu.border", new EmptyBorder(3, 1, 3, 1));
        UIManager.put("MenuItem.background", new Color(80, 80, 80));
        UIManager.put("MenuItem.foreground", Color.white);
        UIManager.put("MenuItem.selectionBackground", new Color(120, 120, 120));
        UIManager.put("MenuItem.selectionForeground", Color.white);
        UIManager.put("MenuItem.border", new EmptyBorder(3, 2, 3, 2));
        UIManager.put("Tree.background", new Color(80, 80, 80));
        UIManager.put("Tree.selectionBackground", new Color(120, 120, 120));
        UIManager.put("Tree.selectionBorderColor", new Color(200, 200, 200));
        UIManager.put("Panel.background", new Color(80, 80, 80));
        UIManager.put("SplitPane.border", new EmptyBorder(0, 0, 0, 0));
        UIManager.put("ScrollPane.border", new EmptyBorder(0, 0, 0, 0));
        UIManager.put("PopupMenu.background", new Color(200, 200, 200));
        UIManager.put("PopupMenu.border", new EmptyBorder(1, 1, 1, 1));
        UIManager.put("TextField.background", new Color(80, 80, 80));
        UIManager.put("TextField.foreground", Color.white);
        UIManager.put("TextField.selectionBackground", new Color(180, 180, 180));
        UIManager.put("TextField.selectionForeground", new Color(50, 50, 50));
        UIManager.put("TextField.caretForeground", Color.white);
    }

    private TreeWindow treeWindow;

    private MainFrame() {

    }

    public static void initialise() {
        if (instance == null) {
            instance = new MainFrame(); // Initialise new main frame
            instance.initialiseGUI(); // Initialise main frame GUI
            instance.setVisible(true); // Display main frame
        }
    }

    private void initialiseGUI() {
        // Setup main frame GUI
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension windowSize = toolkit.getScreenSize();
        windowSize.width *= 0.7f;
        windowSize.height *= 0.7f;
        setSize(windowSize); // Set size
        setLocationRelativeTo(null); // Center on screen
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximize window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("GeRuMap"); // Set window title

        // Setup menu bar
        setJMenuBar(new MainMenuBar());

        // Create tree window
        treeWindow = new TreeWindow(App.getRepository().getWorkspace().getName());

        // Setup main split
        CustomSplitPane mainSplit = new CustomSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        mainSplit.setDividerSize(4);
        mainSplit.setLeftComponent(treeWindow);
        mainSplit.setRightComponent(new JPanel());
        mainSplit.setDividerLocation((int)(windowSize.width * 0.25f));
        add(mainSplit, BorderLayout.CENTER);
    }

    public static MainFrame getInstance() {
        return instance;
    }

    public TreeWindow getTreeWindow() {
        return treeWindow;
    }
}