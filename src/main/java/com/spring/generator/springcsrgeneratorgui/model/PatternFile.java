package com.spring.generator.springcsrgeneratorgui.model;

import java.io.File;

public class PatternFile {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNameFromPatternFile(File patternFile) {
        if(!patternFile.isFile()) return;
        var title = patternFile.getName();
        this.name = title.split("Pattern")[0];
    }

    @Override
    public String toString() {
        return this.name;
    }
}

