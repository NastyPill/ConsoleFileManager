package FileManager.Actions;

public interface Action extends Runnable, AutoCloseable {

    String ANSI_RESET = "\u001B[0m";
    String ANSI_RED = "\u001B[31m";
    String ANSI_GREEN = "\u001B[32m";

    default void start() {
        Runnable task = () -> {
            this.run();
            try {
                this.close();
            } catch (Exception e) {
                e.printStackTrace(System.err);
            }
        };
        task.run();
    }
}
