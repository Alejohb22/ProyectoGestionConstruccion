package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import logic.Admin;
import persistence.Controller;
import model.Job;
import model.Worker;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class WorkerForm {
    private Stage primaryStage;
    private Admin admin;
    private Scene previousScene;
    private ObservableList<Job> jobOptions;
    private ComboBox<Job> jobComboBox;
    private Controller controller;


    public WorkerForm(Stage primaryStage, Admin admin, Scene previousScene) {
        this.primaryStage = primaryStage;
        this.admin = admin;
        this.previousScene = previousScene;
        controller = new Controller();
        controller.loadRol();
        jobOptions = FXCollections.observableArrayList(controller.getResultJob());
    }


    public Scene createCreateWorkerMenu() {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #2F3C45;");

        VBox titleBox = new VBox(20);
        titleBox.setPadding(new Insets(50, 0, 0, 0));
        titleBox.setAlignment(Pos.CENTER);
        Label titleLabel = new Label("CREAR TRABAJADOR");
        titleLabel.setStyle("-fx-font-size: 36px; -fx-font-weight: bold; -fx-text-fill: #ECF0F1;");
        titleBox.getChildren().addAll(titleLabel);

        VBox contentBox = new VBox(20);
        contentBox.setPadding(new Insets(20));
        contentBox.setAlignment(Pos.CENTER);

        TextField nameField = new TextField();
        nameField.setPromptText("Nombre del trabajador");
        nameField.setStyle("-fx-prompt-text-fill: #95A5A6; -fx-font-size: 14px;");

        TextField lastNameField = new TextField();
        lastNameField.setPromptText("Apellido del trabajador");
        lastNameField.setStyle("-fx-prompt-text-fill: #95A5A6; -fx-font-size: 14px;");

        jobComboBox = new ComboBox<>();
        jobComboBox.setItems(jobOptions);
        jobComboBox.setPromptText("Seleccionar cargo");
        jobComboBox.setStyle("-fx-font-size: 14px;");

        Button createButton = new Button("Crear");
        createButton.setStyle("-fx-background-color: #16A085; -fx-text-fill: white; -fx-font-size: 14px;");
        Button backButton = new Button("Volver");
        backButton.setStyle("-fx-background-color: #C0392B; -fx-text-fill: white; -fx-font-size: 14px;");

        HBox buttonBox = new HBox(20);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(createButton, backButton);

        contentBox.getChildren().addAll(nameField, lastNameField, jobComboBox, buttonBox);
        root.setTop(titleBox);
        root.setCenter(contentBox);

        createButton.setOnAction(e -> createNewWorker(nameField.getText(), lastNameField.getText(), jobComboBox.getValue()));
        backButton.setOnAction(e -> goBack());

        Scene scene = new Scene(root, 800, 600);
        return scene;
    }


    public void addJobToComboBox(Job job) {
        controller.addJob(job);
        jobOptions=FXCollections.observableArrayList(controller.getResultJob());
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
                controller.addWorker(worker);
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
