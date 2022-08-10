package com.spring.generator.springcsrgeneratorgui.service;

import lombok.SneakyThrows;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import static com.spring.generator.springcsrgeneratorgui.utils.Const.APPLICATION_PROPERTIES;
import static com.spring.generator.springcsrgeneratorgui.utils.Const.DIRECTORY_PATH;

public class SaveService {

    private static String lastDirectoryModelPath = ".";

    private String path;

    private static final String SAVE_KEY = "directoryPath";

    @SneakyThrows
    public SaveService() {
        this.path = new PropertiesReaderService(APPLICATION_PROPERTIES).getProperty(DIRECTORY_PATH);
    }

    /**
     * Save lastDirectoryModelPath in Json file
     * @param directoryModelPath String
     */
    @SneakyThrows
    public void saveLastDirectoryModelPath(String directoryModelPath) {
        if(directoryModelPath == null) return;
        setLastDirectoryModelPath(directoryModelPath);

        var st = new SaveThread();
        st.start();
    }

    /**
     * Load Json file
     */
    public void loadLastDirectoryModelPath() {

        var lt = new LoadThread();
        lt.start();
    }

    public String getLastDirectoryModelPath() {
        return lastDirectoryModelPath;
    }

    static void setLastDirectoryModelPath(String directoryModelPath) {
        lastDirectoryModelPath = directoryModelPath;
    }

    private class SaveThread extends Thread {

        @SneakyThrows
        @Override
        public void run() {
            super.run();

            var file = new File(path);

            if(file.exists()) {
                cleanUp(Path.of(path));
            }
            else if(!file.getParentFile().mkdirs() && !file.createNewFile()) {
                return;
            }

            var json = new JSONObject();
            json.put(SAVE_KEY, lastDirectoryModelPath);

            try (FileWriter fw = new FileWriter(path) ) {
                fw.write(json.toString());

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void cleanUp(Path path) throws IOException {
            Files.delete(path);
        }
    }

    private class LoadThread extends Thread {

        @Override
        public void run() {
            super.run();

            try (Scanner scan = new Scanner(new File(path))) {
                String myJson = scan.useDelimiter("\\Z").next();
                JSONObject myJsonobject = new JSONObject(myJson);

                setLastDirectoryModelPath(myJsonobject.getString(SAVE_KEY));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
