/**
 * Sample Skeleton for 'PatientForm.fxml' Controller Class
 */

package patient;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.util.StringConverter;
import utilities.Database;
import utilities.FormValidation;

public class PatientForm {

    private SpinnerValueFactory<Integer> ageValueFactory;
    private SpinnerValueFactory<Double> billValueFactory;
    @FXML // fx:id="nameField"
    private JFXTextField nameField; // Value injected by FXMLLoader

    @FXML // fx:id="phoneNoField"
    private JFXTextField phoneNoField; // Value injected by FXMLLoader

    @FXML // fx:id="ageSpinner"
    private Spinner<Integer> ageSpinner; // Value injected by FXMLLoader

    @FXML // fx:id="addresField"
    private JFXTextField addresField; // Value injected by FXMLLoader

    @FXML // fx:id="symptomsField"
    private JFXTextField symptomsField; // Value injected by FXMLLoader

    @FXML // fx:id="billSpinner"
    private Spinner<Double> billSpinner; // Value injected by FXMLLoader

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        ageValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 127);
        billValueFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(0, Double.MAX_VALUE, 3500.0);
        formatSpinners();
        ageSpinner.setValueFactory(ageValueFactory);
        billSpinner.setValueFactory(billValueFactory);

    }

    private void formatSpinners() {
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

    private @FXML void submit(ActionEvent event) {
        String name = nameField.getText();
        String phoneNo = phoneNoField.getText();
        int age = ageSpinner.getValue();
        String address = addresField.getText();
        String symptoms = symptomsField.getText();
        double bill = billSpinner.getValue();
        String prescription = "";
        if (name.equals("") || phoneNo.equals("") || symptoms.equals("") || bill == 0) {
            FormValidation.showError("Some fields are empty! Fill them before submitting the form!");
        } else if (!FormValidation.validatePhoneNo(phoneNo)) {
            FormValidation.showError("The Phone No is not valid!");
        } else {
            new Database().addPatient(new Patient(name, age, phoneNo, address, symptoms, prescription, bill));
            nameField.setText("");
            phoneNoField.setText("");
            addresField.setText("");
            ageSpinner.getValueFactory().setValue(0);
            billSpinner.getValueFactory().setValue(3500.0);
            symptomsField.setText("");
            FormValidation.showInformation("Patient Added Successfully!");
        }
    }

}
