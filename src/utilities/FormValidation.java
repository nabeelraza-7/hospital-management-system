package utilities;

import java.util.Optional;
import java.util.regex.*;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class FormValidation {
    /**
     * An Alert contains 3 parts: The header The content text The confirmation
     * buttons
     */
    // public static void main(String[] args) {
    // // String[] testEmails = { "user@domain.com", "user@domain.co.in",
    // "user1@domain.com", "user.name@domain.com",
    // // "user_name@domain.co.in", "user-name@domain.co.in", "user@domaincom",
    // "yahoo.com" };
    // // for (String s : testEmails) {
    // // System.out.println("Testing: " + s + " answer: " + validateEmail(s));
    // // }
    // // System.out.println(validatePassword("321321Testing"));
    // // System.out.println("Testing 321321Testing:\t" +
    // validatePhoneNo("321321Testing"));
    // // System.out.println("Testing 0300-4755991:\t" +
    // validatePhoneNo("0300-4755991"));
    // // System.out.println("Testing 0310-4755991:\t" +
    // validatePhoneNo("0310-4755991"));
    // // System.out.println("Testing 0320-4755991:\t" +
    // validatePhoneNo("0320-4755991"));
    // // System.out.println("Testing 0330-4755991:\t" +
    // validatePhoneNo("0330-4755991"));
    // // System.out.println("Testing 0340-4755991:\t" +
    // validatePhoneNo("0340-4755991"));
    // // System.out.println("Testing 0350-4755991:\t" +
    // validatePhoneNo("0350-4755991"));
    // // System.out.println("Testing 0360-4755991:\t" +
    // validatePhoneNo("0360-4755991"));
    // // System.out.println("Testing 0370-4755991:\t" +
    // validatePhoneNo("0370-4755991"));
    // // System.out.println("Testing 0301-4755991:\t" +
    // validatePhoneNo("0301-4755991"));
    // // System.out.println("Testing 0312-4755991:\t" +
    // validatePhoneNo("0312-4755991"));
    // // System.out.println("Testing 0323-4755991:\t" +
    // validatePhoneNo("0323-4755991"));
    // // System.out.println("Testing 0334-4755991:\t" +
    // validatePhoneNo("0334-4755991"));
    // // System.out.println("Testing 0345-4755991:\t" +
    // validatePhoneNo("0345-4755991"));
    // // System.out.println("Testing 0356-4755991:\t" +
    // validatePhoneNo("0356-4755991"));
    // // System.out.println("Testing 0367-4755991:\t" +
    // validatePhoneNo("0367-4755991"));
    // // System.out.println("Testing 0378-4755991:\t" +
    // validatePhoneNo("0378-4755991"));
    // }

    public static boolean validateEmail(String email) {
        // The regex below will check if the email starts with
        // an upper case A-Z, a lower case a-z, 0-9, or a plus sign, underscore, dot,
        // dash.
        // and if the letters appear 1 or more times. (indicated by the + dign after
        // square brakets)
        // @ symbol for the @ in email
        // (.+) shows any thing can come after the @ symbol
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matches = pattern.matcher(email);
        return (boolean) matches.matches();
    }

    public static boolean validatePassword(String password) {
        boolean lower_found = false;
        boolean upper_found = false;
        boolean number_found = false;
        String[] lower_letters = "abcdefghijklmnopqrstuvwxyz".split("");
        String[] upper_letters = "abcdefghijklmnopqrstuvwxyz".toUpperCase().split("");
        String[] numbers = "0123456789".split("");
        if (password.length() < 8)
            return false;
        for (int i = 0; i < lower_letters.length && !lower_found; i++) {
            if (password.contains(lower_letters[i])) {
                lower_found = true;
            }
        }
        for (int i = 0; i < upper_letters.length && !upper_found; i++) {
            if (password.contains(upper_letters[i])) {
                upper_found = true;
            }
        }
        for (int i = 0; i < numbers.length && !number_found; i++) {
            if (password.contains(numbers[i])) {
                number_found = true;
            }
        }
        return lower_found && upper_found && number_found;
    }

    public static void showError(String message) {
        // This will be used to display exceptions/reports on the screen
        Alert a = new Alert(AlertType.ERROR);
        a.setHeaderText(null);
        a.setContentText(message);
        Stage stage = (Stage) a.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("/style/hospital.png"));
        a.show();
    }

    public static void showError(String title, String message) {
        // This will be used to display exceptions/reports on the screen
        Alert a = new Alert(AlertType.ERROR);
        a.setTitle(title);
        a.setHeaderText(null);
        a.setContentText(message);
        Stage stage = (Stage) a.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("/style/hospital.png"));
        a.show();
    }

    public static void showInformation(String info) {
        // This will be used to display exceptions/reports on the screen
        Alert a = new Alert(AlertType.INFORMATION);
        // a.getIcon().add(new Image("/style/hospital.png"));
        a.setHeaderText(null);
        a.setContentText(info);
        Stage stage = (Stage) a.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("/style/hospital.png"));
        a.show();
    }

    public static void showInformation(String title, String info) {
        // This will be used to display exceptions/reports on the screen
        Alert a = new Alert(AlertType.INFORMATION);
        a.setHeaderText(null);
        a.setTitle(title);
        a.setContentText(info);
        Stage stage = (Stage) a.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("/style/hospital.png"));
        a.show();
    }

    public static boolean validatePhoneNo(String phoneNo) {
        String regex = "^03[0-5][0-9]-\\d{7}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matches = pattern.matcher(phoneNo);
        return (boolean) matches.matches();
    }

    public static boolean showConfirmationMessage(String msg) {
        // This will be used to display exceptions/reports on the screen
        Alert a = new Alert(AlertType.CONFIRMATION);
        // a.getIcon().add(new Image("/style/hospital.png"));
        a.setHeaderText(null);
        a.setContentText(msg);
        Stage stage = (Stage) a.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("/style/hospital.png"));
        Optional<ButtonType> result = a.showAndWait();
        if (result.get() == ButtonType.OK) {
            return true;
        } else {
            return false;
        }
    }
}
