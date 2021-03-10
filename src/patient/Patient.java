package patient;

import utilities.Database;

public class Patient extends utilities.Person {
    private static int count;
    private int age;
    private String address;
    private String symptoms;
    private String prescription;
    private double bill;
    private boolean checked;

    public Patient(String name, int age, String phoneNo, String address, String symptoms, String prescription,
            double bill) {
        /**
         * Constructor 1: Auto-increments id.
         */
        this.setCount();
        this.setId(count);
        this.setName(name);
        this.setPhoneNo(phoneNo);
        this.setAge(age);
        this.setAddress(address);
        this.setSymptoms(symptoms);
        this.setPrescription(prescription);
        this.setBill(bill);
        this.setStatus("Patient");
        this.setChecked(false);
        count++;
    }

    public Patient(int id, String name, int age, String phoneNo, String address, String symptoms, String prescription,
            double bill, boolean checked) {
        this.setId(id);
        /**
         * Constructor 2: Does not auto-increments id.
         */
        this.setName(name);
        this.setPhoneNo(phoneNo);
        this.setAge(age);
        this.setAddress(address);
        this.setSymptoms(symptoms);
        this.setPrescription(prescription);
        this.setBill(bill);
        this.setStatus("Patient");
        this.setChecked(checked);
    }

    // Getters
    public int getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public String getPrescription() {
        return prescription;
    }

    public double getBill() {
        return bill;
    }

    // END getters
    // Setters
    public boolean isChecked() {
        return checked;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }

    public void setBill(double bill) {
        this.bill = bill;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    // END setters
    public void setCount() {// Absract method implemented here
        Database db = new Database();
        // Gets the last staff Id and increments it
        // and gives it to the count variable
        count = db.getLastPatientId() + 1;
    }

}
