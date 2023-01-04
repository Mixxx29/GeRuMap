package dsw.gerumap.app.repository.models;

import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.observer.NotificationType;
import dsw.gerumap.app.repository.composite.CompositeModelNode;
import dsw.gerumap.app.repository.composite.ModelNode;

import java.awt.*;

public class Project extends CompositeModelNode {

    public static final String DEFAULT_NAME = "New Project";

    transient private boolean changed = true;

    transient private String oldName;
    private String fileDirectory;

    private int termElementFillColorHex;
    private int termElementStrokeColorHex;
    private int termElementStrokeSize;

    private int linkElementStrokeColorHex;
    private int linkElementStrokeSize;

    public Project(String name) {
        super(name);
        setTermElementFillColor(new Color(118, 226, 248));
        setTermElementStrokeColor(new Color(30, 78, 211));
        termElementStrokeSize = 3;

        setLinkElementStrokeColor(new Color(250, 74, 84));
        linkElementStrokeSize = 4;
    }

    public void load() {
        for (ModelNode node : nodes) {
            if (node instanceof MindMap mindMap) {
                node.setParent(this);
                notifyListeners(NotificationType.CREATE, node);
                notifyListeners(NotificationType.TERM_FILL_COLOR, new Color(termElementFillColorHex));
                notifyListeners(NotificationType.TERM_STROKE_COLOR, new Color(termElementStrokeColorHex));
                notifyListeners(NotificationType.TERM_STROKE_SIZE, termElementStrokeSize);
                notifyListeners(NotificationType.LINK_STROKE_COLOR, new Color(linkElementStrokeColorHex));
                notifyListeners(NotificationType.LINK_STROKE_SIZE, linkElementStrokeSize);
                mindMap.loadElements();
            }
        }
        changed = false;
    }

    public boolean hasChanged() {
        return changed;
    }

    public void setChanged(boolean changed) {
        this.changed = changed;
        MainFrame.getInstance().getActionManager().getSaveAction().setEnabled(changed);
    }

    public void save() {
        for (ModelNode node : nodes) {
            if (node instanceof MindMap mindMap) {
                mindMap.saveElements();
            }
        }
        changed = false;
    }

    public String getOldName() {
        return oldName;
    }

    public void setOldName(String oldName) {
        this.oldName = oldName;
    }

    public String getFileDirectory() {
        return fileDirectory;
    }

    public void setFileDirectory(String fileDirectory) {
        this.fileDirectory = fileDirectory;
    }

    @Override
    public void addNode(ModelNode node) {
        if (!(node instanceof MindMap)) return;
        super.addNode(node);
    }

    public void setTermElementFillColor(Color termElementFillColor) {
        this.termElementFillColorHex = termElementFillColor.getRGB();
        notifyListeners(NotificationType.TERM_FILL_COLOR, new Color(termElementFillColorHex));
    }

    public void setTermElementStrokeColor(Color termElementStrokeColor) {
        this.termElementStrokeColorHex = termElementStrokeColor.getRGB();
        notifyListeners(NotificationType.TERM_STROKE_COLOR, new Color(termElementStrokeColorHex));
    }

    public void setTermElementStrokeSize(int termElementStrokeSize) {
        this.termElementStrokeSize = termElementStrokeSize;
        notifyListeners(NotificationType.TERM_STROKE_SIZE, termElementStrokeSize);
    }

    public void setLinkElementStrokeColor(Color linkElementStrokeColor) {
        this.linkElementStrokeColorHex = linkElementStrokeColor.getRGB();
        notifyListeners(NotificationType.LINK_STROKE_COLOR, new Color(linkElementStrokeColorHex));
    }

    public void setLinkElementStrokeSize(int linkElementStrokeSize) {
        this.linkElementStrokeSize = linkElementStrokeSize;
        notifyListeners(NotificationType.LINK_STROKE_SIZE, linkElementStrokeSize);
    }

    public Color getTermElementFillColor() {
        return new Color(termElementFillColorHex);
    }

    public Color getTermElementStrokeColor() {
        return new Color(termElementStrokeColorHex);
    }

    public float getTermElementStrokeSize() {
        return termElementStrokeSize;
    }

    public Color getLinkElementStrokeColor() {
        return new Color(linkElementStrokeColorHex);
    }

    public float getLinkElementStrokeSize() {
        return linkElementStrokeSize;
    }
}