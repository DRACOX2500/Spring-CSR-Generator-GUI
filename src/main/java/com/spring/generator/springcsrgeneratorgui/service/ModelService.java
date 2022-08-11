package com.spring.generator.springcsrgeneratorgui.service;

import com.spring.generator.springcsrgeneratorgui.model.ModelFile;

public class ModelService {

    public ModelFile getModels(final String path ) {
        var modelRepo = new ModelFile(path);
        if (modelRepo.exists() && modelRepo.isDirectory())
            return modelRepo;

        return null;
    }
}
