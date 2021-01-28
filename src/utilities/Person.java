package utilities;

abstract public class Person {
    /**
     * Parent class for every account that we need to store in the database. That
     * means, every class extending this abstract class will have to be another
     * abstract class or they have to implement the abstract methods
     */
    private int id;
    private String name;
    private String phoneNo;
    private String status;

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    // END getters
    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
    // END setters

    // Following are the abstract methods that needs to be implemented
    // by Admin, Doctor, Patient, Receptionist, staff
    public abstract void setCount();

    // Just for debugging purposes
    public String toString() {
        return "(" + getId() + "\t" + getName() + "\t" + getStatus() + ")";
    }
}
