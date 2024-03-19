package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import logic.Admin;
import model.Job;

public class JobCreator {
    private Stage primaryStage;
    private Admin admin;
    private Scene previousScene;
    private WorkerForm workerForm;

    public JobCreator(Stage primaryStage, Admin admin, Scene previousScene) {
        this.primaryStage = primaryStage;
        this.admin = admin;
        this.previousScene = previousScene;
        this.workerForm = new WorkerForm(primaryStage,admin,previousScene);
    }

    public Scene createJobScene() {
        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);
        root.setBackground(new Background(new BackgroundFill(Color.rgb(47, 60, 69), CornerRadii.EMPTY, Insets.EMPTY)));
        root.setPadding(new Insets(40));

        Label titleLabel = new Label("Crear Nuevo Cargo");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        titleLabel.setTextFill(Color.WHITE);

        Label infoLabel = new Label("Recuerde que ya existen 4 cargos predeterminados");
        infoLabel.setFont(Font.font("Arial", 14));
        infoLabel.setTextFill(Color.WHITE);

        TextField nameField = new TextField();
        nameField.setPromptText("Nombre del cargo");
        nameField.setStyle("-fx-prompt-text-fill: #95A5A6;");
        nameField.setBackground(new Background(new BackgroundFill(Color.rgb(63, 81, 181), CornerRadii.EMPTY, Insets.EMPTY)));

        TextField hourValueField = new TextField();
        hourValueField.setPromptText("Valor pagado por hora");
        hourValueField.setStyle("-fx-prompt-text-fill: #95A5A6;");
        hourValueField.setBackground(new Background(new BackgroundFill(Color.rgb(63, 81, 181), CornerRadii.EMPTY, Insets.EMPTY)));

        Button createButton = new Button("Crear Cargo");
        createButton.setStyle("-fx-background-color: #27AE60; -fx-text-fill: white; -fx-font-size: 16px;");
        createButton.setPrefWidth(150);
        createButton.setOnAction(e -> {
            String name = nameField.getText();
            int hourValue = Integer.parseInt(hourValueField.getText());
            createNewJob(name, hourValue);
        });

        Button backButton = new Button("Volver");
        backButton.setStyle("-fx-background-color: #C0392B; -fx-text-fill: white; -fx-font-size: 16px;");
        backButton.setPrefWidth(150);
        backButton.setOnAction(e -> primaryStage.setScene(previousScene));

        VBox.setMargin(titleLabel, new Insets(0, 0, 20, 0));

        root.getChildren().addAll(titleLabel, infoLabel, nameField, hourValueField, createButton, backButton);

        Scene scene = new Scene(root, 600, 400);
        return scene;
    }


    private void createNewJob(String name, int hourValue) {
        if (name.isEmpty()) {
            showAlert("Error", "Por favor, complete todos los campos.");
        } else {
            try {
                if (hourValue < 4833) {
                    throw new IllegalArgumentException("El valor por hora no puede ser menor a $4833.");
                }

                Job job = new Job(name, hourValue);
                admin.addJob(job);
                showAlert("Información", "Nuevo trabajo creado correctamente.");

                // Agregar el nuevo trabajo al ComboBox en WorkerForm
                workerForm.addJobToComboBox(job);
            } catch (Exception e) {
                showAlert("Error", e.getMessage());
            }
        }
    }


    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
