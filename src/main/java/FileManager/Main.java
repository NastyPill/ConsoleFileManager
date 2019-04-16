package FileManager;

import FileManager.Console.ConsoleUI;

import java.io.IOException;

public class Main extends ConsoleUI {

    public Main() throws IOException {
        super(Commands.class);
    }

    public static void main(String[] args) throws IOException {
        new Main().run();
    }
}