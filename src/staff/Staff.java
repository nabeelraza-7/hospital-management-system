package staff;

import utilities.Database;

public class Staff extends utilities.Person {
    private static int count;

    public Staff(String name, String phoneNo, String status) {
        /**
         * Constructor 1: Auto-increments id.
         */
        this.setCount();
        this.setId(count);
        this.setName(name);
        this.setPhoneNo(phoneNo);
        this.setStatus(status);
        count++;
    }

    public Staff(int id, String name, String phoneNo, String status) {
        /**
         * Constructor 2: Does not auto-increments id.
         */
        this.setId(id);
        this.setName(name);
        this.setPhoneNo(phoneNo);
        this.setStatus(status);
    }

    public void setCount() { // Absract method implemented here
        Database db = new Database();
        //Gets the last staff Id and increments it
        // and gives it to the count variable
        count = db.getLastStaffId() + 1;
    }

}
