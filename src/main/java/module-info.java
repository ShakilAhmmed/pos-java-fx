module com.example.quicklookapplication {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.example.quicklookapplication to javafx.fxml;
    exports com.example.quicklookapplication;
    exports com.example.quicklookapplication.Category;
    opens com.example.quicklookapplication.Category to javafx.fxml;
}