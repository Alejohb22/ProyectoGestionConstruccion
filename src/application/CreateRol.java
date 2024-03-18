package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import logic.Admin;

public class CreateRol {
    private Stage primaryStage;
    private Admin admin;


    public CreateRol(Stage primaryStage, Admin admin) {
        this.primaryStage = primaryStage;
        this.admin = admin;
    }

    public Scene createWorkerMenu() {
        BorderPane root = new BorderPane();
        VBox workerLayout = new VBox(10);
        workerLayout.setStyle("-fx-background-color: #2F3C45;");
        Label nameLabel = new Label("Digite el nombre del rol:");
        nameLabel.setStyle("-fx-text-fill: white");
        TextField nameField = new TextField();
        Label lastNameLabel = new Label("Digite el precio:");
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
