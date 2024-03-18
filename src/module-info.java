module ProyectoGestionContruccion {
    requires javafx.controls;
    requires javafx.graphics;
    requires com.google.gson;
    requires java.desktop;

    opens model to com.google.gson;
    opens application to javafx.graphics, javafx.fxml;
}
