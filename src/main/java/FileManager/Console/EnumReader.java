package FileManager.Console;

//Waiting for the 1st argument from console

import java.io.Closeable;
import java.io.IOException;

public class EnumReader<E extends Enum<E>> implements Closeable {

    private String command;

    private Class<E> cls;

    public EnumReader(String command, Class<E> cls) {
        this.command = command;
        this.cls = cls;
    }

    public final E getCommmand() {
        try {
            return E.valueOf(cls, command);
        } catch (IllegalArgumentException e) {
            throw new IllegalAccessError("No such command");
        }
    }

    @Override
    public void close() {
        command = null;
        cls = null;
    }
}
