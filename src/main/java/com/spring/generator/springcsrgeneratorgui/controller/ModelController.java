package com.spring.generator.springcsrgeneratorgui.controller;

import com.spring.generator.springcsrgeneratorgui.model.ModelFile;
import com.spring.generator.springcsrgeneratorgui.service.ModelService;
import com.spring.generator.springcsrgeneratorgui.service.SaveService;
import javafx.scene.control.TreeItem;

public class ModelController {

    private final SaveService saveService = new SaveService();
    private final ModelService modelService = new ModelService();

    private ModelFile modelRepo;

    public void load() {

        this.modelRepo = modelService.getModels(
                saveService.getLastDirectoryModelPath()
        );
    }

    public TreeItem<ModelFile> getTreeRoot() {
        if(modelRepo == null || !modelRepo.exists()) return null;

        var root = new TreeItem<>(this.modelRepo);
        this.addTreeItemToParent(root, this.modelRepo);

        return root;
    }

    private void addTreeItemToParent(TreeItem<ModelFile> treeItem, ModelFile repo) {
        if (repo.isDirectory() && repo.listFiles() != null) {
            for (ModelFile fChild:
                    repo.listModelFiles()) {

                var treeNode = new TreeItem<>(fChild);
                treeItem.getChildren().add(treeNode);
                if (fChild.isDirectory())
                    this.addTreeItemToParent(treeNode, fChild);
            }
        }
    }
}
