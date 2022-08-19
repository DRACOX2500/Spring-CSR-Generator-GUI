package com.spring.generator.springcsrgeneratorgui.model;


import lombok.Getter;

import java.io.File;

@Getter
public class PatternFile extends File {

    private final String pName;

    public PatternFile(File file) {
        super(file.getAbsolutePath());
        this.pName = file.getName().split("Pattern")[0];
    }

    @Override
    public String toString() {
        return this.pName;
    }
}

