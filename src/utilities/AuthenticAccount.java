package utilities;

public abstract class AuthenticAccount extends Person {
    /**
     * This class will be used by all the classes that needs login access. These
     * classes are: Admin, Doctor, Receptionist
     */
    private String email;
    private String password;

    // Getters
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
    // END getters

    // Setters
    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    // END setters
}
