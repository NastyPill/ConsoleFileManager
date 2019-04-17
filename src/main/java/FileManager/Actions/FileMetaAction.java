package FileManager.Actions;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;

public class FileMetaAction implements Action {

    public static final String ANSI_BOLD = "\u001B[1m";
    public static final String ANSI_PURPLE = "\u001B[35m";

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


    //isn't safety can return null or throw NullPointerException
    private long folderSize(File dir) {
        long len = 0;
        try {
            for (File file : dir.listFiles()) {
                if (!file.canRead()) {
                } else {
                    if (file.isFile()) {
                        len += file.length();
                    } else {
                        len += folderSize(file);
                    }
                }
            }
            return len;
        } catch (NullPointerException e) {
            System.out.println(ANSI_RED + "Can't calculate size accurately because some files are unreadable" + ANSI_PURPLE);
        }
        return 0;
    }

    @Override
    public void close() throws Exception {

    }
}
