module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires lombok;
    requires org.apache.commons.lang3;
    requires com.google.common;
    requires java.desktop;

    opens com.example.demo to javafx.fxml;
    exports com.example.demo;
}