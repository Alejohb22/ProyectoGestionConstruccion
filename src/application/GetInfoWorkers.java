package application;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import logic.Admin;

public class GetInfoWorkers {
    private Stage primaryStage;
    private Admin admin;

    public GetInfoWorkers(Stage primaryStage, Admin admin) {
        this.primaryStage = primaryStage;
        this.admin = admin;
    }

    public Scene createGetInfoWorkers() {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color:#2F3C45");
        ListView<String> workerListView = new ListView<>(
                FXCollections.observableArrayList(admin.getListWorker().stream()
                        .map(worker -> worker.getName() + " " + worker.getlastname() + " Código: " + worker.getCode() + " Cargo: " + worker.getJob().getName() + " Sueldo: " + worker.getSalary())
                        .toList())
        );

        Label warningLabel = new Label("Aún no se han creado trabajadores");
        warningLabel.setStyle("-fx-font-size: 25px; -fx-font-weight: bold;-fx-text-fill: #CAD2C5; -fx-font-family: Sans-Serif");

        VBox boxInfo;

        if (admin.getListWorker().isEmpty()) {
            boxInfo = new VBox(warningLabel);
        } else {
            boxInfo = new VBox(workerListView);
        }
        root.setCenter(boxInfo);

        Button atrasButton = new Button("Atras");
        atrasButton.setStyle("-fx-font-size: 18px; -fx-background-color: #354F53; -fx-text-fill: white;-fx-font-family: Algerian");
        atrasButton.setMinWidth(100);
        atrasButton.setAlignment(Pos.CENTER);

        HBox bottomButtonsBox = new HBox(20);
        bottomButtonsBox.setEffect(new DropShadow());
        bottomButtonsBox.getChildren().addAll( atrasButton);
        bottomButtonsBox.setAlignment(Pos.CENTER);
        bottomButtonsBox.setPadding(new Insets(20));

        root.setBottom(bottomButtonsBox);



        Scene scene = new Scene(root, 900, 500);
        primaryStage.setScene(scene);

        // Establecer la ventana en pantalla completa
        primaryStage.setFullScreen(true);

        return scene;
    }
}
