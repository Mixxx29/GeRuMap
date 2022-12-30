package dsw.gerumap.app.gui.swing.commands;

import dsw.gerumap.app.gui.swing.view.MainFrame;

import java.util.ArrayList;
import java.util.List;

public class CommandManager {

    private List<AbstractCommand> commands;
    private int currentCommand;

    public CommandManager() {
        commands = new ArrayList<>();
    }

    public void addCommand(AbstractCommand command) {
        while (currentCommand < commands.size()) {
            commands.remove(currentCommand);
        }
        commands.add(command);
        doCommand();
    }

    public void doCommand() {
        if (currentCommand < commands.size()) {
            commands.get(currentCommand++).doCommand();
            MainFrame.getInstance().getActionManager().getUndoAction().setEnabled(true);
        }

        if (currentCommand == commands.size()) {
            MainFrame.getInstance().getActionManager().getRedoAction().setEnabled(false);
        }
    }

    public void undoCommand() {
        if (currentCommand > 0) {
            commands.get(--currentCommand).undoCommand();
            MainFrame.getInstance().getActionManager().getRedoAction().setEnabled(true);
        }

        if (currentCommand == 0) {
            MainFrame.getInstance().getActionManager().getUndoAction().setEnabled(false);
        }
    }

    public boolean canDoCommand() {
        return currentCommand < commands.size();
    }

    public boolean canUndoCommand() {
        return currentCommand > 0;
    }
}
