module ProyectoGestionContruccion {
    requires javafx.controls;
    requires javafx.graphics;


    opens model to com.google.gson;
    opens application to javafx.graphics, javafx.fxml;
}
