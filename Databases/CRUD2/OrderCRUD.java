package CRUD2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderCRUD {
    static Connection connection = null;
    static ResultSet resultSet = null;
    static PreparedStatement pstat = null;
    static PreparedStatement tempPstat = null;

    public static ResultSet RetrieveTable(Connection connectionIn) {
        try {
            pstat = connectionIn.prepareStatement("SELECT * FROM reservation"); // sql query
            resultSet = pstat.executeQuery(); // executes the query

            return resultSet;
        } catch (SQLException sqlException) { // catch errors
            sqlException.printStackTrace();
        }
        return null;
    }// method

    public static void DeleteOrder(Connection connectionIn, int ID) {
        try {
            String query = "DELETE FROM reservation WHERE reservationID = ?";
            pstat = connectionIn.prepareStatement(query);
            pstat.setInt(1, ID);
            pstat.executeUpdate();

        } catch (SQLException sqlException) { // catch errors
            sqlException.printStackTrace();
        } finally {
            if (pstat != null) {
                try {
                    pstat.close();
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }
        }

    }
}
