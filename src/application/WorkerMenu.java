package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import logic.Admin;
import logic.Controller;

public class WorkerMenu {
    private Stage primaryStage;
    private Admin admin;
    private Scene previousScene;

    private Controller controller;

    public WorkerMenu(Stage primaryStage, Admin admin) {
        this.primaryStage = primaryStage;
        this.admin = admin;
        this.previousScene = previousScene;
    }

    public Scene createWorkerMenu() {

        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #2F3C45");


        VBox tituloMenuPrincipalMenutrab = new VBox(20);
        tituloMenuPrincipalMenutrab.setAlignment(Pos.CENTER);

        VBox Texto=new VBox(20);
        tituloMenuPrincipalMenutrab.setPadding(new Insets(50, 0, 0, 0));
        tituloMenuPrincipalMenutrab.setStyle("-fx-background-color: #2F3C45;");//color fondo titulo
        Label TittleWorkers=new Label("MENU TRABAJADORES");
        TittleWorkers.setStyle("-fx-font-size: 50px; -fx-font-weight: bold;-fx-text-fill: #CAD2C5; -fx-font-family: sans-serif;-fx-alignment: center");
        root.setTop(tituloMenuPrincipalMenutrab);
        tituloMenuPrincipalMenutrab.getChildren().addAll(TittleWorkers);

        VBox botones = new VBox(10);
        botones.setPadding(new Insets(60));
        botones.setAlignment(Pos.CENTER);
        //botones.setPadding(new Insets(60,60,60,60));

        Button botonCT = new Button(" Crear Trabajadores");
        botonCT.setStyle("-fx-background-color: #CAD2C5;");
        botonCT.setOnMouseEntered(e -> botonCT.setStyle("-fx-background-color: #AAB6A2;"));
        botonCT.setOnMouseExited(e -> botonCT.setStyle("-fx-background-color: #CAD2C5;"));
        botonCT.setOnAction(e -> crearTrabajadores());

        //Botones

        Button botonCCarT=new Button("Crear Roles para Trabajadores");
        botonCCarT.setStyle("-fx-background-color: #CAD2C5");
        botonCCarT.setOnMouseEntered(e -> botonCCarT.setStyle("-fx-background-color: #AAB6A2;"));
        botonCCarT.setOnMouseExited(e -> botonCCarT.setStyle("-fx-background-color: #CAD2C5;"));

        botonCCarT.setOnAction(e -> crearRoles());

        Button botonMIT=new Button("Mostrar la informacion de los trabajadores");
        botonMIT.setStyle("-fx-background-color: #CAD2C5");
        botonMIT.setOnMouseEntered(e -> botonMIT.setStyle("-fx-background-color: #AAB6A2;"));
        botonMIT.setOnMouseExited(e -> botonMIT.setStyle("-fx-background-color: #CAD2C5;"));
        botonMIT.setOnAction(e -> obtenerInformacionTrabajadores());

        Button botonCSalT=new Button("Calcular salario de los trabajadores");
        botonCSalT.setStyle("-fx-background-color: #CAD2C5");
        botonCSalT.setOnMouseEntered(e -> botonCSalT.setStyle("-fx-background-color: #AAB6A2;"));
        botonCSalT.setOnMouseExited(e -> botonCSalT.setStyle("-fx-background-color: #CAD2C5;"));
        botonCSalT.setOnAction(e -> obtenerSalario());


        Button botonVolver=new Button("Volver");
        botonVolver.setStyle("-fx-background-color: #CAD2C5");
        botonVolver.setOnMouseEntered(e -> botonVolver.setStyle("-fx-background-color: #AAB6A2;"));
        botonVolver.setOnMouseExited(e -> botonVolver.setStyle("-fx-background-color: #CAD2C5;"));
        botonVolver.setOnAction(e -> volver());

        botonCT.setMinWidth(600);
        botonCT.setMinHeight(80);
        botonCCarT.setMinWidth(600);
        botonCCarT.setMinHeight(80);
        botonMIT.setMinWidth(600);
        botonMIT.setMinHeight(80);
        botonCSalT.setMinWidth(600);
        botonCSalT.setMinHeight(80);
        botonVolver.setMinWidth(600);
        botonVolver.setMinHeight(80);

        botones.getChildren().addAll(botonCT,botonCCarT,botonMIT,botonCSalT,botonVolver);

        root.setCenter(botones);
        Scene scene = new Scene(root, 1280, 720);
        primaryStage.setScene(scene);



        return scene;

    }

    private void crearTrabajadores(){
        Scene previousScene = primaryStage.getScene(); // Store the current scene
        WorkerForm workerForm = new WorkerForm(primaryStage, admin, previousScene);

        Scene workerForm1m = workerForm.createCreateWorkerMenu();
        primaryStage.setScene(workerForm1m);
    }

    private void crearRoles(){
        Scene previousScene = primaryStage.getScene(); // Store the current scene
        JobCreator crearRoles = new JobCreator(primaryStage, admin, previousScene);
        Scene createRol = crearRoles.createJobScene();
        primaryStage.setScene(createRol);
    }

    private void obtenerInformacionTrabajadores() {
        GetInfoWorkers obtenerInformacion = new GetInfoWorkers(primaryStage, admin, primaryStage.getScene());
        Scene getInfo = obtenerInformacion.showWorkerInfo();
        primaryStage.setScene(getInfo);
        primaryStage.show(); // Asegúrate de llamar a show() después de cambiar la escena
    }

    private void obtenerSalario(){
        Controller controller = new Controller(); // Crear una instancia de Controller
        Scene previousScene = primaryStage.getScene(); // Store the current scene
        WorkerSalaryCalculator obtenerSalario = new WorkerSalaryCalculator(primaryStage,controller, previousScene);
        Scene getSalary = obtenerSalario.createSalaryCalculatorScene();
        primaryStage.setScene(getSalary);

    }

    private void volver(){
        Scene previousScene = primaryStage.getScene(); // Store the current scene
        ConsoleMenu menu = new ConsoleMenu(primaryStage,admin,previousScene);
        Scene getMenu = menu.createMenu();
        primaryStage.setScene(getMenu);
    }
}
