package dsw.gerumap.app.gui.swing.resources;

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
}
