package FileManager.Actions;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;

public class TreeAction implements Action {

    private String currentDir;

    private String[] path;

    private String[] newPath;

    public TreeAction(String currentDir) {
        this.currentDir = currentDir;
        path = new String[0];
        newPath = new String[0];
    }

    @Override
    public void run() {
        try {
            Files.walkFileTree(Paths.get(currentDir), new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    if (path.length == 0) {
                        newPath = file.toString().split("/");
                        for (int i = 0; i < newPath.length; i++) {
                            for (int j = 0; j < i; j++) {
                                System.out.print("|    ");
                            }
                            System.out.print("|--> ");
                            if(i < newPath.length - 1) {
                                System.out.println(ANSI_YELLOW + ANSI_BOLD + ANSI_UNDERLINED + newPath[i] + ANSI_RESET);
                            } else {
                                System.out.println(ANSI_BLUE + newPath[i] + ANSI_RESET);
                            }
                        }
                    } else {
                        newPath = file.toString().split("/");
                        for (int i = 0; i < newPath.length - 1; i++) {
                            if (i >= path.length || !path[i].equals(newPath[i])) {
                                for (int j = 0; j < i - 1; j++) {
                                    System.out.print("|    ");
                                }
                                System.out.print("|--> " + ANSI_YELLOW + ANSI_UNDERLINED + ANSI_BOLD);
                                System.out.println(newPath[i] + ANSI_RESET);
                            }
                        }
                    }
                    path = newPath;
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                    System.out.println(ANSI_RED + "Skipped file: " + file + " (" + exc + ")" + ANSI_RESET);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    if (exc != null) {
                        System.out.println(ANSI_RED + "Have some trouble" + dir + " (" + exc + ")" + ANSI_RESET);
                        return FileVisitResult.CONTINUE;
                    }
                    Arrays.stream(dir.toFile().listFiles()).filter(i -> !i.isDirectory()).sorted().forEach(TreeAction::print);
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void print(File file) {
        int i = file.toString().split("/").length;
        for (int j = 0; j < i - 2; j++) {
            System.out.print("|    ");
        }
        System.out.print("|--> ");
        System.out.println(ANSI_BLUE + file.getName() + ANSI_RESET);
    }

    @Override
    public void close() throws Exception {

    }
}
