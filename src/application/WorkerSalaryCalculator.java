package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import logic.Admin;
import logic.Utilidades;

public class WorkerSalaryCalculator {
    private Stage primaryStage;
    private Admin admin;
    private Scene previousScene;

    public WorkerSalaryCalculator(Stage primaryStage, Admin admin, Scene previousScene) {
        this.primaryStage = primaryStage;
        this.admin = admin;
        this.previousScene = previousScene;
    }

    public Scene createSalaryCalculatorScene() {
        VBox root = new VBox(10);
        root.setStyle("-fx-background-color: #2F3C45;");
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));

        Label titleLabel = new Label("Calculadora de Salario de Trabajadores");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-text-fill: white;");

        TextField idField = new TextField();
        idField.setPromptText("Ingrese el ID del trabajador");

        TextField hoursField = new TextField();
        hoursField.setPromptText("Ingrese las horas trabajadas");

        Label resultLabel = new Label();

        Button calculateButton = new Button("Calcular Salario");

        Button backButton = new Button("Atrás");

        root.getChildren().addAll(titleLabel, idField, hoursField, calculateButton, resultLabel, backButton);

        calculateButton.setOnAction(e -> {
            String idTrabajador = idField.getText();
            int numHorasTrabajadas = Integer.parseInt(hoursField.getText());

            try {
                int pos = new Utilidades().buscarSecuencial(admin.getListWorker(), idTrabajador);
                if (pos != -1) {
                    double salario = admin.getListWorker().get(pos).CalcularSalary(numHorasTrabajadas);
                    resultLabel.setText("El salario del trabajador es $" + salario);
                } else {
                    resultLabel.setText("ID de trabajador no registrado");
                }
            } catch (NumberFormatException ex) {
                resultLabel.setText("Por favor ingrese un número válido de horas");
            }
        });

        backButton.setOnAction(e -> {
            primaryStage.setScene(previousScene);
        });

        Scene scene = new Scene(root, 600, 400);
        return scene;
    }
}