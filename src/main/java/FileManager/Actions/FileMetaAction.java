package FileManager.Actions;

import FileManager.Utils.Validator;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.concurrent.atomic.AtomicLong;

public class FileMetaAction implements Action {

    private String path;

    private Validator validator;

    public FileMetaAction(String[] args, String currentDir) {
        validator = new Validator();
        if (args.length < 2) {
            this.path = currentDir;
        } else {
            this.path = validator.isPath(args[1], currentDir);
        }
        if (args.length > 2) {
            throw new IllegalArgumentException();
        }
    }


    @Override
    public void run() {
        BasicFileAttributes attr = null;
        try {
            attr = Files.readAttributes(Paths.get(path), BasicFileAttributes.class);
        } catch (IOException e) {
            System.out.println(ANSI_RED + "No such file" + ANSI_RESET);
        }

        System.out.println(ANSI_BOLD + "Name: " + new File(path).getName() + ANSI_RESET);
        System.out.println(ANSI_PURPLE + "creationTime: " + attr.creationTime());
        System.out.println("lastAccessTime: " + attr.lastAccessTime());
        System.out.println("lastModifiedTime: " + attr.lastModifiedTime());

        System.out.println("isDirectory: " + attr.isDirectory());
        System.out.println("isOther: " + attr.isOther());
        System.out.println("isRegularFile: " + attr.isRegularFile());
        System.out.println("isSymbolicLink: " + attr.isSymbolicLink());
        System.out.println("size: " + folderSize(new File(path)) + " bytes" + ANSI_RESET);
    }

    private long folderSize(File dir) {
        final AtomicLong size = new AtomicLong(0);

        try {
            Files.walkFileTree(dir.toPath(), new SimpleFileVisitor<Path>(){

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    size.addAndGet(attrs.size());
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                    System.out.println(ANSI_RED + "Skipped file: " + file + " (" + exc +")" + ANSI_RESET);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    if (exc != null) {
                        System.out.println(ANSI_RED + "Have some trouble" + dir + " (" + exc +")" + ANSI_RESET);
                    }
                    return FileVisitResult.CONTINUE;
                }
            });

        } catch (IOException e) {
            //nothing
        }
        return size.get();
    }

    @Override
    public void close() throws Exception {

    }
}
