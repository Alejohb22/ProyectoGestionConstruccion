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
import logic.Admin;

public class DeleteMaterialsMenu {
    private Stage primaryStage;
    private Admin admin;
    private Scene previousScene;

    public DeleteMaterialsMenu(Stage primaryStage, Admin admin, Scene previousScene) {
        this.primaryStage = primaryStage;
        this.admin = admin;
        this.previousScene = previousScene;
    }

    public Scene createDeleteMaterialsMenu() {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #2F3C45");

        VBox titleBox = new VBox(20);
        titleBox.setPadding(new Insets(50, 0, 0, 0));
        titleBox.setStyle("-fx-background-color: #2F3C45;");
        Label titleLabel = new Label("ELIMINAR MATERIALES");
        titleLabel.setStyle("-fx-font-size: 50px; -fx-font-weight: bold;-fx-text-fill: #CAD2C5; -fx-font-family: Algerian;-fx-alignment: center");
        root.setTop(titleBox);
        titleBox.getChildren().addAll(titleLabel);

        VBox contentBox = new VBox(10);
        for (String materialId : admin.getListMaterH().keySet()) {
            Label materialLabel = new Label(materialId + " " + admin.getListMaterH().get(materialId).getName() + " "
                    + admin.getListMaterH().get(materialId).getAmount() + " "
                    + admin.getListMaterH().get(materialId).getUnidadMedida() + " $"
                    + admin.getListMaterH().get(materialId).getPrecioTotal());
            contentBox.getChildren().add(materialLabel);
        }
        TextField materialIdField = new TextField();
        materialIdField.setPromptText("Ingrese el ID del material a eliminar");
        Button deleteButton = new Button("Eliminar");
        deleteButton.setOnAction(e -> deleteMaterial(materialIdField.getText()));

        Button backButton = new Button("Volver");
        backButton.setOnAction(e -> goBack());

        contentBox.getChildren().addAll(materialIdField, deleteButton, backButton);
        root.setCenter(contentBox);

        Scene scene = new Scene(root, 1280, 720);
        primaryStage.setScene(scene);
        return scene;
    }

    private void deleteMaterial(String materialId) {
        if (admin.getListMaterH().containsKey(materialId)) {
            admin.getListMaterH().remove(materialId);
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

