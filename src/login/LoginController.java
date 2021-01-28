package login;

import java.io.File;
import java.util.HashMap;
import java.util.ArrayList;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import utilities.Database;
import utilities.FormValidation;
import receptionist.Receptionist;
import admin.Admin;
import doctor.Doctor;

public class LoginController {

    private HashMap<String, String> adminCredentials = new HashMap<String, String>();
    private HashMap<String, String> doctorCredentials = new HashMap<String, String>();
    private HashMap<String, String> receptionistCredentials = new HashMap<String, String>();

    @FXML
    private JFXTextField emailField;

    @FXML
    private JFXPasswordField passField;

    @FXML
    void checkLogin(ActionEvent event) {
        String email = emailField.getText();
        String password = passField.getText();
        if (adminCredentials.keySet().contains(email)) {
            loginAdmin(email, password);
        } else if (doctorCredentials.keySet().contains(email)) {
            loginDoctor(email, password);
        } else if (receptionistCredentials.keySet().contains(email)) {
            loginReceptionist(email, password);
        } else {
            FormValidation.showError("Email not found!");
            emailField.setText("");
            passField.setText("");
        }

    }

    private void loginAdmin(String email, String password) {
        if (adminCredentials.get(email).equals(password)) {
            String path = "src/admin/AdminView.fxml";
            Platform.runLater(() -> {
                openForm(path);
            });
            Stage stage = (Stage) emailField.getScene().getWindow();
            stage.close();
            // FormValidation.showInformation("Login Successful!");
        } else {
            FormValidation.showError("Wrong Password!");
        }
    }

    private void loginDoctor(String email, String password) {
        if (doctorCredentials.get(email).equals(password)) {
            String path = "src/doctor/DoctorView.fxml";
            Platform.runLater(() -> {
                openForm(path);
            });
            Stage stage = (Stage) emailField.getScene().getWindow();
            stage.close();
            // FormValidation.showInformation("Login Successful!");
        } else {
            FormValidation.showError("Wrong Password!");
        }
    }

    private void loginReceptionist(String email, String password) {
        if (receptionistCredentials.get(email).equals(password)) {
            String path = "src/receptionist/ReceptionistView.fxml";
            Platform.runLater(() -> {
                openForm(path);
            });
            Stage stage = (Stage) emailField.getScene().getWindow();
            stage.close();
            // FormValidation.showInformation("Login Successful!");
        } else {
            FormValidation.showError("Wrong Password!");
        }
    }

    private void openForm(String path) {
        Parent root;
        try {
            root = new FXMLLoader(new File(path).toURI().toURL()).load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.getIcons().add(new Image("/style/hospital.png"));
            stage.setTitle("Hospital Management System");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        // Admin's emails
        // Doctor's emails
        // Receptionist's emails

        Database db = new Database();
        ArrayList<Admin> admins = db.getAdmins();
        ArrayList<Doctor> doctors = db.getDoctors();
        ArrayList<Receptionist> receptionists = db.getReceptionists();

        for (Admin a : admins) {
            adminCredentials.put(a.getEmail(), a.getPassword());
        }
        for (Doctor d : doctors) {
            doctorCredentials.put(d.getEmail(), d.getPassword());
        }
        for (Receptionist r : receptionists) {
            receptionistCredentials.put(r.getEmail(), r.getPassword());
        }
    }
}
