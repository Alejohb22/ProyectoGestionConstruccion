package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import logic.Admin;
import model.Worker;

public class GetInfoWorkers {
    private Stage primaryStage;
    private Admin admin;
    private Scene previousScene;

    public GetInfoWorkers(Stage primaryStage, Admin admin, Scene previousScene) {
        this.primaryStage = primaryStage;
        this.admin = admin;
        this.previousScene = previousScene;
    }

    public Scene showWorkerInfo() {
        if (admin.getListWorker().isEmpty()) {
            showAlert("Información", "Aún no se han creado trabajadores.");
            return null;
        }

        VBox container = new VBox(20);
        container.setPadding(new Insets(20));
        container.setAlignment(Pos.CENTER);

        for (Worker worker : admin.getListWorker()) {
            VBox workerInfoBox = createWorkerInfoBox(worker);
            container.getChildren().add(workerInfoBox);
        }

        BackgroundFill backgroundFill = new BackgroundFill(Color.rgb(240, 240, 240), CornerRadii.EMPTY, Insets.EMPTY);
        container.setBackground(new Background(backgroundFill));

        Scene scene = new Scene(container, 600, 400);
        return scene;
    }

    private VBox createWorkerInfoBox(Worker worker) {
        VBox workerInfoBox = new VBox(10);
        workerInfoBox.setAlignment(Pos.CENTER);
        workerInfoBox.setStyle("-fx-background-color: #FFFFFF; -fx-padding: 10px; -fx-border-color: #DDDDDD; -fx-border-width: 1px; -fx-border-radius: 5px;");

        Label nameLabel = createLabel("Nombre: " + worker.getName());
        Label lastNameLabel = createLabel("Apellido: " + worker.getLastName());
        Label codeLabel = createLabel("Código: " + worker.getCode());
        Label jobLabel = createLabel("Cargo: " + worker.getJob());
        Label salaryLabel = createLabel("Sueldo: " + worker.getSalary());

        workerInfoBox.getChildren().addAll(nameLabel, lastNameLabel, codeLabel, jobLabel, salaryLabel);
        return workerInfoBox;
    }

    private Label createLabel(String text) {
        Label label = new Label(text);
        label.setFont(Font.font("Arial", 14));
        label.setTextFill(Color.BLACK);
        return label;
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
