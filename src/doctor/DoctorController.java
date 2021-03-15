package doctor;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import com.jfoenix.controls.JFXButton;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import patient.Patient;
import pdf.CreatePDF;
import utilities.Database;
import utilities.FormValidation;

public class DoctorController {
    int index;
    Database db = new Database();
    Patient current = null;
    ObservableList<Patient> list = FXCollections.observableArrayList(db.getUncheckedPatients());
    ObservableList<String> listOfNames = FXCollections.observableArrayList();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-YYYY");
    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm");
    LocalDate date = LocalDate.now();
    LocalTime time = LocalTime.now();

    @FXML // fx:id="patComboBox"
    private ComboBox<String> patComboBox; // Value injected by FXMLLoader

    @FXML // fx:id="patName"
    private TextField patName; // Value injected by FXMLLoader

    @FXML // fx:id="patAge"
    private TextField patAge; // Value injected by FXMLLoader

    @FXML // fx:id="patSymptoms"
    private TextArea patSymptoms; // Value injected by FXMLLoader

    @FXML // fx:id="patPrescription"
    private TextArea patPrescription; // Value injected by FXMLLoader

    @FXML // fx:id="medicine"
    private TextField medicine; // Value injected by FXMLLoader

    @FXML // fx:id="duration"
    private TextField duration; // Value injected by FXMLLoader

    @FXML
    private JFXButton saveBtn;

    @FXML
    void printPrescription(ActionEvent event) {
        if (index != 0) {
            current.setChecked(true);
            current.setName(patName.getText());
            current.setAge(Integer.parseInt(patAge.getText()));
            current.setSymptoms(patSymptoms.getText());
            current.setPrescription(patPrescription.getText() + "\n\nDate & Time:   " + formatter.format(date) + "  " + timeFormatter.format(time));
        }
        CreatePDF.writeToPDF(current);
        ProcessBuilder pb = new ProcessBuilder("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe", "file:///E:/Hospital%20Management%20System/Hospital%20Management%20System/prescription.pdf");
        try {
            pb.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        saveBtn.setDisable(false);
    }

    @FXML
    void saveRecord(ActionEvent event) {
        if (index > 0) {
            current.setChecked(true);
            current.setName(patName.getText());
            current.setAge(Integer.parseInt(patAge.getText()));
            current.setSymptoms(patSymptoms.getText());
            current.setPrescription(patPrescription.getText() + "\n\nDate & Time:   " + formatter.format(date) + "  " + timeFormatter.format(time));
            db.editPatient(current);
            FormValidation.showInformation("Save", "Patient's Record has been saved!");
            list.remove(index - 1);
            listOfNames.remove(index);
            patComboBox.getSelectionModel().selectFirst();
            index = 0;
        }
        patName.setText("");
        patAge.setText("");
        patSymptoms.setText("");
        patPrescription.setText("");
        saveBtn.setDisable(true);
    }

    @FXML
    void refresh(ActionEvent event) {
        patComboBox.getSelectionModel().selectFirst();
        for (int i = 1; i < listOfNames.size(); i++) {
            listOfNames.remove(i);
        }
        list.clear();
        list = FXCollections.observableArrayList(db.getUncheckedPatients());
        list.forEach((items) -> {
            listOfNames.add(items.getName());
        });
        medicine.setText("");
        duration.setText("");
    }

    @FXML
    void startWorking(ActionEvent event) {
        patName.setDisable(false);
        patAge.setDisable(false);
        patSymptoms.setDisable(false);
        patPrescription.setDisable(false);
        index = patComboBox.getSelectionModel().getSelectedIndex();
        if (index != 0) {
            current = list.get(index - 1);
            System.out.println(current);
            patName.setText(current.getName());
            patAge.setText(current.getAge() + "");
            patSymptoms.setText(current.getSymptoms());
        } else {
            patName.setText("");
            patAge.setText("");
            patSymptoms.setText("");
            patPrescription.setText("");
            patName.setDisable(true);
            patAge.setDisable(true);
            patSymptoms.setDisable(true);
            patPrescription.setDisable(true);
        }
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        saveBtn.setDisable(true);
        patName.setDisable(true);
        patAge.setDisable(true);
        patSymptoms.setDisable(true);
        patPrescription.setDisable(true);
        listOfNames.add("Select Patient");
        list.forEach((items) -> {
            listOfNames.add(items.getName());
        });
        patComboBox.setItems(listOfNames);
    }

    @FXML
    void logout(ActionEvent event) {
        String path = "src/login/Login.fxml";
        Platform.runLater(() -> {
            openForm(path);
        });
        Stage stage = (Stage) patComboBox.getScene().getWindow();
        stage.close();
    }

    public void openForm(String path) {
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

    @FXML
    void addToPrescription(ActionEvent event) {
        if (!(medicine.getText().isEmpty() && duration.getText().isEmpty())) {
            patPrescription.appendText(medicine.getText() + "            " + duration.getText() + "\n");
        }
        medicine.setText("");
        duration.setText("");
    }

    @FXML
    void admitPatient(ActionEvent event) {
        if (index > 0) {
            current.setChecked(true);
            current.setName(patName.getText());
            current.setAge(Integer.parseInt(patAge.getText()));
            current.setSymptoms(patSymptoms.getText());
            current.setPrescription(patPrescription.getText() + "\n\nDate & Time:   " + formatter.format(date) + "  " + timeFormatter.format(time));
            db.editPatient(current);
            db.admitPatient(current.getId());
            FormValidation.showInformation("Note", "Patient has been admitted!");
            list.remove(index - 1);
            listOfNames.remove(index);
            patComboBox.getSelectionModel().selectFirst();
            index = 0;
        }
        patName.setText("");
        patAge.setText("");
        patSymptoms.setText("");
        patPrescription.setText("");
        // saveBtn.setDisable(true);
    }

}
