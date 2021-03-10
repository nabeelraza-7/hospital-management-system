package utilities;

public class Management {
    public static void main(String[] args) {
        Database db = new Database();
        // db.createDischargedTable();
        db.dischargePatient(12, "Recovered");
    }
}
