package fileManager.actions;

import java.io.*;

public class HelpAction implements Action {

    @Override
    public void run() {
        String path = getClass().getClassLoader().getResource("help.txt").getPath();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path)))) {
            String line = br.readLine();
            while (line != null) {
                System.out.println(ANSI_CYAN + line + ANSI_RESET);
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Sorry help.txt file was lost :(");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void close() throws Exception {

    }
}
