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
     // Retrieve one row

    public static ResultSet RetrieveOrder(Connection connectionIn, int ID) {
        try {
            pstat = connectionIn.prepareStatement("SELECT * FROM reservation WHERE reservationID = ?"); // sql query
            pstat.setInt(1, ID);
            resultSet = pstat.executeQuery(); // executes the query
            return resultSet;
        } catch (SQLException sqlException) { // catch errors
            sqlException.printStackTrace();
        }
        return null;
    }// method

    public static int DeleteOrder(Connection connectionIn, int ID) {
        try {
            String query = "DELETE FROM reservation WHERE reservationID = ?";
            pstat = connectionIn.prepareStatement(query);
            pstat.setInt(1, ID);
            int i = pstat.executeUpdate();
            return i;
        } catch (SQLException sqlException) { // catch errors
            sqlException.printStackTrace();
            return -1;
        }
    }

    public static void UpdateOrder(Connection connectionIn, int idIn, String rentDate, String days, String pickedUp,
            String returned, String datepickedUp, String registration) {
        try {
            // Create query for update
            String query = "UPDATE reservation SET rentalDate=?, daysRented=?, ifPickedUp=?, ifReturned=?, datePickedUp=?, regPlate=? WHERE reservationID=?";
            PreparedStatement pstat = connectionIn.prepareStatement(query);

            // Set parameter values
            pstat.setString(1, rentDate);
            pstat.setString(2, days);
            pstat.setString(3, pickedUp);
            pstat.setString(4, returned);
            pstat.setString(5, datepickedUp);
            pstat.setString(6, registration);
            pstat.setInt(7, idIn);

            // Execute the update operation
            pstat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                // Close PreparedStatement
                if (pstat != null) {
                    pstat.close();
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    public static ResultSet getRegistrationPlates(Connection connection) {
        ResultSet resultSet = null;
        try {
            String query = "SELECT regPlate FROM vehicle";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }
}
