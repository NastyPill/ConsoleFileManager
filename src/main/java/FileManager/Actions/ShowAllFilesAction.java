package FileManager.Actions;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import static FileManager.Console.ConsoleUI.*;

public class ShowAllFilesAction implements Action {

    File dir;

    public ShowAllFilesAction(String currentDir) {
        this.dir = new File(currentDir);
    }

    private static void print(File file) {
        if (file.isDirectory()) {
            System.out.println(ANSI_YELLOW + file.getName() + ANSI_RESET);
        } else {
            System.out.println(ANSI_BLUE + file.getName() + ANSI_RESET);
        }
    }

    @Override
    public void run() {
        File[] list = dir.listFiles();
        Arrays.stream(list).filter(i -> i.isDirectory()).sorted().forEach(ShowAllFilesAction::print);
        Arrays.stream(list).filter(i -> !i.isDirectory()).sorted().forEach(ShowAllFilesAction::print);
    }

    @Override
    public void close() throws Exception {

    }
}
