package com.spring.generator.springcsrgeneratorgui.fx.controller;

import com.spring.generator.springcsrgeneratorgui.controller.PatternController;
import com.spring.generator.springcsrgeneratorgui.fx.dialog.NotSupportedError;
import com.spring.generator.springcsrgeneratorgui.model.PatternFile;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.CheckBoxListCell;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ListViewController {

    private final PatternController patternController = new PatternController();
    private final Map<String,Boolean> checkPattern = new HashMap<>();

    private final ListView<PatternFile> listView;

    @Getter
    @Setter
    private boolean checkAllActivate = false;

    public ListViewController(ListView<PatternFile> listView, TextArea fileEditor) {

        this.listView = listView;

        this.listView.setItems(
                FXCollections.observableList(this.patternController.getPatternFileList())
        );

        this.listView.setOnMouseClicked(
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

        this.checkInit(false);

    }

    public Map<String, Boolean> getCheckPattern() {
        return this.checkPattern;
    }

    public PatternController getPatternController() {
        return patternController;
    }

    public void reload() {
        this.patternController.load();
    }

    public void unCheckAll() {
        this.checkPattern.clear();
        this.checkInit(false);
        this.checkAllActivate = false;
    }

    public void checkAll() {
        this.checkPattern.clear();
        this.checkInit(true);
        this.checkAllActivate = true;
    }

    private void checkInit(final boolean initialBoolean) {

        this.listView.setCellFactory(CheckBoxListCell.forListView(item -> {
            SimpleBooleanProperty observable;
            if(checkPattern.get(item.getPName()) == null) {
                observable = new SimpleBooleanProperty(initialBoolean);
                checkPattern.put(item.getPName(), observable.getValue());
            }
            else
                observable = new SimpleBooleanProperty(checkPattern.get(item.getPName()));

            observable.addListener((obs, wasSelected, isNowSelected)
                    -> checkPattern.replace(item.getPName(), wasSelected, isNowSelected)
            );
            return observable;
        }));
    }
}
