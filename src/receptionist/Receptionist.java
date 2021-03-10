package receptionist;

import utilities.Database;

public class Receptionist extends utilities.AuthenticAccount {
    private static int count;

    public Receptionist(String name, String phoneNo, String email, String password) {
        /**
         * Constructor 1: Auto-increments id.
         */
        this.setCount();
        this.setId(count);
        this.setName(name);
        this.setPhoneNo(phoneNo);
        this.setEmail(email);
        this.setPassword(password);
        this.setStatus("Receptionist");
        count++;
    }

    public Receptionist(int id, String name, String phoneNo, String email, String password) {
        /**
         * Constructor 2: Does not auto-increments id.
         */
        this.setId(id);
        this.setName(name);
        this.setPhoneNo(phoneNo);
        this.setEmail(email);
        this.setPassword(password);
        this.setStatus("Receptionist");
    }

    public void setCount() {// Absract method implemented here
        Database db = new Database();
        // Gets the last staff Id and increments it
        // and gives it to the count variable
        count = db.getLastReceptionistId() + 1;
    }

}
