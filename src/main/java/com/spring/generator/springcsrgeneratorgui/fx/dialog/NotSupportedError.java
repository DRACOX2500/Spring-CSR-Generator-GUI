package com.spring.generator.springcsrgeneratorgui.fx.dialog;

import javafx.scene.control.Alert;

public class NotSupportedError {

    public NotSupportedError() {
        var alertDialog = new Alert(Alert.AlertType.ERROR);

        alertDialog.setTitle("Error Dialog");
        alertDialog.setHeaderText(null);
        alertDialog.setContentText("The file is not supported by Spring CSR Generator ! ;[");

        alertDialog.showAndWait();
    }
}
