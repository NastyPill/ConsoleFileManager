package fileManager.actions;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;

public class FileFindAction implements Action {

    private ArrayList<File> listOfFiles;

    private String name;

    private String currentDir;

    public FileFindAction(String[] args, String currentDir) {
        if (args.length != 2) {
            throw new IllegalArgumentException();
        }
        this.name = args[1];
        this.currentDir = currentDir;
        this.listOfFiles = new ArrayList<>();
    }

    @Override
    public void run() {
        try {
            Files.walkFileTree(Paths.get(currentDir), new SimpleFileVisitor<Path>() {

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
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
                    File[] arrOfFiles = dir.toFile().listFiles();
                    for (File file : arrOfFiles) {
                        if (file.getName().equals(name)) {
                            listOfFiles.add(file);
                        }
                    }
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (listOfFiles.isEmpty()) {
            System.out.println(ANSI_RED + "No such file with name: " + name + ANSI_RESET);
        }
        for (File file : listOfFiles) {
            System.out.println(ANSI_PURPLE + file.getAbsolutePath() + ANSI_RESET);
        }
    }

    @Override
    public void close() throws Exception {

    }
}
