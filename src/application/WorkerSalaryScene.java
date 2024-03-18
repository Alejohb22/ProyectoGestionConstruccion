package application;

import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import logic.Admin;
import logic.Utilidades;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class WorkerSalaryScene {

    private Stage primaryStage;
    private Admin admin;
    private boolean casoUno;

    private Scene previousScene;

    public WorkerSalaryScene(Stage primaryStage, Admin admin, Scene previousScene) {
        this.primaryStage = primaryStage;
        this.admin = admin;
        this.previousScene = previousScene;
    }

    public Scene createScene() {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color:#2F3C45");

        VBox boxInfo = new VBox(); // Aquí puedes agregar cualquier contenido adicional
        root.setCenter(boxInfo); // Agregar el VBox al centro del BorderPane

        Button backButton = new Button("Volver");
        backButton.setStyle("-fx-font-size: 18px; -fx-background-color: #354F53; -fx-text-fill: white;-fx-font-family: Algerian");
        backButton.setMinWidth(100);

        HBox bottomButtonsBox = new HBox(20);
        bottomButtonsBox.setEffect(new DropShadow());
        bottomButtonsBox.getChildren().addAll(backButton);
        bottomButtonsBox.setSpacing(20);

        root.setBottom(bottomButtonsBox); // Mantener el botón de atrás en la parte inferior

        backButton.setOnAction(e -> {
            // Lógica para volver a la pantalla anterior
        });

        backButton.setOnAction(e -> {
            primaryStage.setScene(previousScene); // Volvemos a la escena anterior
        });

        // Llamar al método calcWorkerSalary() para mostrar los diálogos de cálculo de salario
        calcWorkerSalary();

        Scene scene = new Scene(root, 1280, 720);
        primaryStage.setScene(scene);



        return scene;
    }


    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public boolean calcWorkerSalary() {
        boolean otroTrabajador = false;
        int opc2 = 0;

        if (!casoUno) {
            showAlert("Debe primero crear trabajadores en la opción 1, para ingresar a esta opción");
            return false;
        }

        Utilidades utilidades = new Utilidades();

        while (!otroTrabajador) {
            String idTrabajador = "";
            showAlert("Digite el ID del trabajador:");
            Optional<String> result = showTextInputDialog("Digite el ID del trabajador:");
            if (result.isPresent()) {
                idTrabajador = result.get();
            } else {
                return true;
            }

            int pos = utilidades.buscarSecuencial(admin.getListWorker(), idTrabajador);
            if (pos != -1) {
                showAlert("Ingrese la cantidad de horas trabajadas por el " + admin.getListWorker().get(pos).getJob().getName() + " " + admin.getListWorker().get(pos).getName() + ":");
                Optional<String> horasResult = showTextInputDialog("Ingrese la cantidad de horas trabajadas por el " + admin.getListWorker().get(pos).getJob().getName() + " " + admin.getListWorker().get(pos).getName() + ":");
                if (horasResult.isPresent()) {
                    int numHorasTrabajadas;
                    try {
                        numHorasTrabajadas = Integer.parseInt(horasResult.get());
                        if (numHorasTrabajadas >= 0) {
                            // Corregir este método según corresponda en tu aplicación
                            //double salario = admin.getListWorker().get(pos).calcularSalary(numHorasTrabajadas);
                            //showAlert("El salario del trabajador es $" + salario);
                        } else {
                            showAlert("No se deben ingresar números negativos");
                        }
                    } catch (NumberFormatException e) {
                        showAlert("Debe ingresar números para la cantidad de horas trabajadas");
                    }
                } else {
                    return true;
                }
            } else {
                showAlert("Código no registrado");
            }

            opc2 = showConfirmationDialog("¿Desea calcular el salario de otro trabajador?", "Si", "No");
            if (opc2 == 2) {
                otroTrabajador = true;
            }
        }
        return true;
    }

    private Optional<String> showTextInputDialog(String message) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Ingresar Datos");
        dialog.setHeaderText(null);
        dialog.setContentText(message);
        return dialog.showAndWait();
    }

    private int showConfirmationDialog(String message, String option1, String option2) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmación");
        alert.setHeaderText(null);
        alert.setContentText(message);

        // Crear la lista de botones
        ButtonType buttonType1 = new ButtonType(option1);
        ButtonType buttonType2 = new ButtonType(option2);
        List<ButtonType> buttonTypes = Arrays.asList(buttonType1, buttonType2);

        // Establecer todos los tipos de botón en el diálogo de alerta
        alert.getButtonTypes().setAll(buttonTypes);

        // Mostrar el diálogo de alerta y esperar a que el usuario haga clic en un botón
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent()) {
            if (result.get() == buttonType1) {
                return 1;
            } else if (result.get() == buttonType2) {
                return 2;
            }
        }
        return 0; // Otra opción
    }
}
