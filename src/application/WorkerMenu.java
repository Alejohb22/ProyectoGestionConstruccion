package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import logic.Admin;

public class WorkerMenu {
    private Stage primaryStage;
    private Admin admin;

    public WorkerMenu(Stage primaryStage, Admin admin) {
        this.primaryStage = primaryStage;
        this.admin = admin;
    }

    public Scene createWorkerMenu() {
        BorderPane root = new BorderPane();
        VBox botones = new VBox();
        Button botonCT = new Button();


         return new Scene(root, 300, 200);
    }
}
