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

public class Main extends Application {
    private Admin admin = new Admin();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        admin.addJob(new Job("Jefe de obrx", 12000));
        admin.addJob(new Job("Albañil", 7500));
        admin.addJob(new Job("Soldador", 7000));
        admin.addJob(new Job("Electricista", 8000));

        BorderPane root = new BorderPane();

        root.setStyle("-fx-background-color: #0a2057");

        Scene scene = new Scene(root, 900, 500);

        Label titleLabel = new Label("Gestión de Obras de Construcción");
        titleLabel.setStyle("-fx-font-size: 50px; -fx-font-weight: bold;-fx-text-fill: white; -fx-font-family: Algerian");

        VBox tituloMenuPrincipal = new VBox(20);
        tituloMenuPrincipal.setPadding(new Insets(50, 0, 0, 0));
        tituloMenuPrincipal.setStyle("-fx-background-color: #0a2057;");//color fondo titulo

        VBox boxCalcular = new VBox(20);
        boxCalcular.setPadding(new Insets(50));
        boxCalcular.setStyle("-fx-background-color: #192b55; -fx-background-radius: 10;"); //color fondo botones y bordes
        boxCalcular.setEffect(new DropShadow());

        Image imageTrabajadores = new Image(getClass().getResourceAsStream("/resources/trabajadores.gif"));
        ImageView imageViewTrabajadores = new ImageView(imageTrabajadores);
        Button trabajadoresButton = new Button("Trabajadores", imageViewTrabajadores);
        trabajadoresButton.setStyle("-fx-font-size: 18px; -fx-background-color: #5588ff; -fx-text-fill: white; -fx-font-family: Algerian");
        trabajadoresButton.setMinWidth(200);
        trabajadoresButton.setAlignment(Pos.CENTER);
        trabajadoresButton.setContentDisplay(ContentDisplay.TOP);
        trabajadoresButton.setPadding(new Insets(10));
        trabajadoresButton.setOnAction(e -> workerMenu());

        VBox boxTrabajadores = new VBox();
        boxTrabajadores.setPadding(new Insets(50, 0, 0, 0));
        boxTrabajadores.setStyle("-fx-background-color: #192b55; -fx-background-radius: 10;"); //color fondo botones y bordes
        boxTrabajadores.setEffect(new DropShadow());
        boxTrabajadores.getChildren().addAll(trabajadoresButton);
        boxTrabajadores.setAlignment(Pos.CENTER_LEFT);

        Image imageMateriales = new Image(getClass().getResourceAsStream("/resources/gif materiales.gif"));
        ImageView imageViewMateriales = new ImageView(imageMateriales);
        Button materialesButton = new Button("Materiales", imageViewMateriales);
        materialesButton.setStyle("-fx-font-size: 18px; -fx-background-color: #5588ff; -fx-text-fill: white; -fx-font-family: Algerian");
        materialesButton.setMinWidth(200);
        materialesButton.setAlignment(Pos.CENTER);
        materialesButton.setContentDisplay(ContentDisplay.TOP);
        materialesButton.setPadding(new Insets(10));
        materialesButton.setOnAction(e -> materialMenu());

        VBox boxMateriales = new VBox();
        boxMateriales.setPadding(new Insets(50, 0, 0, 0));
        boxMateriales.setStyle("-fx-background-color: #192b55; -fx-background-radius: 10;"); //color fondo botones y bordes
        boxMateriales.setEffect(new DropShadow());
        boxMateriales.getChildren().addAll(materialesButton);
        boxMateriales.setAlignment(Pos.CENTER_RIGHT);

        Button calcularButton = new Button("Calcular gasto del dia");
        calcularButton.setStyle("-fx-font-size: 18px; -fx-background-color: #5588ff; -fx-text-fill: white;-fx-font-family: Algerian");

        calcularButton.setMinWidth(400);
        calcularButton.setAlignment(Pos.CENTER);
        calcularButton.setOnAction(e -> calSalary());

        Button salirButton = new Button("Salir");
        salirButton.setStyle("-fx-font-size: 18px; -fx-background-color: #5588ff; -fx-text-fill: white;-fx-font-family: Algerian");
        salirButton.setMinWidth(100);
        salirButton.setAlignment(Pos.CENTER);
        salirButton.setOnAction(e -> primaryStage.close());

        HBox bottomButtonsBox = new HBox(20);
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

        primaryStage.setMaximized(true);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Gestión de Obras de Construcción");
        primaryStage.show();
    }

    private void workerMenu() {
        // Implementa el menú de trabajadores aquí
    BorderPane rootT=new BorderPane();
    rootT.setStyle("-fx-background-color: White");
    Scene scene1=new Scene(rootT,900,500);
    Label titlleTra=new Label("GESTION DE TRABAJADORES");
    titlleTra.setStyle("-fx-font-size: 50px;-fx-font-weight: bold;-fx-text-fill: white; -fx-font-family: Algerian");



    }

    private void materialMenu() {
        // Implementa el menú de materiales aquí
    }

    private void calSalary() {
        // Implementa el cálculo del precio total de la obra aquí
    }
}
