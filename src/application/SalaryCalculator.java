package application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import persistence.Controller;

public class SalaryCalculator {
    private Stage primaryStage;
    private Controller controller;
    private Scene previousScene;

    public SalaryCalculator(Stage primaryStage, Controller controller, Scene previousScene) {
        this.primaryStage = primaryStage;
        this.controller = controller;
        this.previousScene = previousScene;
    }

    public Scene createSalaryCalculatorScene() {
        BorderPane root = new BorderPane();
        root.setBackground(new Background(new BackgroundFill(Color.web("#2F3C45"), CornerRadii.EMPTY, Insets.EMPTY)));

        VBox contentBox = new VBox(20);
        contentBox.setPadding(new Insets(50));
        contentBox.setAlignment(Pos.CENTER);

        Button calculateButton = new Button("Calcular Salario");
        calculateButton.setOnAction(e -> calSalary());
        setButtonStyle(calculateButton);

        contentBox.getChildren().add(calculateButton);
        root.setCenter(contentBox);

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        return scene;
    }


    private void calSalary() {
        double totalExpense = controller.calcDailyExpense();
        showAlert("Gasto Total de la Obra", "$" + totalExpense + " es el gasto total de la obra generado hoy.");
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void setButtonStyle(Button button) {
        button.setStyle("-fx-background-color: #3498DB; -fx-text-fill: white; -fx-font-size: 18px; -fx-padding: 10px 20px;");
    }
}
