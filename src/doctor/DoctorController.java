package doctor;

import java.io.*;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;

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
import utilities.Database;
import utilities.FormValidation;

public class DoctorController {
    int index;
    Database db = new Database();
    Patient current = null;
    ObservableList<Patient> list = FXCollections.observableArrayList(db.getUncheckedPatients());
    ObservableList<String> listOfNames = FXCollections.observableArrayList();

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

    @FXML // fx:id="bedRequiredCheckBox"
    private JFXCheckBox bedRequiredCheckBox; // Value injected by FXMLLoader

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
            current.setPrescription(patPrescription.getText());
        }
        writeFile();
        ProcessBuilder pb = new ProcessBuilder("Notepad.exe", "prescription.txt");
        try {
            pb.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        saveBtn.setDisable(false);
    }

    private void writeFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("prescription.txt"))) {
            writer.write("================================= Medical Report =================================\n\n");
            writer.write("Patient ID: " + current.getId() + "\n");
            writer.write("Name:       " + current.getName() + "\n");
            writer.write("Age:        " + current.getAge() + "\n");
            writer.write("Phone No:   " + current.getPhoneNo() + "\n");
            writer.write("Address:    " + current.getAddress() + "\n\n");
            writer.write("==================================== Symptoms ====================================\n\n");
            writer.write(current.getSymptoms() + "\n\n");
            writer.write("================================== Prescription ==================================\n\n");
            writer.write(current.getPrescription() + "\n\n");
            writer.write("==================================================================================\n\n");
            if (bedRequiredCheckBox.isSelected()) {
                writer.write("--> Requires bed\n");
                writer.write("==================================================================================\n\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void saveRecord(ActionEvent event) {
        System.out.println(patComboBox.getValue());
        if (bedRequiredCheckBox.isSelected()) {
            int numBeds = db.getBeds();
            if (db.getTotalBeds() <= numBeds) {
                db.setBeds(numBeds - 1);
            } else {
                FormValidation.showError("Limited Resources", "No more beds can be alotted.");
            }
        }
        if (index > 0) {
            current.setChecked(true);
            current.setName(patName.getText());
            current.setAge(Integer.parseInt(patAge.getText()));
            current.setSymptoms(patSymptoms.getText());
            current.setPrescription(patPrescription.getText());
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
        bedRequiredCheckBox.setSelected(false);
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
        bedRequiredCheckBox.setDisable(false);
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
            bedRequiredCheckBox.setSelected(false);
            patName.setDisable(true);
            patAge.setDisable(true);
            patSymptoms.setDisable(true);
            patPrescription.setDisable(true);
            bedRequiredCheckBox.setDisable(true);
        }
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        saveBtn.setDisable(true);
        patName.setDisable(true);
        patAge.setDisable(true);
        patSymptoms.setDisable(true);
        patPrescription.setDisable(true);
        bedRequiredCheckBox.setDisable(true);
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
        if (!(medicine.getText().isEmpty() && duration.getText().isEmpty())){
            patPrescription.appendText(medicine.getText() + "\t\t" + duration.getText() + "\n");
        }
        medicine.setText("");
        duration.setText("");
    }

}
