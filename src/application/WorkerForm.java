package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import logic.Admin;

public class WorkerForm {
    private Stage primaryStage;
    private Admin admin;
    private Scene previousScene;// variable para devolver escena

    public WorkerForm(Stage primaryStage, Admin admin, Scene previousScene) {
        this.primaryStage = primaryStage;
        this.admin = admin;
        this.previousScene = previousScene;
    }


    public Scene createWorkerForm() {
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
        Button backButton = new Button("Volver");

        workerLayout.getChildren().addAll(nameLabel, nameField, lastNameLabel, lastNameField, jobLabel, jobField, submitButton);
        workerLayout.setAlignment(Pos.CENTER);
        workerLayout.setPadding(new Insets(20));
        root.setCenter(workerLayout);
        root.setBottom(backButton);

        submitButton.setOnAction(e -> {
            // Lógica para crear un nuevo trabajador
            // Por ahora, simplemente cerraremos la ventana
            primaryStage.setScene(primaryStage.getScene()); // Volvemos al menú principal
        });

        backButton.setOnAction(e -> {
            primaryStage.setScene(previousScene); // Volvemos a la escena anterior
        });

        return new Scene(root, 1280, 720);
    }
}
