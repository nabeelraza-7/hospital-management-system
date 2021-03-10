package doctor;

import java.time.LocalTime;

import utilities.Database;

public class Doctor extends utilities.AuthenticAccount {
    private static int count;
    private String specialization;
    private LocalTime timeFrom;
    private LocalTime timeTo;

    public Doctor(String name, String phoneNo, String email, String password, String specialization, LocalTime timeFrom,
            LocalTime timeTo) {
        /**
         * Constructor 1: Auto-increments id.
         */
        this.setCount();
        this.setId(count);
        this.setName(name);
        this.setPhoneNo(phoneNo);
        this.setEmail(email);
        this.setPassword(password);
        this.specialization = specialization;
        this.timeFrom = timeFrom;
        this.timeTo = timeTo;
        this.setStatus("Doctor");
        count++;
    }

    public Doctor(int id, String name, String phoneNo, String email, String password, String specialization,
            LocalTime timeFrom, LocalTime timeTo) {
        /**
         * Constructor 2: Does not auto-increments id.
         */
        this.setId(id);
        this.setName(name);
        this.setPhoneNo(phoneNo);
        this.setEmail(email);
        this.setPassword(password);
        this.specialization = specialization;
        this.timeFrom = timeFrom;
        this.timeTo = timeTo;
        this.setStatus("Doctor");
    }

    // Getters
    public String getSpecialization() {
        return specialization;
    }

    public LocalTime getTimeFrom() {
        return timeFrom;
    }

    public LocalTime getTimeTo() {
        return timeTo;
    }

    // END getters
    // Setters
    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public void setTimeFrom(LocalTime timeFrom) {
        this.timeFrom = timeFrom;
    }

    public void setTimeTo(LocalTime timeTo) {
        this.timeTo = timeTo;
    }

    // END setters
    public void setCount() {// Absract method implemented here
        Database db = new Database();
        // Gets the last staff Id and increments it
        // and gives it to the count variable
        count = db.getLastDoctorId() + 1;
    }
}
