package com.spring.generator.springcsrgeneratorgui;

import com.spring.generator.springcsrgeneratorgui.service.PropertiesReaderService;
import com.spring.generator.springcsrgeneratorgui.service.SaveService;

import java.io.IOException;


public class App {

    private PropertiesReaderService propertiesReaderService;
    private final SaveService saveService = new SaveService();

    private final String title;
    private final String version;
    private final String icon;

    private final int frameWidth;
    private final int frameHeight;

    public App() {

        try {
            this.propertiesReaderService = new PropertiesReaderService("application.properties");
        }
        catch (IOException e) {
            System.getLogger(App.class.getName());
        }

        this.saveService.loadLastDirectoryModelPath();


        this.title = propertiesReaderService.getProperty("application.title");
        this.version = propertiesReaderService.getProperty("application.version");
        this.icon = propertiesReaderService.getProperty("application.icon");

        this.frameWidth = Integer.parseInt(propertiesReaderService.getProperty("application.frame.width"));
        this.frameHeight = Integer.parseInt(propertiesReaderService.getProperty("application.frame.height"));
    }

    public String getTitle() {
        return title;
    }

    public String getVersion() {
        return version;
    }

    public String getIcon() {
        return icon;
    }

    public int getFrameWidth() {
        return frameWidth;
    }

    public int getFrameHeight() {
        return frameHeight;
    }
}
