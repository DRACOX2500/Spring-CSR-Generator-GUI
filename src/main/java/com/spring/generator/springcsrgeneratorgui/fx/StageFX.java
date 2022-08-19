package com.spring.generator.springcsrgeneratorgui.fx;

import com.spring.generator.springcsrgeneratorgui.App;
import com.spring.generator.springcsrgeneratorgui.MainApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class StageFX {

    private final Stage stage;

    public StageFX(Stage stage, App application) throws IOException {

        this.stage = stage;

        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), application.getFrameWidth(), application.getFrameHeight());

        this.stage.setTitle(application.getTitle() + " [" + application.getVersion() + "]");
        this.stage.getIcons().add(new Image(application.getIcon()));

        this.stage.setScene(scene);
    }

    public Stage getStage() {
        return stage;
    }
}
