package application;


import javax.swing.*;
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
import logic.Controller;
import model.Material;

public class CreateMaterial {
    private Stage primaryStage;
    private Admin admin;
    private Scene previousScene;
    private Controller controller;

    public CreateMaterial(Stage primaryStage, Admin admin, Scene previousScene) {
        this.primaryStage = primaryStage;
        this.admin = admin;
        this.previousScene = previousScene;
        controller = new Controller();
    }
    public Scene createMaterialForm() {


        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #2F3C45");

        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER_LEFT);
        vbox.setPadding(new Insets(80));

        Label lblTitle = new Label("CREAR UN MATERIAL");
        lblTitle.setAlignment(Pos.CENTER);
        lblTitle.setStyle("-fx-text-fill: white; -fx-font-size: 60px;");

        Label nameLabel = new Label("Ingrese el nombre del material:");
        nameLabel.setStyle("-fx-text-fill: white");
        TextField nameField = new TextField();

        Label unitLabel = new Label("Ingrese la unidad del material:");
        unitLabel.setStyle("-fx-text-fill: white");
        TextField unitField = new TextField();

        Label priceLabel = new Label("Ingrese el precio unitario:");
        priceLabel .setStyle("-fx-text-fill: white");
        TextField priceField = new TextField();

        Label quantityLabel = new Label("Ingrese la cantidad:");
        quantityLabel.setStyle("-fx-text-fill: white");
        TextField quantityField = new TextField();

        Button btnAgregar = new Button("Agregar");
        btnAgregar.setOnAction(e -> {
            String nombreMaterial = nameField.getText();
            String unidadMaterial = unitField.getText();
            String precioUnitario = priceField.getText();
            String cantidad = quantityField.getText();
            if (nombreMaterial.isEmpty() || unidadMaterial.isEmpty() || precioUnitario.isEmpty() || cantidad.isEmpty()) {
                // Aquí puedes mostrar un mensaje de error
                System.out.println("Por favor, llena todos los campos.");
                JOptionPane.showMessageDialog(null,"Antes de agregar debe rellenar todos los campos.");
            } else {
                // Aquí puedes proceder con la operación de agregar el material

                int agregar = 0;

                try {
                    Double.parseDouble(precioUnitario);
                    agregar += 1;
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null,"El precio no debe contener String ni carácteres especiales");
                }

                try {
                    Double.parseDouble(cantidad);
                    agregar += 1;
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null,"La cantidad no debe contener String ni carácteres especiales");
                }

                if(agregar == 2) {
                    double precio = Double.parseDouble(precioUnitario);
                    double cant = Double.parseDouble(cantidad);
                    double total = precio*cant;
                    Material m = new Material();

                    m.setName(nombreMaterial);
                    m.setUnidadMedida(unidadMaterial);
                    m.setPrecio(precio);
                    m.setAmount(cant);
                    m.setPrecioTotal(total);

                    controller.addMaterial(m);
                }
            }

        });

        Button btnVolver = new Button("<-- Volver");
        btnVolver.setPrefHeight(100);
        btnVolver.setPrefHeight(40);
        btnVolver.setOnAction(e -> volver());

        vbox.getChildren().addAll(lblTitle,nameLabel, nameField, unitLabel, unitField, priceLabel, priceField, quantityLabel, quantityField, btnAgregar);

        root.setCenter(vbox); // Agrega el VBox al BorderPane
        root.setBottom(btnVolver); // Agrega el botón al BorderPane
        BorderPane.setAlignment(btnVolver, Pos.BOTTOM_LEFT); // Alinea el botón a la izquierda
        BorderPane.setMargin(btnVolver, new Insets(0, 0, 50, 50)); // Agrega un margen inferior de 30px



        Scene scene = new Scene(root, 1280, 720);
        primaryStage.setScene(scene);
        return scene; // Retorna la escena que ya ha sido establecida en primaryStage
    }
    private void volver(){
        Scene previousScene = primaryStage.getScene(); // Store the current scene
        MaterialsMenu menu = new MaterialsMenu(primaryStage,admin);
        Scene getMenu = menu.createWorkerMenu();
        primaryStage.setScene(getMenu);
}

}
