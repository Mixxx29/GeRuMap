package dsw.gerumap.app.gui.swing.view.repository.composite;
import dsw.gerumap.app.repository.composite.ModelNode;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class CompositeModelView extends ModelView {

    protected List<ModelView> views;

    public CompositeModelView(ModelNode model) {
        super(model);
        views = new ArrayList<>();
    }

    public void addView(ModelView view) {
        if (views.contains(view)) return;
        views.add(view);
        view.setParentView(this);
        updateView();
    }

    public void removeView(ModelView view) {
        views.remove(view);
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

    public void display(ModelView view) {
        display();
    }
}
