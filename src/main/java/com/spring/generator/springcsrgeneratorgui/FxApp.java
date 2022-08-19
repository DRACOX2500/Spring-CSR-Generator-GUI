package com.spring.generator.springcsrgeneratorgui;

import com.spring.generator.springcsrgeneratorgui.fx.StageFX;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class FxApp extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        var application = new App();
        var stageFX = new StageFX(stage, application);

        stageFX.getStage().show();
    }

    public static void mainLaunch(String[] args) {
        launch(args);
    }
}
