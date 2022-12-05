package dsw.gerumap.app.resources;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class ResourceLoader {

    private static ResourceLoader instance;

    private ResourceLoader() {

    }

    public static void initialise() {
        if (instance == null) {
            instance = new ResourceLoader();
        }
    }

    public static <T> T load(String filename, ResourceType type) {
        switch (type) {
            case ICON -> {
                return (T)instance.loadIcon(filename);
            }

            case IMAGE -> {
                return (T)instance.loadImage(filename);
            }

            case CURSOR -> {
                return (T)instance.loadCursor(filename);
            }
        }

        return null;
    }

    private Icon loadIcon(String path) {
        path = "/icons/" + path;
        URL iconURL = instance.getClass().getResource(path);
        if (iconURL == null) {
            System.out.println("Resource missing!");
            return null;
        }

        return new ImageIcon(iconURL);
    }

    private Image loadImage(String path) {
        path = "/images/" + path;
        URL iconURL = instance.getClass().getResource(path);
        if (iconURL == null) {
            System.out.println("Resource missing!");
            return null;
        }

        return new ImageIcon(iconURL).getImage();
    }

    private Cursor loadCursor(String path) {
        Point offset = new Point(0, 0);
        switch (path) {
            case "SELECTION_CURSOR" -> {
                path = "selection_cursor.png";
            }

            case "MOVE_CURSOR" -> {
                offset = new Point(16, 16);
                path = "move_cursor.png";
            }

            case "ERASER_CURSOR" -> {
                offset = new Point(16, 31);
                path = "eraser_cursor.png";
            }

            case "TERM_CURSOR" -> {
                path = "term_cursor.png";
            }

            case "LINK_CURSOR" -> {
                path = "link_cursor.png";
            }

            case "ZOOM_CURSOR" -> {
                offset = new Point(16, 16);
                path = "zoom_cursor.png";
            }

            default -> {
                return null;
            }
        }
        Image image = loadImage("../cursors/" + path);
        if (image == null) return null;
        return Toolkit.getDefaultToolkit().createCustomCursor(
                image,
                offset,
                "Custom Cursor"
        );
    }
}