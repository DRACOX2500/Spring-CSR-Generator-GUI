package com.spring.generator.springcsrgeneratorgui.fx.dialog;

import com.spring.generator.springcsrgeneratorgui.fx.controller.TextAreaController;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.util.Optional;

public class SelectEditorFontSizeConfirm {

    public SelectEditorFontSizeConfirm(TextAreaController textArea) {
        var alert = new Alert(Alert.AlertType.CONFIRMATION);

        alert.setTitle("Select Editor Font Size");
        alert.setHeaderText(null);

        var slider = new Slider(0,100, textArea.getFontSize());
        slider.setShowTickLabels(true);

        var label = new Label(String.valueOf((int) slider.getValue()));
        slider.valueProperty().addListener(
                (observableValue, number, t1) -> label.setText(String.valueOf((int) slider.getValue()))
        );

        var expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(slider, 0, 0);
        expContent.add(label, 0, 1);

        alert.getDialogPane().setContent(expContent);

        Optional<ButtonType> result = alert.showAndWait();
        if ( result.isPresent() && result.get() == ButtonType.OK)
            textArea.setFontSize((int) slider.getValue());
    }
}
