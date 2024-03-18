package application;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import logic.Admin;
import model.Job;
import model.Worker;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class WorkerForm {
    private Stage primaryStage;
    private Admin admin;
    private Scene previousScene;

    public WorkerForm(Stage primaryStage, Admin admin, Scene previousScene) {
        this.primaryStage = primaryStage;
        this.admin = admin;
        this.previousScene = previousScene;
    }

    public Scene createCreateWorkerMenu() {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #2F3C45");

        VBox titleBox = new VBox(20);
        titleBox.setPadding(new Insets(50, 0, 0, 0));
        titleBox.setStyle("-fx-background-color: #2F3C45;");
        Label titleLabel = new Label("CREAR TRABAJADOR");
        titleLabel.setStyle("-fx-font-size: 50px; -fx-font-weight: bold;-fx-text-fill: #CAD2C5; -fx-font-family: Algerian;-fx-alignment: center");
        root.setTop(titleBox);
        titleBox.getChildren().addAll(titleLabel);

        VBox contentBox = new VBox(10);
        TextField nameField = new TextField();
        nameField.setPromptText("Nombre del trabajador");

        TextField lastNameField = new TextField();
        lastNameField.setPromptText("Apellido del trabajador");

        ComboBox<Job> jobComboBox = new ComboBox<>();
        ObservableList<Job> jobOptions = FXCollections.observableArrayList(
                new Job("Manager", 100),
                new Job("Developer", 80),
                new Job("Designer", 70),
                new Job("Salesperson", 60),
                new Job("Other", 50));
        jobComboBox.setItems(jobOptions);
        jobComboBox.setPromptText("Seleccionar cargo");

        Button createButton = new Button("Crear");
        createButton.setOnAction(e -> createNewWorker(nameField.getText(), lastNameField.getText(), jobComboBox.getValue()));

        Button backButton = new Button("Volver");
        backButton.setOnAction(e -> goBack());

        contentBox.getChildren().addAll(nameField, lastNameField, jobComboBox, createButton, backButton);
        root.setCenter(contentBox);

        Scene scene = new Scene(root, 1280, 720);
        primaryStage.setScene(scene);
        return scene;
    }

    private void createNewWorker(String name, String lastName, Job job) {
        if (name.isEmpty() || lastName.isEmpty() || job == null) {
            showAlert("Error", "Por favor, complete todos los campos.");
        } else {
            try {
                Worker worker = new Worker();
                worker.setName(name);
                worker.setLastName(lastName);
                worker.setJob(job);
                admin.addWorker(worker);
                showAlert("Éxito", "Trabajador creado exitosamente.");
            } catch (Exception e) {
                showAlert("Error", "No se pudo crear el trabajador.");
            }
        }
    }

    private void goBack() {
        primaryStage.setScene(previousScene);
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
