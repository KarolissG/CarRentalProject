package CRUD2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerCRUD {
    static ResultSet resultSet = null;
    static PreparedStatement pstat = null;
    static PreparedStatement tempPstat = null;

    public static ResultSet RetrieveTable(Connection connectionIn) {
        try {
            pstat = connectionIn.prepareStatement("SELECT * FROM customer"); // sql query
            resultSet = pstat.executeQuery(); // executes the query

            return resultSet;
        } catch (SQLException sqlException) { // catch errors
            sqlException.printStackTrace();
        }
        return null;
    }// method

    public static ResultSet RetrieveCustomer(Connection connectionIn, int ID) {
        try {
            pstat = connectionIn.prepareStatement("SELECT * FROM customer WHERE customerID = ?"); // sql query
            pstat.setInt(1, ID);
            resultSet = pstat.executeQuery(); // executes the query
            return resultSet;
        } catch (SQLException sqlException) { // catch errors
            sqlException.printStackTrace();
        }
        return null;
    }// method

    public static Boolean LoginCheck(Connection connectionIn, String email, String password) {
        try {
            pstat = connectionIn.prepareStatement("SELECT * FROM customer WHERE email = ? AND password = ?");
            pstat.setString(1, email);
            pstat.setString(2, password);
            resultSet = pstat.executeQuery(); // executes the query
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException sqlException) { // catch errors
            sqlException.printStackTrace();
        }
        return false;
    }

    public static int DeleteCustomer(Connection connectionIn, int ID) {
        try {
            String query = "DELETE FROM customer WHERE customerID = ?";
            pstat = connectionIn.prepareStatement(query);
            pstat.setInt(1, ID);
            int i = pstat.executeUpdate();
            return i;

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

    public static void RegisterCustomer(Connection connectionIn, String name, String password, String eircode,
            String phoneNum, String DOB, String email, String driverNum, String review) {
        try {
            // Create PreparedStatement with SQL query
            String query = "INSERT INTO customer (name, password, eircode, phoneNo, DOB, email, driverNum, review) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            pstat = connectionIn.prepareStatement(query);

            // Set values for PreparedStatement parameters
            pstat.setString(1, name);
            pstat.setString(2, password);
            pstat.setString(3, eircode);
            pstat.setString(4, phoneNum);
            pstat.setString(5, DOB);
            pstat.setString(6, email);
            pstat.setString(7, driverNum);
            pstat.setString(8, review);

            // Execute the update operation
            pstat.executeUpdate();
        } catch (Exception exception) {
            exception.printStackTrace();
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

    public static void UpdateCustomer(Connection connectionIn, int idIn, String name, String password, String eircode,
            String phoneNum, String DOB, String email, String driverNum, String review) {
        try {
            // Create query for update
            String query = "UPDATE customer SET name=?, password=?, eircode=?, phoneNo=?, DOB=?, email=?, driverNum=?, review=? WHERE customerID=?";
            PreparedStatement pstat = connectionIn.prepareStatement(query);

            // Set parameter values
            pstat.setString(1, name);
            pstat.setString(2, password);
            pstat.setString(3, eircode);
            pstat.setString(4, phoneNum);
            pstat.setString(5, DOB);
            pstat.setString(6, email);
            pstat.setString(7, driverNum);
            pstat.setString(8, review);
            pstat.setInt(9, idIn);

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
}