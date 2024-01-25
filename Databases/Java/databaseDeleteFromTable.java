import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class databaseDeleteFromTable {
    public static void main(String[] args) {
        // database URL
        final String DATABASE_URL = "jdbc:mysql://localhost:3306/carrental";
        Connection connection = null;
        PreparedStatement pstat = null;
        int i = 0;
        int customerID = 379; // value to remove

        try {
            // establish connection to database
            connection = DriverManager.getConnection(DATABASE_URL, "root", "");
            // create Prepared Statement for updating data in the table
            pstat = connection.prepareStatement("DELETE FROM login WHERE UserID=?");
            pstat.setInt(1, customerID);
            i = pstat.executeUpdate();
            System.out.println(i + " record successfully updated in the table.");
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } finally {
            try {
                pstat.close();
                connection.close();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    } // end main
} // end class
