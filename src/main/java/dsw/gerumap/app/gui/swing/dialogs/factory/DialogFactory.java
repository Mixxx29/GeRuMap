package dsw.gerumap.app.gui.swing.dialogs.factory;

import dsw.gerumap.app.gui.swing.dialogs.*;
import dsw.gerumap.app.repository.composite.ModelNode;
import dsw.gerumap.app.repository.factory.ModelType;

public class DialogFactory {

    public static AbstractDialog createDialog(DialogType type, Object object) {
        switch (type) {
            case CREATE -> {
                if (object instanceof ModelType modelType) {
                    return new CreateModelDialog(modelType);
                }
            }

            case DELETE -> {
                if (object instanceof ModelNode modelNode) {
                    return new DeleteModelDialog(modelNode);
                }
            }

            case RENAME -> {
                if (object instanceof ModelNode modelNode) {
                    return new RenameModelDialog(modelNode);
                }
            }

            case DEVELOPERS -> {
                return new DevelopersDialog();
            }

            case ERROR -> {
                return new ErrorDialog();
            }
        }

        System.out.println("Creating dialog failed!");
        return null;
    }
}