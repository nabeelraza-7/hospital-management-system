package utilities;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;

import appointments.Appointment;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = new FXMLLoader(new File("src/login/Login.fxml").toURI().toURL()).load();
        // Parent root = new FXMLLoader(new
        // File("src/admin/AdminView.fxml").toURI().toURL()).load();
        // Parent root = new FXMLLoader(new
        // File("src/doctor/DoctorView.fxml").toURI().toURL()).load();
        // Parent root = new FXMLLoader(new
        // File("src/receptionist/ReceptionistView.fxml").toURI().toURL()).load();
        // Parent root = new FXMLLoader(new
        // File("src/appointments/Appointments.fxml").toURI().toURL()).load();
        Scene scene = new Scene(root);
        stage.getIcons().add(new Image("/style/hospital.png"));
        stage.setTitle("Hospital Management System");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void init() {
        LocalDate today = LocalDate.now();
        Database db = new Database();
        ArrayList<Appointment> list = db.getAppointments();
        for (Appointment a : list) {
            int cmp = a.getDate().compareTo(today);
            if (cmp < 0) {
                db.deleteAppointment(a);
            }
        }
    }
}