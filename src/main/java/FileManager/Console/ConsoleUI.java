package FileManager.Console;

import java.io.IOException;

public class ConsoleUI<E extends Enum<E>> extends Reader implements Runnable {


    private static Boolean exit = false;

    private CommandManager cm;

    public ConsoleUI(Class<E> cls) throws IOException {
        super(System.in, cls);
    }

    @Override
    public void run() {
        while (!exit) {
            try {
                commandProcessing();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void commandProcessing() throws IOException {
        read();
        cm = new CommandManager();
        cm.onCommand(getEnumReader().getCommand(), getArgs());
        cm = null;
        actionPerformed();
        if (!exit)
            System.out.print(">>> ");
    }

    protected void exit() {
        exit = true;
    }

    @Override
    public void close() throws IOException {
        exit();
        super.close();
    }
}
