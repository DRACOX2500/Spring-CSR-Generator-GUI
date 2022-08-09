package com.spring.generator.springcsrgeneratorgui.service;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;


//TODO: install thread
public class SaveService {

    private static String lastDirectoryModelPath = ".";

    private String path;

    public void saveLastDirectoryModelPath(String directoryModelPath) {
        this.setLastDirectoryModelPath(directoryModelPath);

        try {
            this.path = new PropertiesReaderService("application.properties").getProperty("directory.path");

            File file = new File(this.path);

            if(file.exists()){
                file.delete();
            }
            else {
                file.getParentFile().mkdirs();
                file.createNewFile(); //TODO: Log
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        JSONObject json = new JSONObject();
        try{
            json.put("directoryPath", lastDirectoryModelPath);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try (
                FileWriter fw = new FileWriter(this.path)
                ) {

            fw.write(json.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadLastDirectoryModelPath() {
        //TODO
    }

    public String getLastDirectoryModelPath() {
        return lastDirectoryModelPath;
    }

    private void setLastDirectoryModelPath(String directoryModelPath) {
        lastDirectoryModelPath = directoryModelPath;
    }
}
