import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Scanner;

public class databaseCRUD {
    static Connection connection = null;
    static ResultSet resultSet = null;
    static PreparedStatement pstat = null;

    public static void ShowTables(String DB_URL, String user, String query) {
        try {
            connection = DriverManager.getConnection(DB_URL, user, ""); // connect
            pstat = connection.prepareStatement(query); // sql query
            resultSet = pstat.executeQuery(); // executes the query
            System.out.println("--Tables in Database--");
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1)); // output tables
            }
        } catch (SQLException sqlException) { // catch errors
            sqlException.printStackTrace();
        } finally {
            try {// close all connections to database and get errors
                resultSet.close();
                pstat.close();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }// method

    public static void RetrieveTable(Connection connectionIn, String query) {
        try {
            connection = connectionIn; // connect
            pstat = connection.prepareStatement(query); // sql query
            resultSet = pstat.executeQuery(); // executes the query

            ResultSetMetaData metaData = resultSet.getMetaData();
            int numberOfColumns = metaData.getColumnCount();
            System.out.println("--Table Details--");
            for (int i = 1; i <= numberOfColumns; i++)
                System.out.print(metaData.getColumnName(i) + "\t");// output tables

            System.out.println();
            while (resultSet.next()) {
                for (int i = 1; i <= numberOfColumns; i++)
                    System.out.print(resultSet.getObject(i) + "\t");
                System.out.println();
            }
        } catch (SQLException sqlException) { // catch errors
            sqlException.printStackTrace();
        } finally {
            try {// close all connections to database and get errors
                resultSet.close();
                pstat.close();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }// method

    public static void AmmendItem(Connection connectionIn, String tableIN) {

        try {
            connection = connectionIn; // connect
            pstat = connection.prepareStatement(" " + tableIN); // sql query
            resultSet = pstat.executeQuery(); // executes the query

            ResultSetMetaData metaData = resultSet.getMetaData();
            int numberOfColumns = metaData.getColumnCount();
            System.out.println("--Table Details--");
            for (int i = 1; i <= numberOfColumns; i++)
                System.out.print(metaData.getColumnName(i) + "\t");// output tables

            System.out.println();
            while (resultSet.next()) {
                for (int i = 1; i <= numberOfColumns; i++)
                    System.out.print(resultSet.getObject(i) + "\t");
                System.out.println();
            }

        }

        catch (SQLException sqlException) { // catch errors
            sqlException.printStackTrace();
        } finally {
            try {// close all connections to database and get errors
                resultSet.close();
                pstat.close();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    public static void AmmendCustomer(Connection connectionIn) {
        Scanner dataIn = new Scanner(System.in);
        try {
            connection = connectionIn; // connect
            String query = "INSERT INTO customer (name, password, eircode, phoneNo, DOB, email, driverNum, review) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            pstat = connection.prepareStatement(query); // sql query
            System.out.print("Enter Name \n: ");
            pstat.setString(1, dataIn.nextLine());

            System.out.print("Enter password \n: ");
            pstat.setString(2, dataIn.nextLine());

            System.out.print("Enter Eircode varchar(7)\n: ");
            pstat.setString(3, dataIn.nextLine());

            System.out.print("Enter Phone Number varchar(15)\n: ");
            pstat.setString(4, dataIn.nextLine());

            System.out.print("Enter DOB (YYYY-MM-DD): ");
            String dobInput = dataIn.nextLine();

            Date dob = Date.valueOf(dobInput);
            pstat.setDate(5, dob); // Set the DOB parameter

            System.out.print("Enter email\n: ");
            pstat.setString(6, dataIn.nextLine());

            System.out.print("Enter driverNum varchar(10)\n: ");
            pstat.setString(7, dataIn.nextLine());

            System.out.print("Enter review 1-5\n: ");
            pstat.setString(8, dataIn.nextLine());

            int i = pstat.executeUpdate();
            System.out.println(i + " record successfully updated in the table.");
        }

        catch (SQLException sqlException) { // catch errors
            sqlException.printStackTrace();
        } finally {
            try {// close all connections to database and get errors
                resultSet.close();
                pstat.close();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    public static void AmmendVehicle() {

    }

    public static void AmmendReservation() {

    }

    public static void UpdateItem() {

    }

    public static void DeleteItem() {

    }

}// class