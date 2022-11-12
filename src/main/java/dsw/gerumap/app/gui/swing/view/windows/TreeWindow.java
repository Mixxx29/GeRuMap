package dsw.gerumap.app.gui.swing.view.windows;

import dsw.gerumap.app.core.App;
import dsw.gerumap.app.gui.swing.tree.ITree;
import dsw.gerumap.app.gui.swing.tree.WorkspaceTree;

import javax.swing.border.EmptyBorder;

public class TreeWindow extends AbstractWindow {

    private ITree tree;

    public TreeWindow() {
        super("");
        tree = new WorkspaceTree();
        if (App.getRepository() != null && App.getRepository().getWorkspace() != null) {
            setTitle(App.getRepository().getWorkspace().getName());
            content.add(tree.generateView(App.getRepository().getWorkspace()));
        }
        content.setBorder(new EmptyBorder(10, 10, 10, 10));
        add(content);
    }
}
