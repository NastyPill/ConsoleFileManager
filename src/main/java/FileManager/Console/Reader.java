package FileManager.Console;

import java.io.*;

//Reads input from console

public class Reader<E extends Enum<E>> implements Closeable {

    private BufferedReader reader;

    private String[] args;

    private EnumReader<E> enumReader;

    public Reader(InputStream stream, Class<E> cls) throws IOException {
        reader = new BufferedReader(new InputStreamReader(stream));
        args = reader.readLine().split("\\s+");
        enumReader = new EnumReader<>(args[0], cls);
    }

    public String[] getArgs() {
        return args;
    }

    public EnumReader<E> getEnumReader() {
        return enumReader;
    }

    @Override
    public void close() throws IOException {
        reader.close();
        enumReader.close();
        enumReader = null;
        reader = null;
        args = null;
    }
}
