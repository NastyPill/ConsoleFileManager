package FileManager.Actions;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class FileCopyAction implements Action {

    private String from, to;

    public FileCopyAction(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException();
        } else {
            this.from = args[1];
            this.to = args[2];
        }
    }

    private void copyFile(String from, String to) throws IOException {
        String errMessage = new Validator().validate(from) + "\n" + new Validator().validate(to);
        if (!errMessage.equalsIgnoreCase("\n")) {
            System.out.println(ANSI_RED + errMessage + ANSI_RESET);
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
