package dsw.gerumap.app.repository.models;

import dsw.gerumap.app.gui.swing.commands.CommandManager;
import dsw.gerumap.app.gui.swing.commands.graphics.*;
import dsw.gerumap.app.observer.NotificationType;
import dsw.gerumap.app.repository.composite.ModelNode;
import dsw.gerumap.app.repository.elements.LinkElement;
import dsw.gerumap.app.repository.elements.MindMapElement;
import dsw.gerumap.app.repository.elements.TermElement;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MindMap extends ModelNode {
    public static final String DEFAULT_NAME = "New Mind Map";

    private CommandManager commandManager;

    private List<MindMapElement> elements;
    private List<MindMapElement> selectedElements;

    public MindMap(String name) {
        super(name);
        commandManager = new CommandManager();
        elements = new ArrayList<>();
        selectedElements = new ArrayList<>();
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }

    public void addElement(MindMapElement element) {
        if (element instanceof LinkElement) elements.add(0, element);
        else elements.add(element);
        notifyListeners(NotificationType.ADD_ELEMENT, element);
    }

    public void removeElement(MindMapElement element) {
        elements.remove(element);
        selectedElements.remove(element);
        notifyListeners(NotificationType.REMOVE_ELEMENT, element);
    }

    public void moveElements(Point2D.Float offset) {
        for (MindMapElement element : selectedElements) {
            if (element instanceof TermElement termElement) {
                termElement.setPosition(
                        new Point2D.Float(
                                termElement.getPosition().x + offset.x,
                                termElement.getPosition().y + offset.y
                        )
                );
            }
        }
        notifyListeners(NotificationType.UPDATE_ELEMENTS, elements);
    }

    public void selectElements(List<MindMapElement> elements) {
        selectedElements.clear();
        selectedElements.addAll(elements);
        notifyListeners(NotificationType.SELECT_ELEMENTS, selectedElements);
    }

    public boolean hasSelected() {
        return selectedElements.size() > 0;
    }

    public void setTermElementsFillColor(List<TermElement> elements, Color termElementFillColor) {
        for (TermElement element : elements) {
            element.setFillColor(termElementFillColor);
        }
        notifyListeners(NotificationType.UPDATE_ELEMENTS, null);
    }

    public void setTermElementStrokeColor(List<TermElement> elements, Color termElementStrokeColor) {
        for (TermElement element : elements) {
            element.setStrokeColor(termElementStrokeColor);
        }
        notifyListeners(NotificationType.UPDATE_ELEMENTS, null);
    }

    public void setTermElementStrokeSize(List<TermElement> elements, float termElementStrokeSize) {
        for (TermElement element : elements) {
            element.setStroke(new BasicStroke(termElementStrokeSize));
        }
        notifyListeners(NotificationType.UPDATE_ELEMENTS, null);
    }

    public void setLinkElementStrokeColor(List<LinkElement> elements, Color linkElementStrokeColor) {
        for (LinkElement element : elements) {
            element.setStrokeColor(linkElementStrokeColor);
        }
        notifyListeners(NotificationType.UPDATE_ELEMENTS, null);
    }

    public void setLinkElementStrokeSize(List<LinkElement> elements, int linkElementStrokeSize) {
        for (LinkElement element : elements) {
            element.setStroke(new BasicStroke(linkElementStrokeSize));
        }
        notifyListeners(NotificationType.UPDATE_ELEMENTS, null);
    }

    public void addCreateTermCommand(TermElement element) {
        // Create and add new 'create term command'
        commandManager.addCommand(new CreateTermCommand(this, element));
    }

    public void addLinkCommand(LinkElement element) {
        // Create and add new 'create term command'
        commandManager.addCommand(new LinkCommand(this, element));
    }

    public void addEraseElementCommand(MindMapElement element) {
        List<MindMapElement> toRemove = new ArrayList<>();
        toRemove.add(element);

        if (element instanceof TermElement termElement) {
            for (MindMapElement mindMapElement : new ArrayList<>(elements)) {
                if (mindMapElement instanceof LinkElement linkElement) {
                    if (linkElement.getTermElement1() == termElement || linkElement.getTermElement2() == termElement) {
                        toRemove.add(linkElement);
                    }
                }
            }
        }

        // Create and add new 'erase element command'
        commandManager.addCommand(new EraseElementCommand(this, toRemove));
    }

    public void addMoveElementCommand(Point2D.Float offset) {
        // Create and add new 'create term command'
        commandManager.addCommand(new MoveElementCommand(this, offset, new ArrayList<>(selectedElements)));
    }

    public void addSelectionCommand(List<MindMapElement> elements) {
        // Check if previous command is same
        if (selectedElements.size() == elements.size()) {
            boolean same = true;
            for (MindMapElement element : elements) {
                if (!selectedElements.contains(element)) {
                    same = false;
                    break;
                }
            }
            if (same) return;
        }

        // Create and add new 'selection command'
        commandManager.addCommand(
                new SelectCommand(this, new ArrayList<>(elements), new ArrayList<>(selectedElements))
        );
    }

    public void addTermElementFillColorCommand(Color newColor, Color oldColor) {
        Map<TermElement, Color> termElements = new HashMap<>();
        selectedElements.stream()
                .filter(element -> element instanceof TermElement)
                .forEach(element -> termElements.put((TermElement) element, ((TermElement) element).getFillColor()));
        commandManager.addCommand(
                new TermElementFillColorCommand(
                        (Project) getParent(),
                        this,
                        termElements,
                        newColor,
                        oldColor
                )
        );
    }

    public void addTermElementStrokeColorCommand(Color newColor, Color oldColor) {
        Map<TermElement, Color> termElements = new HashMap<>();
        selectedElements.stream()
                .filter(element -> element instanceof TermElement)
                .forEach(element -> termElements.put((TermElement) element, element.getStrokeColor()));
        commandManager.addCommand(
                new TermElementStrokeColorCommand(
                        (Project) getParent(),
                        this,
                        termElements,
                        newColor,
                        oldColor
                )
        );
    }

    public void addTermElementStrokeSizeCommand(int newSize, int oldSize) {
        Map<TermElement, Integer> termElements = new HashMap<>();
        selectedElements.stream()
                .filter(element -> element instanceof TermElement)
                .forEach(element -> {
                    termElements.put((TermElement) element, (int)((BasicStroke)element.getStroke()).getLineWidth());
                });
        commandManager.addCommand(
                new TermELementStrokeSizeCommand(
                        (Project) getParent(),
                        this,
                        termElements,
                        newSize,
                        oldSize
                )
        );
    }

    public void addLinkElementStrokeColorCommand(Color newColor, Color oldColor) {
        Map<LinkElement, Color> linkElements = new HashMap<>();
        selectedElements.stream()
                .filter(element -> element instanceof LinkElement)
                .forEach(element -> linkElements.put((LinkElement) element, element.getStrokeColor()));
        commandManager.addCommand(
                new LinkElementStrokeColorCommand(
                        (Project) getParent(),
                        this,
                        linkElements,
                        newColor,
                        oldColor
                )
        );
    }

    public void addLinkElementStrokeSizeCommand(int newSize, int oldSize) {
        Map<LinkElement, Integer> linkElements = new HashMap<>();
        selectedElements.stream()
                .filter(element -> element instanceof LinkElement)
                .forEach(element -> {
                    linkElements.put((LinkElement) element, (int)((BasicStroke)element.getStroke()).getLineWidth());
                });
        commandManager.addCommand(
                new LinkElementStrokeSizeCommand(
                        (Project) getParent(),
                        this,
                        linkElements,
                        newSize,
                        oldSize
                )
        );
    }
}