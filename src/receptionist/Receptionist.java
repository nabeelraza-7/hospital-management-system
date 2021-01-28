package receptionist;

import java.io.*;

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

    public static Receptionist readFromFile() {
        /**
         * Needed to use the same Object creation form (The form that pops up to create
         * a person's record in database) to perform editing
         */
        Receptionist temp = null;
        try (BufferedReader br = new BufferedReader(new FileReader("receptionist.txt"))) {
            String line = br.readLine();
            if (line != null) {
                String[] args = line.split(", ");
                temp = new Receptionist(Integer.parseInt(args[0]), args[1], args[2], args[3], args[4]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return temp;
    }

    public void writeToFile() {
        /**
         * Needed to use the same Object creation form (The form that pops up to create
         * a person's record in database) to perform editing
         */
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("receptionist.txt"))) {
            bw.write(
                    getId() + ", " + getName() + ", " + getPhoneNo() + ", " + getEmail() + ", " + getPassword() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
