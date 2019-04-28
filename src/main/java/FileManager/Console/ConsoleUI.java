package FileManager.Console;

import FileManager.Actions.*;
import FileManager.Commands;

import java.io.IOException;

public class ConsoleUI<E extends Enum<E>> extends Reader implements Runnable {

    //Colors for text
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_WHITE = "\u001B[37m";

    private Boolean exit = false;

    private String currentDir;

    public ConsoleUI(Class<E> cls) throws IOException {
        super(System.in, cls);
        currentDir = System.getProperty("user.dir");
    }

    @Override
    public void run() {
        System.out.println(ANSI_GREEN + currentDir + ANSI_RESET);
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
        try {
            onCommand((E) getEnumReader().getCommand(), getArgs());
        } catch (IllegalArgumentException e) {
            System.out.println(ANSI_RED + "No such command, type 'help'" + ANSI_RESET);
        }
        actionPerformed();
        System.out.println(ANSI_GREEN + currentDir + ANSI_RESET);
    }

    protected void onCommand(E command, String[] args) {
        if (!(command instanceof Commands)) {
            throw new IllegalArgumentException();
        }
        Commands commands = (Commands) command;
        switch (commands) {
            case EXIT:
                exit();
                break;

            case COPY:
                new FileCopyAction(args, currentDir).start();
                break;

            case HELP:
                new HelpAction().start();
                break;

            case DELETE:
                new FileDeleteAction(args, currentDir).start();
                break;

            case CD:
                currentDir = new ChangePathAction().start(currentDir, args);
                break;

            case SHOWALL:
                new ShowAllFilesAction(currentDir).start();
                break;

            case META:
                new FileMetaAction(args, currentDir).start();
                break;

            case MOVE:
                new FileMoveAction(args, currentDir).start();
                break;

            case RENAME:
                new FileRenameAction(args, currentDir).start();
                break;

            case FIND:
                new FileFindAction(args, currentDir).start();
                break;

            case TREE:
                new TreeAction(currentDir).start();
                break;
        }
    }

    protected void exit() {
        exit = true;
    }

    @Override
    public void close() throws IOException {
        super.close();
    }
}
