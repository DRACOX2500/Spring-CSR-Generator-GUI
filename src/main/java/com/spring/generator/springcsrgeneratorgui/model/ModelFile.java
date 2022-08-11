package com.spring.generator.springcsrgeneratorgui.model;

import lombok.Getter;
import lombok.Setter;

import java.io.File;

@Getter
@Setter
public class ModelFile extends File {

    public ModelFile(String path) {
        super(path);
    }

    public ModelFile(File file) {
        super(file.getAbsolutePath());
    }

    public ModelFile[] listModelFiles() {
        if(!this.isDirectory()) return new ModelFile[0];
        var list = this.listFiles();
        assert list != null;

        var modelList = new ModelFile[list.length];

        for (int i = 0; i < list.length; i++) {
            modelList[i] = new ModelFile(list[i]);
        }
        return modelList;
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
