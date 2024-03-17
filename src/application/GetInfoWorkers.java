package application;

import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
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
        warningLabel.setStyle("-fx-font-size: 25px; -fx-font-weight: bold;-fx-text-fill: #CAD2C5; -fx-font-family: Algerian");

        VBox boxInfo;

        if (admin.getListWorker().isEmpty()) {
            boxInfo = new VBox(warningLabel);
        } else {
            boxInfo = new VBox(workerListView);
        }
        root.setCenter(boxInfo);


        Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);

        // Establecer la ventana en pantalla completa
        primaryStage.setFullScreen(true);

        return scene;
    }
}
