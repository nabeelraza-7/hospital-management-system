package appointments;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;

import doctor.Doctor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.StringConverter;
import utilities.Database;

public class AppointmentController {
    /*
     * I am assuming a doctor will take almost 20 minutes to check one patient.
     * Every appointment will be calculated accordingly
     */
    private Database db = new Database();
    // list for all the appointments
    private ObservableList<Appointment> list = FXCollections.observableArrayList();
    // A list that corresponds to the doctors
    private ObservableList<Doctor> docs = FXCollections.observableArrayList();
    private Doctor temp = null;
    private LocalTime availableTime = null;

    @FXML
    private JFXButton makeAppBtn;// Make appointment button
    @FXML // fx:id="patNameField"
    private JFXTextField patNameField; // Value injected by FXMLLoader

    @FXML // fx:id="docList"
    private JFXComboBox<String> docList; // Value injected by FXMLLoader

    @FXML // fx:id="dateField"
    private JFXDatePicker dateField; // Value injected by FXMLLoader

    @FXML // fx:id="timeField"
    private JFXTimePicker timeField; // Value injected by FXMLLoader
    // yyyy-mm-dd
    // dd-mm-yyyy
    @FXML // fx:id="patNameCol"
    private TableView<Appointment> table; // Value injected by FXMLLoader

    @FXML // fx:id="patNameCol"
    private TableColumn<Appointment, String> patNameCol; // Value injected by FXMLLoader

    @FXML // fx:id="docNameCol"
    private TableColumn<Appointment, String> docNameCol; // Value injected by FXMLLoader

    @FXML // fx:id="dateCol"
    private TableColumn<Appointment, LocalDate> dateCol; // Value injected by FXMLLoader

    @FXML // fx:id="timeCol"
    private TableColumn<Appointment, LocalTime> timeCol; // Value injected by FXMLLoader

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        ObservableList<String> docNames = FXCollections.observableArrayList();
        docNames.add("Select Doctor");
        for (Doctor d : db.getDoctors()) {
            docNames.add(d.getName());
            docs.add(d);
        }
        docList.setItems(docNames);
        docList.getSelectionModel().selectFirst();
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
        StringConverter<LocalTime> timeFormat = new StringConverter<LocalTime>() {
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

        dateField.setConverter(dateFormat);
        timeField.setConverter(timeFormat);

        dateField.setValue(LocalDate.now());
        timeField.setValue(LocalTime.now());
        dateField.setDisable(true);
        timeField.setDisable(true);

        patNameCol.setCellValueFactory(new PropertyValueFactory<Appointment, String>("patientName"));
        docNameCol.setCellValueFactory(new PropertyValueFactory<Appointment, String>("doctorName"));

        dateCol.setCellValueFactory(new PropertyValueFactory<Appointment, LocalDate>("Date"));
        timeCol.setCellValueFactory(new PropertyValueFactory<Appointment, LocalTime>("Time"));

        dateCol.setCellFactory(TextFieldTableCell.forTableColumn(dateFormat));
        timeCol.setCellFactory(TextFieldTableCell.forTableColumn(timeFormat));

        loadList();
        table.setItems(list);
    }

    public void loadList() {
        for (Appointment a : db.getAppointments()) {
            list.add(a);
        }
    }

    @FXML
    public void makeAppointment(ActionEvent e) {
        LocalDate date = dateField.getValue();
        LocalTime time = timeField.getValue();
        String patName = patNameField.getText();
        String docName = docList.getValue();
        Appointment app = null;
        if (!(docName.equals("") || patName.equals(""))) {
            app = new Appointment(patName, docName, date, time);
            db.addAppointment(app);
            list.add(app);
            docList.getSelectionModel().select(0);
            dateField.setValue(LocalDate.now());
            timeField.setValue(LocalTime.now());
            patNameField.setText("");
        }

    }

    @FXML
    void deleteAppointment(ActionEvent event) {
        int index = table.getSelectionModel().getSelectedIndex();
        Appointment temp = table.getSelectionModel().getSelectedItem();
        db.deleteAppointment(temp);
        list.remove(index);
        table.getSelectionModel().clearSelection();
    }

    @FXML
    void refresh(ActionEvent event) {
        LocalDate today = LocalDate.now();
        ArrayList<Appointment> listOfAppointments = db.getAppointments();
        for (Appointment a : listOfAppointments) {
            int cmp = a.getDate().compareTo(today);
            if (cmp < 0) {
                deleteFromList(a.getDate());
                db.deleteAppointment(a);
            }
        }
    }

    public void deleteFromList(LocalDate date) {
        for (int i = 0; i < list.size(); i++) {
            int cmp = list.get(i).getDate().compareTo(date);
            if (cmp == 0) {
                list.remove(i);
            }
        }
    }

    @FXML
    void setTheTime(ActionEvent event) {
        int index = docList.getSelectionModel().getSelectedIndex();
        if (index != 0) {
            temp = docs.get(index - 1);
            System.out.println(temp);
            dateField.setDisable(false);
            timeField.setDisable(false);
            dateField.setValue(LocalDate.now());
            // timeField.setValue(temp.getTimeFrom());
            availableTime = temp.getTimeFrom();
            timeField.setValue(calculateTime());
        } else {
            dateField.setDisable(true);
            timeField.setDisable(true);
            dateField.setValue(LocalDate.now());
            timeField.setValue(LocalTime.now());
        }
    }

    private LocalTime calculateTime() {
        String docName = temp.getName();
        LocalDate date = dateField.getValue();
        if (!db.checkAppointment(docName, date, availableTime)) {
            return availableTime;
        } else {
            availableTime = availableTime.plusMinutes(20);
        }
        return calculateTime();
    }
}
