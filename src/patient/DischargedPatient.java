package patient;

import java.time.LocalDate;

public class DischargedPatient {
    private String name;
    private int age;
    private String phoneNo;
    private String address;
    private String symptoms;
    private int patientId;
    private LocalDate dateDischarged;
    private String reason;
    private int bedNo;
    
    public int getPatientId() {
        return patientId;
    }
    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }
    public LocalDate getDateDischarged() {
        return dateDischarged;
    }
    public void setDateDischarged(LocalDate dateDischarged) {
        this.dateDischarged = dateDischarged;
    }
    public int getBedNo() {
        return bedNo;
    }
    public void setBedNo(int bedNo) {
        this.bedNo = bedNo;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getPhoneNo() {
        return phoneNo;
    }
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getSymptoms() {
        return symptoms;
    }
    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }
    public DischargedPatient(String name, int age, String phoneNo, String address, String symptoms, int patientId,
            LocalDate dateDischarged, int bedNo, String reason) {
        this.name = name;
        this.age = age;
        this.phoneNo = phoneNo;
        this.address = address;
        this.symptoms = symptoms;
        this.patientId = patientId;
        this.dateDischarged = dateDischarged;
        this.bedNo = bedNo;
        this.reason = reason;
    }

    public String toString() {
        return "(" + patientId + ", " + name + ", " + dateDischarged + ", " + bedNo + ")";
    }
    public String getReason() {
        return reason;
    }
    public void setReason(String reason) {
        this.reason = reason;
    }
}
