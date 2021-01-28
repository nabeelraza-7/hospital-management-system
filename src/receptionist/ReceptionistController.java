package receptionist;

import java.io.BufferedWriter;

// Methods to create, read, update, delete Patient records
// Change the number of beds
// Methods to create, read, update, delete Appointment records

import java.io.File;
import java.io.FileWriter;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import patient.Patient;
import utilities.Database;
import utilities.FormValidation;

public class ReceptionistController {

    @FXML
    private Spinner<Integer> totalBedsSpinner;

    @FXML
    private Spinner<Integer> bedsSpinner;

    @FXML
    private Label totalBeds;

    @FXML
    private Label bedsOccupied;

    @FXML
    private Label bedsRemaining;

    Database db = new Database();

    ObservableList<Patient> patList = FXCollections.observableArrayList(db.getPatients());
    ObservableList<Patient> patSearchList = FXCollections.observableArrayList();
    // Declaring patient Tab's required fields
    // Declaring main patient's table fields
    @FXML
    private TableView<Patient> patTable;

    @FXML
    private TableColumn<Patient, String> patName;

    @FXML
    private TableColumn<Patient, Integer> patAge;

    @FXML
    private TableColumn<Patient, String> patPhoneNo;

    @FXML
    private TableColumn<Patient, String> patAddress;

    @FXML
    private TableColumn<Patient, String> patSymptoms;

    @FXML
    private TableColumn<Patient, Double> patBill;

    @FXML
    private TableColumn<Patient, Boolean> patChecked;
    // END declaring main patient's table fields
    // declaring patient's search field
    @FXML
    private TextField patSearchField;
    // Declaring patients search table's required fields
    @FXML
    private TableView<Patient> patSearchTable;

    @FXML
    private TableColumn<Patient, String> patSearchName;

    @FXML
    private TableColumn<Patient, Integer> patSearchAge;

    @FXML
    private TableColumn<Patient, String> patSearchPhoneNo;

    @FXML
    private TableColumn<Patient, String> patSearchAddress;

    @FXML
    private TableColumn<Patient, String> patSearchSymptoms;

    @FXML
    private TableColumn<Patient, Double> patSearchBill;

    @FXML
    private TableColumn<Patient, Boolean> patSearchChecked;
    // end declaring patient search table's required field

    @FXML
    void openPatientForm(ActionEvent event) {
        String path = "src/patient/PatientForm.fxml";
        Platform.runLater(() -> {
            openForm(path);
        });
    }

    @FXML
    void createAppointment(ActionEvent event) {
        String path = "src/appointments/Appointments.fxml";
        Platform.runLater(() -> {
            openForm(path);
        });
    }

    // general method to open a form.
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

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        totalBeds.setText("" + db.getTotalBeds());
        bedsOccupied.setText("" + (db.getTotalBeds() - db.getBeds()));
        bedsRemaining.setText("" + db.getBeds());
        initializePatientStuff();
        bedsSpinner.setValueFactory(
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE, db.getBeds()));
        totalBedsSpinner.setValueFactory(
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE, db.getTotalBeds()));

    }

    private void initializePatientStuff() {
        patName.setCellValueFactory(new PropertyValueFactory<Patient, String>("name"));
        patAge.setCellValueFactory(new PropertyValueFactory<Patient, Integer>("age"));
        patPhoneNo.setCellValueFactory(new PropertyValueFactory<Patient, String>("phoneNo"));
        patAddress.setCellValueFactory(new PropertyValueFactory<Patient, String>("address"));
        patSymptoms.setCellValueFactory(new PropertyValueFactory<Patient, String>("symptoms"));
        patBill.setCellValueFactory(new PropertyValueFactory<Patient, Double>("bill"));
        patChecked.setCellValueFactory(new PropertyValueFactory<Patient, Boolean>("checked"));

        patSearchName.setCellValueFactory(new PropertyValueFactory<Patient, String>("name"));
        patSearchAge.setCellValueFactory(new PropertyValueFactory<Patient, Integer>("age"));
        patSearchPhoneNo.setCellValueFactory(new PropertyValueFactory<Patient, String>("phoneNo"));
        patSearchAddress.setCellValueFactory(new PropertyValueFactory<Patient, String>("address"));
        patSearchSymptoms.setCellValueFactory(new PropertyValueFactory<Patient, String>("symptoms"));
        patSearchBill.setCellValueFactory(new PropertyValueFactory<Patient, Double>("bill"));
        patSearchChecked.setCellValueFactory(new PropertyValueFactory<Patient, Boolean>("checked"));

        patTable.setItems(patList);
        patSearchTable.setItems(patSearchList);
    }

    // Patient's Tab actions
    @FXML
    void deletePatRecord(ActionEvent event) {
        if (FormValidation.showConfirmationMessage("Are you sure you want to delete?")) {
            Patient temp = patTable.getSelectionModel().getSelectedItem();
            Patient tempSearch = patSearchTable.getSelectionModel().getSelectedItem();
            if (temp == null && tempSearch == null)
                return;
            if (temp == null && tempSearch != null) {
                temp = tempSearch;
                db.deletePatient(tempSearch.getId());
                patSearchList.remove(patSearchTable.getSelectionModel().getSelectedIndex());
                patSearchTable.getSelectionModel().clearSelection();
            }
            if (temp != null) {
                db.deletePatient(temp.getId());
                patList.remove(patTable.getSelectionModel().getSelectedIndex());
                patTable.getSelectionModel().clearSelection();
            }
        }
    }

    @FXML
    void editPatRecord(ActionEvent event) {
        Patient temp = patTable.getSelectionModel().getSelectedItem();
        Patient tempSearch = patSearchTable.getSelectionModel().getSelectedItem();
        if (temp == null && tempSearch == null)
            return;
        if (temp == null && tempSearch != null) {
            temp = tempSearch;
        }
        temp.writeToFile();
        String path = "src/patient/EditPatientForm.fxml";
        Platform.runLater(() -> {
            openForm(path);
        });
    }

    @FXML
    void refreshPat(ActionEvent event) {
        patList.clear();
        patSearchList.clear();
        // docList = FXCollections.observableArrayList(db.getDoctors());
        for (Patient p : db.getPatients()) {
            patList.add(p);
        }
    }

    @FXML
    void searchPat(ActionEvent event) {
        String searchText = patSearchField.getText();
        patSearchList.clear();
        if (searchText.equals("")) {
            patSearchList.clear();
        } else {
            boolean shouldAdd = false;
            for (Patient p : patList) {
                if (p.getName().contains(searchText)) {
                    shouldAdd = true;
                }
                if (p.getAddress().contains(searchText)) {
                    shouldAdd = true;
                }
                if (p.getSymptoms().contains(searchText)) {
                    shouldAdd = true;
                }
                if (p.getPhoneNo().contains(searchText)) {
                    shouldAdd = true;
                }
                if (shouldAdd) {
                    patSearchList.add(p);
                    shouldAdd = false;
                }
            }
        }
    }

    @FXML
    void openPrescription(ActionEvent event) {
        Patient temp = patTable.getSelectionModel().getSelectedItem();
        Patient tempSearch = patSearchTable.getSelectionModel().getSelectedItem();
        if (temp == null && tempSearch == null)
            return;
        if (temp == null && tempSearch != null) {
            temp = tempSearch;
        }
        writeFile(temp);
        ProcessBuilder pb = new ProcessBuilder("Notepad.exe", "prescription.txt");
        try {
            pb.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void writeFile(Patient temp) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("prescription.txt"))) {
            writer.write("================================= Medical Report =================================\n\n");
            writer.write("Patient ID: " + temp.getId() + "\n");
            writer.write("Name:       " + temp.getName() + "\n");
            writer.write("Age:        " + temp.getAge() + "\n");
            writer.write("Phone No:   " + temp.getPhoneNo() + "\n");
            writer.write("Address:    " + temp.getAddress() + "\n\n");
            writer.write("==================================== Symptoms ====================================\n\n");
            writer.write(temp.getSymptoms() + "\n\n");
            writer.write("================================== Prescription ==================================\n\n");
            writer.write(temp.getPrescription() + "\n\n");
            writer.write("==================================================================================\n\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Ending Patient's Tab actions
    // beds and stuff
    @FXML
    void changeBedNumbers(ActionEvent event) {
        db.setBeds(bedsSpinner.getValue());
        bedsOccupied.setText("" + (db.getTotalBeds() - db.getBeds()));
        bedsRemaining.setText("" + db.getBeds());
    }

    @FXML
    void changeTotalBedNumbers(ActionEvent event) {
        db.setTotalBeds(totalBedsSpinner.getValue());
        totalBeds.setText("" + db.getTotalBeds());
        bedsOccupied.setText("" + (db.getTotalBeds() - db.getBeds()));
        bedsRemaining.setText("" + db.getBeds());
    }
}
