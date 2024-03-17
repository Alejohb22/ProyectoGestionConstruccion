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

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Gestión de Obras de Construcción");

        ConsoleMenu consoleMenu = new ConsoleMenu(primaryStage, admin);
        Scene scene = consoleMenu.createMenu();

        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
