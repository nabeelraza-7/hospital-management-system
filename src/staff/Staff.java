package staff;

import java.io.*;

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

    public void writeToFile() {
        /**
         * Needed to use the same Object 
         * creation form (The form that pops up to create a 
         * person's record in database) to perform editing
         */
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("staff.txt"))) {
            bw.write(getId() + ", " + getName() + ", " + getPhoneNo() + ", " + getStatus() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Staff readFromFile() {
        /**
         * Needed to use the same Object 
         * creation form (The form that pops up to create a 
         * person's record in database) to perform editing
         */
        Staff temp = null;
        try (BufferedReader br = new BufferedReader(new FileReader("staff.txt"))) {
            String line = br.readLine();
            if (line != null) {
                String[] args = line.split(", ");
                temp = new Staff(Integer.parseInt(args[0]), args[1], args[2], args[3]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return temp;
    }

}
