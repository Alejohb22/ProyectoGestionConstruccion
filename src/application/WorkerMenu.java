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
    private Scene previousScene;

    public WorkerMenu(Stage primaryStage, Admin admin) {
        this.primaryStage = primaryStage;
        this.admin = admin;
        this.previousScene = previousScene;
    }

    public Scene createWorkerMenu() {





        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #2F3C45");


        VBox tituloMenuPrincipalMenutrab = new VBox(20);
        tituloMenuPrincipalMenutrab.setPadding(new Insets(50, 0, 0, 0));
        tituloMenuPrincipalMenutrab.setStyle("-fx-background-color: #2F3C45;");//color fondo titulo
        Label TittleWorkers=new Label("MENU TRABAJADORES");
        TittleWorkers.setStyle("-fx-font-size: 50px; -fx-font-weight: bold;-fx-text-fill: #CAD2C5; -fx-font-family: Algerian;-fx-alignment: center");
        root.setTop(tituloMenuPrincipalMenutrab);
        tituloMenuPrincipalMenutrab.getChildren().addAll(TittleWorkers);

        VBox botones = new VBox(10);
        Button botonCT = new Button(" Crear Trabajadores");
        botonCT.setOnAction(e -> crearTrabajadores());


        Button botonCCarT=new Button("Crear Roles para Trabajadores");
        botonCCarT.setOnAction(e -> crearRoles());

        Button botonMIT=new Button("Mostrar la informacion de los trabajadores");
        botonMIT.setOnAction(e -> obtenerInformacionTrabajadores());

        Button botonCSalT=new Button("Calcular salario de los trabajadores");
        botonCSalT.setOnAction(e -> obtenerSalario());


        Button botonVolver=new Button("Volver");
        botonVolver.setOnAction(e -> volver());

        botonCT.setMinWidth(170);
        botonCT.setMinHeight(120);
        botonCCarT.setMinWidth(170);
        botonCCarT.setMinHeight(120);
        botonMIT.setMinWidth(170);
        botonMIT.setMinHeight(120);
        botonCSalT.setMinWidth(170);
        botonCSalT.setMinHeight(120);
        botonVolver.setMinWidth(170);
        botonVolver.setMinHeight(120);
        botones.getChildren().addAll(botonCT,botonCCarT,botonMIT,botonCSalT,botonVolver);
        root.setLeft(botones);

        Scene scene = new Scene(root, 1280, 720);
        primaryStage.setScene(scene);
        //primaryStage.setMaximized(true);



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
            CreateRol crearRoles = new CreateRol(primaryStage, admin, previousScene);
        Scene createRol = crearRoles.createFormRol();
        primaryStage.setScene(createRol);
    }

    private void obtenerInformacionTrabajadores() {
        GetInfoWorkers obtenerInformacion = new GetInfoWorkers(primaryStage, admin, primaryStage.getScene());
        Scene getInfo = obtenerInformacion.showWorkerInfo();
        primaryStage.setScene(getInfo);
        primaryStage.show(); // Asegúrate de llamar a show() después de cambiar la escena
    }

    private void obtenerSalario(){
        Scene previousScene = primaryStage.getScene(); // Store the current scene
        WorkerSalaryScene obtenerSalario = new WorkerSalaryScene(primaryStage,admin, previousScene);
        Scene getSalary = obtenerSalario.createScene();
        primaryStage.setScene(getSalary);

    }

    private void volver(){
        Scene previousScene = primaryStage.getScene(); // Store the current scene
        ConsoleMenu menu = new ConsoleMenu(primaryStage,admin,previousScene);
        Scene getMenu = menu.createMenu();
        primaryStage.setScene(getMenu);
    }
}
