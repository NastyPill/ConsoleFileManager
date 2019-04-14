package FileManager.Console;

import java.io.*;

//Reads input from console

public class Reader<E extends Enum<E>> implements Closeable {

    private BufferedReader reader;

    private String[] args;

    private EnumReader<E> enumReader;

    private Class<E> cls;

    public Reader(InputStream stream, Class<E> cls) {
        reader = new BufferedReader(new InputStreamReader(stream));
        this.cls = cls;
    }

    public void read() throws IOException {
        args = reader.readLine().split("\\s+");
        enumReader = new EnumReader<>(args[0], cls);
    }

    public String[] getArgs() {
        return args;
    }

    public EnumReader<E> getEnumReader() {
        return enumReader;
    }

    public void actionPerformed() {
        args = new String[]{""};
    }

    @Override
    public void close() throws IOException {
        reader.close();
        enumReader.close();
        enumReader = null;
        reader = null;
    }
}
