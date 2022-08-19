package com.spring.generator.springcsrgeneratorgui.fx.controller;

import javafx.scene.control.TextArea;

public class TextAreaController {

    private final TextArea textArea;

    private int fontSize = 15;

    public TextAreaController(TextArea textArea) {
        this.textArea = textArea;
        this.applyFontSize();
    }

    public int getFontSize() {
        return this.fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
        this.applyFontSize();
    }

    private void applyFontSize() {
        this.textArea.setStyle("-fx-font-size: " + this.fontSize);
    }
}
