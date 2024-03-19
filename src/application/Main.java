package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import logic.Admin;
import model.Job;
import model.Material;
import model.Worker;

public class Main extends Application {

    private Admin admin = new Admin();
    private Scene previousScene; // variable para devolver la escena

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Gestion de obras de construcción");

        ConsoleMenu consoleMenu = new ConsoleMenu(primaryStage, admin, previousScene);
        Scene scene = consoleMenu.createMenu();

        primaryStage.setScene(scene);

        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
