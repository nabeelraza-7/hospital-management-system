/**
 * Sample Skeleton for 'StaffForm.fxml' Controller Class
 */

package staff;

import com.jfoenix.controls.JFXTextField;

import admin.AdminController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import utilities.Database;
import utilities.FormValidation;

public class EditStaffForm {

    Staff temp = null;

    @FXML // fx:id="nameField"
    private JFXTextField nameField; // Value injected by FXMLLoader

    @FXML // fx:id="phoneNoField"
    private JFXTextField phoneNoField; // Value injected by FXMLLoader

    @FXML // fx:id="status"
    private JFXTextField status; // Value injected by FXMLLoader

    @FXML
    void initialize() {
        temp = AdminController.staff;
        nameField.setText(temp.getName());
        phoneNoField.setText(temp.getPhoneNo());
        status.setText(temp.getStatus());
    }

    @FXML
    void submit(ActionEvent event) {
        String name = nameField.getText();
        String phoneNo = phoneNoField.getText();
        String st = status.getText();
        if (name.equals("") || phoneNo.equals("") || st.equals("")) {
            FormValidation.showError("Some fields are empty! Fill them before submitting the form!");
        } else if (!FormValidation.validatePhoneNo(phoneNo)) {
            FormValidation.showError("The Phone No is not valid!");
        } else {
            temp.setName(name);
            temp.setPhoneNo(phoneNo);
            temp.setStatus(st);
            new Database().editStaff(temp);
            FormValidation.showInformation("Staff Member Edited Successfully!");
            Stage stage = (Stage) nameField.getScene().getWindow();
            stage.close();
        }
    }
}
