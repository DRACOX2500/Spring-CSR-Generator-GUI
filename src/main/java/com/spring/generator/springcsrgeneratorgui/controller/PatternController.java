package com.spring.generator.springcsrgeneratorgui.controller;

import com.spring.generator.springcsrgeneratorgui.model.PatternFile;
import com.spring.generator.springcsrgeneratorgui.service.PatternService;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PatternController {

    private List<PatternFile> patternFileList;

    private final PatternService patternService = new PatternService();

    public PatternController() {
        this.patternFileList = new ArrayList<>();
        this.load();
    }

    private void load() {
        var list = this.patternService.getPatternSystemFiles();
        for (File f:
             list) {
            this.addPatternFromPatternFile(f);
        }
    }

    public List<PatternFile> getPatternFileList() {
        return patternFileList;
    }

    public void setPatternFileList(List<PatternFile> patternFileList) {
        this.patternFileList = patternFileList;
    }

    public void addPatternFromPatternFile(File patternFile) {
        if(patternFile == null || !patternFile.isFile()) return;
        if(patternFile.isDirectory() && patternFile.listFiles() != null) {
            for (File f:
                 patternFile.listFiles()) {
                this.addPatternFromPatternFile(f);
            }
        }

        var title = patternFile.getName();

        this.patternFileList.add(
                new PatternFile(
                        title.split("Pattern")[0],
                        patternFile.getAbsolutePath()
                )
        );
    }
}
