package receptionist;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import admin.AdminController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import utilities.Database;
import utilities.FormValidation;

public class EditReceptionistForm {
    Receptionist temp = null;

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
    void initialize() {
        temp = AdminController.receptionist;
        nameField.setText(temp.getName());
        phoneNoField.setText(temp.getPhoneNo());
        emailField.setText(temp.getEmail());
        passwordField.setText(temp.getPassword());
        confirmPasswordField.setText(temp.getPassword());
    }

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
            temp.setName(name);
            temp.setPhoneNo(phoneNo);
            temp.setEmail(email);
            temp.setPassword(password);
            new Database().editReceptionist(temp);
            // Inform success
            FormValidation.showInformation("Receptionist Edited Successfully!");
            Stage stage = (Stage) nameField.getScene().getWindow();
            stage.close();
        }
    }
}
