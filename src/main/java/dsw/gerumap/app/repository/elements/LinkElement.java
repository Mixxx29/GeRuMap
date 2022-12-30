package dsw.gerumap.app.repository.elements;

import dsw.gerumap.app.observer.NotificationType;

import java.awt.*;

public class LinkElement extends MindMapElement {

    private TermElement termElement1;
    private TermElement termElement2;

    public LinkElement(TermElement termElement1, TermElement termElement2) {
        super(new BasicStroke(4.0f), new Color(250, 74, 84));
        this.termElement1 = termElement1;
        this.termElement2 = termElement2;
    }

    public LinkElement(TermElement termElement1, TermElement termElement2, Stroke stroke, Color color) {
        super(stroke, color);
        this.termElement1 = termElement1;
        this.termElement2 = termElement2;
    }

    public TermElement getTermElement1() {
        return termElement1;
    }

    public void setTermElement1(TermElement termElement1) {
        this.termElement1 = termElement1;
    }

    public TermElement getTermElement2() {
        return termElement2;
    }

    public void setTermElement2(TermElement termElement2) {
        this.termElement2 = termElement2;
    }

    @Override
    public void update(NotificationType notificationType, Object object) {

    }
}