package dsw.gerumap.app.repository.models;

import dsw.gerumap.app.observer.NotificationType;
import dsw.gerumap.app.repository.composite.ModelNode;
import dsw.gerumap.app.repository.elements.LinkElement;
import dsw.gerumap.app.repository.elements.MindMapElement;
import dsw.gerumap.app.repository.elements.TermElement;

import java.util.ArrayList;
import java.util.List;

public class MindMap extends ModelNode {
    public static final String DEFAULT_NAME = "New Mind Map";

    private List<MindMapElement> elements;

    public MindMap(String name) {
        super(name);
        elements = new ArrayList<>();
    }

    public void addElement(MindMapElement element) {
        if (element instanceof LinkElement) elements.add(0, element);
        else elements.add(element);
        notifyListeners(NotificationType.ADD_ELEMENT, element);
    }

    public void removeElement(MindMapElement element) {
        elements.remove(element);
        notifyListeners(NotificationType.REMOVE_ELEMENT, element);

        if (element instanceof TermElement termElement) {
            for (MindMapElement mindMapElement : new ArrayList<>(elements)) {
                if (mindMapElement instanceof LinkElement linkElement) {
                    if (linkElement.getTermElement1() == termElement || linkElement.getTermElement2() == termElement) {
                        elements.remove(linkElement);
                        notifyListeners(NotificationType.REMOVE_ELEMENT, linkElement);
                    }
                }
            }
        }
    }


}