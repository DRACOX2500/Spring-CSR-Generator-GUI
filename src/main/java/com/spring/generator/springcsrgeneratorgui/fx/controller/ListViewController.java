package com.spring.generator.springcsrgeneratorgui.fx.controller;

import com.spring.generator.springcsrgeneratorgui.controller.PatternController;
import com.spring.generator.springcsrgeneratorgui.fx.dialog.NotSupportedError;
import com.spring.generator.springcsrgeneratorgui.model.PatternFile;
import javafx.collections.FXCollections;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

import java.io.File;
import java.util.Scanner;

public class ListViewController {

    public ListViewController(ListView<PatternFile> listView, TextArea fileEditor) {

        var patternController = new PatternController();

        listView.setItems(
                FXCollections.observableList(patternController.getPatternFileList())
        );

        listView.setOnMouseClicked(
                mouseEvent -> {
                    if (listView.getSelectionModel().getSelectedItem() == null) return;

                    try (
                            Scanner scan = new Scanner(new File(listView.getSelectionModel().getSelectedItem().getPath()))
                    ) {

                        fileEditor.setText(
                                scan.useDelimiter("\\Z").next()
                        );

                    } catch (Exception e) {
                        new NotSupportedError();
                    }
                }
        );
    }

}
