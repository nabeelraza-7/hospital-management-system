/**
 * Sample Skeleton for 'ReceptionistForm.fxml' Controller Class
 */

package receptionist;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import utilities.Database;
import utilities.FormValidation;

public class ReceptionistForm {

    @FXML // fx:id="nameField"
    private JFXTextField nameField; // Value injected by FXMLLoader

    @FXML // fx:id="phoneNoField"
    private JFXTextField phoneNoField; // Value injected by FXMLLoader

    @FXML // fx:id="emailField"
    private JFXTextField emailField; // Value injected by FXMLLoader

    @FXML // fx:id="passwordField"
    private JFXPasswordField passwordField; // Value injected by FXMLLoader

    @FXML // fx:id="confirmPasswordField"
    private JFXPasswordField confirmPasswordField; // Value injected by FXMLLoader

    @FXML
    void submit(ActionEvent event) {
        String name = nameField.getText();
        String phoneNo = phoneNoField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();
        // check if any field other than specialization is empty
        if (name.equals("") || phoneNo.equals("") || email.equals("") || password.equals("")
                || confirmPassword.equals("")) {
            FormValidation.showError("Some fields are empty! Fill them before submitting the form!");
        } // use regexes to confirm if the email entered is correct
        else if (!FormValidation.validateEmail(email)) {
            FormValidation.showError("The email is not valid! Try another one!");
        } else if (new Database().emailExists(email)) {
            FormValidation.showError("The email already exists! Try another one!");
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
            // create a doctor object and save it on the database
            new Database().addReceptionist(new Receptionist(name, phoneNo, email, password));
            // clear all the fields
            nameField.setText("");
            phoneNoField.setText("");
            emailField.setText("");
            passwordField.setText("");
            confirmPasswordField.setText("");
            // Inform success
            FormValidation.showInformation("Receptionist Added Successfully!");
        }
    }
}
