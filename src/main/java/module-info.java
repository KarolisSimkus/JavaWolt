module com.example.javakursinis {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires lombok;
    requires java.desktop;
    requires java.sql;
    requires java.naming;
    requires mysql.connector.j;
    requires org.hibernate.orm.core;
    requires jakarta.persistence;

    opens com.example.javakursinis to javafx.fxml, org.hibernate.orm.core, jakarta.persistence;
    exports com.example.javakursinis;
    exports com.example.javakursinis.fxControllers to javafx.fxml;
    opens com.example.javakursinis.fxControllers to javafx.fxml;
    opens com.example.javakursinis.model to javafx.fxml, org.hibernate.orm.core, jakarta.persistence;
    exports com.example.javakursinis.model;
}