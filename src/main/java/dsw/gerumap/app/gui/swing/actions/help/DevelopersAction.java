package dsw.gerumap.app.gui.swing.actions.help;

import dsw.gerumap.app.gui.swing.actions.AbstractCustomAction;
import dsw.gerumap.app.gui.swing.dialogs.AbstractDialog;
import dsw.gerumap.app.gui.swing.dialogs.factory.DialogFactory;
import dsw.gerumap.app.gui.swing.dialogs.factory.DialogType;
import dsw.gerumap.app.observer.IPublisher;
import dsw.gerumap.app.observer.NotificationType;
import dsw.gerumap.app.repository.factory.ModelType;
import dsw.gerumap.app.resources.ResourceLoader;
import dsw.gerumap.app.resources.ResourceType;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class DevelopersAction extends AbstractCustomAction {

    public DevelopersAction(IPublisher publisher) {
        super(publisher);
        putValue(
                ACCELERATOR_KEY,
                KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK)
        );
        putValue(SMALL_ICON, ResourceLoader.load("developers.png", ResourceType.ICON));
        putValue(NAME, " Developers ");
        putValue(SHORT_DESCRIPTION, " About developers ");
    }

    @Override
    public void action(Object object) {
        AbstractDialog dialog = DialogFactory.createDialog(DialogType.DEVELOPERS, null);
        if (dialog != null) dialog.start(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        action(null);
    }

    @Override
    public void update(NotificationType notificationType, Object object) {

    }
}