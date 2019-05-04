package fileManager;

import fileManager.actions.*;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class FileManagerTest {

    @Test
    public void test() {
        copyTest();
        moveTest();
        deleteTest();
        renameTest();
    }

    public void createFiles(){
        File file = new File(System.getProperty("user.dir") + "/txt.txt");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteFiles() {
        File file = new File(System.getProperty("user.dir") + "/txt.txt");
        file.delete();
        File copiedFile = new File(System.getProperty("user.dir") + "/src/txt.txt");
        copiedFile.delete();
    }

    @Test
    public void copyTest() throws IOException {
        createFiles();
        Action action = new FileCopyAction("copy txt.txt src".split(" "), System.getProperty("user.dir"));
        action.start();
        Assert.assertTrue(new File(System.getProperty("user.dir") + "/src/txt.txt").exists());
        deleteFiles();
    }

    @Test
    public void moveTest() throws IOException {
        createFiles();
        Action action = new FileMoveAction("move txt.txt src".split(" "), System.getProperty("user.dir"));
        action.start();
        Assert.assertTrue(new File(System.getProperty("user.dir") + "/src/txt.txt").exists() &&
                !new File(System.getProperty("user.dir") + "/txt.txt").exists());
        deleteFiles();
    }

    @Test
    public void deleteTest() throws IOException {
        createFiles();
        Action action = new FileDeleteAction("delete txt.txt".split(" "), System.getProperty("user.dir"));
        action.start();
        Assert.assertTrue(!new File(System.getProperty("user.dir") + "/txt.txt").exists());
    }

    @Test
    public void renameTest() throws IOException {
        createFiles();
        Action action = new FileRenameAction("rename txt.txt txt1.txt".split(" "), System.getProperty("user.dir"));
        action.start();
        Assert.assertTrue(new File(System.getProperty("user.dir") + "/txt1.txt").exists());
        new File(System.getProperty("user.dir") + "/txt1.txt").delete();
    }
}
