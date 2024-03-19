package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import persistence.Controller;

public class WorkerSalaryCalculator {
    private Stage primaryStage;
    private Controller controller;
    private Scene previousScene;

    public WorkerSalaryCalculator(Stage primaryStage, Controller controller, Scene previousScene) {
        this.primaryStage = primaryStage;
        this.controller = controller;
        this.previousScene = previousScene;
    }

    public Scene createSalaryCalculatorScene() {
        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color:#2F3C45");
        root.setPadding(new Insets(40));

        Label titleLabel = new Label("Calculadora de Salario de Trabajadores");
        titleLabel.setFont(Font.font("Arial", 24));
        titleLabel.setTextFill(Color.WHITE);

        TextField idField = new TextField();
        idField.setPromptText("Ingrese el ID del trabajador");
        idField.setStyle("-fx-background-color: #34495E; -fx-text-fill: #ECF0F1; -fx-font-size: 14px;");
        idField.setPrefWidth(250);

        TextField hoursField = new TextField();
        hoursField.setPromptText("Ingrese las horas trabajadas");
        hoursField.setStyle("-fx-background-color: #34495E; -fx-text-fill: #ECF0F1; -fx-font-size: 14px;");
        hoursField.setPrefWidth(250);

        Label resultLabel = new Label();
        resultLabel.setFont(Font.font("Arial", 16));
        resultLabel.setTextFill(Color.WHITE);

        Button calculateButton = new Button("Calcular Salario");
        calculateButton.setStyle("-fx-background-color: #2ECC71; -fx-text-fill: #ECF0F1; -fx-font-size: 14px;");
        calculateButton.setPrefWidth(150);

        Button backButton = new Button("Atrás");
        backButton.setStyle("-fx-background-color: #E74C3C; -fx-text-fill: #ECF0F1; -fx-font-size: 14px;");
        backButton.setPrefWidth(150);

        calculateButton.setOnAction(e -> {
            String idTrabajador = idField.getText();
            int numHorasTrabajadas = 0;
            try {
                numHorasTrabajadas = Integer.parseInt(hoursField.getText());
                double salario = controller.calcularSalarioTrabajador(idTrabajador, numHorasTrabajadas);
                if (salario != -1) {
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

        root.getChildren().addAll(titleLabel, idField, hoursField, calculateButton, resultLabel, backButton);

        Scene scene = new Scene(root, 600, 400);
        return scene;
    }
}