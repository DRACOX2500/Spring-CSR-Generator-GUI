package com.spring.generator.springcsrgeneratorgui;

import com.spring.generator.springcsrgeneratorgui.fx.StageFX;
import javafx.stage.Stage;
import javafx.application.Application;

import java.io.IOException;

public class MainApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        var application = new App();
        var stageFX = new StageFX(stage, application);

        stageFX.getStage().show();
    }

    public static void main(String[] args) {
        launch();
    }
}