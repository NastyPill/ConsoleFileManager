package fileManager.actions;

import fileManager.utils.Validator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class FileCopyAction implements Action {

    private String from, to;

    private Validator validator;

    public FileCopyAction(String[] args, String currentDir) {
        validator = new Validator();
        if (args.length != 3) {
            throw new IllegalArgumentException();
        } else {
            this.from = validator.isPath(args[1], currentDir);
            this.to = validator.isPath(args[2], currentDir);
        }
    }

    private void copyFile(String from, String to) throws IOException {
        String errMessage1 = validator.validate(from);
        String errMessage2 = validator.validate(to);
        if (errMessage1 != null && errMessage2 != null) {
            if (errMessage1 == null) {
                errMessage1 = "";
            }
            if (errMessage2 == null) {
                errMessage2 = "";
            }
            System.out.println(ANSI_RED + errMessage1 + " " + errMessage2 + ANSI_RESET);
        } else {
            Path pFrom = new File(from).toPath();
            File fileTo = new File(to);
            Path pTo = new File(to).toPath();
            if (fileTo.exists()) {
                if (!fileTo.isDirectory()) {
                    Files.delete(pTo);
                    Files.copy(pFrom, pTo, StandardCopyOption.COPY_ATTRIBUTES);
                } else {
                    pTo = new File(to + "/" + pFrom.toFile().getName()).toPath();
                    Files.copy(pFrom, pTo, StandardCopyOption.COPY_ATTRIBUTES);
                }
            } else {
                Files.copy(pFrom, pTo, StandardCopyOption.COPY_ATTRIBUTES);
            }
        }
    }

    @Override
    public void run() {
        try {
            copyFile(from, to);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
    }
}
