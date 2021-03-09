/**
 * Sample Skeleton for 'PatientForm.fxml' Controller Class
 */

package patient;

import com.jfoenix.controls.JFXTextField;

import admin.AdminController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import utilities.Database;
import utilities.FormValidation;

public class EditPatientForm {

    private Patient temp = null;

    @FXML // fx:id="nameField"
    private JFXTextField nameField; // Value injected by FXMLLoader

    @FXML // fx:id="phoneNoField"
    private JFXTextField phoneNoField; // Value injected by FXMLLoader

    @FXML // fx:id="ageField"
    private Spinner<Integer> ageSpinner; // Value injected by FXMLLoader

    @FXML // fx:id="addresField"
    private JFXTextField addresField; // Value injected by FXMLLoader

    @FXML // fx:id="symptomsField"
    private JFXTextField symptomsField; // Value injected by FXMLLoader

    @FXML // fx:id="billField"
    private Spinner<Double> billSpinner; // Value injected by FXMLLoader

    @FXML
    void initialize() {
        formatSpinners();
        temp = AdminController.patient;
        nameField.setText(temp.getName());
        phoneNoField.setText(temp.getPhoneNo());
        ageSpinner.getValueFactory().setValue(temp.getAge());
        addresField.setText(temp.getAddress());
        symptomsField.setText(temp.getSymptoms());
        billSpinner.getValueFactory().setValue(temp.getBill());
    }

    private void formatSpinners() {
        SpinnerValueFactory<Integer> ageValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 127);
        SpinnerValueFactory<Double> billValueFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(0,
                Double.MAX_VALUE, 3500.0);
        ageSpinner.setValueFactory(ageValueFactory);
        billSpinner.setValueFactory(billValueFactory);

        ageValueFactory.setConverter(new StringConverter<Integer>() {

            @Override
            public String toString(Integer value) {
                return value.toString() + " y/o";
            }

            @Override
            public Integer fromString(String string) {
                String valueWithoutUnits = string.replaceAll(" y/o", "").trim();
                if (valueWithoutUnits.isEmpty()) {
                    return 0;
                } else {
                    return Integer.valueOf(valueWithoutUnits);
                }
            }
        });

        billValueFactory.setConverter(new StringConverter<Double>() {

            @Override
            public String toString(Double value) {
                return "Rs " + value.toString();
            }

            @Override
            public Double fromString(String string) {
                String valueWithoutUnits = string.replaceAll("Rs ", "").trim();
                if (valueWithoutUnits.isEmpty()) {
                    return 0.0;
                } else {
                    return Double.parseDouble(valueWithoutUnits);
                }
            }
        });
    }

    @FXML
    void submit(ActionEvent event) {
        String name = nameField.getText();
        String phoneNo = phoneNoField.getText();
        int age = ageSpinner.getValue();
        String address = addresField.getText();
        String symptoms = symptomsField.getText();
        double bill = billSpinner.getValue();
        if (name.equals("") || phoneNo.equals("") || symptoms.equals("") || bill == 0) {
            FormValidation.showError("Some fields are empty! Fill them before submitting the form!");
        } else if (!FormValidation.validatePhoneNo(phoneNo)) {
            FormValidation.showError("The Phone No is not valid!");
        } else {
            temp.setName(name);
            temp.setPhoneNo(phoneNo);
            temp.setAge(age);
            temp.setAddress(address);
            temp.setSymptoms(symptoms);
            temp.setBill(bill);
            new Database().editPatient(temp);
            FormValidation.showInformation("Patient Edited Successfully!");
            Stage stage = (Stage) nameField.getScene().getWindow();
            stage.close();
        }
    }

}
