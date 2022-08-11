package com.spring.generator.springcsrgeneratorgui.fx.controller;

import com.spring.generator.springcsrgeneratorgui.controller.ModelController;
import com.spring.generator.springcsrgeneratorgui.controller.PatternController;
import com.spring.generator.springcsrgeneratorgui.model.ModelFile;
import com.spring.generator.springcsrgeneratorgui.model.PatternFile;
import com.spring.generator.springcsrgeneratorgui.service.SaveService;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import lombok.SneakyThrows;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class MainController implements Initializable {

    private final SaveService saveService = new SaveService();
    private final PatternController patternController = new PatternController();

    private final ModelController modelController = new ModelController();

    @FXML
    public ListView<PatternFile> listView;
    @FXML
    public TreeView<ModelFile> treeView;
    @FXML
    public VBox root;
    @FXML
    public HBox body;
    @FXML
    public TabPane tabs;
    @FXML
    public VBox contentView;
    @FXML
    public SplitPane splitPane;
    @FXML
    public TextArea fileEditor;
    @FXML
    public Button btnGen;
    @FXML
    private MenuBar menuBar;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        root.getStylesheets().add("style.css");
        splitPane.prefWidthProperty().bind(root.widthProperty());
        splitPane.prefHeightProperty().bind(root.heightProperty());
        splitPane.setDividerPositions(0.25);

        fileEditor.prefHeightProperty().bind(root.heightProperty());
        btnGen.prefWidthProperty().bind(fileEditor.widthProperty());

        menuBar.setFocusTraversable(true);
        listView.setItems(
                FXCollections.observableList(patternController.getPatternFileList())
        );
        listView.setOnMouseClicked(
                new EventHandler<MouseEvent>() {
                    @SneakyThrows
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        Scanner scan = new Scanner(new File(listView.getSelectionModel().getSelectedItem().getPath()));
                        fileEditor.setText(
                                scan.useDelimiter("\\Z").next()
                        );
                        scan.close();
                    }
                }
        );
    }

    public void onClickOpen() {

        var directoryChooser = new DirectoryChooser();
        directoryChooser.setInitialDirectory(new File(saveService.getLastDirectoryModelPath()));

        var file = directoryChooser.showDialog(new Stage());
        if(file != null) {
            saveService.saveLastDirectoryModelPath(file.getAbsolutePath());
            modelController.load();
            treeView.setRoot(modelController.getTreeRoot());
        }
    }

    public void onClickExit() {
        System.exit(0);
    }
}