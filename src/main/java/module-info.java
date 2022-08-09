module com.spring.generator.springcsrgeneratorgui {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;
    requires org.json;

    opens com.spring.generator.springcsrgeneratorgui to javafx.fxml;
    exports com.spring.generator.springcsrgeneratorgui;
    exports com.spring.generator.springcsrgeneratorgui.fx.controller;
    opens com.spring.generator.springcsrgeneratorgui.fx.controller to javafx.fxml;
}