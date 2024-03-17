package presentation;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import logic.Admin;
import model.Job;
import model.Worker;

public class WorkerForm {

    private Admin admin;

    public WorkerForm(Admin admin) {
        this.admin = admin;
    }

    public VBox createForm() {
        VBox formLayout = new VBox(10);
        formLayout.setStyle("-fx-background-color: lightgray;");
        Label nameLabel = new Label("Nombre:");
        TextField nameField = new TextField();
        Label lastNameLabel = new Label("Apellido:");
        TextField lastNameField = new TextField();
        Label jobLabel = new Label("Cargo:");
        TextField jobField = new TextField();
        Button submitButton = new Button("Crear Trabajador");

        submitButton.setOnAction(e -> {
            String name = nameField.getText();
            String lastName = lastNameField.getText();
            String jobName = jobField.getText();
            if (name.isEmpty() || lastName.isEmpty() || jobName.isEmpty()) {
                // Mostrar mensaje de error
                return;
            }
            Job job = admin.getListJob().stream().filter(j -> j.getName().equalsIgnoreCase(jobName)).findFirst()
                    .orElse(null);
            if (job == null) {
                // Mostrar mensaje de error si el cargo no existe
                return;
            }
            //Worker newWorker = new Worker(name, lastName, job);
            //admin.addWorker(newWorker);
            // Mostrar mensaje de éxito
        });

        formLayout.getChildren().addAll(nameLabel, nameField, lastNameLabel, lastNameField, jobLabel, jobField,
                submitButton);
        return formLayout;
//    }
    }
}
