package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import logic.Admin;
import persistence.Controller;

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
        tituloMenuPrincipalMenutrab.setAlignment(Pos.CENTER);
        tituloMenuPrincipalMenutrab.setPadding(new Insets(80));

        tituloMenuPrincipalMenutrab.setPadding(new Insets(50, 0, 0, 0));
        tituloMenuPrincipalMenutrab.setStyle("-fx-background-color: #2F3C45;");//color fondo titulo
        Label TittleMaterials=new Label("MENU MATERIALES");
        TittleMaterials.setStyle("-fx-font-size: 50px; -fx-font-weight: bold;-fx-text-fill: #CAD2C5; -fx-font-family: sans-serif;-fx-alignment: center");
        root.setTop(tituloMenuPrincipalMenutrab);
        tituloMenuPrincipalMenutrab.getChildren().addAll(TittleMaterials);




        VBox botones = new VBox(10);
        botones.setPadding(new Insets(60));
        botones.setAlignment(Pos.CENTER);

        Button botonCM = new Button(" Crear Materiales");
       // botonCT.setOnAction(e -> crearTrabajadores());
        botonCM.setOnMouseEntered(e -> botonCM.setStyle("-fx-background-color: #AAB6A2;"));
        botonCM.setOnMouseExited(e -> botonCM.setStyle("-fx-background-color: #CAD2C5;"));
        botonCM.setOnAction(e -> crearMaterial());
        botonCM.setStyle("-fx-background-color: #CAD2C5;");


        Button botonEM=new Button("Eliminar Materiales");
        botonEM.setOnMouseEntered(e -> botonEM.setStyle("-fx-background-color: #AAB6A2;"));
        botonEM.setOnMouseExited(e -> botonEM.setStyle("-fx-background-color: #CAD2C5;"));
        botonEM.setOnAction(e -> eliminarMateriales());
        botonEM.setStyle("-fx-background-color: #CAD2C5;");

        Button botonCPM=new Button("Calcular precio materiales");
        botonCPM.setOnMouseEntered(e -> botonCPM.setStyle("-fx-background-color: #AAB6A2;"));
        botonCPM.setOnMouseExited(e -> botonCPM.setStyle("-fx-background-color: #CAD2C5;"));
        botonCPM.setOnAction(e -> calcularPrecioMateriales());
        botonCPM.setStyle("-fx-background-color: #CAD2C5;");



        Button botonVolver=new Button("Volver");
        botonVolver.setOnMouseEntered(e -> botonVolver.setStyle("-fx-background-color: #AAB6A2;"));
        botonVolver.setOnMouseExited(e -> botonVolver.setStyle("-fx-background-color: #CAD2C5;"));
        botonVolver.setOnAction(e -> volver());
        botonVolver.setStyle("-fx-background-color: #CAD2C5;");


        botonCM.setMinWidth(600);
        botonCM.setMinHeight(80);
        botonCPM.setMinWidth(600);
        botonCPM.setMinHeight(80);
        botonEM.setMinWidth(600);
        botonEM.setMinHeight(80);

        botonVolver.setMinWidth(600);
        botonVolver.setMinHeight(80);
        botones.getChildren().addAll(botonCM,botonCPM,botonEM,botonVolver);
        root.setCenter(botones);

        Scene scene = new Scene(root, 1280, 720);
        primaryStage.setScene(scene);
        //primaryStage.setMaximized(true);



        return scene;
    }

    private void crearMaterial() {
        CreateMaterial  createMaterial = new CreateMaterial(primaryStage,admin,previousScene);
        Scene getMenu = createMaterial.createMaterialForm();
        primaryStage.setScene(getMenu);
    }

    private void volver(){
        Scene previousScene = primaryStage.getScene(); // Store the current scene
        ConsoleMenu menu = new ConsoleMenu(primaryStage,admin,previousScene);
        Scene getMenu = menu.createMenu();
        primaryStage.setScene(getMenu);
    }

    private void calcularPrecioMateriales(){
        Controller controller = new Controller(); // Crear una instancia de Controller
        CalculatePriceMenu calculatePriceMenu = new CalculatePriceMenu(primaryStage, controller, primaryStage.getScene());
        Scene calculatePriceScene = calculatePriceMenu.createCalculatePriceMenu();
        primaryStage.setScene(calculatePriceScene);

    }

    private void eliminarMateriales(){
        Controller controller = new Controller(); // Crear una instancia de Controller
        DeleteMaterialsMenu deleteMaterialsMenu = new DeleteMaterialsMenu(primaryStage,  primaryStage.getScene());
        Scene deleteMaterial = deleteMaterialsMenu.createDeleteMaterialsMenu();
        primaryStage.setScene(deleteMaterial);

    }
}
