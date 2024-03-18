package application;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import logic.Admin;

public class MaterialsMenu {
    private Stage primaryStage;
    private Admin admin;
    private Scene previousScene;

    public MaterialsMenu(Stage primaryStage, Admin admin) {
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
        Label TittleMaterials=new Label("MENU MATERIALES");
        TittleMaterials.setStyle("-fx-font-size: 50px; -fx-font-weight: bold;-fx-text-fill: #CAD2C5; -fx-font-family: Algerian;-fx-alignment: center");
        root.setTop(tituloMenuPrincipalMenutrab);
        tituloMenuPrincipalMenutrab.getChildren().addAll(TittleMaterials);

        VBox botones = new VBox(10);
        Button botonCM = new Button(" Crear Materiales");
       // botonCT.setOnAction(e -> crearTrabajadores());


        Button botonEM=new Button("Eliminar Materiales");
        //botonCCarT.setOnAction(e -> crearRoles());

        Button botonMT=new Button("Mostrar Materiales");
        //botonMIT.setOnAction(e -> obtenerInformacionTrabajadores());



        Button botonVolver=new Button("Volver");
        botonVolver.setOnAction(e -> volver());

        botonCM.setMinWidth(170);
        botonCM.setMinHeight(120);
        botonMT.setMinWidth(170);
        botonMT.setMinHeight(120);
        botonEM.setMinWidth(170);
        botonEM.setMinHeight(120);

        botonVolver.setMinWidth(170);
        botonVolver.setMinHeight(120);
        botones.getChildren().addAll(botonCM,botonMT,botonEM,botonVolver);
        root.setLeft(botones);

        Scene scene = new Scene(root, 1280, 720);
        primaryStage.setScene(scene);
        //primaryStage.setMaximized(true);



        return scene;
    }

    private void volver(){
        Scene previousScene = primaryStage.getScene(); // Store the current scene
        ConsoleMenu menu = new ConsoleMenu(primaryStage,admin,previousScene);
        Scene getMenu = menu.createMenu();
        primaryStage.setScene(getMenu);
    }
}
