import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class databaseRetrieve {
    public static void main(String[] args) {
        // database URL
        final String DATABASE_URL = "jdbc:mysql://localhost:3306/carrental";
        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement pstat = null;
        try {
            // establish connection to database
            connection = DriverManager.getConnection(DATABASE_URL, "root", "");
            // create Prepared Statement for updating data in the table
            pstat = connection.prepareStatement("SELECT * FROM customer");
            resultSet = pstat.executeQuery();

            ResultSetMetaData metaData = resultSet.getMetaData();
            int numberOfColumns = metaData.getColumnCount();
            
            System.out.println("\n--Login Table-- \n");
            for (int i = 1; i <= numberOfColumns; i++)
                System.out.print(metaData.getColumnName(i) + "\t");

            System.out.println();
            while (resultSet.next()) {
                for (int i = 1; i <= numberOfColumns; i++)
                    System.out.print(resultSet.getObject(i) + "\t");
                System.out.println();
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } finally {
            try {
                resultSet.close();
                pstat.close();
                connection.close();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

    } // end main
} // end class
