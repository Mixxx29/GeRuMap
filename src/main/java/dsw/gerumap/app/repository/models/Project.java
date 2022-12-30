package dsw.gerumap.app.repository.models;

import dsw.gerumap.app.observer.NotificationType;
import dsw.gerumap.app.repository.composite.CompositeModelNode;
import dsw.gerumap.app.repository.composite.ModelNode;

import java.awt.*;

public class Project extends CompositeModelNode {

    public static final String DEFAULT_NAME = "New Project";

    private Color termElementFillColor;
    private Color termElementStrokeColor;
    private int termElementStrokeSize;

    private Color linkElementStrokeColor;
    private int linkElementStrokeSize;
    public Project(String name) {
        super(name);
        termElementFillColor = new Color(118, 226, 248);
        termElementStrokeColor = new Color(30, 78, 211);
        termElementStrokeSize = 3;

        linkElementStrokeColor = new Color(250, 74, 84);
        linkElementStrokeSize = 4;
    }

    @Override
    public void addNode(ModelNode node) {
        if (!(node instanceof MindMap)) return;
        super.addNode(node);
    }

    public void setTermElementFillColor(Color termElementFillColor) {
        this.termElementFillColor = termElementFillColor;
        notifyListeners(NotificationType.TERM_FILL_COLOR, termElementFillColor);
    }

    public void setTermElementStrokeColor(Color termElementStrokeColor) {
        this.termElementStrokeColor = termElementStrokeColor;
        notifyListeners(NotificationType.TERM_STROKE_COLOR, termElementStrokeColor);
    }

    public void setTermElementStrokeSize(int termElementStrokeSize) {
        this.termElementStrokeSize = termElementStrokeSize;
        notifyListeners(NotificationType.TERM_STROKE_SIZE, termElementStrokeSize);
    }

    public void setLinkElementStrokeColor(Color linkElementStrokeColor) {
        this.linkElementStrokeColor = linkElementStrokeColor;
        notifyListeners(NotificationType.LINK_STROKE_COLOR, linkElementStrokeColor);
    }

    public void setLinkElementStrokeSize(int linkElementStrokeSize) {
        this.linkElementStrokeSize = linkElementStrokeSize;
        notifyListeners(NotificationType.LINK_STROKE_SIZE, linkElementStrokeSize);
    }

    public Color getTermElementFillColor() {
        return termElementFillColor;
    }

    public Color getTermElementStrokeColor() {
        return termElementStrokeColor;
    }

    public float getTermElementStrokeSize() {
        return termElementStrokeSize;
    }

    public Color getLinkElementStrokeColor() {
        return linkElementStrokeColor;
    }

    public float getLinkElementStrokeSize() {
        return linkElementStrokeSize;
    }
}