package FileManager.Console;

import FileManager.Commands;

import java.io.IOException;
import java.io.InputStream;

public class CommandManager extends ConsoleUI<Commands> {

    public CommandManager(InputStream stream, Class cls) throws IOException {
        super(Commands.class);
    }

    @Override
    protected void onCommand(Commands command) throws IOException {
        switch (command) {
            case COPY: throw new UnsupportedOperationException();
            //TODO() Switch with commands
        }
    }
}
