package utilities;

import admin.Admin;
import appointments.Appointment;
import doctor.Doctor;
import java.util.ArrayList;

import patient.AdmittedPatient;
import patient.DischargedPatient;
import patient.Patient;
import receptionist.Receptionist;
import staff.Staff;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class Database {
    private final String username = "dummy";
    private final String password = "321321Testing";
    private final String url = "jdbc:mysql://127.0.0.1:3306/hms";

    // CREATING TABLES
    public void createAdminTable() {
        /**
         * Admin ( id integer PRIMARY KEY, name text NOT NULL, phoneNo text, email text
         * NOT NULL, password text NOT NULL)
         */
        String query = "CREATE TABLE IF NOT EXISTS Admins ( id integer PRIMARY KEY, "
                + " name VARCHAR(255) NOT NULL, phoneNo VARCHAR(255), email VARCHAR(255) NOT NULL, password VARCHAR(255) NOT NULL);";
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            Statement statement = conn.createStatement();
            statement.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createDoctorsTable() {
        /**
         * Doctors ( id integer PRIMARY KEY, name text NOT NULL, phoneNo text, email
         * text NOT NULL, password text NOT NULL specialization text, timeSlot text NOT
         * NULL)
         */
        String query = "CREATE TABLE IF NOT EXISTS Doctors ( id integer PRIMARY KEY, "
                + " name VARCHAR(55) NOT NULL, phoneNo VARCHAR(55), email VARCHAR(55) NOT NULL, password VARCHAR(55) NOT NULL,\n"
                + " specialization VARCHAR(55), timeFrom TIME NOT NULL, timeTo TIME NOT NULL);";
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            Statement statement = conn.createStatement();
            statement.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createPatientsTable() {
        /**
         * Patients ( id integer PRIMARY KEY, name text NOT NULL, phoneNo text, age
         * integer, address text, symptoms text, prescription text, bill real)
         */
        String query = "CREATE TABLE IF NOT EXISTS Patients ( id INT PRIMARY KEY, "
                + " name VARCHAR(55) NOT NULL,  age INT, phoneNo VARCHAR(55), address VARCHAR(55), symptoms VARCHAR(55), "
                + " prescription VARCHAR(1024), bill DECIMAL, checked BOOLEAN);";
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            Statement statement = conn.createStatement();
            statement.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createReceptionistsTable() {
        /**
         * Receptionists ( id integer PRIMARY KEY, name text NOT NULL, phoneNo text,
         * email text NOT NULL, password text NOT NULL)
         */
        String query = "CREATE TABLE IF NOT EXISTS Receptionists ( id INT PRIMARY KEY, "
                + " name VARCHAR(55) NOT NULL, phoneNo VARCHAR(55), email VARCHAR(55) NOT NULL, password VARCHAR(55) NOT NULL);";
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            Statement statement = conn.createStatement();
            statement.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createStaffTable() {
        /**
         * Staff ( id integer PRIMARY KEY, name text NOT NULL, phoneNo text, status text
         * NOT NULL)
         */
        String query = "CREATE TABLE IF NOT EXISTS Staff ( id INT PRIMARY KEY, "
                + " name VARCHAR(55) NOT NULL, phoneNo VARCHAR(55), status VARCHAR(55) NOT NULL);";
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            Statement statement = conn.createStatement();
            statement.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // END CREATING TABLE

    // ADDING ACCOUNTS
    public void addAdmin(Admin admin) {
        String query = "INSERT INTO Admins (id,name,phoneNo,email,password) VALUES (?,?,?,?,?)";
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, admin.getId());
            statement.setString(2, admin.getName());
            statement.setString(3, admin.getPhoneNo());
            statement.setString(4, admin.getEmail());
            statement.setString(5, admin.getPassword());
            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addDoctor(Doctor doctor) {
        Time timeFrom = Time.valueOf(doctor.getTimeFrom());
        Time timeTo = Time.valueOf(doctor.getTimeTo());
        String query = "INSERT INTO Doctors (id,name,phoneNo,email,password,specialization,timeFrom,timeTo) VALUES (?,?,?,?,?,?,?,?)";
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, doctor.getId());
            statement.setString(2, doctor.getName());
            statement.setString(3, doctor.getPhoneNo());
            statement.setString(4, doctor.getEmail());
            statement.setString(5, doctor.getPassword());
            statement.setString(6, doctor.getSpecialization());
            statement.setTime(7, timeFrom);
            statement.setTime(8, timeTo);
            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addPatient(Patient patient) {
        String query = "INSERT INTO Patients (id,name,age,phoneNo,address,symptoms,prescription,bill,checked) VALUES (?,?,?,?,?,?,?,?,?)";
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, patient.getId());
            statement.setString(2, patient.getName());
            statement.setInt(3, patient.getAge());
            statement.setString(4, patient.getPhoneNo());
            statement.setString(5, patient.getAddress());
            statement.setString(6, patient.getSymptoms());
            statement.setString(7, patient.getPrescription());
            statement.setDouble(8, patient.getBill());
            statement.setBoolean(9, patient.isChecked());
            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addReceptionist(Receptionist receptionist) {
        String query = "INSERT INTO Receptionists (id,name,phoneNo,email,password) VALUES (?,?,?,?,?)";
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, receptionist.getId());
            statement.setString(2, receptionist.getName());
            statement.setString(3, receptionist.getPhoneNo());
            statement.setString(4, receptionist.getEmail());
            statement.setString(5, receptionist.getPassword());
            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addStaff(Staff staff) {
        String query = "INSERT INTO Staff (id,name,phoneNo,status) VALUES (?,?,?,?)";
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, staff.getId());
            statement.setString(2, staff.getName());
            statement.setString(3, staff.getPhoneNo());
            statement.setString(4, staff.getStatus());
            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // END ADDING ACCOUNTS

    // UPDATING ACCOUNTS
    public void editAdmin(Admin admin) {
        String query = "UPDATE Admins SET name=?, phoneNo=?, email=?, password=? WHERE id=?";
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, admin.getName());
            statement.setString(2, admin.getPhoneNo());
            statement.setString(3, admin.getEmail());
            statement.setString(4, admin.getPassword());
            statement.setInt(5, admin.getId());
            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void editDoctor(Doctor doctor) {
        Time timeFrom = Time.valueOf(doctor.getTimeFrom());
        Time timeTo = Time.valueOf(doctor.getTimeTo());
        String query = "UPDATE Doctors SET name=?,phoneNo=?,email=?,password=?,specialization=?,timeFrom=?,timeTo=? WHERE id=?";
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, doctor.getName());
            statement.setString(2, doctor.getPhoneNo());
            statement.setString(3, doctor.getEmail());
            statement.setString(4, doctor.getPassword());
            statement.setString(5, doctor.getSpecialization());
            statement.setTime(6, timeFrom);
            statement.setTime(7, timeTo);
            statement.setInt(8, doctor.getId());
            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void editPatient(Patient patient) {
        String query = "UPDATE Patients SET name=?,age=?,phoneNo=?,address=?,symptoms=?,prescription=?,bill=?,checked=? WHERE id=?";
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, patient.getName());
            statement.setInt(2, patient.getAge());
            statement.setString(3, patient.getPhoneNo());
            statement.setString(4, patient.getAddress());
            statement.setString(5, patient.getSymptoms());
            statement.setString(6, patient.getPrescription());
            statement.setDouble(7, patient.getBill());
            statement.setBoolean(8, patient.isChecked());
            statement.setInt(9, patient.getId());
            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void editReceptionist(Receptionist receptionist) {
        String query = "UPDATE Receptionists SET name=?, phoneNo=?, email=?, password=? WHERE id=?";
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, receptionist.getName());
            statement.setString(2, receptionist.getPhoneNo());
            statement.setString(3, receptionist.getEmail());
            statement.setString(4, receptionist.getPassword());
            statement.setInt(5, receptionist.getId());
            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void editStaff(Staff staff) {
        String query = "UPDATE Staff SET name=?, phoneNo=?,status=? WHERE id=?";
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, staff.getName());
            statement.setString(2, staff.getPhoneNo());
            statement.setString(3, staff.getStatus());
            statement.setInt(4, staff.getId());
            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // END UPDATING ACCOUNTS

    // GETTING ACCOUNTS
    public Admin getAdmin(int id) {
        String query = "SELECT * FROM Admins WHERE id=?";
        Admin temp = null;
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet results = statement.executeQuery();
            while (results.next()) {
                temp = new Admin(results.getInt("id"), results.getString("name"), results.getString("phoneNo"),
                        results.getString("email"), results.getString("password"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return temp;
    }

    public Doctor getDoctor(int id) {
        String query = "SELECT * FROM Doctors WHERE id=?";
        Doctor temp = null;
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet results = statement.executeQuery();
            while (results.next()) {
                temp = new Doctor(results.getInt("id"), results.getString("name"), results.getString("phoneNo"),
                        results.getString("email"), results.getString("password"), results.getString("specialization"),
                        results.getTime("timeFrom").toLocalTime(), results.getTime("timeTo").toLocalTime());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return temp;
    }

    public Patient getPatient(int id) {
        String query = "SELECT * FROM Patients WHERE id=?";
        Patient temp = null;
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet results = statement.executeQuery();
            while (results.next()) {
                temp = new Patient(results.getInt("id"), results.getString("name"), results.getInt("age"),
                        results.getString("phoneNo"), results.getString("address"), results.getString("symptoms"),
                        results.getString("prescription"), results.getDouble("bill"), results.getBoolean("checked"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return temp;
    }

    public Receptionist getReceptionist(int id) {
        String query = "SELECT * FROM Receptionists WHERE id=?";
        Receptionist temp = null;
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet results = statement.executeQuery();
            while (results.next()) {
                temp = new Receptionist(results.getInt("id"), results.getString("name"), results.getString("phoneNo"),
                        results.getString("email"), results.getString("password"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return temp;
    }

    public Staff getStaff(int id) {
        String query = "SELECT * FROM Staff WHERE id=?";
        Staff temp = null;
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet results = statement.executeQuery();
            while (results.next()) {
                temp = new Staff(results.getInt("id"), results.getString("name"), results.getString("phoneNo"),
                        results.getString("status"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return temp;
    }
    // END GETTING ACCOUNTS

    // GETTING ALL ACCOUNTS
    public ArrayList<Admin> getAdmins() {
        String query = "SELECT * FROM Admins";
        Admin temp = null;
        ArrayList<Admin> list = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            Statement statement = conn.createStatement();
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                temp = new Admin(results.getInt("id"), results.getString("name"), results.getString("phoneNo"),
                        results.getString("email"), results.getString("password"));
                list.add(temp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<Doctor> getDoctors() {
        String query = "SELECT * FROM Doctors";
        Doctor temp = null;
        ArrayList<Doctor> list = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            Statement statement = conn.createStatement();
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                temp = new Doctor(results.getInt("id"), results.getString("name"), results.getString("phoneNo"),
                        results.getString("email"), results.getString("password"), results.getString("specialization"),
                        results.getTime("timeFrom").toLocalTime(), results.getTime("timeTo").toLocalTime());
                list.add(temp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<Patient> getPatients() {
        String query = "SELECT * FROM Patients";
        Patient temp = null;
        ArrayList<Patient> list = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            Statement statement = conn.createStatement();
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                temp = new Patient(results.getInt("id"), results.getString("name"), results.getInt("age"),
                        results.getString("phoneNo"), results.getString("address"), results.getString("symptoms"),
                        results.getString("prescription"), results.getDouble("bill"), results.getBoolean("checked"));
                list.add(temp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<Patient> getUncheckedPatients() {
        String query = "SELECT * FROM Patients WHERE checked = 0";
        Patient temp = null;
        ArrayList<Patient> list = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            Statement statement = conn.createStatement();
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                temp = new Patient(results.getInt("id"), results.getString("name"), results.getInt("age"),
                        results.getString("phoneNo"), results.getString("address"), results.getString("symptoms"),
                        results.getString("prescription"), results.getDouble("bill"), results.getBoolean("checked"));
                list.add(temp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<Receptionist> getReceptionists() {
        String query = "SELECT * FROM Receptionists";
        Receptionist temp = null;
        ArrayList<Receptionist> list = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            Statement statement = conn.createStatement();
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                temp = new Receptionist(results.getInt("id"), results.getString("name"), results.getString("phoneNo"),
                        results.getString("email"), results.getString("password"));
                list.add(temp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<Staff> getStaffMembers() {
        String query = "SELECT * FROM Staff";
        Staff temp = null;
        ArrayList<Staff> list = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            Statement statement = conn.createStatement();
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                temp = new Staff(results.getInt("id"), results.getString("name"), results.getString("phoneNo"),
                        results.getString("status"));
                list.add(temp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    // END GETTING ALL ACCOUNTS

    // DELETING ACCOUNTS
    public void deleteAdmin(int id) {
        String query = "DELETE FROM Admins WHERE id=?";
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id);
            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteDoctor(int id) {
        String query = "DELETE FROM Doctors WHERE id=?";
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id);
            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deletePatient(int id) {
        String query = "DELETE FROM Patients WHERE id=?";
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id);
            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteReceptionist(int id) {
        String query = "DELETE FROM Receptionists WHERE id=?";
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id);
            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteStaff(int id) {
        String query = "DELETE FROM Staff WHERE id=?";
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id);
            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // END DELETING ACCOUNTS

    // GETTING THE LAST ACCOUNT IN TABLES
    public int getLastAdminId() {
        String query = "SELECT * FROM Admins WHERE id = (SELECT MAX(id)  FROM Admins);";
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            Statement statement = conn.createStatement();
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                return results.getInt("id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getLastDoctorId() {
        String query = "SELECT * FROM Doctors WHERE id = (SELECT MAX(id)  FROM Doctors);";
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            Statement statement = conn.createStatement();
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                return results.getInt("id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getLastPatientId() {
        String query = "SELECT * FROM Patients WHERE id = (SELECT MAX(id)  FROM Patients);";
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            Statement statement = conn.createStatement();
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                return results.getInt("id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getLastReceptionistId() {
        String query = "SELECT * FROM Receptionists WHERE id = (SELECT MAX(id)  FROM Receptionists);";
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            Statement statement = conn.createStatement();
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                return results.getInt("id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getLastStaffId() {
        String query = "SELECT * FROM Staff WHERE id = (SELECT MAX(id)  FROM Staff);";
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            Statement statement = conn.createStatement();
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                return results.getInt("id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    // END GETTING THE LAST ACCOUNT IN TABLES

    // ALL ABOUT APPOINTMENTS
    // Create Appointments table
    public void createAppointmentsTable() {
        String query = "CREATE TABLE IF NOT EXISTS Appointments ( id integer PRIMARY KEY AUTO_INCREMENT, "
                + "patient_name VARCHAR(255) NOT NULL, doctor_name VARCHAR(255) NOT NULL, date_op DATE NOT NULL, time_op TIME NOT NULL);";
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            Statement statement = conn.createStatement();
            statement.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    } // END creating Appointments table

    // Add appointments
    public void addAppointment(Appointment ap) {
        Date date_op = Date.valueOf(ap.getDate());
        Time time_op = Time.valueOf(ap.getTime());
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String query = "INSERT INTO Appointments (patient_name,doctor_name,date_op,time_op) VALUES (?,?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, ap.getPatientName());
            stmt.setString(2, ap.getDoctorName());
            stmt.setDate(3, date_op);
            stmt.setTime(4, time_op);
            stmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    } // END adding appointment
      // Edit appointments

    public void deleteAppointment(Appointment ap) {
        Date date_op = Date.valueOf(ap.getDate());
        Time time_op = Time.valueOf(ap.getTime());
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String query = "DELETE FROM Appointments WHERE patient_name=? AND doctor_name=? AND date_op=? AND time_op=?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, ap.getPatientName());
            stmt.setString(2, ap.getDoctorName());
            stmt.setDate(3, date_op);
            stmt.setTime(4, time_op);
            stmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Get all the appointments
    public ArrayList<Appointment> getAppointments() {
        String query = "SELECT * FROM Appointments";
        Appointment temp = null;
        ArrayList<Appointment> list = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            Statement statement = conn.createStatement();
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                temp = new Appointment(results.getString("patient_name").toString(),
                        results.getString("doctor_name").toString(), results.getDate("date_op").toLocalDate(),
                        results.getTime("time_op").toLocalTime());
                list.add(temp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean checkAppointment(String docName, LocalDate date, LocalTime time) {
        String query = "SELECT * FROM Appointments WHERE doctor_name=? AND date_op=? AND time_op=?";
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, docName);
            stmt.setDate(2, Date.valueOf(date));
            stmt.setTime(3, Time.valueOf(time));
            ResultSet results = stmt.executeQuery();
            return results.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    // ENDING appointments

    public boolean emailExists(String email) {
        boolean checked_admins = false;
        boolean checked_doctor = false;
        boolean checked_receptionists = false;
        String query = "SELECT * FROM Admins WHERE email=?";
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            // Checking Admins table
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, email);
            ResultSet results = statement.executeQuery();
            checked_admins = results.next();
            // Checking Doctors table
            query = "SELECT * FROM Doctors WHERE email=?";
            statement = conn.prepareStatement(query);
            statement.setString(1, email);
            results = statement.executeQuery();
            checked_doctor = results.next();
            // Checking Receptionists table
            query = "SELECT * FROM Receptionists WHERE email=?";
            statement = conn.prepareStatement(query);
            statement.setString(1, email);
            results = statement.executeQuery();
            checked_receptionists = results.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return checked_admins || checked_doctor || checked_receptionists;
    }

    public int getTotalBeds() {
        String query = "SELECT * FROM Hospital WHERE id = 1;";
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(query);
            if (result.next()) {
                int totalBeds = result.getInt("totalBeds");
                return totalBeds;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    public int getBeds() {
        String query = "SELECT * FROM Hospital WHERE id = 1;";
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(query);
            if (result.next()) {
                int currentNoBeds = result.getInt("currentNoBeds");
                return currentNoBeds;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    public void setTotalBeds(int number) {
        String query = "UPDATE Hospital SET totalBeds=? WHERE id = 1;";
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, number);
            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setBeds(int number) {
        String query = "UPDATE Hospital SET currentNoBeds=? WHERE id = 1;";
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, number);
            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createAdmittedTable() {
        /**
         * Admitted ( patient_id integer PRIMARY KEY, name text NOT NULL, phoneNo text,
         * age integer, address text, symptoms text, prescription text, bill real)
         */
        String query = "CREATE TABLE IF NOT EXISTS Admitted ( patient_id INT PRIMARY KEY, "
                + " dateAdmitted DATE NOT NULL, bedNo INT NOT NULL);";
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            Statement statement = conn.createStatement();
            statement.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createDischargedTable() {
        /**
         * Admitted ( patient_id integer PRIMARY KEY, name text NOT NULL, phoneNo text,
         * age integer, address text, symptoms text, prescription text, bill real)
         */
        String query = "CREATE TABLE IF NOT EXISTS Discharged ( patient_id INT PRIMARY KEY, "
                + " dateDischarged DATE NOT NULL, bedNo INT NOT NULL, reason VARCHAR(55) );";
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            Statement statement = conn.createStatement();
            statement.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void admitPatient(int id) {
        Date date = Date.valueOf(LocalDate.now());
        String query = "INSERT INTO Admitted (patient_id,dateAdmitted,bedNo) VALUES (?,?,?)";
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id);
            statement.setDate(2, date);
            statement.setInt(3, getBeds());
            setBeds(getBeds() - 1);
            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dischargePatient(int id, String reason) {
        Date date = Date.valueOf(LocalDate.now());
        String query = "INSERT INTO Discharged (patient_id,dateDischarged,bedNo, reason) VALUES (?,?,?,?)";
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id);
            statement.setDate(2, date);
            statement.setInt(3, getBeds());
            setBeds(getBeds() + 1);
            statement.setString(4, reason);
            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<AdmittedPatient> getAdmittedPatients() {
        String query = "SELECT id, name, age, phoneNo, address, symptoms, dateAdmitted, bedNo FROM patients INNER JOIN admitted ON id = patient_id";
        AdmittedPatient temp = null;
        ArrayList<AdmittedPatient> list = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            Statement statement = conn.createStatement();
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                temp = new AdmittedPatient(results.getString("name"), results.getInt("age"),
                        results.getString("phoneNo"), results.getString("address"), results.getString("symptoms"),
                        results.getInt("id"), results.getDate("dateAdmitted").toLocalDate(), results.getInt("bedNo"));
                list.add(temp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<DischargedPatient> getDischargedPatients() {
        String query = "SELECT id, name, age, phoneNo, address, symptoms, dateDischarged, bedNo, reason " +
        "FROM patients INNER JOIN discharged ON id = patient_id";
        DischargedPatient temp = null;
        ArrayList<DischargedPatient> list = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            Statement statement = conn.createStatement();
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                temp = new DischargedPatient(results.getString("name"), results.getInt("age"),
                        results.getString("phoneNo"), results.getString("address"), results.getString("symptoms"),
                        results.getInt("id"), results.getDate("dateAdmitted").toLocalDate(),
                        results.getInt("bedNo"), results.getString("reason"));
                list.add(temp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
