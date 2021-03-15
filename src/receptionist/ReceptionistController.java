package receptionist;

// Methods to create, read, update, delete Patient records
// Change the number of beds
// Methods to create, read, update, delete Appointment records

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import admin.AdminController;
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
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import patient.AdmittedPatient;
import patient.DischargedPatient;
import patient.Patient;
import pdf.CreatePDF;
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

    ObservableList<AdmittedPatient> adPatList = FXCollections.observableArrayList(db.getAdmittedPatients());
    ObservableList<AdmittedPatient> adPatSearchList = FXCollections.observableArrayList();

    ObservableList<DischargedPatient> disPatList = FXCollections.observableArrayList(db.getDischargedPatients());
    ObservableList<DischargedPatient> disPatSearchList = FXCollections.observableArrayList();
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
    @FXML // fx:id="adPatTable"
    private TableView<AdmittedPatient> adPatTable; // Value injected by FXMLLoader

    @FXML // fx:id="adPatName"
    private TableColumn<AdmittedPatient, String> adPatName; // Value injected by FXMLLoader

    @FXML // fx:id="adPatPhoneNo"
    private TableColumn<AdmittedPatient, String> adPatPhoneNo; // Value injected by FXMLLoader

    @FXML // fx:id="adPatSymptoms"
    private TableColumn<AdmittedPatient, String> adPatSymptoms; // Value injected by FXMLLoader

    @FXML // fx:id="adPatDate"
    private TableColumn<AdmittedPatient, LocalDate> adPatDate; // Value injected by FXMLLoader

    @FXML // fx:id="adPatBedNo"
    private TableColumn<AdmittedPatient, Integer> adPatBedNo; // Value injected by FXMLLoader

    @FXML // fx:id="reason"
    private TextField reason; // Value injected by FXMLLoader

    @FXML // fx:id="adPatSearchField"
    private TextField adPatSearchField; // Value injected by FXMLLoader

    @FXML // fx:id="adPatSearchTable"
    private TableView<AdmittedPatient> adPatSearchTable; // Value injected by FXMLLoader

    @FXML // fx:id="adPatSearchName"
    private TableColumn<AdmittedPatient, String> adPatSearchName; // Value injected by FXMLLoader

    @FXML // fx:id="adPatSearchPhoneNo"
    private TableColumn<AdmittedPatient, String> adPatSearchPhoneNo; // Value injected by FXMLLoader

    @FXML // fx:id="adPatSearchSymptoms"
    private TableColumn<AdmittedPatient, String> adPatSearchSymptoms; // Value injected by FXMLLoader

    @FXML // fx:id="adPatSearchDate"
    private TableColumn<AdmittedPatient, LocalDate> adPatSearchDate; // Value injected by FXMLLoader

    @FXML // fx:id="adPatSearchBedNo"
    private TableColumn<AdmittedPatient, Integer> adPatSearchBedNo; // Value injected by FXMLLoader

    @FXML // fx:id="disPatTable"
    private TableView<DischargedPatient> disPatTable; // Value injected by FXMLLoader

    @FXML // fx:id="disPatName"
    private TableColumn<DischargedPatient, String> disPatName; // Value injected by FXMLLoader

    @FXML // fx:id="disPatPhoneNo"
    private TableColumn<DischargedPatient, String> disPatPhoneNo; // Value injected by FXMLLoader

    @FXML // fx:id="disPatSymptoms"
    private TableColumn<DischargedPatient, String> disPatSymptoms; // Value injected by FXMLLoader

    @FXML // fx:id="disPatDate"
    private TableColumn<DischargedPatient, LocalDate> disPatDate; // Value injected by FXMLLoader

    @FXML // fx:id="disPatBedNo"
    private TableColumn<DischargedPatient, Integer> disPatBedNo; // Value injected by FXMLLoader

    @FXML // fx:id="disPatReason"
    private TableColumn<DischargedPatient, String> disPatReason; // Value injected by FXMLLoader

    @FXML // fx:id="disPatSearchField"
    private TextField disPatSearchField; // Value injected by FXMLLoader

    @FXML // fx:id="disPatSearchTable"
    private TableView<DischargedPatient> disPatSearchTable; // Value injected by FXMLLoader

    @FXML // fx:id="disPatSearchName"
    private TableColumn<DischargedPatient, String> disPatSearchName; // Value injected by FXMLLoader

    @FXML // fx:id="disPatSearchPhoneNo"
    private TableColumn<DischargedPatient, String> disPatSearchPhoneNo; // Value injected by FXMLLoader

    @FXML // fx:id="disPatSearchSymptoms"
    private TableColumn<DischargedPatient, String> disPatSearchSymptoms; // Value injected by FXMLLoader

    @FXML // fx:id="disPatSearchDate"
    private TableColumn<DischargedPatient, LocalDate> disPatSearchDate; // Value injected by FXMLLoader

    @FXML // fx:id="disPatSearchBedNo"
    private TableColumn<DischargedPatient, Integer> disPatSearchBedNo; // Value injected by FXMLLoader

    @FXML // fx:id="disPatSearchReason"
    private TableColumn<DischargedPatient, String> disPatSearchReason; // Value injected by FXMLLoader

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        totalBeds.setText("" + db.getTotalBeds());
        bedsOccupied.setText("" + (db.getTotalBeds() - db.getBeds()));
        bedsRemaining.setText("" + db.getBeds());
        initializePatientStuff();
        initializeAdmittedPatientsStuff();
        initializeDischargedPatientsStuff();
        bedsSpinner.setValueFactory(
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE, db.getBeds()));
        totalBedsSpinner.setValueFactory(
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE, db.getTotalBeds()));

    }

    private void initializeAdmittedPatientsStuff() {
        StringConverter<LocalDate> dateFormat = new StringConverter<LocalDate>() {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-YYYY");

            @Override
            public LocalDate fromString(String string) {
                return (string != null & !string.isEmpty()) ? LocalDate.parse(string, formatter) : null;
            }

            @Override
            public String toString(LocalDate date) {
                return (date != null) ? formatter.format(date) : "";
            }

        };
        adPatName.setCellValueFactory(new PropertyValueFactory<AdmittedPatient, String>("name"));
        adPatPhoneNo.setCellValueFactory(new PropertyValueFactory<AdmittedPatient, String>("phoneNo"));
        adPatSymptoms.setCellValueFactory(new PropertyValueFactory<AdmittedPatient, String>("symptoms"));
        adPatDate.setCellValueFactory(new PropertyValueFactory<AdmittedPatient, LocalDate>("dateAdmitted"));
        adPatBedNo.setCellValueFactory(new PropertyValueFactory<AdmittedPatient, Integer>("BedNo"));

        adPatSearchName.setCellValueFactory(new PropertyValueFactory<AdmittedPatient, String>("name"));
        adPatSearchPhoneNo.setCellValueFactory(new PropertyValueFactory<AdmittedPatient, String>("phoneNo"));
        adPatSearchSymptoms.setCellValueFactory(new PropertyValueFactory<AdmittedPatient, String>("symptoms"));
        adPatSearchDate.setCellValueFactory(new PropertyValueFactory<AdmittedPatient, LocalDate>("dateAdmitted"));
        adPatSearchBedNo.setCellValueFactory(new PropertyValueFactory<AdmittedPatient, Integer>("BedNo"));

        adPatDate.setCellFactory(TextFieldTableCell.forTableColumn(dateFormat));
        adPatSearchDate.setCellFactory(TextFieldTableCell.forTableColumn(dateFormat));
        adPatTable.setItems(adPatList);
        adPatSearchTable.setItems(adPatSearchList);
    }

    private void initializeDischargedPatientsStuff() {
        StringConverter<LocalDate> dateFormat = new StringConverter<LocalDate>() {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-YYYY");

            @Override
            public LocalDate fromString(String string) {
                return (string != null & !string.isEmpty()) ? LocalDate.parse(string, formatter) : null;
            }

            @Override
            public String toString(LocalDate date) {
                return (date != null) ? formatter.format(date) : "";
            }

        };
        disPatName.setCellValueFactory(new PropertyValueFactory<DischargedPatient, String>("name"));
        disPatPhoneNo.setCellValueFactory(new PropertyValueFactory<DischargedPatient, String>("phoneNo"));
        disPatSymptoms.setCellValueFactory(new PropertyValueFactory<DischargedPatient, String>("symptoms"));
        disPatDate.setCellValueFactory(new PropertyValueFactory<DischargedPatient, LocalDate>("dateDischarged"));
        disPatBedNo.setCellValueFactory(new PropertyValueFactory<DischargedPatient, Integer>("bedNo"));
        disPatReason.setCellValueFactory(new PropertyValueFactory<DischargedPatient, String>("reason"));

        disPatSearchName.setCellValueFactory(new PropertyValueFactory<DischargedPatient, String>("name"));
        disPatSearchPhoneNo.setCellValueFactory(new PropertyValueFactory<DischargedPatient, String>("phoneNo"));
        disPatSearchSymptoms.setCellValueFactory(new PropertyValueFactory<DischargedPatient, String>("symptoms"));
        disPatSearchDate.setCellValueFactory(new PropertyValueFactory<DischargedPatient, LocalDate>("dateDischarged"));
        disPatSearchBedNo.setCellValueFactory(new PropertyValueFactory<DischargedPatient, Integer>("bedNo"));
        disPatSearchReason.setCellValueFactory(new PropertyValueFactory<DischargedPatient, String>("reason"));

        disPatDate.setCellFactory(TextFieldTableCell.forTableColumn(dateFormat));
        disPatSearchDate.setCellFactory(TextFieldTableCell.forTableColumn(dateFormat));
        disPatTable.setItems(disPatList);
        disPatSearchTable.setItems(disPatSearchList);
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
        AdminController.patient = temp;
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
        CreatePDF.writeToPDF(temp);
        ProcessBuilder pb = new ProcessBuilder("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe", "prescription.pdf");
        try {
            pb.start();
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

    @FXML
    void deleteDisPatRecord(ActionEvent event) {
        if (FormValidation.showConfirmationMessage("Are you sure you want to delete?")) {
            DischargedPatient temp = disPatTable.getSelectionModel().getSelectedItem();
            DischargedPatient tempSearch = disPatSearchTable.getSelectionModel().getSelectedItem();
            if (temp == null && tempSearch == null)
                return;
            if (temp == null && tempSearch != null) {
                temp = tempSearch;
                db.deleteDischargedPatient(tempSearch.getPatientId());
                disPatSearchList.remove(disPatSearchTable.getSelectionModel().getSelectedIndex());
                disPatSearchTable.getSelectionModel().clearSelection();
            }
            if (temp != null) {
                db.deleteDischargedPatient(temp.getPatientId());
                disPatList.remove(disPatTable.getSelectionModel().getSelectedIndex());
                disPatTable.getSelectionModel().clearSelection();
            }
        }
        disPatSearchList.clear();
    }
    @FXML
    void dischargePatient(ActionEvent event) {
        String r = reason.getText();
        if (!r.isEmpty()) {
            if (FormValidation.showConfirmationMessage("Are you sure you want to discharge the patient?")){
                AdmittedPatient temp = adPatTable.getSelectionModel().getSelectedItem();
                AdmittedPatient tempSearch = adPatSearchTable.getSelectionModel().getSelectedItem();
                if (temp == null && tempSearch == null)
                    return;
                if (temp == null && tempSearch != null) {
                    temp = tempSearch;
                    db.dischargePatient(tempSearch.getPatientId(), r);
                    adPatSearchList.remove(adPatSearchTable.getSelectionModel().getSelectedIndex());
                    adPatSearchTable.getSelectionModel().clearSelection();
                }
                if (temp != null) {
                    db.dischargePatient(temp.getPatientId(), r);
                    adPatList.remove(adPatTable.getSelectionModel().getSelectedIndex());
                    adPatTable.getSelectionModel().clearSelection();
                }
            }
        } else {
            FormValidation.showError("The reason's field is empty");
        }
    }
    @FXML
    void refreshAdPat(ActionEvent event) {
        adPatList.clear();
        adPatSearchList.clear();
        for (AdmittedPatient a: db.getAdmittedPatients()) {
            adPatList.add(a);
        }
    }

    @FXML
    void refreshDisPat(ActionEvent event) {
        disPatList.clear();
        disPatSearchList.clear();
        for (DischargedPatient d: db.getDischargedPatients()) {
            disPatList.add(d);
        }
    }
    @FXML
    void searchAdPat(ActionEvent event) {
        String searchText = adPatSearchField.getText().toLowerCase();
        adPatSearchList.clear();
        if (searchText.equals("")) {
            adPatSearchList.clear();
        } else {
            boolean shouldAdd = false;
            for (AdmittedPatient a : adPatList) {
                if (a.getName().toLowerCase().contains(searchText)) {
                    shouldAdd = true;
                }
                if (a.getSymptoms().toLowerCase().contains(searchText)) {
                    shouldAdd = true;
                }
                if (a.getPhoneNo().toLowerCase().contains(searchText)) {
                    shouldAdd = true;
                }
                if (shouldAdd) {
                    adPatSearchList.add(a);
                    shouldAdd = false;
                }
            }
        }
    }

    @FXML
    void logout(ActionEvent event) {
        String path = "src/login/Login.fxml";
        Platform.runLater(() -> {
            openForm(path);
        });
        Stage stage = (Stage) totalBeds.getScene().getWindow();
        stage.close();
    }

    @FXML
    void searchDisPat(ActionEvent event) {
        String searchText = disPatSearchField.getText().toLowerCase();
        disPatSearchList.clear();
        if (searchText.equals("")) {
            disPatSearchList.clear();
        } else {
            boolean shouldAdd = false;
            for (DischargedPatient d : disPatList) {
                if (d.getName().toLowerCase().contains(searchText)) {
                    shouldAdd = true;
                }
                if (d.getSymptoms().toLowerCase().contains(searchText)) {
                    shouldAdd = true;
                }
                if (d.getPhoneNo().toLowerCase().contains(searchText)) {
                    shouldAdd = true;
                }
                if (shouldAdd) {
                    disPatSearchList.add(d);
                    shouldAdd = false;
                }
            }
        }
    }
}
