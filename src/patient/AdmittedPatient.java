package patient;

import java.time.LocalDate;

public class AdmittedPatient {
    private String name;
    private int age;
    private String phoneNo;
    private String address;
    private String symptoms;
    private int patientId;
    private LocalDate dateAdmitted;
    private int bedNo;
    
    public int getPatientId() {
        return patientId;
    }
    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }
    public LocalDate getDateAdmitted() {
        return dateAdmitted;
    }
    public void setDateAdmitted(LocalDate dateAdmitted) {
        this.dateAdmitted = dateAdmitted;
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
    public AdmittedPatient(String name, int age, String phoneNo, String address, String symptoms, int patientId,
            LocalDate dateAdmitted, int bedNo) {
        this.name = name;
        this.age = age;
        this.phoneNo = phoneNo;
        this.address = address;
        this.symptoms = symptoms;
        this.patientId = patientId;
        this.dateAdmitted = dateAdmitted;
        this.bedNo = bedNo;
    }

    public String toString() {
        return "(" + patientId + ", " + name + ", " + dateAdmitted + ", " + bedNo + ")";
    }
}
