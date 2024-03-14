package application;



import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
        admin.addJob(new Job("Jefe de obra", 12000));
        admin.addJob(new Job("Albañil", 7500));
        admin.addJob(new Job("Soldador", 7000));
        admin.addJob(new Job("Electricista", 8000));

        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 800, 600);

        Label titleLabel = new Label("Gestión de Obras de Construcción");
        titleLabel.setStyle("-fx-font-size: 50px; -fx-font-weight: bold;");

        VBox tituloMenuPrincipal = new VBox(20);
        tituloMenuPrincipal.setPadding(new Insets(50,0,0,0));



        VBox menuVBox = new VBox(20);
        menuVBox.setPadding(new Insets(50, 0, 0, 0));
        menuVBox.setStyle("-fx-background-color: #f4f4f4;");



        Button trabajadoresButton = new Button("Trabajadores");
        trabajadoresButton.setStyle("-fx-font-size: 18px;");
        trabajadoresButton.setMinWidth(200);
        trabajadoresButton.setOnAction(e -> workerMenu());

        Button materialesButton = new Button("Materiales");
        materialesButton.setStyle("-fx-font-size: 18px;");
        materialesButton.setMinWidth(200);
        materialesButton.setOnAction(e -> materialMenu());

        Button calcularButton = new Button("Calcular precio total de la obra para el día de hoy");
        calcularButton.setStyle("-fx-font-size: 18px;");
        calcularButton.setMinWidth(200);
        calcularButton.setOnAction(e -> calSalary());

        Button salirButton = new Button("Salir");
        salirButton.setStyle("-fx-font-size: 18px;");
        salirButton.setMinWidth(200);
        salirButton.setOnAction(e -> primaryStage.close());

        menuVBox.getChildren().addAll( trabajadoresButton, materialesButton, calcularButton, salirButton);
        menuVBox.setAlignment(Pos.CENTER);
        root.setCenter(menuVBox);

        tituloMenuPrincipal.getChildren().addAll(titleLabel);
        tituloMenuPrincipal.setAlignment(Pos.TOP_CENTER);
        root.setTop(tituloMenuPrincipal);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Gestión de Obras de Construcción");
        primaryStage.show();
    }

    private void workerMenu() {
        // Implementa el menú de trabajadores aquí
    }

    private void materialMenu() {
        // Implementa el menú de materiales aquí
    }

    private void calSalary() {
        // Implementa el cálculo del precio total de la obra aquí
    }
}
