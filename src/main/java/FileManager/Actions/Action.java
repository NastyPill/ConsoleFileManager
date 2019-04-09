package FileManager.Actions;

public interface Action extends Runnable, AutoCloseable {

    default void start() {
        Runnable task = () -> {
            this.run();

            try {
                this.close();
            } catch (Exception e) {
                e.printStackTrace(System.err);
            }
        };
        System.out.println("sdda");
        task.run();
    }




}
