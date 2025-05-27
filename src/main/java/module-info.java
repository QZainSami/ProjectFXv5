module com.example.projectfxv5 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires org.apache.pdfbox;

    opens com.example.projectfxv5 to javafx.fxml;
    exports com.example.projectfxv5;
}
