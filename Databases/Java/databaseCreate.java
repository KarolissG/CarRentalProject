import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class databaseCreate {
    public static void main(String[] args) {
        // database URL
        final String DATABASE_URL = "jdbc:mysql://localhost:3306/carrental";
        String name = "Commander Kurk"; // Values to add to table
        Connection connection = null;
        PreparedStatement pstat = null;
        int i = 0;
        try {
            // establish connection to database
            connection = DriverManager.getConnection(DATABASE_URL, "root", "");
            // create Prepared Statement for updating data in the table
            pstat = connection.prepareStatement("INSERT INTO login (name) VALUES (?)");
            pstat.setString(1, name);
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
