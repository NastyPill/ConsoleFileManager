package FileManager.Console;

import java.io.IOException;

public class ConsoleUI<E extends Enum<E>> extends Reader implements Runnable {


    Boolean exit;

    public ConsoleUI(Class<E> cls) throws IOException {
        super(System.in, cls);
    }

    @Override
    public void run() {
        while (!exit) {
            commandProcessing();
        }
    }

    private void commandProcessing() {
        try{
            System.out.print(">>> ");
            onCommand((E) getEnumReader().getCommmand());
        } catch (IOException e) {

        }
    }

    protected void onCommand(E commmand) throws IOException {}

    @Override
    public void close() throws IOException {
        exit = true;
        super.close();
    }
}
