package com.spring.generator.springcsrgeneratorgui.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatternFile {

    private String name;

    private String path;

    public PatternFile() {
    }

    public PatternFile(String name, String path) {
        this.name = name;
        this.path = path;
    }

    @Override
    public String toString() {
        return this.name;
    }
}

