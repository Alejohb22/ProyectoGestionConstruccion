package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import logic.Admin;

public class WorkerForm{
    private Stage primaryStage;
    private Admin admin;

    public WorkerForm(Stage primaryStage, Admin admin) {
        this.primaryStage = primaryStage;
        this.admin = admin;
    }

    public Scene createWorkerMenu() {
        BorderPane root = new BorderPane();
        VBox workerLayout = new VBox(10);
        workerLayout.setStyle("-fx-background-color: #2F3C45;");
        Label nameLabel = new Label("Nombre:");
        nameLabel.setStyle("-fx-text-fill: white");
        TextField nameField = new TextField();
        Label lastNameLabel = new Label("Apellido:");
        TextField lastNameField = new TextField();
        Label jobLabel = new Label("Cargo:");
        TextField jobField = new TextField();
        Button submitButton = new Button("Crear Trabajador");

        workerLayout.getChildren().addAll(nameLabel, nameField, lastNameLabel, lastNameField, jobLabel, jobField, submitButton);
        workerLayout.setAlignment(Pos.CENTER);
        workerLayout.setPadding(new Insets(20));
        root.setCenter(workerLayout);

        submitButton.setOnAction(e -> {
            // Lógica para crear un nuevo trabajador
            // Por ahora, simplemente cerraremos la ventana
            primaryStage.setScene(primaryStage.getScene()); // Volvemos al menú principal
        });

        return new Scene(root, 300, 200);
    }
}
