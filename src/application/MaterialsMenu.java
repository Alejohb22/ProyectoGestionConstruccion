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

    public MaterialsMenu(Stage primaryStage, Admin admin) {
        this.primaryStage = primaryStage;
        this.admin = admin;
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
        Button botonCCarT=new Button("Crear Roles para Trabajadores");
        Button botonMIT=new Button("Mostrar la informacion de los trabajadores");
        Button botonCSalT=new Button("Calcular salario de los trabajadores");
        Button botonVolver=new Button("Volver");
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


        return new Scene(root, 300, 200);


    }
}
