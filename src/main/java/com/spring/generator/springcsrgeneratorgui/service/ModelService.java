package com.spring.generator.springcsrgeneratorgui.service;

import com.spring.generator.springcsrgeneratorgui.model.ModelFile;

import java.util.Objects;

public class ModelService {

    public ModelFile getModels(final String path ) {
        var modelRepo = new ModelFile(path);
        if (modelRepo.exists() && modelRepo.isDirectory())
            return modelRepo;

        return null;
    }

    public int getModelNumber(final String path ) {
        int nb = 0;

        var modelRepo = new ModelFile(path);
        if (modelRepo.exists() && modelRepo.isDirectory()) {

            for (var f:
                    Objects.requireNonNull(modelRepo.listFiles())) {
                if(f.isDirectory())
                    nb += getModelNumber(f.getAbsolutePath());
                else
                    nb += 1;
            }
        }

        return nb;
    }
}
