package FileManager.Actions;

import jdk.internal.jline.internal.Ansi;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileDeleteAction implements Action {

    String file;

    public FileDeleteAction(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException();
        } else {
            file = args[1];
        }
    }

    @Override
    public void run() {
        Path p = new File(file).toPath();
        String errMessage = new Validator().validate(p.toString());
        if (errMessage != null) {
            System.out.println(ANSI_RED + errMessage + ANSI_RESET);
            return;
        } else {
            try {
                Files.delete(p);
            } catch (IOException e) {
                if (!new File(file).exists()) {
                    System.out.println(ANSI_RED + "This file doesn't exist" + ANSI_RESET);
                } else {
                    System.out.println(ANSI_RED + "You have no rights to delete this file" + ANSI_RESET);
                }
            }
        }
    }

    @Override
    public void close() throws Exception {

    }
}
