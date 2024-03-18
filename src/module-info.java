module ProyectoGestionContruccion {
    requires javafx.controls;
    requires javafx.graphics;

    opens model to javafx.graphics, com.google.gson;
    opens application to javafx.graphics, javafx.fxml;
}

