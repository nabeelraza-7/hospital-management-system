/**
 * Sample Skeleton for 'DoctorForm.fxml' Controller Class
 */

package doctor;

import java.time.LocalTime;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;

import admin.AdminController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import utilities.Database;
import utilities.FormValidation;

public class EditDoctorController {
    Doctor temp = null;
    @FXML // fx:id="nameField"
    private JFXTextField nameField; // Value injected by FXMLLoader

    @FXML // fx:id="phoneNoField"
    private JFXTextField phoneNoField; // Value injected by FXMLLoader

    @FXML // fx:id="specialization"
    private JFXTextField specialization; // Value injected by FXMLLoader

    @FXML // fx:id="timeFromField"
    private JFXTimePicker timeFromField; // Value injected by FXMLLoader

    @FXML // fx:id="timeToField"
    private JFXTimePicker timeToField; // Value injected by FXMLLoader

    @FXML // fx:id="emailField"
    private JFXTextField emailField; // Value injected by FXMLLoader

    @FXML // fx:id="passwordField"
    private JFXPasswordField passwordField; // Value injected by FXMLLoader

    @FXML // fx:id="confirmPasswordField"
    private JFXPasswordField confirmPasswordField; // Value injected by FXMLLoader

    @FXML
    void initialize() {
        temp = AdminController.doctor;
        nameField.setText(temp.getName());
        phoneNoField.setText(temp.getPhoneNo());
        specialization.setText(temp.getSpecialization());
        timeFromField.setValue(temp.getTimeFrom());
        timeToField.setValue(temp.getTimeTo());
        emailField.setText(temp.getEmail());
        passwordField.setText(temp.getPassword());
        confirmPasswordField.setText(temp.getPassword());
    }

    @FXML
    void submit(ActionEvent event) {
        String name = nameField.getText();
        String phoneNo = phoneNoField.getText();
        String spec = specialization.getText(); // It can be null/empty
        LocalTime timeFrom = timeFromField.getValue();
        LocalTime timeTo = timeToField.getValue();
        String email = emailField.getText();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        // check if any field other than specialization is empty
        if (name.equals("") || phoneNo.equals("") || email.equals("") || password.equals("")
                || confirmPassword.equals("")) {
            FormValidation.showError("Some fields are empty! Fill them before submitting the form!");
        }
        // use regexes to confirm if the email entered is correct
        else if (!FormValidation.validateEmail(email)) {
            FormValidation.showError("The email is not valid! Try another one!");
        } else if (!FormValidation.validatePhoneNo(phoneNo)) {
            FormValidation.showError("The Phone No is not valid!");
        } else if (!password.equals(confirmPassword)) {
            passwordField.setText("");
            confirmPasswordField.setText("");
            FormValidation.showError("The passwords don't match! Try again");
            // confirm if the password is same and 8 letters long
        } else if (!FormValidation.validatePassword(password)) {
            FormValidation.showError(
                    "The password should contain 8 or more characters with Lower Case, Upper Case and Numbers");
        } else {
            temp.setName(name);
            temp.setPhoneNo(phoneNo);
            temp.setSpecialization(spec);
            temp.setEmail(email);
            temp.setTimeFrom(timeFrom);
            temp.setTimeTo(timeTo);
            temp.setPassword(password);
            new Database().editDoctor(temp);

            // Inform success
            FormValidation.showInformation("Doctor Edited Successfully!");
            Stage stage = (Stage) nameField.getScene().getWindow();
            stage.close();
        }
    }
}
