package application;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import persistence.Controller;
import model.Material;

public class DeleteMaterialsMenu {
    private Stage primaryStage;
    private Scene previousScene;
    private Controller controller;

    public DeleteMaterialsMenu(Stage primaryStage, Scene previousScene) {
        this.primaryStage = primaryStage;
        this.previousScene = previousScene;
        this.controller = new Controller();
    }

    public Scene createDeleteMaterialsMenu() {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #2F3C45");

        VBox titleBox = new VBox(20);
        titleBox.setPadding(new Insets(50, 0, 0, 0));
        titleBox.setStyle("-fx-background-color: #2F3C45;");
        Label titleLabel = new Label("ELIMINAR MATERIALES");
        titleLabel.setStyle("-fx-font-size: 36px; -fx-font-weight: bold; -fx-text-fill: #CAD2C5;  -fx-alignment: center");
        titleBox.getChildren().addAll(titleLabel);
        root.setTop(titleBox);

        VBox contentBox = new VBox(10);
        contentBox.setPadding(new Insets(20));
        contentBox.setStyle("-fx-background-color: #2F3C45;");

        // Aquí cargamos los materiales y los mostramos en la lista
        controller.loadMaterial();
        for (Material m : controller.getResultMaterial()) {
            Label materialLabel = new Label(m.getId() + " " + m.getName() + " " + m.getAmount() + " " + m.getUnidadMedida() + " $" + m.getPrecioTotal());
            materialLabel.setStyle("-fx-text-fill: white; -fx-font-size: 16px;");
            contentBox.getChildren().add(materialLabel);
        }

        TextField materialIdField = new TextField();
        materialIdField.setPromptText("Ingrese el ID del material a eliminar");
        materialIdField.setStyle("-fx-prompt-text-fill: #95A5A6;");

        Button deleteButton = new Button("Eliminar");
        deleteButton.setStyle("-fx-background-color: #E74C3C; -fx-text-fill: white; -fx-font-size: 16px;");
        deleteButton.setOnAction(e -> deleteMaterial(materialIdField.getText()));

        Button backButton = new Button("Volver");
        backButton.setStyle("-fx-background-color: #3498DB; -fx-text-fill: white; -fx-font-size: 16px;");
        backButton.setOnAction(e -> goBack());

        contentBox.getChildren().addAll(materialIdField, deleteButton, backButton);
        root.setCenter(contentBox);

        Scene scene = new Scene(root, 1280, 720);
        primaryStage.setScene(scene);
        return scene;
    }

    private void deleteMaterial(String materialId) {
        if (controller.deleteMaterial(materialId)) {
            showAlert("Material Eliminado", "Material eliminado con éxito.");
        } else {
            showAlert("Error", "El ID del material ingresado no existe.");
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
