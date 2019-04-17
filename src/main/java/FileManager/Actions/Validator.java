package FileManager.Actions;

import java.io.File;

public class Validator {

    protected String validate(String path) {
        File f = new File(path);
        if(!f.exists()) {
            return f.getName() + " doesn't exist";
        }
        if(!f.canRead()) {
            return f.getName() + " isn't readable";
        }
        if(!f.canWrite()) {
            return f.getName() + " isn't writable";
        }
        return null;
    }

    protected String isPath(String arg, String currentDir) {
        if (arg.charAt(0) != '/') {
            return currentDir + "/" + arg;
        } else {
            return arg;
        }
    }

}
