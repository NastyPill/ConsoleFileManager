package FileManager.Actions;

import FileManager.Utils.Validator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileDeleteAction implements Action {

    private String from;

    private Validator validator;

    public FileDeleteAction(String[] args, String currentDir) {
        validator = new Validator();
        if (args.length != 2) {
            throw new IllegalArgumentException();
        } else {
            this.from = validator.isPath(args[1], currentDir);
        }
    }

    @Override
    public void run() {
        File file = new File(from);
        Path p = file.toPath();
        String errMessage = validator.validate(p.toString());
        if (errMessage != null) {
            System.out.println(ANSI_RED + errMessage + ANSI_RESET);
            return;
        } else {
            try {
                Files.delete(p);
            } catch (IOException e) {
                if (!file.exists()) {
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
