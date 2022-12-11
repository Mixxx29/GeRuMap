package dsw.gerumap.app.state;

import dsw.gerumap.app.state.states.*;

public class StateManager {

    private AbstractState current;

    private SelectionToolState selectionToolState;
    private MoveToolState moveToolState;
    private ZoomToolState zoomToolState;
    private EraserToolState eraserToolState;

    private TermToolState termToolState;
    private LinkToolState linkToolState;

    public StateManager() {
        selectionToolState = new SelectionToolState();
        moveToolState = new MoveToolState();
        zoomToolState = new ZoomToolState();
        eraserToolState = new EraserToolState();

        termToolState = new TermToolState();
        linkToolState = new LinkToolState();

        setSelectionToolState();
    }

    public AbstractState getCurrent() {
        return current;
    }

    public void setSelectionToolState() {
        current = selectionToolState;
    }

    public void setMoveToolState() {
        current = moveToolState;
    }

    public void setZoomToolState() {
        current = zoomToolState;
    }

    public void setEraserToolState() {
        current = eraserToolState;
    }

    public void setTermToolState() {
        current = termToolState;
    }

    public void setLinkToolState() {
        current = linkToolState;
    }
}
