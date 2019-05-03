package fileManager.actions;

public interface Action extends Runnable, AutoCloseable {

    String ANSI_RESET = "\u001B[0m";
    String ANSI_RED = "\u001B[31m";
    String ANSI_YELLOW = "\u001B[33m";
    String ANSI_BLUE = "\u001B[34m";
    String ANSI_PURPLE = "\u001B[35m";
    String ANSI_BOLD = "\u001B[1m";
    String ANSI_CYAN = "\u001B[36m";
    String ANSI_UNDERLINED = "\u001B[4m";

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
