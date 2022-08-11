package com.spring.generator.springcsrgeneratorgui.fx.controller;

import com.spring.generator.springcsrgeneratorgui.fx.dialog.NotSupportedError;
import com.spring.generator.springcsrgeneratorgui.model.ModelFile;
import javafx.scene.control.TextArea;
import javafx.scene.control.TreeView;

import java.io.File;
import java.util.Scanner;

public class TreeViewController {

    public TreeViewController(TreeView<ModelFile> treeView, TextArea fileEditor) {

        treeView.setOnMouseClicked(
                mouseEvent -> {
                    var model = treeView.getSelectionModel().getSelectedItem();
                    if (model == null) return;

                    if (model.getValue().exists() && model.getValue().isFile()) {
                        try (
                                Scanner scan = new Scanner(new File(model.getValue().getPath()))
                        ) {

                            fileEditor.setText(
                                    scan.useDelimiter("\\Z").next()
                            );

                        } catch (Exception e) {
                            new NotSupportedError();
                        }

                    }
                }
        );
    }
}
