/**
 * Sample Skeleton for 'StaffForm.fxml' Controller Class
 */

package staff;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import utilities.Database;
import utilities.FormValidation;

public class StaffForm {

    @FXML // fx:id="nameField"
    private JFXTextField nameField; // Value injected by FXMLLoader

    @FXML // fx:id="phoneNoField"
    private JFXTextField phoneNoField; // Value injected by FXMLLoader

    @FXML // fx:id="status"
    private JFXTextField status; // Value injected by FXMLLoader

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
            new Database().addStaff(new Staff(name, phoneNo, st));
            nameField.setText("");
            phoneNoField.setText("");
            status.setText("");
            FormValidation.showInformation("Staff Member Added Successfully!");
        }
    }
}
