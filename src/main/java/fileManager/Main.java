package fileManager;

import fileManager.console.ConsoleUI;

import java.io.IOException;
import java.io.InputStream;

public class Main extends ConsoleUI {

    public Main(InputStream stream) throws IOException {
        super(Commands.class, stream);
    }

    public static void main(String[] args) throws IOException {
        new Main(System.in).run();
    }
}