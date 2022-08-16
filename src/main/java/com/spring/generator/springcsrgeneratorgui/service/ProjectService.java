package com.spring.generator.springcsrgeneratorgui.service;

import com.spring.generator.springcsrgeneratorgui.utils.Analyser;

import java.io.File;
import java.util.Objects;

public class ProjectService {

    private final String mainPath;

    private final String basePackage;

    public ProjectService(String modelPath) {

        this.mainPath = modelPath.replaceAll("[\\\\\\/]model","");

        this.basePackage = this.getBasePackage(modelPath);

    }

    private String getBasePackage(String modelPath) {
        File modelPack = new File(modelPath);
        String pack = "";

        // Method 1 : try to get base package from first model
        for(File file : Objects.requireNonNull(modelPack.listFiles())){
            pack = findPack(pack, file);
        }

        // Method 2 : try to get base package from Main file
        if(pack.equals("")){
            var mainDirectory = modelPack.getParentFile().listFiles();
            if( mainDirectory == null) return null;

            for(File file : mainDirectory){
                pack = findPack(pack, file);
            }
        }


        return pack;
    }

    private String findPack(String pack, File file) {
        if(file.isFile()){
            pack = Analyser.searchInFile(file, "package ([a-z0-9]*\\.?)+;");
            if(!pack.equals("")){
                pack = pack.replace("package ","");
                pack = pack.substring(0, pack.length()-1);
                pack = pack.replaceAll("\\.model[\\..]*","");
            }
        }
        return pack;
    }

    public String getMainPath() {
        return mainPath;
    }

    public String getBasePackage() {
        return basePackage;
    }
}
