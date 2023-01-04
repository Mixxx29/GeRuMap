package dsw.gerumap.app.repository.elements;

import dsw.gerumap.app.observer.NotificationType;
import dsw.gerumap.app.repository.models.MindMap;

import java.awt.*;

public class LinkElement extends MindMapElement {

    private int termElement1Index;
    private int termElement2Index;
    transient private TermElement termElement1;
    transient private TermElement termElement2;

    transient private MindMap parent;

    public LinkElement(TermElement termElement1, TermElement termElement2) {
        super(4, new Color(250, 74, 84));
        this.termElement1 = termElement1;
        this.termElement2 = termElement2;
    }

    public LinkElement(TermElement termElement1, TermElement termElement2, int stroke, Color color) {
        super(stroke, color);
        this.termElement1 = termElement1;
        this.termElement2 = termElement2;
    }

    public int getTermElement1Index() {
        return termElement1Index;
    }

    public void setTermElement1Index(int termElement1Index) {
        this.termElement1Index = termElement1Index;
    }

    public int getTermElement2Index() {
        return termElement2Index;
    }

    public void setTermElement2Index(int termElement2Index) {
        this.termElement2Index = termElement2Index;
    }

    public TermElement getTermElement1() {
        return termElement1;
    }

    public void setTermElement1(TermElement termElement1) {
        this.termElement1 = termElement1;
        termElement1Index = parent.indexOfElement(termElement1);
    }

    public TermElement getTermElement2() {
        return termElement2;
    }

    public void setTermElement2(TermElement termElement2) {
        this.termElement2 = termElement2;
        termElement2Index = parent.indexOfElement(termElement2);
    }

    public MindMap getParent() {
        return parent;
    }

    @Override
    public void setParent(MindMap parent) {
        this.parent = parent;
    }

    @Override
    public void update(NotificationType notificationType, Object object) {

    }
}