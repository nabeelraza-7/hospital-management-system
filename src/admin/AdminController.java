// Methods to create, update, read, delete new doctor records
// Methods to create, update, read, delete new patient records
// Methods to create, update, read, delete new receptionist records
// Methods to create, update, read, delete new staff records
// Methods to change the number of beds
package admin;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import doctor.Doctor;
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
import patient.Patient;
import receptionist.Receptionist;
import staff.Staff;
import utilities.Database;
import utilities.FormValidation;

public class AdminController {

    private StringConverter<LocalTime> timeFormat;

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

    ObservableList<Doctor> docList = FXCollections.observableArrayList(db.getDoctors());
    ObservableList<Doctor> docSearchList = FXCollections.observableArrayList();

    ObservableList<Patient> patList = FXCollections.observableArrayList(db.getPatients());
    ObservableList<Patient> patSearchList = FXCollections.observableArrayList();

    ObservableList<Receptionist> recList = FXCollections.observableArrayList(db.getReceptionists());
    ObservableList<Receptionist> recSearchList = FXCollections.observableArrayList();

    ObservableList<Staff> staffList = FXCollections.observableArrayList(db.getStaffMembers());
    ObservableList<Staff> staffSearchList = FXCollections.observableArrayList();

    // Declaring Doctor Tab's required fields
    // Declaring main doctor's table fields
    @FXML
    private TableView<Doctor> docTable;

    @FXML
    private TableColumn<Doctor, String> docName;

    @FXML
    private TableColumn<Doctor, String> docPhoneNo;

    @FXML
    private TableColumn<Doctor, String> docEmail;

    @FXML
    private TableColumn<Doctor, String> docSpec;

    @FXML
    private TableColumn<Doctor, LocalTime> docFrom;

    @FXML
    private TableColumn<Doctor, LocalTime> docTo;
    // End declaring main doctor's table

    // search field for doctors
    @FXML
    private TextField docSearchField;
    // Declaring doctor search table's required fields
    @FXML
    private TableView<Doctor> docSearchTable;

    @FXML
    private TableColumn<Doctor, String> docSearchName;

    @FXML
    private TableColumn<Doctor, String> docSearchPhoneNo;

    @FXML
    private TableColumn<Doctor, String> docSearchEmail;

    @FXML
    private TableColumn<Doctor, String> docSearchSpec;

    @FXML
    private TableColumn<Doctor, LocalTime> docSearchFrom;

    @FXML
    private TableColumn<Doctor, LocalTime> docSearchTo;
    // end declaring doctor search table's required fields

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
    // end declaring patient search table's required fields

    // Declaring Receptionist Tab's required fields
    // Declaring main Receptionist's table fields
    @FXML
    private TableView<Receptionist> recTable;

    @FXML
    private TableColumn<Receptionist, String> recName;

    @FXML
    private TableColumn<Receptionist, String> recPhoneNo;

    @FXML
    private TableColumn<Receptionist, String> recEmail;
    // End declaring main Receptionist's table
    // search field for Receptionist
    @FXML
    private TextField recSearchField;
    // Declaring Receptionist search table's required fields
    @FXML
    private TableView<Receptionist> recSearchTable;

    @FXML
    private TableColumn<Receptionist, String> recSearchName;

    @FXML
    private TableColumn<Receptionist, String> recSearchPhoneNo;

    @FXML
    private TableColumn<Receptionist, String> recSearchEmail;
    // end declaring Receptionist search table's required fields

    // Declaring staff table's field
    @FXML
    private TableView<Staff> staffTable;

    @FXML
    private TableColumn<Staff, String> staffName;

    @FXML
    private TableColumn<Staff, String> staffPhoneNo;

    @FXML
    private TableColumn<Staff, String> staffStatus;
    // END Declaring staff table's field
    // Declaring staff search field
    @FXML
    private TextField staffSearchField;
    // Declaring staff search table's field
    @FXML
    private TableView<Staff> staffSearchTable;

    @FXML
    private TableColumn<Staff, String> staffSearchName;

    @FXML
    private TableColumn<Staff, String> staffSearchPhoneNo;

    @FXML
    private TableColumn<Staff, String> staffSearchStatus;
    // End declaring staff search table's fields

    @FXML
    void openAdminForm(ActionEvent event) {
        String path = "src/admin/AdminForm.fxml";
        Platform.runLater(() -> {
            openForm(path);
        });
    }

    @FXML
    void openDoctorForm(ActionEvent event) {
        String path = "src/doctor/DoctorForm.fxml";
        Platform.runLater(() -> {
            openForm(path);
        });

    }

    @FXML
    void openPatientForm(ActionEvent event) {
        String path = "src/patient/PatientForm.fxml";
        Platform.runLater(() -> {
            openForm(path);
        });
    }

    @FXML
    void openReceptionistForm(ActionEvent event) {
        String path = "src/receptionist/ReceptionistForm.fxml";
        Platform.runLater(() -> {
            openForm(path);
        });
    }

    @FXML
    void openStaffForm(ActionEvent event) {
        String path = "src/staff/StaffForm.fxml";
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
        timeFormat = new StringConverter<LocalTime>() {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");

            @Override
            public LocalTime fromString(String string) {
                return (string != null) ? LocalTime.parse(string, formatter) : null;
            }

            @Override
            public String toString(LocalTime time) {
                return (time != null) ? formatter.format(time) : "";
            }

        };
        initializeDoctorStuff();
        initializePatientStuff();
        initializeReceptionistStuff();
        initializeStaffStuff();
        bedsSpinner.setValueFactory(
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE, db.getBeds()));
        totalBedsSpinner.setValueFactory(
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE, db.getTotalBeds()));

    }

    private void initializeStaffStuff() {
        staffName.setCellValueFactory(new PropertyValueFactory<Staff, String>("name"));
        staffPhoneNo.setCellValueFactory(new PropertyValueFactory<Staff, String>("phoneNo"));
        staffStatus.setCellValueFactory(new PropertyValueFactory<Staff, String>("status"));

        staffSearchName.setCellValueFactory(new PropertyValueFactory<Staff, String>("name"));
        staffSearchPhoneNo.setCellValueFactory(new PropertyValueFactory<Staff, String>("phoneNo"));
        staffSearchStatus.setCellValueFactory(new PropertyValueFactory<Staff, String>("status"));

        staffTable.setItems(staffList);
        staffSearchTable.setItems(staffSearchList);
    }

    private void initializeReceptionistStuff() {
        recName.setCellValueFactory(new PropertyValueFactory<Receptionist, String>("name"));
        recPhoneNo.setCellValueFactory(new PropertyValueFactory<Receptionist, String>("phoneNo"));
        recEmail.setCellValueFactory(new PropertyValueFactory<Receptionist, String>("email"));

        recSearchName.setCellValueFactory(new PropertyValueFactory<Receptionist, String>("name"));
        recSearchPhoneNo.setCellValueFactory(new PropertyValueFactory<Receptionist, String>("phoneNo"));
        recSearchEmail.setCellValueFactory(new PropertyValueFactory<Receptionist, String>("email"));

        recTable.setItems(recList);
        recSearchTable.setItems(recSearchList);
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

    private void initializeDoctorStuff() {
        docName.setCellValueFactory(new PropertyValueFactory<Doctor, String>("name"));
        docPhoneNo.setCellValueFactory(new PropertyValueFactory<Doctor, String>("phoneNo"));
        docEmail.setCellValueFactory(new PropertyValueFactory<Doctor, String>("email"));
        docSpec.setCellValueFactory(new PropertyValueFactory<Doctor, String>("specialization"));
        docFrom.setCellValueFactory(new PropertyValueFactory<Doctor, LocalTime>("timeFrom"));
        docTo.setCellValueFactory(new PropertyValueFactory<Doctor, LocalTime>("timeTo"));

        docSearchName.setCellValueFactory(new PropertyValueFactory<Doctor, String>("name"));
        docSearchPhoneNo.setCellValueFactory(new PropertyValueFactory<Doctor, String>("phoneNo"));
        docSearchEmail.setCellValueFactory(new PropertyValueFactory<Doctor, String>("email"));
        docSearchSpec.setCellValueFactory(new PropertyValueFactory<Doctor, String>("specialization"));
        docSearchFrom.setCellValueFactory(new PropertyValueFactory<Doctor, LocalTime>("timeFrom"));
        docSearchTo.setCellValueFactory(new PropertyValueFactory<Doctor, LocalTime>("timeTo"));

        docFrom.setCellFactory(TextFieldTableCell.forTableColumn(timeFormat));
        docTo.setCellFactory(TextFieldTableCell.forTableColumn(timeFormat));
        docSearchFrom.setCellFactory(TextFieldTableCell.forTableColumn(timeFormat));
        docSearchTo.setCellFactory(TextFieldTableCell.forTableColumn(timeFormat));

        docTable.setItems(docList);
        docSearchTable.setItems(docSearchList);

    }

    // Doctor's Tab actions
    @FXML
    void deleteDocRecord(ActionEvent event) {
        if (FormValidation.showConfirmationMessage("Are you sure you want to delete?")) {
            Doctor temp = docTable.getSelectionModel().getSelectedItem();
            Doctor tempSearch = docSearchTable.getSelectionModel().getSelectedItem();
            if (temp == null && tempSearch == null)
                return;
            if (temp == null && tempSearch != null) {
                temp = tempSearch;
                db.deleteDoctor(tempSearch.getId());
                docSearchList.remove(docSearchTable.getSelectionModel().getSelectedIndex());
                docSearchTable.getSelectionModel().clearSelection();
            }
            if (temp != null) {
                db.deleteDoctor(temp.getId());
                docList.remove(docTable.getSelectionModel().getSelectedIndex());
                docTable.getSelectionModel().clearSelection();
            }
        }
    }

    @FXML
    void editDocRecord(ActionEvent event) {
        Doctor temp = docTable.getSelectionModel().getSelectedItem();
        Doctor tempSearch = docSearchTable.getSelectionModel().getSelectedItem();
        if (temp == null && tempSearch == null)
            return;
        if (temp == null && tempSearch != null) {
            temp = tempSearch;
        }
        temp.writeToFile();
        String path = "src/doctor/EditDoctorForm.fxml";
        Platform.runLater(() -> {
            openForm(path);
        });

    }

    @FXML
    void refreshDoc(ActionEvent event) {
        docList.clear();
        docSearchList.clear();
        // docList = FXCollections.observableArrayList(db.getDoctors());
        for (Doctor d : db.getDoctors()) {
            docList.add(d);
        }
    }

    @FXML
    void searchDoc(ActionEvent event) {
        String searchText = docSearchField.getText().toLowerCase();
        docSearchList.clear();
        if (searchText.equals("")) {
            docSearchList.clear();
        } else {
            boolean shouldAdd = false;
            for (Doctor d : docList) {
                if (d.getName().toLowerCase().contains(searchText)) {
                    shouldAdd = true;
                }
                if (d.getEmail().toLowerCase().contains(searchText)) {
                    shouldAdd = true;
                }
                if (d.getSpecialization().toLowerCase().contains(searchText)) {
                    shouldAdd = true;
                }
                if (d.getPhoneNo().toLowerCase().contains(searchText)) {
                    shouldAdd = true;
                }
                if (shouldAdd) {
                    docSearchList.add(d);
                    shouldAdd = false;
                }
            }
        }
    }

    // Ending Doctor's Tab actions
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
        String searchText = patSearchField.getText().toLowerCase();
        patSearchList.clear();
        if (searchText.equals("")) {
            patSearchList.clear();
        } else {
            boolean shouldAdd = false;
            for (Patient p : patList) {
                if (p.getName().toLowerCase().contains(searchText)) {
                    shouldAdd = true;
                }
                if (p.getAddress().toLowerCase().contains(searchText)) {
                    shouldAdd = true;
                }
                if (p.getSymptoms().toLowerCase().contains(searchText)) {
                    shouldAdd = true;
                }
                if (p.getPhoneNo().toLowerCase().contains(searchText)) {
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
        // FormValidation.showInformation("Prescription", temp.getPrescription());
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
    // Receptionist's Tab actions
    @FXML
    void deleteRecRecord(ActionEvent event) {
        if (FormValidation.showConfirmationMessage("Are you sure you want to delete?")) {
            Receptionist temp = recTable.getSelectionModel().getSelectedItem();
            Receptionist tempSearch = recSearchTable.getSelectionModel().getSelectedItem();
            if (temp == null && tempSearch == null)
                return;
            if (temp == null && tempSearch != null) {
                temp = tempSearch;
                db.deleteReceptionist(tempSearch.getId());
                recSearchList.remove(recSearchTable.getSelectionModel().getSelectedIndex());
                recSearchTable.getSelectionModel().clearSelection();
            }
            if (temp != null) {
                db.deleteReceptionist(temp.getId());
                recList.remove(recTable.getSelectionModel().getSelectedIndex());
                recTable.getSelectionModel().clearSelection();
            }
        }
    }

    @FXML
    void editRecRecord(ActionEvent event) {
        Receptionist temp = recTable.getSelectionModel().getSelectedItem();
        Receptionist tempSearch = recSearchTable.getSelectionModel().getSelectedItem();
        if (temp == null && tempSearch == null)
            return;
        if (temp == null && tempSearch != null) {
            temp = tempSearch;
        }
        temp.writeToFile();
        String path = "src/receptionist/EditReceptionistForm.fxml";
        Platform.runLater(() -> {
            openForm(path);
        });
    }

    @FXML
    void refreshRec(ActionEvent event) {
        recList.clear();
        recSearchList.clear();
        // docList = FXCollections.observableArrayList(db.getDoctors());
        for (Receptionist r : db.getReceptionists()) {
            recList.add(r);
        }
    }

    @FXML
    void searchRec(ActionEvent event) {
        String searchText = recSearchField.getText().toLowerCase();
        recSearchList.clear();
        if (searchText.equals("")) {
            recSearchList.clear();
        } else {
            boolean shouldAdd = false;
            for (Receptionist r : recList) {
                if (r.getName().toLowerCase().contains(searchText)) {
                    shouldAdd = true;
                }
                if (r.getEmail().toLowerCase().contains(searchText)) {
                    shouldAdd = true;
                }
                if (r.getPhoneNo().toLowerCase().contains(searchText)) {
                    shouldAdd = true;
                }
                if (shouldAdd) {
                    recSearchList.add(r);
                    shouldAdd = false;
                }
            }
        }
    }
    // Ending Receptionist's Tab actions

    // Staff's Tab actions
    @FXML
    void deleteStaffRecord(ActionEvent event) {
        if (FormValidation.showConfirmationMessage("Are you sure you want to delete?")) {
            Staff temp = staffTable.getSelectionModel().getSelectedItem();
            Staff tempSearch = staffSearchTable.getSelectionModel().getSelectedItem();
            if (temp == null && tempSearch == null)
                return;
            if (temp == null && tempSearch != null) {
                temp = tempSearch;
                db.deleteStaff(tempSearch.getId());
                staffSearchList.remove(staffSearchTable.getSelectionModel().getSelectedIndex());
                staffSearchTable.getSelectionModel().clearSelection();
            }
            if (temp != null) {
                db.deleteStaff(temp.getId());
                staffList.remove(staffTable.getSelectionModel().getSelectedIndex());
                staffTable.getSelectionModel().clearSelection();
            }
        }
    }

    @FXML
    void editStaffRecord(ActionEvent event) {
        Staff temp = staffTable.getSelectionModel().getSelectedItem();
        Staff tempSearch = staffSearchTable.getSelectionModel().getSelectedItem();
        if (temp == null && tempSearch == null)
            return;
        if (temp == null && tempSearch != null) {
            temp = tempSearch;
        }
        temp.writeToFile();
        String path = "src/staff/EditStaffForm.fxml";
        Platform.runLater(() -> {
            openForm(path);
        });
    }

    @FXML
    void refreshStaff(ActionEvent event) {
        staffList.clear();
        staffSearchList.clear();
        // docList = FXCollections.observableArrayList(db.getDoctors());
        for (Staff s : db.getStaffMembers()) {
            staffList.add(s);
        }
    }

    @FXML
    void searchStaff(ActionEvent event) {
        String searchText = staffSearchField.getText().toLowerCase();
        staffSearchList.clear();
        if (searchText.equals("")) {
            staffSearchList.clear();
        } else {
            boolean shouldAdd = false;
            for (Staff s : staffList) {
                if (s.getName().toLowerCase().contains(searchText)) {
                    shouldAdd = true;
                }
                if (s.getStatus().toLowerCase().contains(searchText)) {
                    shouldAdd = true;
                }
                if (s.getPhoneNo().toLowerCase().contains(searchText)) {
                    shouldAdd = true;
                }
                if (shouldAdd) {
                    staffSearchList.add(s);
                    shouldAdd = false;
                }
            }
        }
    }

    // Ending staff's tab actions

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
