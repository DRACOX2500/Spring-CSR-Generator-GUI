package com.spring.generator.springcsrgeneratorgui.fx.controller;

import com.spring.generator.springcsrgeneratorgui.controller.PatternController;
import com.spring.generator.springcsrgeneratorgui.model.ModelFile;
import com.spring.generator.springcsrgeneratorgui.model.PatternFile;
import com.spring.generator.springcsrgeneratorgui.service.SaveService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TreeView;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    private final SaveService saveService = new SaveService();
    private final PatternController patternController = new PatternController();

    @FXML
    public ListView<PatternFile> listView;
    @FXML
    public TreeView<ModelFile> treeView;
    @FXML
    private MenuBar menuBar;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        menuBar.setFocusTraversable(true);
        listView.setItems(
                FXCollections.observableList(patternController.getPatternFileList())
        );
    }

    public void onClickOpen() {

        var directoryChooser = new DirectoryChooser();
        directoryChooser.setInitialDirectory(new File(saveService.getLastDirectoryModelPath()));

        var file = directoryChooser.showDialog(new Stage());
        if(file != null) {
            saveService.saveLastDirectoryModelPath(file.getAbsolutePath());
        }
    }

    public void onClickExit() {
        System.exit(0);
    }
}