package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import persistence.Controller;

public class CalculatePriceMenu {
    private Stage primaryStage;
    private Controller controller;
    private Scene previousScene;

    public CalculatePriceMenu(Stage primaryStage, Controller controller, Scene previousScene) {
        this.primaryStage = primaryStage;
        this.controller = controller;
        this.previousScene = previousScene;
    }

    public Scene createCalculatePriceMenu() {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #2F3C45");

        VBox titleBox = new VBox(20);
        titleBox.setPadding(new Insets(50, 0, 0, 0));
        titleBox.setAlignment(Pos.CENTER);
        Label titleLabel = new Label("CALCULAR PRECIO MATERIALES");
        titleLabel.setStyle("-fx-font-size: 36px; -fx-font-weight: bold; -fx-text-fill: #CAD2C5; -fx-font-family: Algerian;");
        titleBox.getChildren().add(titleLabel);
        root.setTop(titleBox);

        VBox buttons = new VBox(30);
        buttons.setAlignment(Pos.CENTER);
        Button calculateButton = new Button("Calcular Precio Materiales");
        calculateButton.setOnAction(e -> calculatePrice());
        Button backButton = new Button("Volver");
        backButton.setOnAction(e -> goBack());
        setButtonStyle(calculateButton);
        setButtonStyle(backButton);
        buttons.getChildren().addAll(calculateButton, backButton);
        root.setCenter(buttons);

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        return scene;
    }

    private void calculatePrice() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Seleccionar Material");
        dialog.setHeaderText(null);
        dialog.setContentText("Ingrese el ID del material que desea calcular el precio:");

        String idMaterial = "";
        while (true) {
            dialog.showAndWait();
            idMaterial = dialog.getResult();
            if (idMaterial == null || idMaterial.isEmpty()) {
                showAlert("Error", "Por favor, ingrese un ID válido.");
            } else {
                break;
            }
        }

        TextInputDialog quantityDialog = new TextInputDialog();
        quantityDialog.setTitle("Cantidad a calcular precio");
        quantityDialog.setHeaderText(null);
        quantityDialog.setContentText("Ingrese la cantidad:");

        double cantidad = 0;
        while (true) {
            quantityDialog.showAndWait();
            String quantityStr = quantityDialog.getResult();
            try {
                cantidad = Double.parseDouble(quantityStr);
                if (cantidad <= 0) {
                    showAlert("Error", "La cantidad debe ser un número positivo.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                showAlert("Error", "Por favor, ingrese un número válido para la cantidad.");
            }
        }

        double precioTotal = controller.calcularPrecioMaterial(idMaterial, cantidad);
        if (precioTotal != -1) {
            showInformationAlert("Precio Total", "El precio total para " + cantidad + " unidades del material es: $" + precioTotal);
        } else {
            showAlert("Error", "El ID del material ingresado no existe.");
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void showInformationAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
    private void setButtonStyle(Button button) {
        button.setStyle("-fx-background-color: #3498DB; -fx-text-fill: white; -fx-font-size: 18px; -fx-padding: 10px 20px;");
    }

    private void goBack() {
        primaryStage.setScene(previousScene);
    }
}


