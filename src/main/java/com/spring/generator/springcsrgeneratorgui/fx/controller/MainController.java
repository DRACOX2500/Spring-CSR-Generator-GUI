package com.spring.generator.springcsrgeneratorgui.fx.controller;

import com.spring.generator.springcsrgeneratorgui.controller.Generator;
import com.spring.generator.springcsrgeneratorgui.controller.ModelController;
import com.spring.generator.springcsrgeneratorgui.fx.dialog.GenerateConfirm;
import com.spring.generator.springcsrgeneratorgui.fx.dialog.SelectEditorFontSizeConfirm;
import com.spring.generator.springcsrgeneratorgui.model.ModelFile;
import com.spring.generator.springcsrgeneratorgui.model.PatternFile;
import com.spring.generator.springcsrgeneratorgui.service.SaveService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    private final SaveService saveService = new SaveService();

    private ListViewController listViewController;

    private final ModelController modelController = new ModelController();

    private TextAreaController textAreaController;

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
    @FXML
    public HBox btnMenu;
    @FXML
    public HBox btnMenuTree;
    @FXML
    public Button btnReloadModel;
    @FXML
    public Button btnReloadPattern;
    @FXML
    public Button btnCheckAll;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        root.getStylesheets().add("style.css");
        splitPane.prefWidthProperty().bind(root.widthProperty());
        splitPane.prefHeightProperty().bind(root.heightProperty());
        splitPane.setDividerPositions(0.25);

        fileEditor.prefHeightProperty().bind(root.heightProperty());
        btnGen.prefWidthProperty().bind(fileEditor.widthProperty());
        btnGen.setDisable(true);

        menuBar.setFocusTraversable(true);
        listView.prefHeightProperty().bind(root.heightProperty());
        treeView.prefHeightProperty().bind(root.heightProperty());

        this.listViewController = new ListViewController(listView, fileEditor);
        new TreeViewController(treeView, fileEditor);

        this.textAreaController = new TextAreaController(fileEditor);
    }

    public void onClickOpen() {

        var directoryChooser = new DirectoryChooser();
        directoryChooser.setInitialDirectory(new File(saveService.getLastDirectoryModelPath()));

        var file = directoryChooser.showDialog(new Stage());
        if(file != null) {
            saveService.saveLastDirectoryModelPath(file.getAbsolutePath());
            modelController.load();
            treeView.setRoot(modelController.getTreeRoot());
            btnGen.setDisable(false);
        }
    }

    public void onClickExit() {
        System.exit(0);
    }

    public void onClickOpenLast() {
        if(Objects.equals(saveService.getLastDirectoryModelPath(), ".")) return;

        modelController.load();
        treeView.setRoot(modelController.getTreeRoot());
        btnGen.setDisable(false);
    }

    public void onClickGenerate() {

        if(Boolean.FALSE.equals(new GenerateConfirm(this.listViewController.getCheckPattern()).getResult())) return;

        var generator = new Generator();

        generator.generate(
                listViewController.getPatternController().getPatternFileList(),
                this.listViewController.getCheckPattern()
        );
    }

    public void onClickReloadPattern() {
        this.listViewController.reload();
    }

    public void onClickReloadModel() {
        this.modelController.load();
        treeView.setRoot(modelController.getTreeRoot());
    }

    public void listViewCheckAll() {
        if (this.listViewController.isCheckAllActivate()) {
            this.listViewController.unCheckAll();
            this.btnCheckAll.getStyleClass().remove("uncheck");
            this.btnCheckAll.setTooltip(new Tooltip("Check all"));
        }
        else {
            this.listViewController.checkAll();
            this.btnCheckAll.getStyleClass().add("uncheck");
            this.btnCheckAll.setTooltip(new Tooltip("Uncheck all"));
        }

    }

    public void onClickSelectFontSize() {
        new SelectEditorFontSizeConfirm(textAreaController);
    }
}