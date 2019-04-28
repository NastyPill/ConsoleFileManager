package FileManager.Actions;

import FileManager.Utils.Validator;

import java.io.File;

public class FileRenameAction implements Action {

    String from;

    String to;

    Validator validator;

    public FileRenameAction(String[] args, String currentDir) {
        validator = new Validator();
        if (args.length != 3) {
            throw new IllegalArgumentException();
        } else {
            this.from = validator.isPath(args[1], currentDir);
            this.to = validator.isPath(args[2], currentDir);
            this.validator = new Validator();
        }
    }

    @Override
    public void run() {
        if (to == null) {
            System.out.println(ANSI_RED + "New file name isn't correct" + ANSI_RESET);
        } else {
            File file = new File(from);
            file.renameTo(new File(to));
        }
    }

    @Override
    public void close() throws Exception {

    }

}
