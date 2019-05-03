package fileManager.actions;

import fileManager.utils.Validator;

import java.io.File;

public class FileMoveAction implements Action {

    private String from;

    private String to;

    private Validator validator;

    public FileMoveAction(String[] args, String currentDir) {
        validator = new Validator();
        if (args.length != 3) {
            throw new IllegalArgumentException();
        } else {
            this.from = validator.isPath(args[1], currentDir);
            this.to = validator.isPath(args[2], currentDir);
        }
    }

    @Override
    public void run() {
        String errMessage1 = validator.validate(from);
        String errMessage2 = validator.validate(to);
        if (errMessage1 == null && errMessage2 == null) {
            File fileFrom = new File(from);
            File fileTo = new File(to + "/" + fileFrom.getName());
            fileFrom.renameTo(fileTo);
        } else {
            if (errMessage1 == null) {
                errMessage1 = "";
            }
            if (errMessage2 == null) {
                errMessage2 = "";
            }
            System.out.println(ANSI_RED + errMessage1 + " " + errMessage2 + ANSI_RESET);
        }
    }

    @Override
    public void close() throws Exception {

    }

}
