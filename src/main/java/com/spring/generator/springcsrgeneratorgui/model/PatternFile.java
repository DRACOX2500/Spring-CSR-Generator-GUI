package com.spring.generator.springcsrgeneratorgui.model;

import lombok.Builder;
import lombok.Data;

import java.io.File;

@Builder
@Data
public class PatternFile extends File {

    private String pName;

    public PatternFile(File file) {
        super(file.getAbsolutePath());
        this.pName = file.getName().split("Pattern")[0];
    }

    @Override
    public String toString() {
        return this.pName;
    }
}

