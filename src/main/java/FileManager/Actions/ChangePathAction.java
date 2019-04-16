package FileManager.Actions;

import java.io.File;

public class ChangePathAction {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";

    public String start(String currentPath, String[] args) {
        String path;
        if (args[1].charAt(0) == '/') {
            path = args[1];
        } else  {
            path = currentPath + "/" + args[1];
        }

        if (!new File(path).exists()) {
            System.out.println(ANSI_RED + new File(path).getAbsolutePath() + " doesn't exist" + ANSI_RESET);
            return currentPath;
        } else {
            if (path.charAt(path.length() - 1) == '/') {
                return path.substring(0, path.length() - 1);
            } else {
                return path;
            }
        }
    }
}
