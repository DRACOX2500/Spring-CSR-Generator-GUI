package com.spring.generator.springcsrgeneratorgui.model;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class PatternList implements Runnable {

    private List<File> patternSystemFiles = new ArrayList<>();
    
    private final String path;

    public PatternList(String path) {
        this.path = path;
    }

    @Override
    public void run() {

        var patternDirectory = new File(this.path);
        if(!patternDirectory.exists()){
            System.Logger.Level.WARNING.getName();
            return;
        }

        if(patternDirectory.isDirectory() && patternDirectory.listFiles() != null) {
            this.patternSystemFiles = Arrays.asList(Objects.requireNonNull(patternDirectory.listFiles()));
        }
    }

    public List<File> getPatternSystemFiles() {
        return patternSystemFiles;
    }
}
