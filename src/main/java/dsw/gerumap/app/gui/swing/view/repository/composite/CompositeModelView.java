package dsw.gerumap.app.gui.swing.view.repository.composite;
import dsw.gerumap.app.gui.swing.view.repository.preview.ModelPreview;
import dsw.gerumap.app.repository.composite.ModelNode;

import java.util.ArrayList;
import java.util.List;

public abstract class CompositeModelView extends ModelView {

    protected List<ModelView> views;

    protected ModelPreview selected;

    protected ModelView displayed;

    public CompositeModelView(ModelNode model) {
        super(model);
        views = new ArrayList<>();
    }

    public void addView(ModelView view) {
        if (views.contains(view)) return;
        views.add(view);
        view.setParentView(this);
        view.getPreview().setParentView(this);
        displayView(view);
        updateView();
    }

    public void removeView(ModelView view) {
        views.remove(view);
        view.setParentView(null);
        updateView();
    }

    public ModelView getView(String name) {
        for (ModelView view : views) {
            if (view.getName().equals(name)) {
                return view;
            }
        }
        return null;
    }

    public void displayView(ModelView view) {
        display(); // Display self
        displayed = view;
    }

    public void selectPreview(ModelPreview preview) {
        if (selected != null) {
            selected.select(false);
        }
        selected = preview;
        if (selected != null) selected.select(true);
        updateUI();
    }

    public ModelView getDisplayed() {
        return displayed;
    }
}
