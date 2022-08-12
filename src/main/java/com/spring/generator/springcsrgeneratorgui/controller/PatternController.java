package com.spring.generator.springcsrgeneratorgui.controller;

import com.spring.generator.springcsrgeneratorgui.model.PatternFile;
import com.spring.generator.springcsrgeneratorgui.service.PatternService;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PatternController {

    private final List<PatternFile> patternFileList;

    private final PatternService patternService = new PatternService();

    public PatternController() {
        this.patternFileList = new ArrayList<>();
        this.load();
    }

    private void load() {
        this.patternFileList.clear();
        var list = this.patternService.getPatternSystemFiles();
        for (File f:
             list) {
            this.addPatternFromPatternFile(f);
        }
    }

    public List<PatternFile> getPatternFileList() {
        return patternFileList;
    }

    public void addPatternFromPatternFile(File patternFile) {
        if(patternFile == null || !patternFile.isFile()) return;
        if(patternFile.isDirectory() && patternFile.listFiles() != null) {
            for (File f:
                    Objects.requireNonNull(patternFile.listFiles())) {
                this.addPatternFromPatternFile(f);
            }
        }

        this.patternFileList.add(
                new PatternFile(patternFile)
        );
    }
}
