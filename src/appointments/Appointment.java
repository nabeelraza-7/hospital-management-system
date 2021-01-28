package appointments;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Appointment {
    private String patientName;
    private String doctorName;
    private LocalDate date;
    private LocalTime time;

    public Appointment(String patientName, String doctorName, LocalDate date, LocalTime time) {
	// Simple constructor
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.date = date;
        this.time = time;
    }

    // Getters
    public String getPatientName() {
        return patientName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    // END getters
    // Setters
    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    // END setters
    // For debugging
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-YYYY");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm");
        return "(" + patientName + ", " + doctorName + ", " + formatter.format(date) + ", " + timeFormatter.format(time)
                + ")";
    }
}
