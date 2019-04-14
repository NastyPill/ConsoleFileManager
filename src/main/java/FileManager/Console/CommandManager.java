package FileManager.Console;

import FileManager.Commands;

public class CommandManager<E extends Enum<E>> {

    public CommandManager() {

    }

    protected void onCommand(E command, String[] s) {
        switch ((Commands) command) {
            case EXIT: ConsoleUI.exit = true;
            break;
        }
    }

    public void exit() {
        exit = true;
    }

    public Boolean getExit() {
        return exit;
    }
}
