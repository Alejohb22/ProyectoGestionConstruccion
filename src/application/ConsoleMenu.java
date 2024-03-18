package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import logic.Admin;
import model.Job;

public class ConsoleMenu  {
    private Stage primaryStage;
    private Admin admin;

    private Scene previousScene;

    public ConsoleMenu(Stage primaryStage, Admin admin, Scene previousScene) {
        this.primaryStage = primaryStage;
        this.admin = admin;
        this.previousScene = previousScene;
        initializeJobs();
    }



    public Scene createMenu() {


        BorderPane root = new BorderPane();

        root.setStyle("-fx-background-color:#2F3C45");


                Label titleLabel = new Label("Gestión de Obras de Construcción");
        titleLabel.setStyle("-fx-font-size: 50px; -fx-font-weight: bold;-fx-text-fill: #CAD2C5; -fx-font-family: sans-serif");

        VBox tituloMenuPrincipal = new VBox(20);
        tituloMenuPrincipal.setPadding(new Insets(50, 0, 0, 0));
        tituloMenuPrincipal.setStyle("-fx-background-color: #2F3C45;");//color fondo titulo

        VBox boxCalcular = new VBox(20);
        boxCalcular.setPadding(new Insets(50));
        boxCalcular.setStyle("-fx-background-color: #2F3C45; -fx-background-radius: 10;"); //color fondo botones y bordes
        //boxCalcular.setEffect(new DropShadow());

        Image imageTrabajadores = new Image(getClass().getResourceAsStream("/resources/trabajadores.gif"));
        ImageView imageViewTrabajadores = new ImageView(imageTrabajadores);
        Button trabajadoresButton = new Button("Trabajadores", imageViewTrabajadores);
        trabajadoresButton.setStyle("-fx-font-size: 18px; -fx-background-color: #2F3C45; -fx-text-fill: #CAD2C5; -fx-font-family: sans-serif");
        trabajadoresButton.setMinWidth(500);
        trabajadoresButton.setAlignment(Pos.CENTER);
        trabajadoresButton.setContentDisplay(ContentDisplay.TOP);
        trabajadoresButton.setPadding(new Insets(10));
        trabajadoresButton.setOnAction(e -> workerMenu());

        VBox boxTrabajadores = new VBox();
        boxTrabajadores.setPadding(new Insets(50, 0, 0, 0));
        boxTrabajadores.setStyle("-fx-background-color:#2F3C45; -fx-background-radius: 10;"); //color fondo botones y bordes
        boxTrabajadores.setEffect(new DropShadow());

        boxTrabajadores.getChildren().addAll(trabajadoresButton);
        boxTrabajadores.setAlignment(Pos.CENTER_LEFT);

        Image imageMateriales = new Image(getClass().getResourceAsStream("/resources/gif materiales.gif"));

        ImageView imageViewMateriales = new ImageView(imageMateriales);
        imageViewMateriales.setFitHeight(340);
        imageViewMateriales.setFitWidth(290);
        Button materialesButton = new Button("Materiales", imageViewMateriales);
        materialesButton.setStyle("-fx-font-size: 18px; -fx-background-color: #2F3C45; -fx-text-fill: white; -fx-font-family: sans-serif");
        materialesButton.setMinWidth(500);
        materialesButton.setAlignment(Pos.CENTER);
        materialesButton.setContentDisplay(ContentDisplay.TOP);
        materialesButton.setPadding(new Insets(10));

        materialesButton.setOnAction(e -> materialMenu());

        VBox boxMateriales = new VBox();
        boxMateriales.setPadding(new Insets(50, 0, 0, 0));
        boxMateriales.setStyle("-fx-background-color: #2F3C45; -fx-background-radius: 10;"); //color fondo botones y bordes
        boxMateriales.setEffect(new DropShadow());
        boxMateriales.getChildren().addAll(materialesButton);
        boxMateriales.setAlignment(Pos.CENTER_RIGHT);

        Button calcularButton = new Button("Calcular gasto del dia");
        calcularButton.setStyle("-fx-font-size: 18px; -fx-background-color: #354F53; -fx-text-fill: white;-fx-font-family: sans-serif");



        calcularButton.setMinWidth(400);
        calcularButton.setAlignment(Pos.CENTER);
        calcularButton.setOnAction(e -> calSalary());

        Button salirButton = new Button("Salir");
        salirButton.setStyle("-fx-font-size: 18px; -fx-background-color: #354F53; -fx-text-fill: white;-fx-font-family: sans-serif");
        salirButton.setMinWidth(100);
        salirButton.setAlignment(Pos.CENTER);
        salirButton.setOnAction(e -> primaryStage.close());

        HBox bottomButtonsBox = new HBox(20);
        bottomButtonsBox.setEffect(new DropShadow());
        bottomButtonsBox.getChildren().addAll(calcularButton, salirButton);
        bottomButtonsBox.setAlignment(Pos.CENTER);
        bottomButtonsBox.setPadding(new Insets(20));




        root.setCenter(boxCalcular);
        root.setLeft(boxTrabajadores);
        root.setRight(boxMateriales);
        root.setBottom(bottomButtonsBox);
        BorderPane.setAlignment(bottomButtonsBox, Pos.CENTER);

        tituloMenuPrincipal.getChildren().addAll(titleLabel);
        tituloMenuPrincipal.setAlignment(Pos.TOP_CENTER);
        root.setTop(tituloMenuPrincipal);

        return new Scene(root, 1280, 720);
    }

    private void initializeJobs() {
        admin.addJob(new Job("Jefe de obra", 12000));
        admin.addJob(new Job("Albañil", 7500));
        admin.addJob(new Job("Soldador", 7000));
        admin.addJob(new Job("Electricista", 8000));
    }

    private void workerMenu() {
        WorkerMenu menu = new WorkerMenu(primaryStage, admin);
        Scene workerMenu = menu.createWorkerMenu();
        primaryStage.setScene(workerMenu);

    }

    private void materialMenu() {
        // Implementa el menú de materiales aquí
    }

    private void calSalary() {

    }
}
