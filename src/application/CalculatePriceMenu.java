package application;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import logic.Admin;
import model.Material;

public class CalculatePriceMenu {
    private Stage primaryStage;
    private Admin admin;
    private Scene previousScene;

    public CalculatePriceMenu(Stage primaryStage, Admin admin, Scene previousScene) {
        this.primaryStage = primaryStage;
        this.admin = admin;
        this.previousScene = previousScene;
    }

    public Scene createCalculatePriceMenu() {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #2F3C45");

        VBox titleBox = new VBox(20);
        titleBox.setPadding(new Insets(50, 0, 0, 0));
        titleBox.setStyle("-fx-background-color: #2F3C45;");//color fondo titulo
        Label titleLabel = new Label("CALCULAR PRECIO MATERIALES");
        titleLabel.setStyle("-fx-font-size: 50px; -fx-font-weight: bold;-fx-text-fill: #CAD2C5; -fx-font-family: Algerian;-fx-alignment: center");
        root.setTop(titleBox);
        titleBox.getChildren().addAll(titleLabel);

        VBox buttons = new VBox(10);
        Button calculateButton = new Button("Calcular Precio Materiales");
        calculateButton.setOnAction(e -> calculatePrice());
        Button backButton = new Button("Volver");
        backButton.setOnAction(e -> goBack());
        calculateButton.setMinWidth(170);
        calculateButton.setMinHeight(120);
        backButton.setMinWidth(170);
        backButton.setMinHeight(120);
        buttons.getChildren().addAll(calculateButton, backButton);
        root.setLeft(buttons);

        Scene scene = new Scene(root, 1280, 720);
        primaryStage.setScene(scene);
        return scene;
    }



    private void calculatePrice() {
        if (admin.getListMaterH().size() > 0) {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Seleccionar Material");
            dialog.setHeaderText(null);
            dialog.setContentText("Ingrese el ID del material que desea calcular el precio:");

            String sel = "";
            while (true) {
                dialog.showAndWait();
                sel = dialog.getResult();
                if (sel == null || sel.isEmpty()) {
                    showAlert("Error", "Por favor, ingrese un ID válido.");
                } else if (!admin.getListMaterH().containsKey(sel)) {
                    showAlert("Error", "El ID del material ingresado no existe.");
                } else {
                    break;
                }
            }

            Material selectedMaterial = admin.getListMaterH().get(sel);

            TextInputDialog quantityDialog = new TextInputDialog();
            quantityDialog.setTitle("Cantidad a calcular precio");
            quantityDialog.setHeaderText(null);
            quantityDialog.setContentText("Ingrese la cantidad de " + selectedMaterial.getUnidadMedida() + " que desea calcular:");

            while (true) {
                quantityDialog.showAndWait();
                String quantityStr = quantityDialog.getResult();
                try {
                    double cantidad = Double.parseDouble(quantityStr);
                    if (cantidad <= 0) {
                        showAlert("Error", "La cantidad debe ser un número positivo.");
                    } else {
                        selectedMaterial.setAmount(cantidad);
                        selectedMaterial.calcularPrecioMaterial();
                        double precioTotal = selectedMaterial.getPrecioTotal();
                        showInformationAlert("Precio Total", "El precio total para " + cantidad + " " + selectedMaterial.getUnidadMedida() + " de " + selectedMaterial.getName() + " es: " + precioTotal);
                        break;
                    }
                } catch (NumberFormatException e) {
                    showAlert("Error", "Por favor, ingrese un número válido para la cantidad.");
                }
            }
        } else {
            showAlert("Error", "No hay materiales disponibles para calcular el precio.");
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



    private void goBack() {
        primaryStage.setScene(previousScene);
    }
}
