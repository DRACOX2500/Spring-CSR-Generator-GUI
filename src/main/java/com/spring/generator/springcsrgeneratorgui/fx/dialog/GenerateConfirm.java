package com.spring.generator.springcsrgeneratorgui.fx.dialog;

import com.spring.generator.springcsrgeneratorgui.service.ModelService;
import com.spring.generator.springcsrgeneratorgui.service.SaveService;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Map;
import java.util.Optional;

public class GenerateConfirm {

    private final Optional<ButtonType> result;

    public GenerateConfirm(Map<String, Boolean> map) {
        var alert = new Alert(Alert.AlertType.CONFIRMATION);

        alert.setTitle("Generate Confirmation");
        alert.setHeaderText("Are you sure you want to generate these PATTERNs ?");

        this.setContent(alert, map);



        this.result = alert.showAndWait();
    }

    private void setContent(Alert alert, Map<String, Boolean> map) {

        int nbModels = new ModelService().getModelNumber(new SaveService().getLastDirectoryModelPath());
        var sb = new StringBuilder();

        for (Map.Entry<String, Boolean> entry : map.entrySet()) {
            if(Boolean.TRUE.equals(entry.getValue()))
                sb.append(entry.getKey().toUpperCase()).append(" x").append(nbModels).append("\n");
        }

        alert.setContentText(sb.toString());
    }

    public Boolean getResult() {
        return result.filter(buttonType -> buttonType == ButtonType.OK).isPresent();
    }
}
