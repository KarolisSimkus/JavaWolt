module com.example.javakursinis {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires static lombok;
    requires java.desktop;

    opens com.example.javakursinis to javafx.fxml;
    exports com.example.javakursinis;
    exports com.example.javakursinis.fxControllers to javafx.fxml;
    opens com.example.javakursinis.fxControllers to javafx.fxml;

}